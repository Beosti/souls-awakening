package com.yuanno.soulsawakening.api;

import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public enum SourceElement
{
	NONE(null),
	FIRE(ModResources.FIRE_ICON),
	MAGMA(null),
	ICE(null),
	WATER(null),
	LIGHT(null),
	LIGHTNING(null),
	EXPLOSION(null),
	ACID(null),
	POISON(null),
	SMOKE(null),
	METAL(null),
	AIR(null),
	WIND(null),
	SHADOW(null)
	;

	@Nullable
	private final ResourceLocation texture;

	private SourceElement(ResourceLocation texture)
	{
		this.texture = texture;
	}
	
	public String getUnlocalizedName() {
		return this.toString().toLowerCase();
	}
	@Nullable
	public ResourceLocation getTexture() {
		return this.texture;
	}
}