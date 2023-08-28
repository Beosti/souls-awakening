package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SkillTreeScreen extends Screen {
    float xMouse;
    float yMouse;
    IEntityStats entityStats;
    int posX;
    int posY;
    private final PlayerEntity player;
    int state = 0;
    List<NoTextureButton> hollowButtons;
    List<NoTextureButton> shinigamiButtons;
    List<NoTextureButton> quincyButtons;

    protected SkillTreeScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.entityStats = EntityStatsCapability.get(player);
        this.posX = ((this.width - 256) / 2);
        this.posY = (this.height - 256) / 2;
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity playerEntity = mc.player;
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        this.buttons.clear();
        this.children.clear();

        int leftShift = posX + 0;
        NoTextureButton hollowButton = new NoTextureButton(posX - 50, posY + 200, 70, 20, new TranslationTextComponent("gui.soulsawakening.hollow_tree"), b ->
        {
            this.state = 0;
            this.init();
        });
        this.addButton(hollowButton);
        if (this.state == 0)
            hollowButton.active = false;
        NoTextureButton shinigamiButton = new NoTextureButton(posX + 100, posY + 200, 70, 20, new TranslationTextComponent("gui.soulsawakening.shinigami_tree"), b ->
        {
            this.state = 1;
            this.init();
        });
        this.addButton(shinigamiButton);
        if (this.state == 1)
            shinigamiButton.active = false;
        NoTextureButton quincyButton = new NoTextureButton(posX + 250, posY + 200, 70, 20, new TranslationTextComponent("gui.soulsawakening.quincy_tree"), b ->
        {
            this.state = 2;
            this.init();
        });
        this.addButton(quincyButton);
        if (this.state == 2)
            quincyButton.active = false;

        switch (this.state)
        {
            case (0):
                hollowSkillTree();
                break;
            case (1):
                shinigamiSkillTree();
                break;
            case (2):
                quincySkillTree();
                break;
        }


    }

    public void hollowSkillTree()
    {
        NoTextureButton biteButton = new NoTextureButton(posX + 250, posY + 200, 18, 18, new TranslationTextComponent("gui.soulsawakening.hollow.bite"), b ->
        {
            this.state = 2;
        });
        this.addButton(biteButton);
        this.hollowButtons.add(biteButton);
    }
    public void shinigamiSkillTree()
    {

    }
    public void quincySkillTree()
    {

    }

    class Entry
    {
        private Button button;
        public Entry(Button button)
        {
            this.button = button;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;


        this.renderBackground(matrixStack);
        renderTooltip();
        statsRendering(matrixStack);

        super.render(matrixStack, x, y, f);
    }

    public void renderTooltip()
    {
        // add a render for the tooltip here
    }

    public void statsRendering(MatrixStack matrixStack)
    {
        PlayerEntity playerEntity = this.getMinecraft().player;
        IEntityStats entityStats = EntityStatsCapability.get(playerEntity);
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        switch (this.state)
        {
            case (0):
                drawString(matrixStack, this.font, TextFormatting.BOLD + "Hollow skill tree" , posX + 95, posY + 20, -1);
                break;
            case (1):
                drawString(matrixStack, this.font, TextFormatting.BOLD + "Shinigami skill tree" , posX + 95, posY + 20, -1);
                break;
            case (2):
                drawString(matrixStack, this.font, TextFormatting.BOLD + "Quincy skill tree" , posX + 95, posY + 20, -1);
                break;
        }
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
}
