package com.yuanno.soulsawakening.data.entity.shinigami;

import net.minecraft.nbt.CompoundNBT;

public class ShinigamiStats {
    private double zanjutsuPoints = 0;
    private double hakudaPoints = 0;
    private double hohoPoints = 0;
    private int classLevel = 0;
    private int classPoints = 0;
    private int classExperience = 0;


    public void setClassLevel(int classLevel)
    {
        this.classLevel = classLevel;
    }

    public void alterClassLevel(int alterLevel)
    {
        this.classLevel += alterLevel;
    }

    public int getClassLevel()
    {
        return this.classLevel;
    }

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

    public CompoundNBT save()
    {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putDouble("zanjutsu", this.getZanjutsuPoints());
        compoundNBT.putDouble("hakuda", this.getHakudaPoints());
        compoundNBT.putDouble("hoho", this.getHohoPoints());
        compoundNBT.putInt("classLevel", this.getClassLevel());
        compoundNBT.putInt("classPoints", this.getClassPoints());
        compoundNBT.putInt("classExperience", this.getClassExperience());
        return compoundNBT;
    }

    public void load (CompoundNBT compoundNBT)
    {
        this.zanjutsuPoints = compoundNBT.getDouble("zanjutsu");
        this.hakudaPoints = compoundNBT.getDouble("hakuda");
        this.hohoPoints = compoundNBT.getDouble("hoho");

        this.classLevel = compoundNBT.getInt("classLevel");
        this.classPoints = compoundNBT.getInt("classPoints");
        this.classExperience = compoundNBT.getInt("classExperience");
    }
}
