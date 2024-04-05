package com.yuanno.soulsawakening.client.chatprompts.api;

import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestRewardPacket;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestStartPacket;
import com.yuanno.soulsawakening.networking.client.CSyncQuestDataPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public class BakudoTeacherPrompt extends ChatPrompt {

    public void load()
    {
        this.addQuest(ModQuests.BAKUDO_UNLOCK);
        this.addChatPrompts(this::dialogue1BakudoTeacher);
        this.addQuest(ModQuests.HAINAWA_QUEST);
        this.addChatPrompts(this::dialogue2BakudoTeacher);
        this.addQuest(ModQuests.SEKI_QUEST);
        this.addChatPrompts(this::dialogue3BakudoTeacher);
        this.setOnClose(this::kidoTeacherClose);
    }

    void dialogue1BakudoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "I'm the bakudo teacher, so I teach the bakudo sub-class of kido. What's bakudo? These are kido spells that are mostly supplementary, barriers, seals etc. Are you interested to learn?";
        if (!this.chatPromptScreen.getQuestData().hasFinishedQuest(ModQuests.KIDO_UNLOCK))
            text = "You should first learn kido or the basics at least... Go to the kido teacher to learn some of that!";
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.BAKUDO_UNLOCK))
            text = "The incantation is: 'Ye thy stop moving Bakudo number 1 Sai', you have to look at an enemy to make it work. You have to do that 5 times, forgot to tell you but if you use a bakudo spell against someone with more reishi it'll fail!";
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.BAKUDO_UNLOCK))
            text = "Good job on learning the first bakudo! Don't forget that for bakudo spells if your target has more spiritual pressure or power than you (depending on the spell) the spell will fail. Come back for another spell.";
        if (this.chatPromptScreen.getPage() == -1)
            text = "It's alright, not everyone is made for the support role.";
        if (this.chatPromptScreen.getPage() == 1)
            text = "To learn your first bakudo spell say: 'Ye thy stop moving Bakudo number 1 Sai', the incantation for the sai spell. You have to say that while looking at an entity. Do 5 10 times so you learn this spell";
        this.chatPromptScreen.setText(text);
        if (text.equals("I'm the bakudo teacher, so I teach the bakudo sub-class of kido. What's bakudo? These are kido spells that are mostly supplementary, barriers, seals etc. Are you interested to learn?"))
            this.addAcceptanceDecline = true;
    }
    void dialogue2BakudoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "Now the path of ";
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
    void dialogue3BakudoTeacher()
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
        if (this.chatPromptScreen.getText().equals("Good job on learning the first bakudo! Don't forget that for bakudo spells if your target has more spiritual pressure or power than you (depending on the spell) the spell will fail. Come back for another spell.")) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You have learned the spell: Sai Bakudo #1."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.BAKUDO_UNLOCK).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.BAKUDO_UNLOCK));
        }
        if (this.chatPromptScreen.getText().equals("The incantation is: 'Ye thy stop moving Bakudo number 1 Sai', you have to look at an enemy to make it work. You have to do that 5 times, forgot to tell you but if you use a bakudo spell against someone with more reishi it'll fail!")) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You can now use the incantation for sai: 'Ye thy stop moving Bakudo number 1 Sai'."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.BAKUDO_UNLOCK);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.BAKUDO_UNLOCK));
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
