package com.yuanno.soulsawakening.api.challenges;

import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.challenges.IChallengesData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ChallengeReward {
	public static final ChallengeReward EMPTY = new ChallengeReward();


	private int kan = 0;
	private List<Supplier<ItemStack>> items = new ArrayList<Supplier<ItemStack>>();
	private List<Supplier<ChallengeCore>> challenges = new ArrayList<>();


	public List<Supplier<ItemStack>> getItems() {
		return this.items;
	}

	public int getKan()
	{
		return this.kan;
	}

	public ChallengeReward setKan(int amount)
	{
		this.kan = amount;
		return this;
	}
	public ChallengeReward addChallenge(Supplier<ChallengeCore> challengeSupplier)
	{
		this.challenges.add(challengeSupplier);
		return this;
	}

	public ChallengeReward addItem(Supplier<ItemStack> item) {
		this.items.add(item);
		return this;
	}


	public String giveRewards(PlayerEntity player) {
		IEntityStats props = EntityStatsCapability.get(player);
		IMiscData miscData = MiscDataCapability.get(player);
		IChallengesData challengesData = ChallengesDataCapability.get(player);
		StringBuilder sb = new StringBuilder();

		boolean hasAtLeastOneReward = false;

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

		for (Supplier<ChallengeCore> supplier : this.challenges) {
			ChallengeCore challenge = supplier.get();
			sb.append("  " + challenge.getLocalizedTitle().getString() + " " + "unlocked" + "\n");
			challengesData.addChallenge(challenge);
		}

		if (getKan() > 0 )
		{
			sb.append("  " + "Rewarded: " + getKan() + " kan" + " " + "\n");
			miscData.alterKan(getKan());
			PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
		}

		if(hasAtLeastOneReward) {
			sb.append("\n");
		}

		return sb.toString();
	}

}
