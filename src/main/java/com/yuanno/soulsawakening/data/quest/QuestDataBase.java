package com.yuanno.soulsawakening.data.quest;

import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;

import java.util.ArrayList;

public class QuestDataBase implements IQuestData {

    private ArrayList<Quest> quests = new ArrayList<>();

    @Override
    public ArrayList<Quest> getQuests() {
        return quests;
    }

    /**
     * This is more a 'failsafe' than anything, checking if it contains the string of the title is better because the id doesn't match
     * @return all the titles of the quests from the player
     */
    @Override
    public ArrayList<String> getQuestTitle()
    {
        ArrayList<String> titles = new ArrayList<>();
        for (Quest quest : this.quests) {
            titles.add(quest.getTitle());
        }
        return titles;
    }

    @Override
    public boolean getIsInRotation(Quest quest)
    {
        return getQuest(quest) == null || getQuest(quest).getIsInProgress();
    }

    @Override
    public void clearQuests() {
        this.quests.clear();
    }

    @Override
    public void addQuest(Quest quest) {
        this.quests.add(quest);
    }

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
        quest.setInProgress(true);
        quests.add(quest);
    }

    @Override
    public void removeFinishedQuest(Quest quest) {
        quest.setInProgress(false);
        quests.remove(quest);
    }

    @Override
    public boolean hasFinishedQuest(Quest quest) {
        for (Quest value : quests) {
            if (value.getTitle().equals(quest.getTitle()) && !value.getIsInProgress())
                return true;
        }
        return false;
    }

    @Override
    public boolean isQuestComplete(Quest quest) {
        for (int i = 0; i < quest.getObjectives().size(); i++)
        {
            Objective objective = quest.getObjectives().get(i);
            if (objective.progress >= objective.getMaxProgress())
                continue;
            else
                return false;
        }
        return true;
    }

    @Override
    public Quest getQuest(Quest quest)
    {
        for (int i = 0; i < this.quests.size(); i++)
        {
            if (quests.get(i).equals(quest))
                return quest;
        }
        return null;
    }
}
