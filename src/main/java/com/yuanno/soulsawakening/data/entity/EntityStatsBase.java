package com.yuanno.soulsawakening.data.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityStatsBase implements IEntityStats {

    private String race = "";
    private String rank = "";
    private double hollowPoints;
    private int classLevel;
    private int classPoints;
    private int classExperience;
    private double zanjutsuPoints;
    private double hakudaPoints;
    private double hohoPoints;
    private double reiatsuPoints;
    public List<Double> availableStats = new ArrayList<>();

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
    public void setHollowPoints(double hollowPoints)
    {
        this.hollowPoints = hollowPoints;
    }

    @Override
    public void alterHollowPoints(double hollowPoints)
    {
        this.hollowPoints += hollowPoints;
    }

    @Override
    public double getHollowPoints()
    {
        return this.hollowPoints;
    }

    @Override
    public void setClassLevel(int classLevel)
    {
        this.classLevel = classLevel;
    }

    @Override
    public void alterClassLevel(int alterLevel)
    {
        this.classLevel += alterLevel;
    }

    @Override
    public int getClassLevel()
    {
        return this.classLevel;
    }

    @Override
    public void setClassPoints(int classPoints)
    {
        this.classPoints = classPoints;
    }

    @Override
    public void alterClassPoints(int alteredPoints)
    {
        this.classPoints += alteredPoints;
    }

    @Override
    public int getClassPoints()
    {
        return this.classPoints;
    }

    @Override
    public void setClassExperience(int classExperience)
    {
        this.classExperience = classExperience;
    }

    @Override
    public void alterClassExperience(int alterExperience)
    {
        this.classExperience += alterExperience;
    }

    @Override
    public int getClassExperience()
    {
        return this.classExperience;
    }

    @Override
    public void setZanjutsuPoints(double zanjutsuPoints) {
        this.zanjutsuPoints = zanjutsuPoints;
    }

    @Override
    public void alterZanjutsuPoints(double zanjutsuPoints) {
        this.zanjutsuPoints += zanjutsuPoints;
    }

    @Override
    public double getZanjutsuPoints() {
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
    public void setHohoPoints(double hohoPoints) {
        this.hohoPoints = hohoPoints;
    }

    @Override
    public void alterHohoPoints(double hohoPoints) {
        this.hohoPoints += hohoPoints;
    }

    @Override
    public double getHohoPoints() {
        return this.hohoPoints;
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


    @Override
    public void addAvailableStats(double stats)
    {
        this.availableStats.add(stats);
    }



    @Override
    public void removeAvailableStats(double stats)
    {
        this.availableStats.remove(stats);
    }

    @Override
    public boolean hasAvailableStats(double stat)
    {
        return this.availableStats.contains(stat);
    }

    @Override
    public List<Double> getAvailableStats()
    {
        return this.availableStats;
    }
}
