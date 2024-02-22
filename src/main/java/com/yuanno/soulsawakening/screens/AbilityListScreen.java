package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.helpers.SoulsHelper;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
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

    /*
    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity playerEntity = mc.player;
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        //this.buttons.clear();


        this.addButton(new Button(posX, posY + 80, 32, 32, new TranslationTextComponent("a"), button ->
        {
            //this.page = 1;
        }));
    }

     */

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
            for (int i = 0; i < abilities.size(); i++) {
                if (abilities.get(i).getCategory().equals(Ability.Category.ZANPAKUTO))
                {
                    String originalResourceLocation = abilities.get(i).getRegistryName().toString();
                    String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                    ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                    Beapi.drawIcon(resourceLocation, posX + 40 + (35 * i), posY + 60, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                    Entry entry = new Entry(abilities.get(i), posX + 40 + (35 * i), posY + 60);
                    entries.add(i, entry);
                }
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
            StringBuilder extraInfo = new StringBuilder(name + "\n" + "press §lSHIFT§r for description and attack type");
            //extraInfo.append("\n" + "press §lSHIFT§r for more information");
            if (abilityHovering.getMaxCooldown() != 0)
                fullDescription.append("§lCooldown§r: " + abilityHovering.getMaxCooldown() + "\n");
            String activation_type = "";
            if (abilityHovering.getPassive())
                activation_type = "passive";
            if (activation_type.isEmpty())
            {
                switch (abilityHovering.getActivationType()) {
                    case ATTACK:
                        activation_type = "on-hit";
                        break;
                    case RIGHT_CLICK_BLOCK:
                        activation_type = "right click on block";
                        break;
                    case RIGHT_CLICK_EMPTY:
                        activation_type = "right click";
                        break;
                    case SHIFT_RIGHT_CLICK:
                        activation_type = "right click + shift";
                        break;
                    case RIGHT_CLICK_ENTITY:
                        activation_type = "right click on entity";
                        break;
                    default:
                        activation_type = "activation type not registered";
                        break;
                }
            }
            fullDescription.append("§lActivation type§r: " + activation_type);
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT))
                this.renderTooltip(matrixStack, new TranslationTextComponent(String.valueOf(fullDescription)), x, y);
            else
                this.renderTooltip(matrixStack, new TranslationTextComponent(String.valueOf(extraInfo)), x, y);
        }
        super.render(matrixStack, x, y, f);
    }


    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {

        int iconWidth = 16;  // Width of each icon
        int iconHeight = 16; // Height of each icon

        for (int i = 0; i < entries.size(); i++) {
            int iconX = entries.get(i).x;
            int iconY = entries.get(i).y;

            // Check if the mouse is over the current icon
            if (mouseX >= iconX && mouseX < iconX + iconWidth &&
                    mouseY >= iconY && mouseY < iconY + iconHeight) {
                return true; // Mouse is over the current icon
            }
        }

        return false; // Mouse is not over any icon
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
