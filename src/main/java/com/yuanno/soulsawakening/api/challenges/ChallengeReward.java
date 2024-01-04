package com.yuanno.soulsawakening.api.challenges;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ChallengeReward {
	public static final ChallengeReward EMPTY = new ChallengeReward();

	private int doriki;
	private int belly;
	private int bounty;
	private List<Supplier<ItemStack>> items = new ArrayList<Supplier<ItemStack>>();

	public int getDoriki() {
		return this.doriki;
	}

	public ChallengeReward setDoriki(int doriki) {
		this.doriki = doriki;
		return this;
	}

	public int getBelly() {
		return this.belly;
	}

	public ChallengeReward setBelly(int belly) {
		this.belly = belly;
		return this;
	}

	public int getBounty() {
		return this.bounty;
	}

	public ChallengeReward setBounty(int bounty) {
		this.bounty = bounty;
		return this;
	}

	public List<Supplier<ItemStack>> getItems() {
		return this.items;
	}

	public ChallengeReward addItem(Supplier<ItemStack> item) {
		this.items.add(item);
		return this;
	}

	public String giveRewards(PlayerEntity player) {
		IEntityStats props = EntityStatsCapability.get(player);
		StringBuilder sb = new StringBuilder();

		boolean hasAtLeastOneReward = false;
				/* -> make own logic here
		if(this.doriki > 0 || this.belly > 0 || this.bounty > 0 || !this.items.isEmpty()) {
			sb.append("\n§aRewards§r\n");
			hasAtLeastOneReward = true;
		}

		if (this.doriki > 0) {
			if (props.alterDoriki(this.doriki, StatChangeSource.CHALLENGE)) {
				sb.append("  " + this.doriki + " Doriki\n");
			}
		}

		if (this.belly > 0) {
			if (props.alterBelly(this.belly, StatChangeSource.CHALLENGE)) {
				sb.append("  " + this.belly + " Belly\n");
			}
		}

		if (this.bounty > 0) {
			if (props.alterBounty(this.bounty, StatChangeSource.CHALLENGE)) {
				sb.append("  " + this.belly + " Bounty\n");
			}
		}

		 */

		for (Supplier<ItemStack> supp : this.items) {
			ItemStack stack = supp.get().copy();
			// Keep the rewards array above the addItem method, down the line it will set
			// the count of our stack to 0 resulting in "0 air" to be displayed
			// instead...thanks Mojang
			// Since we only need the name, cloning the stack would be a waste of resources,
			// so we keep it above, put the name in list and move on
			sb.append("  " + stack.getDisplayName().getString() + (stack.getCount() > 1 ? " - " + stack.getCount() : "") + "\n");
			player.addItem(stack);
		}
		
		if(hasAtLeastOneReward) {
			sb.append("\n");
		}

		return sb.toString();
	}
}
