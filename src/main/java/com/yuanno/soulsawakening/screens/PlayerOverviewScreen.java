package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
import com.yuanno.soulsawakening.data.entity.quincy.QuincyStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModConfig;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
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
    ResourceLocation resourceLocation = new ResourceLocation(Main.MODID + ":textures/items/kan.png");
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

        if (entityStats.getRace().equals(ModValues.SHINIGAMI)) {
            ShinigamiStats shinigamiStats = entityStats.getShinigamiStats();
            int totalPoints = (int) (shinigamiStats.getHakudaPoints() + shinigamiStats.getHohoPoints() + shinigamiStats.getHohoPoints() + entityStats.getReiatsuPoints());
            if (ModConfig.stat_limit.get() > totalPoints)
                handleShinigamiInit();
        }
        if (entityStats.getRace().equals(ModValues.HOLLOW)) {
            handleHollowInit();
        }
        if (entityStats.getRace().equals(ModValues.QUINCY)) {
            QuincyStats quincyStats = entityStats.getQuincyStats();
            int totalPoints = (int) (quincyStats.getBlut() + quincyStats.getHirenkyaku() + quincyStats.getConstitution() + entityStats.getReiatsuPoints());
            if (ModConfig.stat_limit.get() > totalPoints)
                handleQuincyInit();
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

        HollowStats hollowStats = entityStats.getHollowStats();
        int totalPoints = (int) (hollowStats.getAgility() + hollowStats.getConstitution() + hollowStats.getHierro() + entityStats.getReiatsuPoints());
        if (ModConfig.stat_limit.get() > totalPoints)
        {// reiatsu point
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
        }


        // evolution button
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift - 180, posY + 160, 95, 16, new TranslationTextComponent("gui.evolution.button"), b -> {
            PacketHandler.sendToServer(new CHollowEvolutionPacket());
            this.init();
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
        })).active = entityStats.getHollowStats().getHollowPoints() >= ModConfig.hollow_evolution.get() && !(entityStats.getRank().equals(ModValues.VASTO_LORDE));
    }

    void handleQuincyInit()
    {
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        int leftShift = posX + 120;

        // reiatsu point
        this.addButton(new Button(leftShift - 65, posY + 104, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getQuincyStats().alterClassPoints(-1);
            entityStats.alterReiatsuPoints(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.reiatsu_quincy.button"), mouseX, mouseY);
        }))).active = this.entityStats.getQuincyStats().getClassPoints() > 0;
        // hirenkyaku point
        this.addButton(new Button(leftShift - 65, posY + 89, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getQuincyStats().alterClassPoints(-1);
            entityStats.getQuincyStats().alterHirenkyaku(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.hirenkyaku.button"), mouseX, mouseY);
        }))).active = this.entityStats.getQuincyStats().getClassPoints() > 0;
        // constitution point
        this.addButton(new Button(leftShift - 65, posY + 74, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getQuincyStats().alterClassPoints(-1);
            entityStats.getQuincyStats().alterConstitution(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.constitution.button"), mouseX, mouseY);
        }))).active = this.entityStats.getQuincyStats().getClassPoints() > 0;
        // blut point
        this.addButton(new Button(leftShift - 65, posY + 59, 8, 8, new TranslationTextComponent("+"), b ->
        {
            entityStats.getQuincyStats().alterClassPoints(-1);
            entityStats.getQuincyStats().alterBlut(1);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            this.init();
        }, ((button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("gui.blut.button"), mouseX, mouseY);
        }))).active = this.entityStats.getQuincyStats().getClassPoints() > 0;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        String name = this.player.getName().getString();
        String race = entityStats.getRace();
        String rank = entityStats.getRank();
        int leftShift = posX - 75;
        this.renderBackground(matrixStack);

        // Every race needs to have this rendered anyway
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Name: " + TextFormatting.RESET + name, leftShift, posY + 20, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Race: " + TextFormatting.RESET + race, leftShift, posY + 40, -1);
        if (!rank.isEmpty())
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Rank: " + TextFormatting.RESET + rank, leftShift + 80 + rank.length(), posY + 40, -1);

        if (this.entityStats.getRace().equals(ModValues.SPIRIT))
            spiritRendering(matrixStack, posX, posY);
        if (this.entityStats.getRace().equals(ModValues.SHINIGAMI))
            shinigamiRendering(matrixStack, posX, posY);
        if (this.entityStats.getRace().equals(ModValues.HOLLOW))
            hollowRendering(matrixStack, posX, posY, mouseX, mouseY);
        if (this.entityStats.getRace().equals(ModValues.QUINCY))
            quincyRendering(matrixStack, posX, posY, mouseX, mouseY);

        ModifiableAttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        ModifiableAttributeInstance attackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
        ModifiableAttributeInstance resistance = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
        leftShift += 180;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Max Health: " + TextFormatting.RESET + maxHealth.getBaseValue(), leftShift, posY + 40, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Base damage: " + TextFormatting.RESET + attackDamage.getBaseValue(), leftShift, posY + 55, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Damage resistance: " + TextFormatting.RESET + resistance.getBaseValue(), leftShift, posY + 70, -1);
        String critChance = entityStats.getSpeedStat() + "%";
        String damageModifier = 1 + ((float) entityStats.getSpeedStat() / 10) + " X damage";
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Crit chance: " + TextFormatting.RESET + critChance, leftShift, posY + 85, -1);
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Crit modifier: " + TextFormatting.RESET + damageModifier, leftShift, posY + 100, -1);

        super.render(matrixStack, mouseX, mouseY, f);
    }

    public void spiritRendering(MatrixStack matrixStack, int posX, int posY)
    {
        int leftShift = posX - 75;
        int spiritChain = miscData.getSpiritChain();
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Spirit chain: " + TextFormatting.RESET + spiritChain, leftShift, posY + 60, -1);

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
        String kan = TextFormatting.BOLD + "Kan: " + TextFormatting.RESET + miscData.getKan();
        drawString(matrixStack, this.font, kan, leftShift, posY + 170, -1);
        Beapi.drawIcon(resourceLocation, leftShift - mc.font.width(kan) - 5, posY + 60, 1, 16, 16, 0, 0, 0);

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

        drawString(matrixStack, this.font, TextFormatting.BOLD + "Constitution points: " + TextFormatting.RESET + constitution, leftShift, posY + 60, -1);
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

    public void quincyRendering(MatrixStack matrixStack, int posX, int posY, int mouseX, int mouseY)
    {
        int leftShift = posX - 75;

        int classPoints = entityStats.getQuincyStats().getClassPoints();
        int blut = entityStats.getQuincyStats().getBlut();
        int constitution = entityStats.getQuincyStats().getConstitution();
        int hirenkyaku = entityStats.getQuincyStats().getHirenkyaku();
        int reiatsuPoints = (int) entityStats.getReiatsuPoints();

        String blutPoints = TextFormatting.BOLD + "Blut points: "  + TextFormatting.RESET + blut;
        drawString(matrixStack, this.font, blutPoints, leftShift, posY + 60, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(blutPoints) && mouseY >= posY + 60 && mouseY <= posY + 60 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.blut_points.tooltip"), mouseX, mouseY);
        String constitutionPoints = TextFormatting.BOLD + "Constitution points: " + TextFormatting.RESET + constitution;
        drawString(matrixStack, this.font, constitutionPoints, leftShift, posY + 75, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(constitutionPoints) && mouseY >= posY + 75 && mouseY <= posY + 75 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.constitution_point.tooltip"), mouseX, mouseY);
        String hirenkyakuPoints = TextFormatting.BOLD + "Hirenkyaku points: " + TextFormatting.RESET + hirenkyaku;
        drawString(matrixStack, this.font, hirenkyakuPoints, leftShift, posY + 90, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(hirenkyakuPoints) && mouseY >= posY + 90 && mouseY <= posY + 90 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.hirenkyaku.tooltip"), mouseX, mouseY);
        String reiatsuPointsString = TextFormatting.BOLD + "Reiatsu points: " + TextFormatting.RESET + reiatsuPoints;
        drawString(matrixStack, this.font, reiatsuPointsString, leftShift, posY + 105, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(reiatsuPointsString) && mouseY >= posY + 105 && mouseY <= posY + 105 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.reiatsu_point.tooltip"), mouseX, mouseY);
        String classPointString = TextFormatting.BOLD + "Class points: " + TextFormatting.RESET + classPoints;
        drawString(matrixStack, this.font, classPointString, leftShift, posY + 120, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(classPointString) && mouseY >= posY + 120 && mouseY <= posY + 120 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.class_point.tooltip"), mouseX, mouseY);
        leftShift = posX + 180;
        String kanString = TextFormatting.BOLD + "Kan: " + TextFormatting.RESET + miscData.getKan();
        drawString(matrixStack, this.font, kanString, leftShift, posY + 170, -1);
        if (mouseX >= leftShift && mouseX <= leftShift + this.mc.font.width(kanString) && mouseY >= posY + 170 && mouseY <= posY + 170 + this.mc.font.lineHeight)
            this.renderTooltip(matrixStack, new TranslationTextComponent("gui.kan.tooltip"), mouseX, mouseY);
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
