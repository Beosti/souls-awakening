package com.yuanno.soulsawakening.api;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public enum SourceElement
{
	NONE,
	FIRE,
	MAGMA,
	ICE,
	WATER,
	LIGHT,
	LIGHTNING,
	RUBBER,
	EXPLOSION,
	ACID,
	POISON,
	SMOKE,
	METAL,
	AIR,
	WIND,
	SHADOW
	;
	


	
	public String getUnlocalizedName() {
		return this.toString().toLowerCase();
	}
}