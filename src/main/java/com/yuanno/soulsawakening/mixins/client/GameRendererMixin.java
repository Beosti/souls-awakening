package com.yuanno.soulsawakening.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.ability.api.AttributeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(value = GameRenderer.class, priority = 990)
public abstract class GameRendererMixin implements ISelectiveResourceReloadListener
{
	@Shadow
	@Final
	private Minecraft minecraft;

	@ModifyConstant(method = "pick(F)V", constant = @Constant(doubleValue = 6.0))
	private double getActualAttackRangeInCreative(final double attackRange)
	{
		if (this.minecraft.player != null)
			return AttributeHelper.getSquaredAttackRangeDistance(this.minecraft.player, attackRange);
		return attackRange;
	}

	@ModifyVariable(method = "pick(F)V", at = @At("STORE"), ordinal = 1)
	private double getActualAttackRangeInSurvival0(double attackRange)
	{
		if (this.minecraft.player != null)
			return AttributeHelper.getSquaredAttackRangeDistance(this.minecraft.player, attackRange);
		return attackRange;
	}

	@ModifyConstant(method = "pick(F)V", constant = @Constant(doubleValue = 9.0))
	private double getActualAttackRangeInSurvival1(final double attackRange)
	{
		if (this.minecraft.player != null)
			return AttributeHelper.getSquaredAttackRangeDistance(this.minecraft.player, attackRange);
		return attackRange;
	}

	/*
	@Inject(
		method = "renderItemInHand", 
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/matrix/MatrixStack;popPose()V",
			shift = At.Shift.AFTER
		)
	)
	private void renderItemInHand(MatrixStack matrixStack, ActiveRenderInfo activeRenderInfo, float partialTicks, CallbackInfo info)
	{
		PlayerEntity player = this.minecraft.player;
		if(player == null)
			return;
		RenderOverlayEvent event = new RenderOverlayEvent(player, matrixStack, activeRenderInfo, partialTicks);
		MinecraftForge.EVENT_BUS.post(event);
		SpecialPotionEffectEvents.renderScreenEffects(player, matrixStack, activeRenderInfo, partialTicks);
	}

	 */
}
