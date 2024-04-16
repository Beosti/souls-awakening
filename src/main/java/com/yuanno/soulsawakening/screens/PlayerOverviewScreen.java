package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
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
    private IEntityStats entityStats;
    private final IMiscData miscData;
    private Minecraft mc;
    protected PlayerOverviewScreen() {
        super(new StringTextComponent(""));
        this.mc = Minecraft.getInstance();
        this.player = Minecraft.getInstance().player;
        this.entityStats = EntityStatsCapability.get(player);
        this.miscData = MiscDataCapability.get(this.player);
        miscData.setCanRenderOverlay(false);
        PacketHandler.sendToServer(new CSyncMiscDataPacket(miscData));

    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        this.entityStats = EntityStatsCapability.get(player);
        this.buttons.clear();
        this.children.clear();

        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        int leftShift = posX - 75;

        if (entityStats.getRace().equals(ModValues.SHINIGAMI))
            handleShinigamiInit();
        if (entityStats.getRace().equals(ModValues.HOLLOW))
            handleHollowInit();
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
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift + 61 + 252, posY + 140, 60, 20, new TranslationTextComponent("Teleports"), b -> {
            PacketHandler.sendToServer(new COpenTeleportScreenPacket());
            this.onClose();
        })).active = !TeleportCapability.get(this.player).getTeleportPositions().isEmpty();
    }

    void handleShinigamiInit()
    {
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        int leftShift = posX + 120;

        // reiatsu point
        this.addButton(new Button(leftShift - 75, posY + 104, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getShinigamiStats().alterClassPoints(-1);
            entityStats.alterReiatsuPoints(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.reiatsu.button"), mouseX, mouseY);
        }))).active = this.entityStats.getShinigamiStats().getClassPoints() > 0;
        // hoho point
        this.addButton(new Button(leftShift - 75, posY + 89, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getShinigamiStats().alterClassPoints(-1);
            entityStats.getShinigamiStats().alterHohoPoints(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.hoho.button"), mouseX, mouseY);
        }))).active = this.entityStats.getShinigamiStats().getClassPoints() > 0;
        // haku point
        this.addButton(new Button(leftShift - 75, posY + 74, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getShinigamiStats().alterClassPoints(-1);
            entityStats.getShinigamiStats().alterHakudaPoints(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.haku.button"), mouseX, mouseY);
        }))).active = this.entityStats.getShinigamiStats().getClassPoints() > 0;
        // zanjutsu point
        this.addButton(new Button(leftShift - 75, posY + 59, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getShinigamiStats().alterClassPoints(-1);
            entityStats.getShinigamiStats().alterZanjutsuPoints(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.zanju.button"), mouseX, mouseY);
        }))).active = this.entityStats.getShinigamiStats().getClassPoints() > 0;
    }

    void handleHollowInit()
    {
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        int leftShift = posX + 120;

        // reiatsu point
        this.addButton(new Button(leftShift - 75, posY + 104, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getHollowStats().alterMutationPoints(-1);
            entityStats.alterReiatsuPoints(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.reiatsu.button"), mouseX, mouseY);
        }))).active = this.entityStats.getHollowStats().getMutationPoints() > 0;
        // constitution point
        this.addButton(new Button(leftShift - 75, posY + 59, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getHollowStats().alterMutationPoints(-1);
            entityStats.getHollowStats().alterConstitution(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.constitution.button"), mouseX, mouseY);
        }))).active = this.entityStats.getHollowStats().getMutationPoints() > 0;
        // hierro point
        this.addButton(new Button(leftShift - 75, posY + 74, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getHollowStats().alterMutationPoints(-1);
            entityStats.getHollowStats().alterHierro(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.hierro.button"), mouseX, mouseY);
        }))).active = this.entityStats.getHollowStats().getMutationPoints() > 0;
        // agility point
        this.addButton(new Button(leftShift - 75, posY + 89, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getHollowStats().alterMutationPoints(-1);
            entityStats.getHollowStats().alterAgility(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.agility.button"), mouseX, mouseY);
        }))).active = this.entityStats.getHollowStats().getMutationPoints() > 0;


        // evolution button
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift - 180, posY + 160, 95, 16, new TranslationTextComponent("gui.evolution.button"), b -> {
            PacketHandler.sendToServer(new CHollowEvolutionPacket());
            entityStats.getHollowStats().setHollowPoints(0);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.onClose();
        }, (button, matrixStack, mouseX, mouseY) -> {
            if (button.isHovered() && button.active) {
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.evolution.tooltip"), mouseX, mouseY);
            }
            else if (button.isHovered() && entityStats.getRank().equals(ModValues.VASTO_LORDE))
            {
                this.renderTooltip(matrixStack, new TranslationTextComponent("WIP"), mouseX, mouseY);
            }
            else if (button.isHovered())
            {
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.evolution.active"), mouseX, mouseY);
            }
        })).active = entityStats.getHollowStats().getHollowPoints() >= 50 && !(entityStats.getRank().equals(ModValues.VASTO_LORDE));

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        String name = this.player.getName().getString();
        String race = entityStats.getRace();
        int leftShift = posX - 75;
        this.renderBackground(matrixStack);

        // Every race needs to have this rendered anyway
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Name: " + TextFormatting.RESET + name, leftShift, posY + 20, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Race: " + TextFormatting.RESET + race, leftShift, posY + 40, -1);

        if (this.entityStats.getRace().equals(ModValues.SHINIGAMI))
            shinigamiRendering(matrixStack, posX, posY);
        if (this.entityStats.getRace().equals(ModValues.HOLLOW))
            hollowRendering(matrixStack, posX, posY, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, f);
    }

    public void shinigamiRendering(MatrixStack matrixStack, int posX, int posY)
    {
        int leftShift = posX - 75;

        int classPoints = entityStats.getShinigamiStats().getClassPoints();
        int zanjutsuPoints = (int) Math.floor(entityStats.getShinigamiStats().getZanjutsuPoints());
        int hakuPoints = (int) Math.floor(entityStats.getShinigamiStats().getHakudaPoints());
        int hohoPoints = (int) Math.floor(entityStats.getShinigamiStats().getHohoPoints());
        int reiatsuPoints = (int) Math.floor(entityStats.getReiatsuPoints());


        drawString(matrixStack, this.font, TextFormatting.BOLD + "Zanjutsu points: " + TextFormatting.RESET + zanjutsuPoints, leftShift, posY + 60, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Hakuda points: " + TextFormatting.RESET + hakuPoints, leftShift, posY + 75, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Hoho points: " + TextFormatting.RESET + hohoPoints, leftShift, posY + 90, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Reiatsu points: " + TextFormatting.RESET + reiatsuPoints, leftShift, posY + 105, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Class points: " + TextFormatting.RESET + classPoints, leftShift, posY + 120, -1);
        leftShift = posX + 180;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Kan: " + TextFormatting.RESET + miscData.getKan(), leftShift, posY + 170, -1);
    }

    public void hollowRendering(MatrixStack matrixStack, int posX, int posY, int mouseX, int mouseY)
    {
        int leftShift = posX - 75;

        int hollowPoints = this.entityStats.getHollowStats().getHollowPoints();
        int mutationPoints = this.entityStats.getHollowStats().getMutationPoints();

        int constitution = this.entityStats.getHollowStats().getConstitution();
        int hierro = this.entityStats.getHollowStats().getHierro();
        int agility = this.entityStats.getHollowStats().getAgility();
        int reiatsuPoints = (int) Math.floor(this.entityStats.getReiatsuPoints());

        drawString(matrixStack, this.font, TextFormatting.BOLD + "Contitution points: " + TextFormatting.RESET + constitution, leftShift, posY + 60, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Hierro points: " + TextFormatting.RESET + hierro, leftShift, posY + 75, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Agility points: " + TextFormatting.RESET + agility, leftShift, posY + 90, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Reiatsu points: " + TextFormatting.RESET + reiatsuPoints, leftShift, posY + 105, -1);

        String mutationPointsString = TextFormatting.BOLD + "Mutation points: " + TextFormatting.RESET + mutationPoints;
        drawString(matrixStack, this.font, mutationPointsString, leftShift, posY + 125, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(mutationPointsString) && mouseY >= posY + 125 && mouseY <= posY + 125 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.mutation_point.tooltip"), mouseX, mouseY);
        String HollowPointsString = TextFormatting.BOLD + "Hollow points: " + TextFormatting.RESET + hollowPoints;
        drawString(matrixStack, this.font, HollowPointsString, leftShift, posY + 140, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(HollowPointsString) && mouseY >= posY + 140 && mouseY <= posY + 140 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.evolution_point.tooltip"), mouseX, mouseY);
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
