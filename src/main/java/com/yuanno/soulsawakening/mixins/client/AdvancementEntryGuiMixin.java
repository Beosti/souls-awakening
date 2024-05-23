package com.yuanno.soulsawakening.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.ability.api.AbilityDisplayInfo;
import com.yuanno.soulsawakening.api.RendererHelper;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementEntryGui;
import net.minecraft.client.gui.advancements.AdvancementState;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(AdvancementEntryGui.class)
public abstract class AdvancementEntryGuiMixin
{
	private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/advancements/widgets.png");
	
	@Shadow
	@Final
	private DisplayInfo display;

	@Shadow
	@Final
	private int x;

	@Shadow
	@Final
	private int y;

	@Shadow
	private AdvancementProgress progress;
	
	@Shadow
	@Final
	private List<AdvancementEntryGui> children;
	
	@Inject(method = "draw", at = @At("HEAD"), cancellable = true)
	public void draw(MatrixStack matrixStack, int pX, int pY, CallbackInfo callback)
	{
		AdvancementEntryGui entry = (AdvancementEntryGui)(Object) this;
		if (this.display instanceof AbilityDisplayInfo)
		{
			AbilityDisplayInfo ablInfo = (AbilityDisplayInfo) this.display;
			if (ablInfo.getAbility() != null)
			{
				if (!this.display.isHidden() || this.progress != null && this.progress.isDone())
				{
					float f = this.progress == null ? 0.0F : this.progress.getPercent();
					AdvancementState advancementstate;
					if (f >= 1.0F)
					{
						advancementstate = AdvancementState.OBTAINED;
					}
					else
					{
						advancementstate = AdvancementState.UNOBTAINED;
					}

					Minecraft.getInstance().getTextureManager().bind(WIDGETS_LOCATION);
					entry.blit(matrixStack, pX + this.x + 3, pY + this.y, this.display.getFrame().getTexture(), 128 + advancementstate.getIndex() * 26, 26, 26);
					RendererHelper.drawAbilityIcon(ablInfo.getAbility(), matrixStack, pX + this.x + 8, pY + this.y + 5, 1, 16, 16);
				}

				for (AdvancementEntryGui advancemententrygui : this.children)
				{
					advancemententrygui.draw(matrixStack, pX, pY);
				}
				callback.cancel();
			}
		}
	}

	@Inject(method = "drawHover", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;renderAndDecorateFakeItem(Lnet/minecraft/item/ItemStack;II)V", shift = At.Shift.BEFORE), cancellable = true)
	public void drawHover(MatrixStack matrixStack, int pX, int pY, float pFade, int pWidth, int pHeight, CallbackInfo callback)
	{
		if (this.display instanceof AbilityDisplayInfo)
		{
			AbilityDisplayInfo ablInfo = (AbilityDisplayInfo) this.display;
			if (ablInfo.getAbility() != null)
			{
				RendererHelper.drawAbilityIcon(ablInfo.getAbility(), matrixStack, pX + this.x + 8, pY + this.y + 5, 1, 16, 16);
				callback.cancel();
			}
		}
	}
}
