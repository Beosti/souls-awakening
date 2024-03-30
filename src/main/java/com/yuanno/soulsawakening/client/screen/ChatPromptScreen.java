package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.entities.npc.ShinigamiTeacherEntity;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
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
    public void init()
    {
        this.buttons.clear();
        this.children.clear();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        if (entity instanceof ShinigamiTeacherEntity)
            shinigamiTeacherInit(posX, posY);
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

    public static void open(Entity entity)
    {
        Minecraft.getInstance().setScreen(new ChatPromptScreen(entity));
    }

    public void onClose()
    {
        super.onClose();
        if (this.entity instanceof ShinigamiTeacherEntity)
            shinigamiTeacherOnClose();
    }

    void shinigamiTeacherInit(int posX, int posY)
    {
        if (questData.getQuest(ModQuests.KILLHOLLOW) == null || questData.getQuest(ModQuests.KILLHOLLOW).getIsInProgress())
            dialogue1ShinigamiTeacher(posX, posY);
        else if (questData.getQuest(ModQuests.RESCUE_PLUSES) == null || questData.getQuest(ModQuests.RESCUE_PLUSES).getIsInProgress())
            dialogue2ShinigamiTeacher(posX, posY);
    }
    void dialogue1ShinigamiTeacher(int posX, int posY)
    {
        text = "So you want to become a shinigami huh?";
        if (entityStats.getRace().equals(ModValues.SHINIGAMI))
            text = "You're already a shinigami. Scram!";
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.KILLHOLLOW))
            text = "You find hollows a bit everywhere in the overworld and a ton in hueco mundo. Go kill one!";
        if (QuestDataCapability.get(player).isQuestComplete(ModQuests.KILLHOLLOW))
        {
            text = "Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff";
        }
        if (this.page == -1)
            text = "I guess not everyone is cut out for this job";
        if (this.page == 1)
        {
            text = "Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.";
        }
        if (!entityStats.getRace().equals(ModValues.SPIRIT))
            text = "How did a non-spirit come here, you should be brought wherever you came from!";
        this.message = new SequencedString(text, 345, this.font.width(text) / 2, 800);
        TexturedIconButton acceptanceButton = new TexturedIconButton(acceptButtonTexture, posX + 180, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = 1;
            init();
        });
        TexturedIconButton declineButton = new TexturedIconButton(declineButtonTexture, posX + 220, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = -1;
            init();
        });
        if (text.equals("So you want to become a shinigami huh?")) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }
    }
    void dialogue2ShinigamiTeacher(int posX, int posY)
    {
        text = "Now you're a shinigami, I do have some missions for you. I got one where you have to rescue some pluses, are you interested?";
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.RESCUE_PLUSES))
            text = "There's pluses all over in the overworld, they're just waiting to be rescued. Go help at least 5.";
        if (QuestDataCapability.get(player).isQuestComplete(ModQuests.RESCUE_PLUSES))
        {
            text = "Great work as a shinigami to help those pluses. You definitely do deserve to be ranked in the gotei 13 now.";
        }
        if (this.page == -1)
            text = "Rescue missions aren't for everyone I suppose";
        if (this.page == 1)
        {
            text = "Great! You have to go to the overworld and rescue 5 pluses, they're lost spirits. Just right click them with your zanpakuto and they'll be saved!";
        }
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI))
            text = "How did a non-spirit come here, you should be brought wherever you came from!";
        this.message = new SequencedString(text, 345, this.font.width(text) / 2, 800);
        TexturedIconButton acceptanceButton = new TexturedIconButton(acceptButtonTexture, posX + 180, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = 1;
            init();
        });
        TexturedIconButton declineButton = new TexturedIconButton(declineButtonTexture, posX + 220, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = -1;
            init();
        });
        if (text.equals("Now you're a shinigami, I do have some missions for you. I got one where you have to rescue some pluses, are you interested?")) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }
    }
    void shinigamiTeacherOnClose()
    {
        if (this.text.equals("Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff")) {
            player.sendMessage(new TranslationTextComponent("You can now increase your stats in the player overview screen and learn from other teachers."), Util.NIL_UUID);
            questData.getQuest(ModQuests.KILLHOLLOW).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KILLHOLLOW));
        }
        if (this.text.equals("Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.")) {
            this.questData.addInProgressQuest(ModQuests.KILLHOLLOW);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.KILLHOLLOW));
            player.sendMessage(new TranslationTextComponent("This entity is now a teleport point, you can teleport back to it in your teleports menu. You need to be in the same dimension to teleport."), Util.NIL_UUID);
        }
        if (this.text.equals("Great work as a shinigami to help those pluses. You definitely do deserve to be ranked in the gotei 13 now.")) {
            player.sendMessage(new TranslationTextComponent("You have received the rank of non-officer official part of the gotei 13."), Util.NIL_UUID);
            questData.getQuest(ModQuests.RESCUE_PLUSES).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.RESCUE_PLUSES));
        }
        if (this.text.equals("Great! You have to go to the overworld and rescue 5 pluses, they're lost spirits. Just right click them with your zanpakuto and they'll be saved!")) {
            this.questData.addInProgressQuest(ModQuests.RESCUE_PLUSES);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
        }
    }
}
