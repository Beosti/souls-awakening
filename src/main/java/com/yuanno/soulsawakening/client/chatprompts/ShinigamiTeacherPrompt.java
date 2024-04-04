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


public class ShinigamiTeacherPrompt extends ChatPrompt {

    public void load()
    {
        this.addQuest(ModQuests.KILLHOLLOW);
        this.addChatPrompts(this::dialogue1ShinigamiTeacher);
        this.addQuest(ModQuests.RESCUE_PLUSES);
        this.addChatPrompts(this::dialogue2ShinigamiTeacher);
        this.addQuest(ModQuests.KILL_SPECIFIC_HOLLOW);
        this.addChatPrompts(this::dialogue3ShinigamiTeacher);
        this.setOnClose(this::shinigamiTeacherOnClose);
    }
    void dialogue1ShinigamiTeacher()
    {
        this.addAcceptanceDecline = false;
        this.chatPromptScreen.setText("So you want to become a shinigami huh?");
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.KILLHOLLOW))
            this.chatPromptScreen.setText("You find hollows a bit everywhere in the overworld and a ton in hueco mundo. Go kill one!");
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.KILLHOLLOW))
            this.chatPromptScreen.setText("Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff");
        if (this.chatPromptScreen.getPage() == -1)
            this.chatPromptScreen.setText("I guess not everyone is cut out for this job");
        if (this.chatPromptScreen.getPage() == 1)
            this.chatPromptScreen.setText("Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.");
        if (!this.chatPromptScreen.getEntityStats().getRace().equals(ModValues.SPIRIT))
            this.chatPromptScreen.setText("How did a non-spirit come here, you should be brought wherever you came from!");
        if (this.chatPromptScreen.getText().equals("So you want to become a shinigami huh?"))
            this.addAcceptanceDecline = true;
    }
    void dialogue2ShinigamiTeacher()
    {
        this.addAcceptanceDecline = false;
        this.chatPromptScreen.setText("Now you're a shinigami, I do have some missions for you. I got one where you have to rescue some pluses, are you interested?");
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.RESCUE_PLUSES))
            this.chatPromptScreen.setText("There's pluses all over in the overworld, they're just waiting to be rescued. Go help at least 5.");
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.RESCUE_PLUSES))
            this.chatPromptScreen.setText("Great work as a shinigami to help those pluses. You definitely do deserve to be ranked in the gotei 13 now.");
        if (this.chatPromptScreen.getPage() == -1)
            this.chatPromptScreen.setText("Rescue missions aren't for everyone I suppose");
        if (this.chatPromptScreen.getPage() == 1)
            this.chatPromptScreen.setText("Great! You have to go to the overworld and rescue 5 pluses, they're lost spirits. Just right click them with your zanpakuto and they'll be saved!");
        if (!this.chatPromptScreen.getEntityStats().getRace().equals(ModValues.SHINIGAMI))
            this.chatPromptScreen.setText("You shouldn't be here...");
        if (this.chatPromptScreen.getText().equals("Now you're a shinigami, I do have some missions for you. I got one where you have to rescue some pluses, are you interested?"))
            this.addAcceptanceDecline = true;
    }
    void dialogue3ShinigamiTeacher()
    {
        this.addAcceptanceDecline = false;
        this.chatPromptScreen.setText("I got another mission for you, now you're officially part of the gotei 13 you can also be paid. It's about a specific hollow.");
        if (this.chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.RESCUE_PLUSES))
            this.chatPromptScreen.setText("You have to go and kill a beast hollow, it walks on 4 feet and looks like a tiger.");
        if (this.chatPromptScreen.getQuestData().isQuestComplete(ModQuests.RESCUE_PLUSES))
            this.chatPromptScreen.setText("There have some money, thanks for handling that hollow. I am sure it took you some time to hunt it down and track it's location.");
        if (this.chatPromptScreen.getPage() == -1)
            this.chatPromptScreen.setText("Tracking down missions ain't for everyone I suppose");
        if (this.chatPromptScreen.getPage() == 1)
            this.chatPromptScreen.setText("Amazing, you'll have to find the 'beast' hollow in the overworld. It's a hollow on 4 feet that walks around and is quite fast. It looks like a tiger. Kill it and come back for your reward!");
        if (!this.chatPromptScreen.getEntityStats().getRace().equals(ModValues.SHINIGAMI))
            this.chatPromptScreen.setText("Where did you even come from?");
        if (this.chatPromptScreen.getText().equals("I got another mission for you, now you're officially part of the gotei 13 you can also be paid. It's about a specific hollow."))
            this.addAcceptanceDecline = true;
    }

    void shinigamiTeacherOnClose()
    {
        if (this.chatPromptScreen.getText().equals("Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff")) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You can now increase your stats in the player overview screen and learn from other teachers."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.KILLHOLLOW).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KILLHOLLOW));
        }
        if (this.chatPromptScreen.getText().equals("Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.")) {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.KILLHOLLOW);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.KILLHOLLOW));
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("This entity is now a teleport point, you can teleport back to it in your teleports menu. You need to be in the same dimension to teleport."), Util.NIL_UUID);
        }
        if (this.chatPromptScreen.getText().equals("Great work as a shinigami to help those pluses. You definitely do deserve to be ranked in the gotei 13 now.")) {
            this.chatPromptScreen.getPlayer().sendMessage(new TranslationTextComponent("You have received the rank of non-officer official part of the gotei 13."), Util.NIL_UUID);
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.RESCUE_PLUSES).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.RESCUE_PLUSES));
        }
        if (this.chatPromptScreen.getText().equals("Great! You have to go to the overworld and rescue 5 pluses, they're lost spirits. Just right click them with your zanpakuto and they'll be saved!")) {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.RESCUE_PLUSES);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
        }
        if (this.chatPromptScreen.getText().equals("Amazing, you'll have to find the 'beast' hollow in the overworld. It's a hollow on 4 feet that walks around and is quite fast. It looks like a tiger. Kill it and come back for your reward!"))
        {
            this.chatPromptScreen.getQuestData().addInProgressQuest(ModQuests.KILL_SPECIFIC_HOLLOW);
            PacketHandler.sendToServer(new CSyncQuestDataPacket(this.chatPromptScreen.getQuestData()));
        }
        if (this.chatPromptScreen.getText().equals("There have some money, thanks for handling that hollow. I am sure it took you some time to hunt it down and track it's location.")) {
            this.chatPromptScreen.getQuestData().getQuest(ModQuests.KILL_SPECIFIC_HOLLOW).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KILL_SPECIFIC_HOLLOW));
        }
    }
}
