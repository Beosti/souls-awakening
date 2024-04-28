package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CGiveItemStackPacket;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
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
    //private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/widget_contour_cooldown.png");
    List<WeaponChoiceScreen.Entry> entries = new ArrayList<>();

    ArrayList<TexturedIconButton> buttons = new ArrayList<>();

    int state = 1;
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
    }

    private void addWeaponButton(int x, int y, Item item)
    {
        Entry entry = new Entry(item, x, y);
        this.entries.add(entry);
        String nameWeapon = item.getRegistryName().toString();
        String formattedNameWeapon = nameWeapon.replace("soulsawakening:", "");
        ResourceLocation finalNameWeaponResource = new ResourceLocation(Main.MODID, "textures/items/" + formattedNameWeapon + ".png");
        TexturedIconButton itemButton = new TexturedIconButton(finalNameWeaponResource, x, y, 16, 16, new TranslationTextComponent(""), b ->
        {
            PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(item)));
            this.onClose();
        });
        itemButton.active = true;
        this.addButton(itemButton);
        buttons.add(itemButton);
        itemButton.visible = true;
        /*
        NoTextureButton button = new NoTextureButton(x, y, 16, 16, new TranslationTextComponent("Buy"), (btn) -> {
           miscData.alterKan(-price);
           PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(item)));
           PacketHandler.sendToServer(new CSyncMiscDataPacket(miscData));
        });
        button.item = item;
        button.number = price;
        button.active = miscData.getKan() >= price;
        this.addButton(button);
        buttons.add(button);
        button.visible = true;

         */
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = this.width / 2;
        int posY = this.height / 2;

        /*
        for (int i = 0; i < buttons.size(); i++)
        {
            NoTextureButton button = buttons.get(i);
            Item item = buttons.get(i).item;
            this.renderItem(new ItemStack(item.asItem()), button.x - 73, button.y);
            Beapi.drawStringWithBorder(this.font, matrixStack, "Price: " + button.number, button.x - 53, button.y + 5, -1);
            if (button.number > miscData.getKan()) {
                button.active = false;
            } else {
                button.active = true;
            }
        }

         */
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
