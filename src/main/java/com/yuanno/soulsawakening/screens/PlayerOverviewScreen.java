package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.events.stats.ZanjutsuGainEvent;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import com.yuanno.soulsawakening.networking.client.CSyncentityStatsPacket;
import com.yuanno.soulsawakening.networking.client.CSyncentityStatsStatsPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

import java.awt.*;


@OnlyIn(Dist.CLIENT)
public class PlayerOverviewScreen extends Screen {
    private final PlayerEntity player;
    private final IEntityStats entityStats;
    private final IMiscData miscData;
    Button plusStatsButton;
    protected PlayerOverviewScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.entityStats = EntityStatsCapability.get(this.player);
        this.miscData = MiscDataCapability.get(this.player);
        miscData.setCanRenderOverlay(false);
        PacketHandler.sendToServer(new CSyncMiscDataPacket(miscData));

    }


    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity playerEntity = mc.player;
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;

        int classPoints = entityStats.getClassPoints();
        int leftShift = posX - 75;

        int statsAmount = 0;
        if (entityStats.getRace().equals(ModValues.FULLBRINGER) || entityStats.getRace().equals(ModValues.SHINIGAMI))
            statsAmount = 3;
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
            statsAmount = 0;
        for (int i = 0; i < statsAmount; i++)
        {
            int finalI = i;
            this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 120, posY + 60 + (i * 15), 10, 10, new TranslationTextComponent("+"), b ->
            {
                entityStats.alterClassPoints(-1);
                handleStats(finalI);
                PacketHandler.sendToServer(new CSyncentityStatsStatsPacket(entityStats));

                init();
            })).active = classPoints > 0 && !entityStats.getRace().equals(ModValues.HUMAN);
        }

    }

    private void handleStats(int integer)
    {
        if (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER))
        {
            if (integer == 2)
                entityStats.alterHohoPoints(1);
            else if (integer == 1)
                entityStats.alterHakudaPoints(1);
            else if (integer == 0)
                entityStats.alterZanjutsuPoints(1);
        }
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
        {
            if (integer == 0)
                entityStats.alterHollowPoints(1);
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        this.renderBackground(matrixStack);
        statsRendering(matrixStack);

        super.render(matrixStack, x, y, f);
    }

    public void statsRendering(MatrixStack matrixStack)
    {
        PlayerEntity playerEntity = this.getMinecraft().player;
        IEntityStats entityStats = EntityStatsCapability.get(playerEntity);
        String name = playerEntity.getName().getString();
        String race = entityStats.getRace();
        int classPoints = entityStats.getClassPoints();
        int zanjutsuPoints = (int) Math.floor(entityStats.getZanjutsuPoints());
        int hakuPoints = (int) Math.floor(entityStats.getHakudaPoints());
        int hohoPoints = (int) Math.floor(entityStats.getHohoPoints());
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        int leftShift = posX - 75;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Name: " + TextFormatting.RESET + name, leftShift, posY + 20, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Race: " + TextFormatting.RESET + race, leftShift, posY + 40, -1);
        if (race.equals(ModValues.HOLLOW))
        {
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Hollow points: " + TextFormatting.RESET + entityStats.getHollowPoints(), leftShift, posY + 60, -1);
            //drawString(matrixStack, this.font, TextFormatting.BOLD + "Class points: " + TextFormatting.RESET + classPoints, leftShift, posY + 105, -1);
        }
        else if (race.equals(ModValues.FULLBRINGER) || race.equals(ModValues.SHINIGAMI))
        {
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Zanjutsu points: " + TextFormatting.RESET + zanjutsuPoints, leftShift, posY + 60, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Haku points: " + TextFormatting.RESET + hakuPoints, leftShift, posY + 75, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Hoho points: " + TextFormatting.RESET + hohoPoints, leftShift, posY + 90, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Class points: " + TextFormatting.RESET + classPoints, leftShift, posY + 105, -1);
        }
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new PlayerOverviewScreen());
    }

    @Override
    public void onClose() {
        super.onClose();
        miscData.setCanRenderOverlay(true);
        PacketHandler.sendToServer(new CSyncMiscDataPacket(miscData));

    }
}
