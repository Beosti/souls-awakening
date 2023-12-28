package com.yuanno.soulsawakening.mixins;

import net.minecraft.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value=RangedAttribute.class, priority=2000)
public interface RangedAttributeMixin
{
	@Accessor("minValue")
	@Mutable
	void setMinValue(double minValue);

	@Accessor("maxValue")
	@Mutable
	void setMaxValue(double maxValue);
}
