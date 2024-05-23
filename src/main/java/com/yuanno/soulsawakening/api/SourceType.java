package com.yuanno.soulsawakening.api;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public enum SourceType
{
	SLASH,
	BLUNT,
	FIST,
	SPIKE, // used for bites type attack
	PHYSICAL,
	INTERNAL,
	PROJECTILE,
	SHOCKWAVE
	;



	public String getUnlocalizedName() {
		return this.toString().toLowerCase();
	}
}
