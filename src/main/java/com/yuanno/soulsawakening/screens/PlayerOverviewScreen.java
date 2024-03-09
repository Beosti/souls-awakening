package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

import java.awt.*;
import java.util.Arrays;


@OnlyIn(Dist.CLIENT)
public class PlayerOverviewScreen extends Screen {
    private final PlayerEntity player;
    private final IMiscData miscData;
    Button plusStatsButton;
    protected PlayerOverviewScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.miscData = MiscDataCapability.get(this.player);
        miscData.setCanRenderOverlay(false);
        PacketHandler.sendToServer(new CSyncMiscDataPacket(miscData));

    }


    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity playerEntity = mc.player;
        this.buttons.clear();
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        IEntityStats entityStats = EntityStatsCapability.get(playerEntity);
        IAbilityData abilityData = AbilityDataCapability.get(playerEntity);
        int classPoints = entityStats.getClassPoints();
        int leftShift = posX - 75;

        int statsAmount = 0;
        if (entityStats.getRace().equals(ModValues.FULLBRINGER) || entityStats.getRace().equals(ModValues.SHINIGAMI))
            statsAmount = 4;
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
            statsAmount = 0;

        if (entityStats.getRace().equals(ModValues.HOLLOW)) {
            this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 120, posY + 57, 80, 16, new TranslationTextComponent("Evolution"), b -> {
                if (entityStats.getHollowPoints() >= 50 && !(entityStats.getRank().equals(ModValues.VASTO_LORDE))) {
                    PacketHandler.sendToServer(new CHollowEvolutionPacket());
                    this.onClose();
                }
            }, (button, matrixStack, mouseX, mouseY) -> {
                if (button.isHovered() && button.active) {
                    this.renderTooltip(matrixStack, new TranslationTextComponent("You can consume all your hollow points, evolving to the next stage"), mouseX, mouseY);
                }
                else if (button.isHovered() && entityStats.getRank().equals(ModValues.VASTO_LORDE))
                {
                    this.renderTooltip(matrixStack, new TranslationTextComponent("WIP"), mouseX, mouseY);
                }
                else if (button.isHovered())
                {
                    this.renderTooltip(matrixStack, new TranslationTextComponent("You need 50 hollow points to evolve"), mouseX, mouseY);
                }
            })).active = entityStats.getHollowPoints() >= 50 && !(entityStats.getRank().equals(ModValues.VASTO_LORDE));
        }

        for (int i = 0; i < statsAmount; i++)
        {
            int finalI = i;
            this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 120, posY + 60 + (i * 15), 10, 10, new TranslationTextComponent("+"), b ->
            {
                if (entityStats.getClassPoints() > 0)
                {
                    entityStats.alterClassPoints(-1);
                    handleStats(finalI, entityStats);
                    //PacketHandler.sendToServer(new CSyncentityStatsStatsPacket(entityStats));
                }

                init();
            }, (button, matrixStack, mouseX, mouseY) ->
            {
                if (button.isHovered() && finalI == 3)
                    this.renderTooltip(matrixStack, new TranslationTextComponent("Increases reiatsu, kid-type abilities deal more damage"), mouseX, mouseY);
                if (button.isHovered() && finalI == 2)
                    this.renderTooltip(matrixStack, new TranslationTextComponent("Increases speed and attack speed"), mouseX, mouseY);
                if (button.isHovered() && finalI == 1)
                    this.renderTooltip(matrixStack, new TranslationTextComponent("Increases damage with your empty hand and more health"), mouseX, mouseY);
                if (button.isHovered() && finalI == 0)
                    this.renderTooltip(matrixStack, new TranslationTextComponent("Increases damage with a sword"), mouseX, mouseY);
            })).active = classPoints > 0;
        }
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 252, posY + 117, 60, 20, new TranslationTextComponent("Challenges"), b ->
        {
            PacketHandler.sendToServer(new COpenChallengeScreenPacket());
            this.onClose();
        }));
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 252, posY + 140, 60, 20, new TranslationTextComponent("Abilities"), b ->
        {
            PacketHandler.sendToServer(new COpenAbilityListScreenPacket());
            this.onClose();
        }));
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 61 +  252, posY + 117, 60, 20, new TranslationTextComponent("Quests"), b ->
        {
            PacketHandler.sendToServer(new COpenQuestScreenPacket());
            this.onClose();
        }));
    }

    private void handleStats(int integer, IEntityStats entityStats)
    {

        if (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER))
        {
            if (integer == 3) {
                PacketHandler.sendToServer(new CSyncentityStatsReiatsuPacket(entityStats));

            }
            if (integer == 2) {
                PacketHandler.sendToServer(new CSyncentityStatsHohoPacket(entityStats));

            }
            else if (integer == 1) {
                PacketHandler.sendToServer(new CSyncentityStatsHakudaPacket(entityStats));

            }
            else if (integer == 0) {
                PacketHandler.sendToServer(new CSyncentityStatsZanjutsuPacket(entityStats));
            }
        }
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
        {
            if (integer == 0) {
                entityStats.alterHollowPoints(1);
                PacketHandler.sendToServer(new CSyncentityStatsHollowPacket(entityStats));
            }
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        this.renderBackground(matrixStack);
        statsRendering(matrixStack);
        baseStatsRendering(matrixStack);
        miscDataRendering(matrixStack);
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
        int reiatsuPoints = (int) Math.floor(entityStats.getReiatsuPoints());

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        int leftShift = posX - 75;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Name: " + TextFormatting.RESET + name, leftShift, posY + 20, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Race: " + TextFormatting.RESET + race, leftShift, posY + 40, -1);
        if (race.equals(ModValues.HOLLOW))
        {
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Hollow points: " + TextFormatting.RESET + entityStats.getHollowPoints(), leftShift, posY + 60, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Rank: " + TextFormatting.RESET + entityStats.getRank(), leftShift, posY + 75, -1);
            //drawString(matrixStack, this.font, TextFormatting.BOLD + "Class points: " + TextFormatting.RESET + classPoints, leftShift, posY + 105, -1);
        }
        else if (race.equals(ModValues.FULLBRINGER) || race.equals(ModValues.SHINIGAMI))
        {
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Zanju points: " + TextFormatting.RESET + zanjutsuPoints, leftShift, posY + 60, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Haku points: " + TextFormatting.RESET + hakuPoints, leftShift, posY + 75, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Hoho points: " + TextFormatting.RESET + hohoPoints, leftShift, posY + 90, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Reiatsu points: " + TextFormatting.RESET + reiatsuPoints, leftShift, posY + 105, -1);
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Class points: " + TextFormatting.RESET + classPoints, leftShift, posY + 120, -1);
        }
    }

    public void baseStatsRendering(MatrixStack matrixStack)
    {
        PlayerEntity playerEntity = this.getMinecraft().player;
        IEntityStats entityStats = EntityStatsCapability.get(playerEntity);
        ModifiableAttributeInstance attributes = playerEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        double baseDamage = 1 + entityStats.getHakudaPoints();
        double hollowBaseDamage = playerEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double speed = playerEntity.getAttributeValue(Attributes.MOVEMENT_SPEED);
        double attackSpeed = playerEntity.getAttributeValue(Attributes.ATTACK_SPEED);
        String formattedSpeed = String.format("%.2f", speed);
        String formattedAttackSpeed = String.format("%.2f", attackSpeed);

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        int leftShift = posX + 180;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Max health: " + TextFormatting.RESET + (int) playerEntity.getMaxHealth(), leftShift, posY + 20, -1);
        if (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER))
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Base damage: " + TextFormatting.RESET + (int) baseDamage, leftShift, posY + 40, -1);
        else if (entityStats.getRace().equals(ModValues.HOLLOW) && entityStats.getRank().equals(ModValues.GILLIAN))
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Base damage: " + TextFormatting.RESET + (int) 6, leftShift, posY + 40, -1);
        else if (entityStats.getRank().equals(ModValues.HOLLOW) && entityStats.getRank().equals(ModValues.VASTO_LORDE))
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Base damage: " + TextFormatting.RESET + (int) 11, leftShift, posY + 40, -1);
        else
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Base damage: " + TextFormatting.RESET + 1, leftShift, posY + 40, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Extra sword damage: " + TextFormatting.RESET + (int) entityStats.getZanjutsuPoints(), leftShift, posY + 60, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Attack speed: " + TextFormatting.RESET + formattedAttackSpeed, leftShift, posY + 80, -1);

        drawString(matrixStack, this.font, TextFormatting.BOLD + "Speed: " + TextFormatting.RESET + formattedSpeed, leftShift, posY + 100, -1);

    }

    public void miscDataRendering(MatrixStack matrixStack)
    {
        int amountKan = miscData.getKan();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        int leftShift = posX + 180;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Kan: " + TextFormatting.RESET + amountKan, leftShift, posY + 170, -1);
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
