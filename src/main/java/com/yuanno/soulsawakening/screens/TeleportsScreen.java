package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncTeleportPacket;
import com.yuanno.soulsawakening.networking.client.CTeleportPacket;
import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TeleportsScreen extends Screen {

    private PlayerEntity player;
    private ITeleports teleportData;

    public TeleportsScreen()
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = minecraft.player;
        this.teleportData = TeleportCapability.get(player);
    }

    @Override
    public void init()
    {
        super.init();
        int posX = this.width / 2;
        int posY = this.height / 2;
        int a = 0;
        int shiftAmount = 0; // Initialize the shift amount
        for (int i = 0; i < teleportData.getTeleportPositions().size(); i++) {
            TeleportPosition teleportPosition = teleportData.getTeleportPositions().get(i);
            int currentPosX = posX + shiftAmount; // Update posX based on shiftAmount
            this.addButton(new Button(currentPosX - 195, posY -ma 80 + (i * 20), 100, 20, new TranslationTextComponent(teleportData.getTeleportPositions().get(i).getName()), b -> {
                PacketHandler.sendToServer(new CTeleportPacket(teleportPosition));
                this.onClose();
            }));
            a++;
            if (a >= 4) { // If 4 iterations are complete, shift by 40 units
                a = 0; // Reset a for the next set of iterations
                shiftAmount += 40; // Update shiftAmount
            }
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = this.width / 2;
        int posY = this.height / 2;

        super.render(matrixStack, x, y, f);
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new TeleportsScreen());
    }

}
