package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.api.challenges.ChallengeReward;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class Quest extends ForgeRegistryEntry<Quest> {

    private List<Objective> objectives = new ArrayList<>();

    private String title = "";
    private String description = "";
    private String rank = "";
    private boolean inProgress = false;
    private QuestReward questReward;
    private QuestStart questStart;

    public Quest()
    {

    }

    public void addObjectives(Objective... objectives)
    {
        for(Objective obj : objectives)
            this.addObjective(obj);
    }

    public void addObjective(Objective objective)
    {
        if(!this.objectives.contains(objective))
            this.objectives.add(objective);
    }

    public List<Objective> getObjectives()
    {
        return this.objectives;
    }

    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getRank()
    {
        return this.rank;
    }
    public void setRank(String rank)
    {
        this.rank = rank;
    }

    public void setInProgress(boolean flag)
    {
        this.inProgress = flag;
    }
    public boolean getIsInProgress()
    {
        return this.inProgress;
    }
    public void setQuestReward(QuestReward questReward)
    {
        this.questReward = questReward;
    }
    public QuestReward getQuestReward()
    {
        return this.questReward;
    }
    public void setQuestStart(QuestStart questStart)
    {
        this.questStart = questStart;
    }
    public QuestStart getQuestStart()
    {
        return this.questStart;
    }

    public CompoundNBT save()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("title", this.title);
        nbt.putString("description", this.description);
        nbt.putString("rank", this.rank);
        nbt.putBoolean("inProgress", this.inProgress);
        ListNBT objectivesData = new ListNBT();
        for (Objective objective : this.getObjectives())
        {
            objectivesData.add(objective.save());
        }
        nbt.put("objectives", objectivesData);

        return nbt;
    }

    public void load(CompoundNBT nbt)
    {
        /*
        this.title = nbt.getString("title");
        this.description = nbt.getString("description");
        this.rank = nbt.getString("rank");
        this.inProgress = nbt.getBoolean("inProgress");

         */
        ListNBT objectivesData = nbt.getList("objectives", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < objectivesData.size(); i++)
        {
            CompoundNBT questData = objectivesData.getCompound(i);
            this.getObjectives().get(i).load(questData);
        }
    }
}
