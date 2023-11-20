package com.yuanno.soulsawakening.api;

import com.yuanno.soulsawakening.init.ModDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class ModEntityDamageSource extends ModDamageSource {
	@Nullable
	protected final Entity entity;

	public ModEntityDamageSource(String damageTypeIn, @Nullable Entity entity) {
		super(damageTypeIn, false, false, false);
		
		this.entity = entity;
	}

	@Override
	@Nullable
	public Entity getEntity() {
		return this.entity;
	}

	@Override
	public ITextComponent getLocalizedDeathMessage(LivingEntity entityLivingBaseIn) {
		ItemStack itemstack = this.entity instanceof LivingEntity ? ((LivingEntity) this.entity).getMainHandItem() : ItemStack.EMPTY;
		
		String s = "death.attack." + this.msgId;
		
		return !itemstack.isEmpty() && itemstack.hasCustomHoverName() ? new TranslationTextComponent(s + ".item", entityLivingBaseIn.getDisplayName(), this.entity.getDisplayName(), itemstack.getHoverName()) : new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName(), this.entity.getDisplayName());
	}

	public boolean isDifficultyScaled() {
		return this.entity != null && this.entity instanceof LivingEntity && !(this.entity instanceof PlayerEntity);
	}

	@Nullable
	public Vector3d getDamageLocation() {
		return this.entity != null ? this.entity.position() : null;
	}
}
