package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class VisualOptionScreen extends Screen {
    protected VisualOptionScreen() {
        super(new StringTextComponent(""));
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        this.buttons.clear();
        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;
        IMiscData config = MiscDataCapability.get(player);
        String textAbilityOverlay = "";
        if (config.getCanRenderOverlay())
            textAbilityOverlay = "keys.soulsawakening.enabled";
        else
            textAbilityOverlay = "keys.soulsawakening.disabled";
        posX += 80;
        this.addButton(new Button(posX + 120, posY + 26, 70, 20, new TranslationTextComponent(textAbilityOverlay), b ->
        {
            config.setCanRenderOverlay(!config.getCanRenderOverlay());
            PacketHandler.sendToServer(new CSyncMiscDataPacket(config));
            init();
        }));
        String textRaceOverlay = "";
        if (config.getRenderRaceOverlay())
            textRaceOverlay = "keys.soulsawakening.enabled";
        else
            textRaceOverlay = "keys.soulsawakening.disabled";
        posY += 40;
        this.addButton(new Button(posX + 120, posY + 26, 70, 20, new TranslationTextComponent(textRaceOverlay), b ->
        {
            config.setRenderRaceOverlay(!config.getRenderRaceOverlay());
            PacketHandler.sendToServer(new CSyncMiscDataPacket(config));
            init();
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.renderBackground(matrixStack);
        drawString(matrixStack, font, TextFormatting.WHITE+ "Ability overlay:", posX - 60, posY + 32, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.WHITE+ "Race overlay:", posX - 60, posY + 72, Color.GRAY.getRGB());

        super.render(matrixStack, x, y, f);
    }
    public static void open()
    {
        Minecraft.getInstance().setScreen(new VisualOptionScreen());
    }

}
