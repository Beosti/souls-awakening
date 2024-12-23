package com.yuanno.soulsawakening.data;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.api.challenges.InProgressChallenge;
import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.challenges.IChallengesData;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.world.challenges.ChallengesChunkGenerator;
import com.yuanno.soulsawakening.world.challenges.DynamicDimensionManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class ChallengesWorldData extends WorldSavedData {
	private static final String IDENTIFIER = "soulsawakening-challenges";
	private static final TranslationTextComponent NOT_UNLOCKED = new TranslationTextComponent("Not Unlocked");
	private static ServerWorld originalServerWorld;
	private static double xCoordinate;
	private static double yCoordinate;
	private static double zCoordinate;
	private Map<UUID, InProgressChallenge> inProgressChallenges = new HashMap<>();

	public ChallengesWorldData() {
		super(IDENTIFIER);
	}

	/** Will return null when used from the client side */
	@Nullable
	public static ChallengesWorldData get() {
		if (ServerLifecycleHooks.getCurrentServer() != null) {
			return ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage().computeIfAbsent(ChallengesWorldData::new, IDENTIFIER);
		}
		return null;
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		return nbt;
	}

	@Override
	public void load(CompoundNBT nbt) {

	}

	public boolean startChallenge(ServerPlayerEntity player, List<LivingEntity> group, ChallengeCore core, boolean isFree) {
		originalServerWorld = player.level.getServer().getLevel(player.level.dimension());
		xCoordinate = player.getX();
		yCoordinate = player.getY();
		zCoordinate = player.getZ();
		IChallengesData props = ChallengesDataCapability.get(player);
		if (props != null) {
			Challenge challenge = props.getChallenge(core);
			if (challenge == null) {
				player.sendMessage(NOT_UNLOCKED, Util.NIL_UUID);
				return false;
			}
			ResourceLocation dimName = new ResourceLocation(Main.MODID, "challenges_" + player.getStringUUID());
			RegistryKey<World> dimension = RegistryKey.create(Registry.DIMENSION_REGISTRY, dimName);
			DynamicRegistries registryAccess = player.level.registryAccess();
			Supplier<DimensionType> type = () -> registryAccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).getOrThrow(RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModResources.DIMENSION_TYPE_CHALLENGES));

			ServerWorld shard = DynamicDimensionManager.getOrCreateWorld(player.getServer(), dimension, ((minecraftServer, levelStemResourceKey) -> {
				ChunkGenerator generator = new ChallengesChunkGenerator(registryAccess.registryOrThrow(Registry.BIOME_REGISTRY));
				return new Dimension(type, generator);
			}));

			UUID id = player.getUUID();
			InProgressChallenge inProgressChallenge = new InProgressChallenge(id, player, shard, group, challenge, isFree);
			if (this.inProgressChallenges.containsKey(id)) {
				this.stopChallenge(inProgressChallenge);
			}
			this.inProgressChallenges.put(id, inProgressChallenge);
			this.setDirty();
			return true;
		}

		return false;
	}

	public void stopChallenge(InProgressChallenge inProgChallenge) {
		for (LivingEntity entity : inProgChallenge.getGroup()) {
			if (entity.isAlive() && Beapi.isInChallengeDimension(entity.level) && entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				player.teleportTo(originalServerWorld, xCoordinate, yCoordinate, zCoordinate, player.yRot, player.xRot);
			}
		}
		this.inProgressChallenges.remove(inProgChallenge.getId());
		this.setDirty();
	}
	
	@Nullable
	public InProgressChallenge getInProgressChallengeFor(LivingEntity entity) {
		UUID id = entity.getUUID();
		return this.inProgressChallenges.get(id);
	}
	
	public void tick(ServerWorld world) {
		for (InProgressChallenge inProgressChallenge : this.inProgressChallenges.values()) {
			if (inProgressChallenge.canDelete()) {
				this.stopChallenge(inProgressChallenge);
			}
		}
		
		for (InProgressChallenge inProgressChallenge : this.inProgressChallenges.values()) {
			inProgressChallenge.tick();
		}
	}
}
