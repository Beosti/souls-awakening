package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncentityStatsPacket;
import com.yuanno.soulsawakening.screens.NoTextureButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZanpakutoNameScreen extends Screen {

    private final PlayerEntity player;
    private TextFieldWidget nameEdit;

    protected ZanpakutoNameScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        int posX = ((this.width - 256) / 2) - 110;
        int posY = (this.height - 256) / 2;
        this.nameEdit = new TextFieldWidget(this.font, posX + 160, posY + 160, 140, 20, new StringTextComponent(""));
        this.nameEdit.setMaxLength(20);
        this.nameEdit.insertText("Zanpakuto name");
        this.children.add(this.nameEdit);
        this.setFocused(this.nameEdit);
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.nameEdit.render(matrixStack, x, y, f);
        String createString = "Create";
        NoTextureButton createCrewButton = new NoTextureButton(posX + 95, posY + 200, 60, 16, new TranslationTextComponent(createString), (btn) -> this.updateName());
        this.addButton(createCrewButton);
        super.render(matrixStack, x, y, f);
    }
    private void updateName()
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        ShinigamiStats shinigamiStats = entityStats.getShinigamiStats();
        shinigamiStats.setZanpakutoName(this.nameEdit.getValue());
        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        this.onClose();
    }
    @Override
    public void resize(Minecraft minecraft, int x, int y)
    {
        String zanpakutoName = this.nameEdit.getValue();
        this.init(minecraft, x, y);
        this.nameEdit.insertText(zanpakutoName);
    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
    @Override
    public void tick()
    {
        this.nameEdit.tick();
    }
    public static void open()
    {
        Minecraft.getInstance().setScreen(new ZanpakutoNameScreen());
    }
}
