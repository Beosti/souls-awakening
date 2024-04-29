package com.yuanno.soulsawakening.mixins.client;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Resource;

@Mixin(MainMenuScreen.class)
public class MainMenuScreenMixin extends Screen
{
	@Mutable
	@Shadow
	@Final
    private static ResourceLocation MINECRAFT_LOGO;
	@Mutable
	@Shadow
	@Final
    public static RenderSkyboxCube CUBE_MAP;
	@Mutable
	@Shadow
	@Final
	private RenderSkybox panorama;
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
		CUBE_MAP = new RenderSkyboxCube(new ResourceLocation(Main.MODID, "textures/gui/panorama/panorama"));
		panorama = new RenderSkybox(CUBE_MAP);
	}
}
