package com.yuanno.soulsawakening.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.api.RendererHelper;
import com.yuanno.soulsawakening.api.ability.AbilityDisplayInfo;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementToast.class)
public class AdvancementToastMixin
{
	@Shadow
	@Final
	private Advancement advancement;
	
	@Inject(
		method = "render", 
		at = @At(
			value = "INVOKE", 
			target = "Lnet/minecraft/client/renderer/ItemRenderer;renderAndDecorateFakeItem(Lnet/minecraft/item/ItemStack;II)V", 
			shift = At.Shift.BEFORE), 
		cancellable = true)
	public void render(MatrixStack matrixStack, ToastGui pToastComponent, long p_230444_3_, CallbackInfoReturnable<IToast.Visibility> callback)
	{
		if (this.advancement.getDisplay() instanceof AbilityDisplayInfo)
		{
			AbilityDisplayInfo ablInfo = (AbilityDisplayInfo) this.advancement.getDisplay();
			if (ablInfo.getAbility() != null)
			{
				RendererHelper.drawAbilityIcon(ablInfo.getAbility(), matrixStack, 8, 8, 1, 16, 16);
				callback.setReturnValue(p_230444_3_ >= 5000L ? IToast.Visibility.HIDE : IToast.Visibility.SHOW);
			}
		}
	}
}
