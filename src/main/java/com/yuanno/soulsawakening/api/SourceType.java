package com.yuanno.soulsawakening.api;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public enum SourceType
{
	SLASH,
	BLUNT,
	/** Used for punches, kicks, bites and other appendage-related hits */
	FIST(),
	/** All fists are physical, but sometimes there's abilities that shoot physical projectiles that are not fists/legs, this is for those cases */
	PHYSICAL,
	INTERNAL,
	PROJECTILE,
	;



	public String getUnlocalizedName() {
		return this.toString().toLowerCase();
	}
}
