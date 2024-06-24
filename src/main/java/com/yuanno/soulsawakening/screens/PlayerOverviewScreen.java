package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.RendererHelper;
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
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


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
            if (ModConfig.STAT_LIMIT.get() > totalPoints)
                handleShinigamiInit();
        }
        if (entityStats.getRace().equals(ModValues.HOLLOW)) {
            handleHollowInit();
        }
        if (entityStats.getRace().equals(ModValues.QUINCY)) {
            QuincyStats quincyStats = entityStats.getQuincyStats();
            int totalPoints = (int) (quincyStats.getBlut() + quincyStats.getHirenkyaku() + quincyStats.getConstitution() + entityStats.getReiatsuPoints());
            if (ModConfig.STAT_LIMIT.get() > totalPoints)
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
        if (ModConfig.STAT_LIMIT.get() > totalPoints)
        {
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
        }


        // evolution button
        this.addButton(new net.minecraft.client.gui.widget.button.Button(leftShift - 180, posY + 150, 95, 16, new TranslationTextComponent("gui.evolution.button"), b -> {
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
        })).active = entityStats.getHollowStats().getHollowPoints() >= ModConfig.HOLLOW_EVOLUTION_VALUE.get() && !(entityStats.getRank().equals(ModValues.VASTO_LORDE));
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
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.kan.string").withStyle(TextFormatting.BOLD), miscData.getKan() + "", new TranslationTextComponent("gui.kan.tooltip"),
                leftShift, posY + 170);
        if (this.entityStats.getRace().equals(ModValues.SPIRIT))
            spiritRendering(matrixStack, posX, posY, mouseX, mouseY);
        if (this.entityStats.getRace().equals(ModValues.SHINIGAMI))
            shinigamiRendering(matrixStack, posX, posY, mouseX, mouseY);
        if (this.entityStats.getRace().equals(ModValues.HOLLOW))
            hollowRendering(matrixStack, posX, posY, mouseX, mouseY);
        if (this.entityStats.getRace().equals(ModValues.QUINCY))
            quincyRendering(matrixStack, posX, posY, mouseX, mouseY);

        ModifiableAttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        ModifiableAttributeInstance attackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
        ModifiableAttributeInstance resistance = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
        leftShift += 240;

        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.max_health.string").withStyle(TextFormatting.BOLD), "" + maxHealth.getBaseValue(), new TranslationTextComponent("gui.max_health.tooltip"),
                leftShift, posY + 40);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.base_damage.string").withStyle(TextFormatting.BOLD), "" + attackDamage.getBaseValue(), new TranslationTextComponent("gui.base_damage.tooltip"),
                leftShift, posY + 55);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.damage_resistance.string").withStyle(TextFormatting.BOLD), "" + resistance.getBaseValue() * 100 + "%", new TranslationTextComponent("gui.damage_resistance.tooltip"),
                leftShift, posY + 70);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.damage_resistance.string").withStyle(TextFormatting.BOLD), "" + resistance.getBaseValue() * 100 + "%", new TranslationTextComponent("gui.damage_resistance.tooltip"),
                leftShift, posY + 70);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.crit_chance.string").withStyle(TextFormatting.BOLD), entityStats.getSpeedStat() + "%", new TranslationTextComponent("gui.crit_chance.tooltip"),
                leftShift, posY + 85);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.crit_mod.string").withStyle(TextFormatting.BOLD), 1 + ((float) entityStats.getSpeedStat() / 10) + "", new TranslationTextComponent("gui.crit_mod.tooltip"),
                leftShift, posY + 100);
        super.render(matrixStack, mouseX, mouseY, f);
    }

    public void spiritRendering(MatrixStack matrixStack, int posX, int posY, int mouseX, int mouseY)
    {
        int leftShift = posX - 75;
        int spiritChain = miscData.getSpiritChain();
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.chain_faith.string").withStyle(TextFormatting.BOLD), spiritChain + "", new TranslationTextComponent("gui.chain_faith.tooltip"),
                leftShift, posY + 60);
    }
    public void shinigamiRendering(MatrixStack matrixStack, int posX, int posY, int mouseX, int mouseY)
    {
        int leftShift = posX - 75;

        int classPoints = entityStats.getShinigamiStats().getClassPoints();
        int zanjutsuPoints = (int) Math.floor(entityStats.getShinigamiStats().getZanjutsuPoints());
        int hakuPoints = (int) Math.floor(entityStats.getShinigamiStats().getHakudaPoints());
        int hohoPoints = (int) Math.floor(entityStats.getShinigamiStats().getHohoPoints());
        int reiatsuPoints = (int) Math.floor(entityStats.getReiatsuPoints());


        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.zanjutsu.string").withStyle(TextFormatting.BOLD), zanjutsuPoints + "", new TranslationTextComponent("gui.zanjutsu.tooltip"),
                leftShift, posY + 60);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.hakuda.string").withStyle(TextFormatting.BOLD), hakuPoints + "", new TranslationTextComponent("gui.hakuda.tooltip"),
                leftShift, posY + 75);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.hoho.string").withStyle(TextFormatting.BOLD), hohoPoints + "", new TranslationTextComponent("gui.hoho.tooltip"),
                leftShift, posY + 90);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.reiatsu.string").withStyle(TextFormatting.BOLD), reiatsuPoints + "", new TranslationTextComponent("gui.reiatsu_shinigami.tooltip"),
                leftShift, posY + 105);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.class.string").withStyle(TextFormatting.BOLD), classPoints + "", new TranslationTextComponent("gui.class.tooltip"),
                leftShift, posY + 135);
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

        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.constitution.string").withStyle(TextFormatting.BOLD), constitution + "", new TranslationTextComponent("gui.constitution.tooltip"),
                leftShift, posY + 60);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.hierro.string").withStyle(TextFormatting.BOLD), hierro + "", new TranslationTextComponent("gui.hierro.tooltip"),
                leftShift, posY + 75);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.agility.string").withStyle(TextFormatting.BOLD), agility + "", new TranslationTextComponent("gui.agility.tooltip"),
                leftShift, posY + 90);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.reiatsu.string").withStyle(TextFormatting.BOLD), reiatsuPoints + "", new TranslationTextComponent("gui.reiatsu_hollow.tooltip"),
                leftShift, posY + 105);

        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.mutation_point.string").withStyle(TextFormatting.BOLD), mutationPoints + "", new TranslationTextComponent("gui.mutation_point.tooltip"),
                leftShift, posY + 125);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.evolution_point.string").withStyle(TextFormatting.BOLD), hollowPoints + "", new TranslationTextComponent("gui.evolution_point.tooltip"),
                leftShift, posY + 140);
    }

    public void quincyRendering(MatrixStack matrixStack, int posX, int posY, int mouseX, int mouseY)
    {
        int leftShift = posX - 75;

        int classPoints = entityStats.getQuincyStats().getClassPoints();
        int blut = entityStats.getQuincyStats().getBlut();
        int constitution = entityStats.getQuincyStats().getConstitution();
        int hirenkyaku = entityStats.getQuincyStats().getHirenkyaku();
        int reiatsuPoints = (int) entityStats.getReiatsuPoints();

        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.blut.string").withStyle(TextFormatting.BOLD), blut + "", new TranslationTextComponent("gui.blut.tooltip"),
                leftShift, posY + 60);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.constitution_quincy.string").withStyle(TextFormatting.BOLD), constitution + "", new TranslationTextComponent("gui.constitution_quincy.tooltip"),
                leftShift, posY + 75);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.hirenkyaku.string").withStyle(TextFormatting.BOLD), hirenkyaku + "", new TranslationTextComponent("gui.hirenkyaku.tooltip"),
                leftShift, posY + 90);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.reiatsu.string").withStyle(TextFormatting.BOLD), reiatsuPoints + "", new TranslationTextComponent("gui.reiatsu_quincy.tooltip"),
                leftShift, posY + 105);
        RendererHelper.drawTwoStringWithTooltip(this, mouseX, mouseY, matrixStack, this.font,
                new TranslationTextComponent("gui.class_point.string").withStyle(TextFormatting.BOLD), classPoints + "", new TranslationTextComponent("gui.class_point.tooltip"),
                leftShift, posY + 135);
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
