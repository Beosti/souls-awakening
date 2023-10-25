package com.yuanno.soulsawakening.data.entity;

public class EntityStatsBase implements IEntityStats {

    private String race = "";
    private String rank = "";
    private int hollowPoints;
    private int zanjutsuPoints;
    private double hakudaPoints;
    private int hohoPoints;

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
    public void setHollowPoints(int hollowPoints)
    {
        this.hollowPoints = hollowPoints;
    }

    @Override
    public void alterHollowPoints(int hollowPoints)
    {
        this.hollowPoints += hollowPoints;
    }

    @Override
    public int getHollowPoints()
    {
        return this.hollowPoints;
    }

    @Override
    public void setZanjutsuPoints(int zanjutsuPoints) {
        this.zanjutsuPoints = zanjutsuPoints;
    }

    @Override
    public void alterZanjutsuPoints(int zanjutsuPoints) {
        this.zanjutsuPoints += zanjutsuPoints;
    }

    @Override
    public int getZanjutsuPoints() {
        return this.zanjutsuPoints;
    }

    @Override
    public void setHakudaPoints(double hakudaPoints) {
        this.hakudaPoints = hakudaPoints;
    }

    @Override
    public void alterHakudaPoints(double hakudaPoints) {
        this.hakudaPoints += hakudaPoints;
    }

    @Override
    public double getHakudaPoints() {
        return this.hakudaPoints;
    }

    @Override
    public void setHohoPoints(int hohoPoints) {
        this.hohoPoints = hohoPoints;
    }

    @Override
    public void alterHohoPoints(int hohoPoints) {
        this.hohoPoints += hohoPoints;
    }

    @Override
    public int getHohoPoints() {
        return this.hohoPoints;
    }
}
