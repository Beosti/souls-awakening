package com.yuanno.soulsawakening.client.chatprompts;

import com.yuanno.soulsawakening.client.chatprompts.api.ChatPrompt;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestRewardPacket;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestStartPacket;
import com.yuanno.soulsawakening.networking.client.CSyncQuestDataPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public class KidoTeacherPrompt extends ChatPrompt {

    public void load()
    {
        this.addQuestWithChat(ModQuests.KIDO_UNLOCK, this::dialogue1KidoTeacher);
        this.addQuestWithChat(ModQuests.BYAKURAI_QUEST, this::dialogue2KidoTeacher);
        this.addQuestWithChat(ModQuests.TSUZURI_QUEST, this::dialogue3KidoTeacher);
        this.setOnClose(this::kidoTeacherClose);
    }

    void dialogue1KidoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "I'm the kido and hado teacher, you want to learn the path of 'demon arts'?";
        if (!this.chatPromptScreen.getEntityStats().getRace().equals(ModValues.SHINIGAMI))
            text = "I only teach this stuff to shinigamis!";
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.KIDO_UNLOCK))
            text = "The incantation is: 'Push back, repel the vile knave! Hadou number 1 Sho', do that a few times!";
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.KIDO_UNLOCK))
            text = "You've truly learned this kido! I'm the hado instructor, the 'destructive' type of kido. But I'm sure the other kido teachers are down to teach you their path!";
        if (this.chatPromptScreen.getPage() == -1)
            text = "Some just like brawling into fights I guess";
        if (this.chatPromptScreen.getPage() == 1)
            text = "To learn your first kido type this in chat: 'Push back, repel the vile knave! Hadou number 1 Sho', it's an incantation that makes a kido-spell happen. Do that 10 times so you learn this spell";
        this.chatPromptScreen.setText(text);
        if (text.equals("I'm the kido and hado teacher, you want to learn the path of 'demon arts'?"))
            this.addAcceptanceDecline = true;
    }
    void dialogue2KidoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "Now you unlocked kido, do you want to advance in the path of hado? It's the 'destructive' path, I have something else to teach you";
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.BYAKURAI_QUEST))
            text = "Go use sho multiple times and use the byakurai incantation: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai'!";
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.BYAKURAI_QUEST))
            text = "Good job on learning this useful kido! I always got more to teach you.";
        if (this.chatPromptScreen.getPage() == -1)
            text = "Some people are afraid of their own potential of destruction...";
        if (this.chatPromptScreen.getPage() == 1)
            text = "Use this incantation 5 times: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai' and sho 10 times";
        this.chatPromptScreen.setText(text);
        if (text.equals("Now you unlocked kido, do you want to advance in the path of hado? It's the 'destructive' path, I have something else to teach you"))
            this.addAcceptanceDecline = true;
    }
    void dialogue3KidoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "I have another one that I can teach you, it's more of a 'useful' one than a destructive one. Are you interested?";
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.TSUZURI_QUEST))
            text = "To learn how to channel the lightning, go kill entities with a conductor!";
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.TSUZURI_QUEST))
            text = "Good job on learning this useful kido! Feel free to come by again for something else.";
        if (this.chatPromptScreen.getPage() == -1)
            text = "Some people really just want to explode things...";
        if (this.chatPromptScreen.getPage() == 1)
            text = "Go kill multiple entities with a conductor-type item. Items that can pass electricity to train on your next hado!";
        if (text.equals("I have another one that I can teach you, it's more of a 'useful' one than a destructive one. Are you interested?"))
            this.addAcceptanceDecline = true;
    }
    void kidoTeacherClose()
    {
        if (this.chatPromptScreen.getText().equals("You've truly learned this kido! I'm the hado instructor, the 'destructive' type of kido. But I'm sure the other kido teachers are down to teach you their path!")) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You can now scroll to known kido-spells using control+mouse wheel and press 'g' to use the spell."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.KIDO_UNLOCK).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KIDO_UNLOCK));
        }
        if (this.chatPromptScreen.getText().equals("To learn your first kido type this in chat: 'Push back, repel the vile knave! Hadou number 1 Sho', it's an incantation that makes a kido-spell happen. Do that 10 times so you learn this spell")) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You can now use the incantation for sho: 'Push back, repel the vile knave! Hadou number 1 Sho'."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.KIDO_UNLOCK);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.KIDO_UNLOCK));
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("This entity is now a teleport point, you can teleport back to it in your teleports menu. You need to be in the same dimension to teleport."), Util.NIL_UUID);
        }
        if (this.chatPromptScreen.getText().equals("Good job on learning this useful kido! I always got more to teach you.")) {
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.BYAKURAI_QUEST).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.BYAKURAI_QUEST));
        }
        if (this.chatPromptScreen.getText().equals("Use this incantation 5 times: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai' and sho 10 times")) {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.BYAKURAI_QUEST);
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.BYAKURAI_QUEST));
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You can now use the incantation for byakurai: 'Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai'."), Util.NIL_UUID);

        }
        if (this.chatPromptScreen.getText().equals("Go kill multiple entities with a conductor-type item. Items that can pass electricity to train on your next hado!"))
        {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.TSUZURI_QUEST);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
        }
        if (this.chatPromptScreen.getText().equals("Good job on learning this useful kido! Feel free to come by again for something else.")) {
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.TSUZURI_QUEST).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.TSUZURI_QUEST));
        }
    }
}
