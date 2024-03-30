package com.yuanno.soulsawakening.data.quest;

import com.yuanno.soulsawakening.quests.Quest;

import java.util.ArrayList;

public interface IQuestData {


    ArrayList<Quest> getQuests();

    ArrayList<String> getQuestTitle();

    void clearQuests();
    void addQuest(Quest quest);

    void addInProgressQuest(Quest quest);
    void removeInProgressQuest(Quest quest);
    boolean hasInProgressQuest(Quest quest);

    void addFinishedQuest(Quest quest);
    void removeFinishedQuest(Quest quest);
    boolean hasFinishedQuest(Quest quest);

    boolean isQuestComplete(Quest quest);

    Quest getQuest(Quest quest);
}
