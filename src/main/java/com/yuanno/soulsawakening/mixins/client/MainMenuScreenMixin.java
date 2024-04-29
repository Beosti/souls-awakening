package com.yuanno.soulsawakening.mixins.client;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenuScreen.class)
public class MainMenuScreenMixin extends Screen
{
	@Mutable
	@Shadow
	@Final
    private static ResourceLocation MINECRAFT_LOGO;

	public MainMenuScreenMixin(ITextComponent title)
	{
		super(title);
	}

	@Inject(
		method = "init", 
		at = @At("HEAD")
	)
	public void init(CallbackInfo callback)
	{
		MINECRAFT_LOGO = new ResourceLocation(Main.MODID, "textures/gui/soulsawakening_title.png");
	}
}
