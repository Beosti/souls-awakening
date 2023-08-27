package com.yuanno.soulsawakening.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.UUID;

public class SoulboundItemHelper {

	/**
	 * @param world
	 * @param itemStack
	 * @return Returns the entity itself if its online otherwise returns null
	 */
	@Nullable
	public static Pair<UUID, LivingEntity> getOwner(World world, ItemStack itemStack) {
		UUID uuid = itemStack.getOrCreateTag().getUUID("ownerUUID");
		return ImmutablePair.of(uuid, (LivingEntity) ((ServerWorld) world).getEntity(uuid));
	}
	
	/**
	 * @param world
	 * @param itemStack
	 * @return Return the owner's id as stored in the ItemStack's nbt data if it exists, otherwise returns null
	 */
	@Nullable
	public static UUID getOwnerUUID(World world, ItemStack itemStack) {
		CompoundNBT tag = itemStack.getOrCreateTag();
		if(!tag.hasUUID("ownerUUID")) {
			return null;
		}
		return tag.getUUID("ownerUUID");
	}
	
	public static void setOwner(ItemStack itemStack, LivingEntity owner) {
		itemStack.getOrCreateTag().putUUID("ownerUUID", owner.getUUID());
	}
}
