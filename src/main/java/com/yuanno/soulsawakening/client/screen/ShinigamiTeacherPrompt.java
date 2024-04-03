package com.yuanno.soulsawakening.client.screen;

import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.screens.TexturedIconButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.io.Serializable;

public class ShinigamiTeacherPrompt extends ChatPrompt {

    ChatPromptScreen chatPromptScreen;
    boolean addAcceptanceDecline = false;
    public ShinigamiTeacherPrompt(ChatPromptScreen chatPromptScreen)
    {
        this.addQuest(ModQuests.KILLHOLLOW);
        this.addQuest(ModQuests.RESCUE_PLUSES);
        this.addQuest(ModQuests.KILL_SPECIFIC_HOLLOW);
        this.chatPromptScreen = chatPromptScreen;
    }
    void dialogue1ShinigamiTeacher(int posX, int posY)
    {
        chatPromptScreen.setText("So you want to become a shinigami huh?");
        if (chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.KILLHOLLOW))
            chatPromptScreen.setText("You find hollows a bit everywhere in the overworld and a ton in hueco mundo. Go kill one!");
        if (chatPromptScreen.getQuestData().isQuestComplete(ModQuests.KILLHOLLOW))
            chatPromptScreen.setText("Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff");
        if (chatPromptScreen.getPage() == -1)
            chatPromptScreen.setText("I guess not everyone is cut out for this job");
        if (chatPromptScreen.getPage() == 1)
            chatPromptScreen.setText("Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.");
        if (!chatPromptScreen.getEntityStats().getRace().equals(ModValues.SPIRIT))
            chatPromptScreen.setText("How did a non-spirit come here, you should be brought wherever you came from!");
        if (chatPromptScreen.getText().equals("So you want to become a shinigami huh?"))
            this.addAcceptanceDecline = true;
    }
    void dialogue2ShinigamiTeacher(int posX, int posY)
    {
        chatPromptScreen.setText("Now you're a shinigami, I do have some missions for you. I got one where you have to rescue some pluses, are you interested?");
        if (chatPromptScreen.getPage() == 0)
            this.addAcceptanceDecline = true;
        if (chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.RESCUE_PLUSES))
            chatPromptScreen.setText("There's pluses all over in the overworld, they're just waiting to be rescued. Go help at least 5.");
        if (chatPromptScreen.getQuestData().isQuestComplete(ModQuests.RESCUE_PLUSES))
            chatPromptScreen.setText("Great work as a shinigami to help those pluses. You definitely do deserve to be ranked in the gotei 13 now.");
        if (chatPromptScreen.getPage() == -1)
            chatPromptScreen.setText("Rescue missions aren't for everyone I suppose");
        if (chatPromptScreen.getPage() == 1)
            chatPromptScreen.setText("Great! You have to go to the overworld and rescue 5 pluses, they're lost spirits. Just right click them with your zanpakuto and they'll be saved!");
        if (!chatPromptScreen.getEntityStats().getRace().equals(ModValues.SHINIGAMI))
            chatPromptScreen.setText("How did a non-spirit come here, you should be brought wherever you came from!");
    }
    void dialogue3ShinigamiTeacher(int posX, int posY)
    {
        chatPromptScreen.setText("I got another mission for you, now you're officially part of the gotei 13 you can also be paid. It's about a specific hollow.");
        if (chatPromptScreen.getPage() == 0)
            this.addAcceptanceDecline = true;
        if (chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.RESCUE_PLUSES))
            chatPromptScreen.setText("You have to go and kill a beast hollow, it walks on 4 feet and looks like a tiger.");
        if (chatPromptScreen.getQuestData().isQuestComplete(ModQuests.RESCUE_PLUSES))
            chatPromptScreen.setText("There have some money, thanks for handling that hollow. I am sure it took you some time to hunt it down and track it's location.");
        if (chatPromptScreen.getPage() == -1)
            chatPromptScreen.setText("Tracking down missions ain't for everyone I suppose");
        if (chatPromptScreen.getPage() == 1)
            chatPromptScreen.setText("Amazing, you'll have to find the 'beast' hollow in the overworld. It's a hollow on 4 feet that walks around and is quite fast. It looks like a tiger. Kill it and come back for your reward!");
        if (!chatPromptScreen.getEntityStats().getRace().equals(ModValues.SHINIGAMI))
            chatPromptScreen.setText("How did a non-spirit come here, you should be brought wherever you came from!");
    }
}
