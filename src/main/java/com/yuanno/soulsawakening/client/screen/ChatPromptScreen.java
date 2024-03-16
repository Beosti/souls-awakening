package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.screens.ChallengesScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {


    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/screens/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/decline_button.png");

    private PlayerEntity player;
    private IEntityStats entityStats;
    private SequencedString message = new SequencedString("", 0, 0);

    public ChatPromptScreen()
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = minecraft.player;
        this.entityStats = EntityStatsCapability.get(player);
    }

    @Override
    public void init()
    {
        String text = "So you want to become a shinigami huh?";
        this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.minecraft.textureManager.bind(chatPrompt);
        //GuiUtils.drawTexturedModalRect(posX - 128,  posY + 38, 0, 0, 256, 256, 0);
        this.blit(matrixStack, posX + 4, posY + 8, 0, 0, 256, 256);
        this.message.render(matrixStack, posX + 12, posY + 223);
        super.render(matrixStack, x, y, f);
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new ChatPromptScreen());
    }
}
