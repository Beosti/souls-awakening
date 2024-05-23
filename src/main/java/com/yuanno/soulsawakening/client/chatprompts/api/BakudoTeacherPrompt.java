package com.yuanno.soulsawakening.client.chatprompts.api;

import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestRewardPacket;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestStartPacket;
import com.yuanno.soulsawakening.networking.client.CSyncQuestDataPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public class BakudoTeacherPrompt extends ChatPrompt {

    private String acceptance1 = "To learn your first bakudo spell say: 'Ye thy stop moving Bakudo number 1 Sai', the incantation for the sai spell. You have to say that while looking at an entity. Do it 5 times so you learn this spell";
    private String congratulation1 = "Good job on learning the first bakudo! Don't forget that for bakudo spells if your target has more spiritual pressure or power than you (depending on the spell) the spell will fail. Come back for another spell.";
    private String acceptance2 = "Use the bakudo spell: sai 5 times on an entity that you put on a leash. It has to be put on a leash!";
    private String congratulation2 = "That's a stronger sealing technique than sai, immobilizes the target too instead of just making him unable to attack. You can always come back for more bakudo spells";
    private String acceptance3 = "For your next spell you'll have to protect yourself from physical attacks 10 times! You'll learn the useful spell seki";
    private String congratulation3 = "That's a great defensive bakudo spell, don't forget it only works with physical hits and has quite a large cooldown!";
    public void load()
    {
        this.addQuestWithChat(ModQuests.BAKUDO_UNLOCK, this::dialogue1BakudoTeacher);
        this.addQuestWithChat(ModQuests.HAINAWA_QUEST, this::dialogue2BakudoTeacher);
        this.addQuestWithChat(ModQuests.SEKI_QUEST, this::dialogue3BakudoTeacher);
        this.setOnClose(this::bakudoTeacherOnClose);
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
            text = congratulation1;
        if (this.chatPromptScreen.getPage() == -1)
            text = "It's alright, not everyone is made for the support role.";
        if (this.chatPromptScreen.getPage() == 1)
            text = acceptance1;
        this.chatPromptScreen.setText(text);
        if (text.equals("I'm the bakudo teacher, so I teach the bakudo sub-class of kido. What's bakudo? These are kido spells that are mostly supplementary, barriers, seals etc. Are you interested to learn?"))
            this.addAcceptanceDecline = true;
    }
    void dialogue2BakudoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "Now the path of bakudo has been opened, do you want to continue? The next one is a stronger type of sealing spell or 'Fu' called hainawa that uses a projectile";
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.HAINAWA_QUEST))
            text = "Use the spell sai 5 times on an entity that's on a leash. That's how you'll learn the bakudo spell hainawa";
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.HAINAWA_QUEST))
            text = congratulation2;
        if (this.chatPromptScreen.getPage() == -1)
            text = "The basics of bakudo are good, but it'd always be better if you learned more";
        if (this.chatPromptScreen.getPage() == 1)
            text = acceptance2;
        this.chatPromptScreen.setText(text);
        if (text.equals("Now the path of bakudo has been opened, do you want to continue? The next one is a stronger type of sealing spell or 'Fu' called hainawa that uses a projectile"))
            this.addAcceptanceDecline = true;
    }
    void dialogue3BakudoTeacher()
    {
        this.addAcceptanceDecline = false;
        String text = "";
        text = "The next spell I'd like to teach you is purely defensive this time. Are you interested in learning it?";
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.SEKI_QUEST))
            text = "You'll have to block a physical hit 10 times with a shield to learn this new spell!";
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.SEKI_QUEST))
            text = congratulation3;
        if (this.chatPromptScreen.getPage() == -1)
            text = "Protecting yourself ain't always good enough I suppose";
        if (this.chatPromptScreen.getPage() == 1)
            text = acceptance3;
        this.chatPromptScreen.setText(text);
        if (text.equals("The next spell I'd like to teach you is purely defensive this time. Are you interested in learning it?"))
            this.addAcceptanceDecline = true;
    }
    void bakudoTeacherOnClose()
    {
        if (this.chatPromptScreen.getText().equals(congratulation1)) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You have learned the spell: Sai Bakudo #1."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.BAKUDO_UNLOCK).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.BAKUDO_UNLOCK));
        }
        if (this.chatPromptScreen.getText().equals(acceptance1)) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You can now use the incantation for sai: 'Ye thy stop moving Bakudo number 1 Sai'."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.BAKUDO_UNLOCK);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.BAKUDO_UNLOCK));
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("This entity is now a teleport point, you can teleport back to it in your teleports menu. You need to be in the same dimension to teleport."), Util.NIL_UUID);
        }
        if (this.chatPromptScreen.getText().equals(congratulation2)) {
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.HAINAWA_QUEST).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.HAINAWA_QUEST));
        }
        if (this.chatPromptScreen.getText().equals(acceptance2)) {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.HAINAWA_QUEST);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
        }
        if (this.chatPromptScreen.getText().equals(acceptance3))
        {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.SEKI_QUEST);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
        }
        if (this.chatPromptScreen.getText().equals(congratulation3)) {
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.SEKI_QUEST).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.SEKI_QUEST));
        }
    }
}
