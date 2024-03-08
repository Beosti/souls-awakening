package com.yuanno.soulsawakening.data.quest;

import com.yuanno.soulsawakening.quests.Quest;

import java.util.ArrayList;

public interface IQuestData {


    ArrayList<Quest> getQuests();
    void clearQuests();

    void addInProgressQuest(Quest quest);
    void removeInProgressQuest(Quest quest);
    boolean hasInProgressQuest(Quest quest);

    void addFinishedQuest(Quest quest);
    void removeFinishedQuest(Quest quest);
    boolean hasFinishedQuest(Quest quest);
}
