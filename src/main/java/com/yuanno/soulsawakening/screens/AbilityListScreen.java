package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
    protected AbilityListScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.abilityData = AbilityDataCapability.get(player);
        this.miscData = MiscDataCapability.get(this.player);
        miscData.setCanRenderOverlay(false);
        abilities = abilityData.getUnlockedAbilities();

    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        Color iconColor = Beapi.hexToRGB("#FFFFFF");
        this.renderBackground(matrixStack);

        entries.clear();
        for (int i = 0; i < abilities.size(); i++)
        {
            String originalResourceLocation = abilities.get(i).getRegistryName().toString();
            String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
            ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
            Beapi.drawIcon(resourceLocation, posX + 40, posY + 40 + (20 * i), 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
            Entry entry = new Entry(abilities.get(i), posX + 40, posY + 40 + (20 * i));
            entries.add(i, entry);
        }

        if (isMouseOver(x, y))
        {
            this.renderTooltip(matrixStack, new TranslationTextComponent(getHoveredEntry(x, y).ability.getName()), x, y);

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
