package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CGiveItemStackPacket;
import com.yuanno.soulsawakening.networking.client.CSyncQuestDataPacket;
import com.yuanno.soulsawakening.screens.ChallengesScreen;
import com.yuanno.soulsawakening.screens.TexturedIconButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {


    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/screens/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/decline_button.png");

    private PlayerEntity player;
    private IEntityStats entityStats;
    private SequencedString message = new SequencedString("", 0, 0);
    private int page = 0;

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
        this.buttons.clear();
        this.children.clear();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        String text = "";
        boolean hasQuest = QuestDataCapability.get(player).hasInProgressQuest(ModQuests.KILLHOLLOW);
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.KILLHOLLOW))
        {
            text = "Are you done with your task?";
            this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
            return;
        }
        if (hasQuest)
            return;
        if (this.page == -1)
            text = "Never mind then.";
        if (this.page == 0)
            text = "So you want to become an official shinigami huh?";
        if (this.page == 1) {
            text = "Here's a blade without a spirit, beat this hollow and we'll infuse it with a spirit.";
            IQuestData questData = QuestDataCapability.get(player);
            questData.addInProgressQuest(ModQuests.KILLHOLLOW);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            Item item = ModItems.ASAUCHI.get();
            PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(item)));
        }
        this.message = new SequencedString(text, 245, this.font.width(text) / 2, 800);
        TexturedIconButton acceptanceButton = new TexturedIconButton(acceptButtonTexture, posX + 180, posY + 57, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = 1;
            init();
        });
        TexturedIconButton declineButton = new TexturedIconButton(declineButtonTexture, posX + 120, posY + 57, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = -1;
            init();
        });
        if (this.page == 0) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }

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
