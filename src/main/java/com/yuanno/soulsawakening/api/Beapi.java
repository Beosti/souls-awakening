package com.yuanno.soulsawakening.api;

import com.google.common.base.Predicates;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModRegistry;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
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
}
