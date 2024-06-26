package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CGiveItemStackPacket;
import com.yuanno.soulsawakening.networking.client.CSAchievementSpiritWeaponPacket;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import com.yuanno.soulsawakening.networking.client.CSyncentityStatsPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class WeaponChoiceScreen extends Screen {
    private PlayerEntity player;
    private IMiscData miscData;
    List<WeaponChoiceScreen.Entry> entries = new ArrayList<>();

    ArrayList<TexturedIconButton> buttons = new ArrayList<>();

    public WeaponChoiceScreen(PlayerEntity player)
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = player;
        this.miscData = MiscDataCapability.get(player);

    }



    public void renderItem(ItemStack stack, int posX, int posY)
    {
        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, posX, posY);
    }

    @Override
    public void init()
    {
        super.init();
        int posX = this.width / 2;
        int posY = this.height / 2;

        addWeaponButton(50, 50, ModItems.FISHING_ROD_REISHI.get());
        addWeaponButton(100, 50, ModItems.SPEAR_REISHI.get());
        //addWeaponButton(150, 50, ModItems.TRIDENT_REISHI.get());
        addWeaponButton(150, 50, ModItems.SWORD_REISHI.get());
        addWeaponButton(200, 50, ModItems.GINREI_KOJAKU.get());

    }

    private void addWeaponButton(int x, int y, Item item)
    {
        Entry entry = new Entry(item, x, y);
        this.entries.add(entry);
        String nameWeapon = item.getRegistryName().toString();
        String formattedNameWeapon = nameWeapon.replace("soulsawakening:", "");
        ResourceLocation finalNameWeaponResource = new ResourceLocation(Main.MODID, "textures/items/" + formattedNameWeapon + ".png");
        TexturedIconButton itemButton = new TexturedIconButton(finalNameWeaponResource, x, y, 32, 32, new TranslationTextComponent(""), b ->
        {
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (!entityStats.getRace().equals(ModValues.QUINCY) || !entityStats.hasQuincyStats())
                return;
            entityStats.getQuincyStats().setSpiritWeapon(item);
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
            PacketHandler.sendToServer(new CSAchievementSpiritWeaponPacket());
            this.onClose();
        });
        itemButton.active = true;
        this.addButton(itemButton);
        buttons.add(itemButton);
        itemButton.visible = true;
    }

    @Override
    public void onClose() {
        System.out.println("CLOSED");
        super.onClose();
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

    public static void open(PlayerEntity player)
    {
        Minecraft.getInstance().setScreen(new WeaponChoiceScreen(player));
    }

    public WeaponChoiceScreen.Entry getHoveredEntry(double mouseX, double mouseY) {
        int iconWidth = 16;  // Width of each icon
        int iconHeight = 16; // Height of each icon

        for (WeaponChoiceScreen.Entry entry : entries) {
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
        private Item item;
        private int x;
        private int y;
        public Entry(Item item, int x, int y)
        {
            this.item = item;
            this.x = x;
            this.y = y;
        }
    }
}
