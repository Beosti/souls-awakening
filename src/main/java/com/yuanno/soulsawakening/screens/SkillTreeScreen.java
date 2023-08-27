package com.yuanno.soulsawakening.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkillTreeScreen extends Screen {
    float xMouse;
    float yMouse;
    private final PlayerEntity player;

    protected SkillTreeScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }
}
