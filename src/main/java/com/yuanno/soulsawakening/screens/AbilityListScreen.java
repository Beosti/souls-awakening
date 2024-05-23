package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.*;
import com.yuanno.soulsawakening.ability.api.interfaces.*;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.RendererHelper;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.helpers.SoulsHelper;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AbilityListScreen extends Screen {
    private final PlayerEntity player;
    private final IAbilityData abilityData;
    private final IMiscData miscData;
    List<Ability> abilities = new ArrayList<>();
    List<Entry> entries = new ArrayList<>();
    private int page;

    ArrayList<Ability> baseAbilities = new ArrayList<>();
    ArrayList<Ability> gillianAbilities = new ArrayList<>();
    ArrayList<Ability> adjuchaAbilities = new ArrayList<>();
    ArrayList<Ability> vastolordeAbilities = new ArrayList<>();
    protected AbilityListScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.abilityData = AbilityDataCapability.get(player);
        this.miscData = MiscDataCapability.get(this.player);
        miscData.setCanRenderOverlay(false);
        abilities = abilityData.getUnlockedAbilities();
        page = 0;
    }

    @Override
    public void init()
    {
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        //this.buttons.clear();

        if (SoulsHelper.hasCategoryAbility(this.player, Ability.Category.ZANPAKUTO))
        {
            this.addButton(new TexturedIconButton(ModResources.SHINIGAMI_ICON, posX, posY + 80, 32, 32, new TranslationTextComponent(""), button ->
            {
                this.page = 1;
            }));
        }
        if (SoulsHelper.hasCategoryAbility(this.player, Ability.Category.HOLLOW))
        {
            this.addButton(new TexturedIconButton(ModResources.HOLLOW_ICON, posX, posY + 80, 32, 32, new TranslationTextComponent(""), button ->
            {
                if (this.page == 2)
                    return;
                for (Ability ability : abilities) {
                    switch (ability.getSubCategory()) {
                        case BASE:
                            baseAbilities.add(ability);
                            break;
                        case GILLIAN:
                            gillianAbilities.add(ability);
                            break;
                        case ADJUCHA:
                            adjuchaAbilities.add(ability);
                            break;
                        case VASTO_LORDE:
                            vastolordeAbilities.add(ability);
                            break;
                    }
                }
                this.page = 2;
            }));
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        Color iconColor = Beapi.hexToRGB("#FFFFFF");
        this.renderBackground(matrixStack);

        entries.clear();

        if (page == 1) // page for zanpakuto abilities
        {
            Beapi.drawStringWithBorder(this.font, matrixStack, "Zanpakuto: ", posX + 40, posY + 40, -1);
            Beapi.drawStringWithBorder(this.font, matrixStack, "Bankai: ", posX + 40, posY + 87, -1);
            ArrayList<Ability> zanpakutoAbilities = new ArrayList<>();
            for (Ability ability : abilities) {
                if (ability.getCategory().equals(Ability.Category.ZANPAKUTO)) {
                    zanpakutoAbilities.add(ability);
                }
            }
            for (int i = 0; i < zanpakutoAbilities.size(); i++)
            {
                String originalResourceLocation = zanpakutoAbilities.get(i).getRegistryName().toString();
                String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                Beapi.drawIcon(resourceLocation, posX + 40 + (35 * i), posY + 60, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                Entry entry = new Entry(zanpakutoAbilities.get(i), posX + 40 + (35 * i), posY + 60);
                entries.add(i, entry);
            }
        }
        if (page == 2)
        {
            Beapi.drawStringWithBorder(this.font, matrixStack, "Base form: ", posX + 40, posY + 40, -1);
            Beapi.drawStringWithBorder(this.font, matrixStack, "Gillian form: ", posX + 40, posY + 87, -1);
            Beapi.drawStringWithBorder(this.font, matrixStack, "Adjucha form: ", posX + 40, posY + 124, -1);
            Beapi.drawStringWithBorder(this.font, matrixStack, "Vasto Lorde form: ", posX + 40, posY + 161, -1);

            for (int i = 0; i < baseAbilities.size(); i++)
            {
                String originalResourceLocation = abilities.get(i).getRegistryName().toString();
                String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                Beapi.drawIcon(resourceLocation, posX + 40 + (35 * i), posY + 60, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                Entry entry = new Entry(abilities.get(i), posX + 40 + (35 * i), posY + 60);
                entries.add(i, entry);
            }
            for (int i = 0; i < gillianAbilities.size(); i++)
            {
                String originalResourceLocation = gillianAbilities.get(i).getRegistryName().toString();
                String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                Beapi.drawIcon(resourceLocation, posX + 40 + (35 * i), posY + 101, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                Entry entry = new Entry(gillianAbilities.get(i), posX + 40 + (35 * i), posY + 107);
                entries.add(i, entry);
            }
            for (int i = 0; i < adjuchaAbilities.size(); i++)
            {
                String originalResourceLocation = adjuchaAbilities.get(i).getRegistryName().toString();
                String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                Beapi.drawIcon(resourceLocation, posX + 40 + (35 * i), posY + 140, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                Entry entry = new Entry(adjuchaAbilities.get(i), posX + 40 + (35 * i), posY + 140);
                entries.add(i, entry);
            }
            for (int i = 0; i < vastolordeAbilities.size(); i++)
            {
                String originalResourceLocation = vastolordeAbilities.get(i).getRegistryName().toString();
                String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                Beapi.drawIcon(resourceLocation, posX + 40 + (35 * i), posY + 179, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                Entry entry = new Entry(vastolordeAbilities.get(i), posX + 40 + (35 * i), posY + 107);
                entries.add(i, entry);
            }
        }

        if (isMouseOver(x, y))
        {
            Ability abilityHovering = getHoveredEntry(x, y).ability;
            String name = abilityHovering.getName();
            StringBuilder fullDescription = new StringBuilder(name + "\n" + "§lDescription§r: " + abilityHovering.getDescription() + "\n");
            //extraInfo.append("\n" + "press §lSHIFT§r for more information");
            if (abilityHovering.getMaxCooldown() != 0)
                fullDescription.append("§lCooldown§r: " + abilityHovering.getMaxCooldown() + " seconds" + " / " + abilityHovering.getMaxCooldown() * 20 + " ticks" + "\n");
            String activation_type = "";
            if (abilityHovering instanceof IAttackAbility)
                activation_type = "on-hit";
            if (abilityHovering instanceof IReiatsuAbility)
                activation_type = "spell";
            if (abilityHovering instanceof IPassiveAbility)
                activation_type = "passive";
            if (abilityHovering instanceof IRightClickAbility)
                activation_type = "right click";
            if (abilityHovering instanceof IEntityRayTrace)
                activation_type = "right click an entity, in range of  " + ((IEntityRayTrace) abilityHovering).getDistance() + " blocks away";
            if (abilityHovering instanceof IBlockRayTrace)
                activation_type = "right click a block of entity, in range of " + ((IBlockRayTrace) abilityHovering).getDistance() + " blocks away";
            if (abilityHovering.getSubCategory().equals(Ability.SubCategory.SHIKAI))
                fullDescription.append("§lRequirement§r: Zanpakuto in Shikai state").append("\n");
            fullDescription.append("§lActivation type§r: ").append(activation_type).append("\n");
            if (abilityHovering instanceof IReiatsuAbility && ((IReiatsuAbility) abilityHovering).addedVariable(player) > 0) {
                String extra = "";
                if (abilityHovering instanceof IBlockRayTrace)
                    extra = "§lExtra range§r: " + ((IReiatsuAbility) abilityHovering).addedVariable(player) + " (reiatsu)";
                if (abilityHovering instanceof IWaveAbility)
                    extra = "§lExtra damage§r: " + ((IReiatsuAbility) abilityHovering).addedVariable(player) + " (reiatsu)";
                if (abilityHovering instanceof ISelfEffect && ((ISelfEffect) abilityHovering).healAmount() > 0)
                    extra = "§lExtra healing§r: " + ((IReiatsuAbility) abilityHovering).addedVariable(player) + " (reiatsu)";
                if (abilityHovering instanceof IShootAbility)
                    extra = "§lExtra damage§r: " + ((IReiatsuAbility) abilityHovering).addedVariable(player) + " (reiatsu)";
                if (!extra.isEmpty())
                    fullDescription.append(extra).append("\n");
            }
            int backgroundColorStart = Beapi.hexToRGB("#000000").getRGB();
            int backgroundColorEnd = Beapi.hexToRGB("#FFFFFF").getRGB();
            int backgroundStart = Beapi.hexToRGB("FFFFFF").getRGB();
            int backgroundEnd = Beapi.hexToRGB("000000").getRGB();
            RendererHelper.drawAbilityTooltip(abilityHovering, matrixStack, Arrays.asList(new StringTextComponent(fullDescription.toString())), x, y, this.width, this.height, 210, backgroundColorStart, backgroundColorEnd, backgroundStart, backgroundEnd, this.getMinecraft().font);
        }
        super.render(matrixStack, x, y, f);
    }


    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {

        int iconWidth = 16;  // Width of each icon
        int iconHeight = 16; // Height of each icon

        for (Entry entry : entries) {
            int iconX = entry.x;
            int iconY = entry.y;

            // Check if the mouse is over the current icon
            if (mouseX >= iconX && mouseX < iconX + iconWidth &&
                    mouseY >= iconY && mouseY < iconY + iconHeight) {
                return true; // Mouse is over the current icon
            }
        }

        return false; // Mouse is not over any icon
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new AbilityListScreen());
    }
    @Override
    public void onClose() {
        super.onClose();
        miscData.setCanRenderOverlay(true);
        PacketHandler.sendToServer(new CSyncMiscDataPacket(miscData));

    }

    public Entry getHoveredEntry(double mouseX, double mouseY) {
        int iconWidth = 16;  // Width of each icon
        int iconHeight = 16; // Height of each icon

        for (Entry entry : entries) {
            int iconX = entry.x;
            int iconY = entry.y;

            // Check if the mouse is over the current icon
            if (mouseX >= iconX && mouseX < iconX + iconWidth &&
                    mouseY >= iconY && mouseY < iconY + iconHeight) {
                return entry; // Mouse is over the current icon
            }
        }

        return null; // Mouse is not over any icon
    }

    class Entry
    {
        private Ability ability;
        private int x;
        private int y;
        public Entry(Ability ability, int x, int y)
        {
            this.ability =ability;
            this.x = x;
            this.y = y;
        }
    }
}
