package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.client.chatprompts.api.ChatPrompt;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.entities.npc.SoulNPCEntity;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.screens.TexturedIconButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {


    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/screens/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/decline_button.png");

    private PlayerEntity player;
    private IEntityStats entityStats;
    private IQuestData questData;
    private SequencedString message = new SequencedString("", 0, 0);
    private int page = 0;
    private String text = "";
    private Entity entity;
    TexturedIconButton acceptanceButton;
    TexturedIconButton declineButton;
    public ChatPromptScreen(Entity entity)
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = minecraft.player;
        this.entityStats = EntityStatsCapability.get(player);
        this.questData = QuestDataCapability.get(player);
        this.entity = entity;
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.minecraft.textureManager.bind(chatPrompt);
        this.blit(matrixStack, posX + 4, posY + 8, 0, 0, 256, 256);
        matrixStack.pushPose();
        matrixStack.scale(0.7f, 0.7f, 0.7f);
        this.message.render(matrixStack, (int) ((posX + 12) / 0.7), (int) ((posY + 223) / 0.7));
        matrixStack.popPose();
        super.render(matrixStack, x, y, f);
    }

    @Override
    public void init()
    {
        this.buttons.clear();
        this.children.clear();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        acceptanceButton = new TexturedIconButton(acceptButtonTexture, posX + 180, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = 1;
            init();
        });
        declineButton = new TexturedIconButton(declineButtonTexture, posX + 220, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = -1;
            init();
        });

        SoulNPCEntity soulNPCEntity = (SoulNPCEntity) entity;
        ChatPrompt chatPrompt = soulNPCEntity.getChatPrompt();
        chatPrompt.setChatPromptScreen(this);
        chatPrompt.load();
        for (Map.Entry<Quest, ChatPrompt.IChatPrompt> entry : chatPrompt.getChatPromptHashMap().entrySet()) {
            Quest quest = entry.getKey();
            ChatPrompt.IChatPrompt IChatPrompt = entry.getValue();
            if (questData.getIsInRotation(quest)) {
                System.out.println(quest);
                IChatPrompt.chat();
                break;
            }
        }
        this.message = new SequencedString(text, 345, this.font.width(text) / 2, 400);
        if (chatPrompt.getAcceptOrDecline()) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }
    }

    public static void open(Entity entity)
    {
        Minecraft.getInstance().setScreen(new ChatPromptScreen(entity));
    }

    public void onClose()
    {
        super.onClose();
        SoulNPCEntity soulNPCEntity = (SoulNPCEntity) entity;
        ChatPrompt chatPrompt = soulNPCEntity.getChatPrompt();
        chatPrompt.setChatPromptScreen(this);
        chatPrompt.load();
        chatPrompt.getOnClose().onClose();
    }


    public String getText()
    {
        return this.text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public int getPage()
    {
        return this.page;
    }
    public void setPage(int page)
    {
        this.page = page;
    }
    public IQuestData getQuestData()
    {
        return this.questData;
    }
    public IEntityStats getEntityStats()
    {
        return this.entityStats;
    }
    public PlayerEntity getPlayer()
    {
        return this.player;
    }

}
