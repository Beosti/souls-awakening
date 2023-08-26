package com.yuanno.soulsawakening.data.entity;

public class EntityStatsBase implements IEntityStats {

    private String race = "";

    @Override
    public void setRace(String race)
    {
        this.race = race;
    }

    @Override
    public String getRace()
    {
        return this.race;
    }

    @Override
    public boolean hasRace()
    {
        return !race.isEmpty();
    }
}
