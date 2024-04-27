package com.yuanno.soulsawakening.data.entity.quincy;

import net.minecraft.nbt.CompoundNBT;

public class QuincyStats {

    private int constitution = 0;
    private int blut = 0;
    private int hirenkyaku = 0;

    private int classPoints = 0;
    private int classExperience = 0;
    private int maxClassExperience = 0;

    public void setConstitution(int constitution)
    {
        this.constitution = constitution;
    }
    public int getConstitution()
    {
        return this.constitution;
    }
    public void alterConstitution(int amount)
    {
        this.constitution += amount;
    }

    public void setBlut(int blut)
    {
        this.blut = blut;
    }
    public int getBlut()
    {
        return this.blut;
    }
    public void alterBlut(int amount)
    {
        this.blut += amount;
    }

    public void setHirenkyaku(int hirenkyaku)
    {
        this.hirenkyaku = hirenkyaku;
    }
    public int getHirenkyaku()
    {
        return this.hirenkyaku;
    }
    public void alterHirenkyaku(int amount)
    {
        this.hirenkyaku += amount;
    }

    public void setClassPoints(int classPoints)
    {
        this.classPoints = classPoints;
    }
    public int getClassPoints()
    {
        return this.classPoints;
    }
    public void alterClassPoints(int amount)
    {
        this.classPoints += amount;
    }

    public void setExperiencePoints(int amount)
    {
        this.classExperience = amount;
    }
    public int getClassExperience()
    {
        return this.classExperience;
    }
    public void alterClassExperience(int amount)
    {
        this.classExperience += amount;
    }

    public void setMaxClassExperience(int amount)
    {
        this.maxClassExperience = amount;
    }
    public int getMaxClassExperience()
    {
        return this.maxClassExperience;
    }
    public void alterMaxClassExperience(int amount)
    {
        this.maxClassExperience += amount;
    }

    public CompoundNBT save()
    {
        CompoundNBT compoundNBT = new CompoundNBT();

        compoundNBT.putInt("constitution", this.getConstitution());
        compoundNBT.putInt("blut", this.getBlut());
        compoundNBT.putInt("hirenkyaku", this.getHirenkyaku());

        compoundNBT.putInt("class", this.getClassPoints());
        compoundNBT.putInt("classExperience", this.getClassExperience());
        compoundNBT.putInt("classExperienceMax", this.getMaxClassExperience());

        return compoundNBT;
    }

    public void load (CompoundNBT compoundNBT)
    {
        this.constitution = compoundNBT.getInt("constitution");
        this.blut = compoundNBT.getInt("blut");
        this.hirenkyaku = compoundNBT.getInt("hirenkyaku");

        this.classPoints = compoundNBT.getInt("class");
        this.classExperience = compoundNBT.getInt("classExperience");
        this.maxClassExperience = compoundNBT.getInt("classExperienceMax");
    }
}
