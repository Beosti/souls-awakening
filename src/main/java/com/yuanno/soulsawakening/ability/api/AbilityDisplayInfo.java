package com.yuanno.soulsawakening.ability.api;

import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AbilityDisplayInfo extends DisplayInfo
{
	private Ability icon;
	
	public AbilityDisplayInfo(Ability pIcon, ITextComponent pTitle, ITextComponent pDescription, ResourceLocation pBackground, FrameType pFrame, boolean pShowToast, boolean pAnnounceChat, boolean pHidden)
	{
		super(new ItemStack(Items.APPLE), pTitle, pDescription, pBackground, pFrame, pShowToast, pAnnounceChat, pHidden);
		this.icon = pIcon;
	} 

	public Ability getAbility()
	{
		return this.icon;
	}
}
