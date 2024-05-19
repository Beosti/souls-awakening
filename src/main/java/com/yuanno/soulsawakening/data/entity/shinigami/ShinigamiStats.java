package com.yuanno.soulsawakening.data.entity.shinigami;

import net.minecraft.nbt.CompoundNBT;

public class ShinigamiStats {
    private double zanjutsuPoints = 0;
    private double hakudaPoints = 0;
    private double hohoPoints = 0;
    private int classPoints = 0;
    private int classExperience = 0;
    private int maxClassExperience = 0;
    private String zanpakutoName = "";
    private String zanpakutoCommand = "";



    public void setClassPoints(int classPoints)
    {
        this.classPoints = classPoints;
    }

    public void alterClassPoints(int alteredPoints)
    {
        this.classPoints += alteredPoints;
    }

    public int getClassPoints()
    {
        return this.classPoints;
    }

    public void setClassExperience(int classExperience)
    {
        this.classExperience = classExperience;
    }

    public void alterClassExperience(int alterExperience)
    {
        this.classExperience += alterExperience;
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

    public int getClassExperience()
    {
        return this.classExperience;
    }

    public void setZanjutsuPoints(double zanjutsuPoints) {
        this.zanjutsuPoints = zanjutsuPoints;
    }

    public void alterZanjutsuPoints(double zanjutsuPoints) {
        this.zanjutsuPoints += zanjutsuPoints;
    }

    public double getZanjutsuPoints() {
        return this.zanjutsuPoints;
    }

    public void setHakudaPoints(double hakudaPoints) {
        this.hakudaPoints = hakudaPoints;
    }

    public void alterHakudaPoints(double hakudaPoints) {
        this.hakudaPoints += hakudaPoints;
    }

    public double getHakudaPoints() {
        return this.hakudaPoints;
    }

    public void setHohoPoints(double hohoPoints) {
        this.hohoPoints = hohoPoints;
    }

    public void alterHohoPoints(double hohoPoints) {
        this.hohoPoints += hohoPoints;
    }

    public double getHohoPoints() {
        return this.hohoPoints;
    }

    public String getZanpakutoName()
    {
        return this.zanpakutoName;
    }
    public void setZanpakutoName(String name)
    {
        this.zanpakutoName = name;
    }

    public String getZanpakutoCommand()
    {
        return this.zanpakutoCommand;
    }
    public void setZanpakutoCommand(String command)
    {
        this.zanpakutoCommand = command;
    }

    public CompoundNBT save()
    {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putDouble("zanjutsu", this.getZanjutsuPoints());
        compoundNBT.putDouble("hakuda", this.getHakudaPoints());
        compoundNBT.putDouble("hoho", this.getHohoPoints());
        compoundNBT.putInt("classPoints", this.getClassPoints());
        compoundNBT.putInt("classExperience", this.getClassExperience());
        compoundNBT.putInt("classExperienceMax", this.getMaxClassExperience());
        compoundNBT.putString("zanpakutoName", this.getZanpakutoName());
        compoundNBT.putString("zanpakutoCommand", this.getZanpakutoCommand());

        return compoundNBT;
    }

    public void load (CompoundNBT compoundNBT)
    {
        this.zanjutsuPoints = compoundNBT.getDouble("zanjutsu");
        this.hakudaPoints = compoundNBT.getDouble("hakuda");
        this.hohoPoints = compoundNBT.getDouble("hoho");

        this.classPoints = compoundNBT.getInt("classPoints");
        this.classExperience = compoundNBT.getInt("classExperience");
        this.maxClassExperience = compoundNBT.getInt("classExperienceMax");

        this.zanpakutoName = compoundNBT.getString("zanpakutoName");
        this.zanpakutoCommand = compoundNBT.getString("zanpakutoCommand");

    }
}
