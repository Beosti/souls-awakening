package com.yuanno.soulsawakening.api;

import com.google.common.base.Predicates;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ChunkHolder;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Beapi {

    private static HashMap<String, String> langMap = new HashMap<String, String>();




    public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, BlockState toPlace, int flag, @Nullable BlockProtectionRule rule) {
        BlockPos pos = new BlockPos(posX, posY, posZ);

        if (World.isOutsideBuildHeight(pos)) {
            return false;
        }

        BlockState currentBlockState = world.getBlockState(pos);

        /*
        ProtectedAreasData worldData = ProtectedAreasData.get(world);
        ProtectedArea area = worldData.getProtectedArea((int) posX, (int) posY, (int) posZ);

        boolean isGriefDisabled = !CommonConfig.INSTANCE.isAbilityGriefingEnabled();
        boolean isGriefBypass = false;
        boolean canPlace = !RestrictedBlockProtectionRule.INSTANCE.check(world, pos, currentBlockState);
        if (rule != null) {
            isGriefBypass = rule.getBypassGriefRule();
            canPlace = rule.check(world, pos, currentBlockState);
        }

        if (!isGriefBypass) {
            if (isGriefDisabled) {
                return false;
            }

            if (area != null) {
                if (!area.canDestroyBlocks()) {
                    return false;
                } else if (area.canDestroyBlocks() && area.canRestoreBlocks()) {
                    int hash = (int) ((posY * 31) + posZ + posX);

                    BlockPlacingHelper.DistanceBlockPos pos2 = new BlockPlacingHelper.DistanceBlockPos(posX, posY, posZ, hash);
                    BlockSnapshot snapshot = BlockSnapshot.create(world.dimension(), world, pos, 2);

                    area.queueForRestoration(pos2, new RestorationEntry(world.getGameTime(), snapshot));
                }
            }
        }


         */
        if (true) {
            Beapi.setBlockStateInChunk(world, pos, toPlace, flag);
            return true;
        }

        return false;
    }

    public static boolean setBlockStateInChunk(World world, BlockPos pos, BlockState newState, int flags)
    {
        if (Beapi.isInChallengeDimension(world) && world.players().size() <= 0) {
            Beapi.swapBlockData(world, pos, newState);
            return false;
        }
        if (World.isOutsideBuildHeight(pos))
            return false;
        else if (!world.isClientSide && world.isDebug())
            return false;
        else
        {
            Chunk chunk = world.getChunkAt(pos);
            pos = pos.immutable();

            BlockState old = world.getBlockState(pos);
            int oldLight = old.getLightValue(world, pos);
            int oldOpacity = old.getLightBlock(world, pos);

            BlockState blockstate = chunk.setBlockState(pos, newState, (flags & 64) != 0);
            if (blockstate != null)
            {
                BlockState blockstate1 = world.getBlockState(pos);
                if ((flags & 128) == 0 && blockstate1 != blockstate && (blockstate1.getLightBlock(world, pos) != oldOpacity || blockstate1.getLightValue(world, pos) != oldLight || blockstate1.useShapeForLightOcclusion() || blockstate.useShapeForLightOcclusion()))
                    world.getChunkSource().getLightEngine().checkBlock(pos);

                if((flags & 256) != 0)
                    world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags, 512);
                else if ((flags & 2) != 0 && (!world.isClientSide || (flags & 4) == 0) && (world.isClientSide || chunk == null || chunk.getFullStatus() != null && chunk.getFullStatus().isOrAfter(ChunkHolder.LocationType.TICKING)))
                    world.sendBlockUpdated(pos, blockstate, newState, flags);
            }
            return true;
        }
    }
    public static BlockState swapBlockData(IWorld world, BlockPos pos, BlockState newState) {
        IChunk chunk = world.getChunk(pos);
        ChunkSection cs = chunk.getSections()[pos.getY() >> 4];
        if (cs == Chunk.EMPTY_SECTION) {
            cs = new ChunkSection(pos.getY() >> 4 << 4);
            chunk.getSections()[pos.getY() >> 4] = cs;
        }
        BlockState state = cs.getStates().getAndSet(pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15, newState);
        return state;
    }
    public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
        return createFilledCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, blockToPlace, 2, rule);
    }

    public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, int flag, BlockProtectionRule rule) {
        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int x = -sizeX; x <= sizeX; x++) {
            for (int y = -sizeY; y <= sizeY; y++) {
                for (int z = -sizeZ; z <= sizeZ; z++) {
                    BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
                    if (true) {
                        blockPositions.add(pos);
                    }
                }
            }
        }

        return blockPositions;
    }

    public static List<BlockPos> createPlatform(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, BlockProtectionRule rule)
    {
        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int x = -sizeX; x <= sizeX; x++)
        {
            for (int z = -sizeZ; z <= sizeZ; z++)
                {
                    BlockPos pos = new BlockPos(posX + x, posY, posZ + z);
                    blockPositions.add(pos);
                }
        }
        return blockPositions;
    }

    public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule)
    {
        return createEmptyCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, 2, blockToPlace, rule);
    }

    public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int flags, Block blockToPlace, BlockProtectionRule rule)
    {
        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int x = -sizeX; x <= sizeX; x++)
        {
            for (int y = -sizeY; y <= sizeY; y++)
            {
                for (int z = -sizeZ; z <= sizeZ; z++)
                {
                    if (x == -sizeX || x == sizeX || y == -sizeY || y == sizeY || z == -sizeZ || z == sizeZ)
                    {
                        BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
                        if (true)
                            blockPositions.add(pos);
                    }
                }
            }
        }
        return blockPositions;
    }

    public static void sendApplyEffectToAllNearby(LivingEntity player, Vector3d pos, int distance, EffectInstance effect) {
        player.getServer().getPlayerList().broadcast(null, pos.x, pos.y, pos.z, distance, player.getCommandSenderWorld().dimension(), new SPlayEntityEffectPacket(player.getId(), effect));
    }

    public static void sendRemoveEffectToAllNearby(LivingEntity player, Vector3d pos, int distance, Effect effect) {
        player.getServer().getPlayerList().broadcast(null, pos.x, pos.y, pos.z, distance, player.getCommandSenderWorld().dimension(), new SRemoveEntityEffectPacket(player.getId(), effect));
    }
    public static double randomDouble()
    {
        return new Random().nextDouble() * 2 - 1;
    }
    public static double randomWithRange(int min, int max)
    {
        return new Random().nextInt(max + 1 - min) + min;
    }
    public static String formatTimeMMSS(long time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public static boolean isInChallengeDimension(World world) {
        return isInChallengeDimension(world.dimension());
    }

    public static boolean isInChallengeDimension(RegistryKey<World> world) {
        return world.location().toString().contains("challenges_");
    }
    public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ)
    {
        IPacket<?> ipacket = new SSpawnParticlePacket(data, true, (float) posX, (float) posY, (float) posZ, 0, 0, 0, 0, 1);

        for (int j = 0; j < world.players().size(); ++j)
        {
            ServerPlayerEntity player = world.players().get(j);
            BlockPos blockpos = new BlockPos(player.getX(), player.getY(), player.getZ());
            if (blockpos.closerThan(new Vector3d(posX, posY, posZ), 512))
            {
                player.connection.send(ipacket);
            }
        }
    }

    public static Vector3d propulsion(LivingEntity entity, double extraVelX, double extraVelZ)
    {
        return propulsion(entity, extraVelX, 0, extraVelZ);
    }
    public static Vector3d propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ)
    {
        return entity.getLookAngle().multiply(extraVelX, extraVelY, extraVelZ);
    }

    public static <T extends Entity> java.util.List<T> getNearbyEntities(BlockPos pos, IWorld world, double radius, @Nullable Predicate<Entity> predicate, Class<? extends T>... clazzez) {
        return getNearbyEntities(pos, world, radius, radius, radius, predicate, clazzez);
    }
    public static final Predicate<Entity> IS_ALIVE_AND_SURVIVAL = EntityPredicates.NO_CREATIVE_OR_SPECTATOR.and(EntityPredicates.ENTITY_STILL_ALIVE);

    public static <T extends Entity> java.util.List<T> getNearbyEntities(BlockPos pos, IWorld world, double sizeX, double sizeY, double sizeZ, @Nullable Predicate<Entity> predicate, Class<? extends T>... clazzez) {
        if(clazzez.length <= 0) {
            clazzez = new Class[]{ Entity.class };
        }
        if(predicate == null) {
            predicate = Predicates.alwaysTrue();
        }
        predicate = IS_ALIVE_AND_SURVIVAL.and(predicate);
        AxisAlignedBB aabb = new AxisAlignedBB(pos).inflate(sizeX, sizeY, sizeZ);
        List<T> list = new ArrayList<T>();
        for (Class<? extends T> clz : clazzez) {
            list.addAll(world.getEntitiesOfClass(clz, aabb, predicate));
        }
//		list.removeIf(target -> target == entity);
        return list;
    }

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }
    public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, IReorderingProcessor text, int posX, int posY, int color)
    {
        matrixStack.pushPose();
        font.drawShadow(matrixStack, text, posX, posY - 0.7f, 0);
        font.drawShadow(matrixStack, text, posX, posY + 0.7f, 0);
        font.drawShadow(matrixStack, text, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, text, posX - 0.7f, posY, 0);
        matrixStack.translate(0, 0, 1);
        font.draw(matrixStack, text, posX, posY, color);
        matrixStack.popPose();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
    }
    public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, String text, int posX, int posY, int color)
    {
        matrixStack.pushPose();
        String unformattedText = escapeTextFormattingChars(text);
        font.drawShadow(matrixStack, unformattedText, posX, posY - 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX, posY + 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, unformattedText, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, unformattedText, posX - 0.7f, posY, 0);
        matrixStack.translate(0, 0, 1);
        font.draw(matrixStack, unformattedText, posX, posY, -1);
        matrixStack.popPose();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
    }
    public static String getResourceName(String text)
    {
        return text
                .replaceAll("[ \\t]+$", "")
                .replaceAll("\\(","")
                .replaceAll("\\)","")
                .replaceAll("\\s+", "_")
                .replaceAll("[\\'\\:\\-\\,\\#]", "")
                .replaceAll("\\&", "and").toLowerCase();
    }

    public static Color hexToRGB(String hexColor)
    {
        if (hexColor.startsWith("#"))
            hexColor = hexColor.substring(1);

        if (hexColor.length() == 8)
        {
            int r = Integer.parseInt(hexColor.substring(0, 2), 16);
            int g = Integer.parseInt(hexColor.substring(2, 4), 16);
            int b = Integer.parseInt(hexColor.substring(4, 6), 16);
            int a = Integer.parseInt(hexColor.substring(6, 8), 16);
            return new Color(r, g, b, a);
        }
        else
            return Color.decode("#" + hexColor);
    }
    public static String escapeTextFormattingChars(String text)
    {
        return text.replaceAll("§[0-9a-f]", "");
    }

    public static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(String localizedName, Supplier<EntityType<T>> supp)
    {
        String resourceName = Beapi.getResourceName(localizedName);
        Beapi.getLangMap().put("entity." + Main.MODID + "." + resourceName, localizedName);

        RegistryObject<EntityType<T>> reg = ModRegistry.ENTITY_TYPES.register(resourceName, supp);

        return reg;
    }

    public static <T extends Entity> EntityType.Builder createEntityType(EntityType.IFactory<T> factory)
    {
        return createEntityType(factory, EntityClassification.MISC);
    }
    public static <T extends Entity> EntityType.Builder createEntityType(EntityType.IFactory<T> factory, EntityClassification classification)
    {
        EntityType.Builder<T> builder = EntityType.Builder.<T>of(factory, classification);

        builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).sized(0.6F, 1.8F);

        return builder;
    }

    public static RayTraceResult rayTraceBlocksAndEntities(Entity entity) {
        return Beapi.rayTraceBlocksAndEntities(entity, 1024, 0.4f);
    }

    public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance) {
        return Beapi.rayTraceBlocksAndEntities(entity, distance, 0.2f);
    }

    public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance, float entityBoxRange) {
        Vector3d lookVec = entity.getLookAngle();
        Vector3d startVec = entity.position().add(0, entity.getEyeHeight(), 0);
        Vector3d endVec = startVec.add(entity.getLookAngle().scale(distance));
        RayTraceResult blockResult = entity.level.clip(new RayTraceContext(startVec, endVec,  RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
        RayTraceResult entityResult = null;

        for (int i = 0; i < distance * 2; i++)
        {
            if (entityResult != null)
                break;

            float scale = i / 2F;
            Vector3d pos = startVec.add(lookVec.scale(scale));

            Vector3d min = pos.add(entityBoxRange, entityBoxRange, entityBoxRange);
            Vector3d max = pos.add(-entityBoxRange, -entityBoxRange, -entityBoxRange);
            List<Entity> list = entity.level.getEntities(entity, new AxisAlignedBB(min.x, min.y, min.z, max.x, max.y, max.z));
            list.remove(entity);
            for (Entity e : list) {


                entityResult = new EntityRayTraceResult(e, pos);
                break;
            }
        }

        if (entityResult != null && entityResult.getLocation().distanceTo(startVec) <= blockResult.getLocation().distanceTo(startVec)) {
            return entityResult;
        } else {
            return blockResult;
        }

    }


    public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, int flag, BlockProtectionRule rule)
    {
        return placeBlockIfAllowed(world, posX, posY, posZ, toPlace.defaultBlockState(), flag, rule);
    }
}
