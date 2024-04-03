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
        if (chatPromptScreen.getEntityStats().getRace().equals(ModValues.SHINIGAMI))
            chatPromptScreen.setText("You're already a shinigami. Scram!");
        if (chatPromptScreen.getQuestData().hasInProgressQuest(ModQuests.KILLHOLLOW))
            chatPromptScreen.setText("You find hollows a bit everywhere in the overworld and a ton in hueco mundo. Go kill one!");
        if (chatPromptScreen.getQuestData().isQuestComplete(ModQuests.KILLHOLLOW))
        {
            chatPromptScreen.setText("Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff");
        }
        if (chatPromptScreen.getPage() == -1)
            chatPromptScreen.setText("I guess not everyone is cut out for this job");
        if (chatPromptScreen.getPage() == 1)
        {
            chatPromptScreen.setText("Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.");
        }
        if (!chatPromptScreen.getEntityStats().getRace().equals(ModValues.SPIRIT))
            chatPromptScreen.setText("How did a non-spirit come here, you should be brought wherever you came from!");
    }
}
