package com.yuanno.soulsawakening.quests;

import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class Quest extends ForgeRegistryEntry<Quest> {

    private List<Objective> objectives = new ArrayList<>();

    private String title = "";
    private String description = "";
    private String rank = "";

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
}
