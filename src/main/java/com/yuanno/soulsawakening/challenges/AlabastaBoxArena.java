package com.yuanno.soulsawakening.challenges;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.challenges.ArenaStyle;
import com.yuanno.soulsawakening.api.challenges.ChallengeArena;
import com.yuanno.soulsawakening.api.challenges.InProgressChallenge;
import com.yuanno.soulsawakening.entity.ShinigamiEntity;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlabastaBoxArena extends ChallengeArena {

	public static final AlabastaBoxArena INSTANCE = new AlabastaBoxArena();
	
	private static final int ARENA_SIZE = 25;
	private static final int SAND_HEIGHT = ARENA_SIZE / 3;

	private AlabastaBoxArena() {
		super(ArenaStyle.BOX);
	}
	
	@Override
	public void buildArena(InProgressChallenge challenge) {
		final int bottomLayerOffset = (-ARENA_SIZE) + 1;

		// Outer barrier
		ArrayList<BlockPos> blocks1 = new ArrayList<>(Beapi.createEmptyCube(challenge.getShard(),
				challenge.getArenaPos().getX(), challenge.getArenaPos().getY(), challenge.getArenaPos().getZ(), ARENA_SIZE, ARENA_SIZE, ARENA_SIZE, Blocks.BARRIER, null));
		for (BlockPos block : blocks1) {
			challenge.getShard().setBlockAndUpdate(block, Blocks.BARRIER.defaultBlockState());
		}
		// Sand layers
		/*
		blocks.addAll(Beapi.createFilledCube(challenge.getShard(), challenge.getArenaPos().getX(), challenge.getArenaPos().getY() + bottomLayerOffset + SAND_HEIGHT, challenge.getArenaPos().getZ(), ARENA_SIZE - 1, SAND_HEIGHT, ARENA_SIZE - 1, Blocks.SANDSTONE, null));
		blocks.addAll(Beapi.createFilledCube(challenge.getShard(), challenge.getArenaPos().getX(), challenge.getArenaPos().getY() - SAND_HEIGHT, challenge.getArenaPos().getZ(), ARENA_SIZE - 1, 1, ARENA_SIZE - 1, Blocks.SAND, null));
		for (BlockPos block : blocks) {
			challenge.getShard().setBlockAndUpdate(block, Blocks.SANDSTONE.defaultBlockState());
		}

		 */
		// Bedrock layer at the very bottom
		/*
		for (int x = -ARENA_SIZE + 1; x < ARENA_SIZE; x++) {
			for (int z = -ARENA_SIZE + 1; z < ARENA_SIZE; z++) {
				BlockPos pos = challenge.getArenaPos().offset(x, bottomLayerOffset, z);
				challenge.getShard().setBlockAndUpdate(pos, Blocks.SANDSTONE.defaultBlockState());
				//Beapi.placeBlockIfAllowed(challenge.getShard(), pos.getX(), pos.getY(), pos.getZ(), Blocks.BEDROCK, null);
			}
		}

		 */
	}

	@Override
	public void spawnPlayers(InProgressChallenge challenge) {		
		for (LivingEntity groupMember : challenge.getGroup()) {
			if (groupMember instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) groupMember;
				player.teleportTo(challenge.getShard(), challenge.getArenaPos().getX() + 15, challenge.getArenaPos().getY() - 5, challenge.getArenaPos().getZ() + 15, 135, 0);
			}
		}
	}
	
	@Override
	public Set<LivingEntity> spawnEnemies(InProgressChallenge challenge) {
		
		Set<LivingEntity> set = new HashSet<>();
		
		ShinigamiEntity boss = new ShinigamiEntity(ModEntities.SHINIGAMI.get(), challenge.getShard());
		boss.forcedLoading = true;
		challenge.getShard().addFreshEntity(boss);
		boss.teleportTo(challenge.getArenaPos().getX() - 15, challenge.getArenaPos().getY() - 5, challenge.getArenaPos().getZ() - 15);
		boss.setYBodyRot(-45);
		boss.setTarget(challenge.getOwner());
		//GoalUtil.lookAtEntity(boss, challenge.getOwner());
		set.add(boss);
		
		return set;
	}
}
