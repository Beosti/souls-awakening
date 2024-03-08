package com.yuanno.soulsawakening.data.quest;

import com.yuanno.soulsawakening.quests.Quest;

import java.util.ArrayList;

public class QuestDataBase implements IQuestData{

    private ArrayList<Quest> quests = new ArrayList<>();

    @Override
    public void addInProgressQuest(Quest quest) {
        quest.setInProgress(true);
        quests.add(quest);
    }

    @Override
    public void removeInProgressQuest(Quest quest) {
        quest.setInProgress(false);
        quests.add(quest);
    }

    @Override
    public boolean hasInProgressQuest(Quest quest) {
        for (Quest value : quests) {
            if (value.getTitle().equals(quest.getTitle()) && value.getIsInProgress())
                return true;
        }
        return false;
    }

    @Override
    public void addFinishedQuest(Quest quest) {

    }

    @Override
    public void removeFinishedQuest(Quest quest) {

    }

    @Override
    public boolean hasFinishedQuest(Quest quest) {
        return false;
    }
}
