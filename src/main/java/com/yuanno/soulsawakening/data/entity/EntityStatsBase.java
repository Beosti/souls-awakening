package com.yuanno.soulsawakening.data.entity;

import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;

public class EntityStatsBase implements IEntityStats {

    private String race = "";
    private String rank = "";
    private double reiatsuPoints;
    private ShinigamiStats shinigamiStats;
    private HollowStats hollowStats;

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

    @Override
    public void setRank(String rank)
    {
        this.rank = rank;
    }

    @Override
    public String getRank()
    {
        return this.rank;
    }

    @Override
    public boolean hasRank()
    {
        return !rank.isEmpty();
    }

    @Override
    public void setShinigamiStats(ShinigamiStats shinigamiStats) {
        this.shinigamiStats = shinigamiStats;
    }

    @Override
    public boolean hasShinigamiStats() {
        return shinigamiStats != null;
    }

    @Override
    public ShinigamiStats getShinigamiStats() {
        return this.shinigamiStats;
    }

    @Override
    public void setHollowStats(HollowStats hollowStats) {
        this.hollowStats = hollowStats;
    }

    @Override
    public boolean hasHollowStats() {
        return hollowStats != null;
    }

    @Override
    public HollowStats getHollowStats() {
        return this.hollowStats;
    }

    @Override
    public void setReiatsuPoints(double reiatsuPoints)
    {
        this.reiatsuPoints = reiatsuPoints;
    }

    @Override
    public void alterReiatsuPoints(double reiatsuPoints)
    {
        this.reiatsuPoints += reiatsuPoints;
    }

    @Override
    public double getReiatsuPoints()
    {
        return this.reiatsuPoints;
    }
}
