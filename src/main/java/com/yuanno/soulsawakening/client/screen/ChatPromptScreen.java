package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.entities.npc.KidoTeacherEntity;
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
        if (entity instanceof ShinigamiTeacherEntity)
            shinigamiTeacherInit(posX, posY);
        if (this.entity instanceof KidoTeacherEntity)
            kidoTeacherInit(posX, posY);

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
        if (this.entity instanceof KidoTeacherEntity)
            kidoTeacherOnClose();
    }
    void kidoTeacherInit(int posX, int posY)
    {
        if (questData.getIsInRotation(ModQuests.KIDO_UNLOCK))
            dialogue1KidoTeacher(posX, posY);
        else if (questData.getIsInRotation(ModQuests.BYAKURAI_QUEST))
            dialogue2KidoTeacher(posX, posY);
        else if (questData.getIsInRotation(ModQuests.TSUZURI_QUEST))
            dialogue3KidoTeacher(posX, posY);
    }
    void dialogue1KidoTeacher(int posX, int posY)
    {
        text = "I'm the kido and hado teacher, you want to learn the path of 'demon arts'?";
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI))
            text = "I only teach this stuff to shinigamis!";
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.KIDO_UNLOCK))
            text = "The incantation is: 'Push back, repel the vile knave! Hadou number 1 Sho', do that a few times!";
        if (QuestDataCapability.get(player).isQuestComplete(ModQuests.KIDO_UNLOCK))
        {
            text = "You've truly learned this kido! I'm the hado instructor, the 'destructive' type of kido. But I'm sure the other kido teachers are down to teach you their path!";
        }
        if (this.page == -1)
            text = "Some just like brawling into fights I guess";
        if (this.page == 1)
        {
            text = "To learn your first kido type this in chat: 'Push back, repel the vile knave! Hadou number 1 Sho', it's an incantation that makes a kido-spell happen. Do that 10 times so you learn this spell";
        }
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
        if (text.equals("I'm the kido and hado teacher, you want to learn the path of 'demon arts'?")) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }
    }
    void dialogue2KidoTeacher(int posX, int posY)
    {
        text = "Now you unlocked kido, do you want to advance in the path of hado? It's the 'destructive' path, I have something else to teach you";
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.BYAKURAI_QUEST))
            text = "Go use sho multiple times and use the byakurai incantation: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai'!";
        if (QuestDataCapability.get(player).isQuestComplete(ModQuests.BYAKURAI_QUEST))
        {
            text = "Good job on learning this useful kido! I always got more to teach you.";
        }
        if (this.page == -1)
            text = "Some people are afraid of their own potential of destruction...";
        if (this.page == 1)
        {
            text = "Use this incantation 5 times: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai' and sho 10 times";
        }
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
        if (text.equals("Now you unlocked kido, do you want to advance in the path of hado? It's the 'destructive' path, I have something else to teach you")) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }
    }
    void dialogue3KidoTeacher(int posX, int posY)
    {
        text = "I have another one that I can teach you, it's more of a 'useful' one than a destructive one. Are you interested?";
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.TSUZURI_QUEST))
            text = "To learn how to channel the lightning, go kill entities with a conductor!";
        if (QuestDataCapability.get(player).isQuestComplete(ModQuests.TSUZURI_QUEST))
        {
            text = "Good job on learning this useful kido! Feel free to come by again for something else.";
        }
        if (this.page == -1)
            text = "Some people really just want to explode things...";
        if (this.page == 1)
        {
            text = "Go kill multiple entities with a conductor-type item. Items that can pass electricity to train on your next hado!";
        }
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
        if (text.equals("I have another one that I can teach you, it's more of a 'useful' one than a destructive one. Are you interested?")) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }
    }

    void kidoTeacherOnClose()
    {
        if (this.text.equals("You've truly learned this kido! I'm the hado instructor, the 'destructive' type of kido. But I'm sure the other kido teachers are down to teach you their path!")) {
            player.sendMessage(new TranslationTextComponent("You can now scroll to known kido-spells using control+mouse wheel and press 'g' to use the spell."), Util.NIL_UUID);
            questData.getQuest(ModQuests.KIDO_UNLOCK).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KIDO_UNLOCK));
        }
        if (this.text.equals("To learn your first kido type this in chat: 'Push back, repel the vile knave! Hadou number 1 Sho', it's an incantation that makes a kido-spell happen. Do that 10 times so you learn this spell")) {
            player.sendMessage(new TranslationTextComponent("You can now use the incantation for sho: 'Push back, repel the vile knave! Hadou number 1 Sho'."), Util.NIL_UUID);
            this.questData.addInProgressQuest(ModQuests.KIDO_UNLOCK);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.KIDO_UNLOCK));
            player.sendMessage(new TranslationTextComponent("This entity is now a teleport point, you can teleport back to it in your teleports menu. You need to be in the same dimension to teleport."), Util.NIL_UUID);
        }
        if (this.text.equals("Good job on learning this useful kido! I always got more to teach you.")) {
            questData.getQuest(ModQuests.BYAKURAI_QUEST).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.BYAKURAI_QUEST));
        }
        if (this.text.equals("Use this incantation 5 times: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai' and sho 10 times")) {
            this.questData.addInProgressQuest(ModQuests.BYAKURAI_QUEST);
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.BYAKURAI_QUEST));
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            player.sendMessage(new TranslationTextComponent("You can now use the incantation for byakurai: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai'."), Util.NIL_UUID);

        }
        if (this.text.equals("Go kill multiple entities with a conductor-type item. Items that can pass electricity to train on your next hado!"))
        {
            this.questData.addInProgressQuest(ModQuests.TSUZURI_QUEST);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
        }
        if (this.text.equals("Good job on learning this useful kido! Feel free to come by again for something else.")) {
            questData.getQuest(ModQuests.TSUZURI_QUEST).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.TSUZURI_QUEST));
        }
    }

    void shinigamiTeacherInit(int posX, int posY)
    {
        ShinigamiTeacherPrompt shinigamiTeacherPrompt = new ShinigamiTeacherPrompt(this);
        if (questData.getIsInRotation(shinigamiTeacherPrompt.getQuests().get(0))) {
            shinigamiTeacherPrompt.dialogue1ShinigamiTeacher(posX, posY);
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
        else if (questData.getIsInRotation(shinigamiTeacherPrompt.getQuests().get(1))) {
            shinigamiTeacherPrompt.dialogue2ShinigamiTeacher(posX, posY);
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
        else if (questData.getIsInRotation(shinigamiTeacherPrompt.getQuests().get(2))) {
            shinigamiTeacherPrompt.dialogue3ShinigamiTeacher(posX, posY);
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
            if (text.equals("I got another mission for you, now you're officially part of the gotei 13 you can also be paid. It's about a specific hollow.")) {
                this.addButton(acceptanceButton);
                this.addButton(declineButton);
            }
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
        if (this.text.equals("Amazing, you'll have to find the 'beast' hollow in the overworld. It's a hollow on 4 feet that walks around and is quite fast. It looks like a tiger. Kill it and come back for your reward!"))
        {
            this.questData.addInProgressQuest(ModQuests.KILL_SPECIFIC_HOLLOW);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
        }
        if (this.text.equals("There have some money, thanks for handling that hollow. I am sure it took you some time to hunt it down and track it's location.")) {
            questData.getQuest(ModQuests.KILL_SPECIFIC_HOLLOW).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KILL_SPECIFIC_HOLLOW));
        }
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

}
