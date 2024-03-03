package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class TradingScreen extends Screen {
    private PlayerEntity player;
    private IMiscData miscData;
    //private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/widget_contour_cooldown.png");

    ArrayList<NoTextureButton> buttons = new ArrayList<>();

    int state = 1;
    public TradingScreen(PlayerEntity player)
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
        addBuyButton(posX - 15, posY - 73, Items.DIAMOND, 150);
        addBuyButton(posX - 15, posY - 55, Items.IRON_INGOT, 10);
        addBuyButton(posX - 15, posY - 37, Items.COAL, 1);
        addBuyButton(posX - 15, posY - 19, ModItems.REISHI.get(), 20);
        addBuyButton(posX + 120, posY - 73, ModItems.REISHI_INGOT.get(), 100);
        //System.out.println(buttons);
    }

    private void addBuyButton(int x, int y, Item item, int price)
    {
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
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = this.width / 2;
        int posY = this.height / 2;
       // Minecraft.getInstance().getTextureManager().bind(background);
        //GuiUtils.drawTexturedModalRect(posX - 90, posY - 75, 0, 0, 256, 256, 0);
        miscDataRendering(matrixStack);
 
        

        // TODO add more stuff to trade for
        /*
        this.renderItem(new ItemStack(Items.DIAMOND.asItem()), posX - 90, posY - 75);
        this.renderItem(new ItemStack(Items.IRON_INGOT.asItem()), posX - 90, posY - 57);
        this.renderItem(new ItemStack(Items.COAL.asItem()), posX - 90, posY - 39);

         */
        //Beapi.drawStringWithBorder(this.font, matrixStack, "Price: 150", posX - 70, posY - 70, -1);

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
        /*
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            this.renderItem(new ItemStack(item.asItem()), posX - 90, posY - 75 + (i * 18));

        }

         */
        /*
        for (Map.Entry<NoTextureButton, Item> entry : buttons.entrySet()) {
            NoTextureButton key = entry.getKey();
            Item button = entry.getValue();

            // Render a string for each button
            Beapi.drawStringWithBorder(this.font, matrixStack, "Price: " + key.number, posX - 70, posY - 70 + (index * 18), -1);
            this.renderItem(new ItemStack(button.asItem()), posX - 90, posY - 75 + (index * 18));
            // Check a condition and update the 'active' property of the button
            if (key.number > miscData.getKan()) {
                key.active = false;
            } else {
                key.active = true;
            }
            index++;
        }

         */


        super.render(matrixStack, x, y, f);
    }

    public void miscDataRendering(MatrixStack matrixStack)
    {
        int amountKan = miscData.getKan();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        int leftShift = posX - 20;
        drawString(matrixStack, this.font, TextFormatting.BOLD + "Kan: " + TextFormatting.RESET + amountKan, leftShift, posY + 160, -1);



    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open(PlayerEntity player)
    {
        Minecraft.getInstance().setScreen(new TradingScreen(player));
    }
}
