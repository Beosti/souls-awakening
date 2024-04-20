package com.yuanno.soulsawakening.data.entity.hollow;

import net.minecraft.nbt.CompoundNBT;

public class HollowStats {

    private int hollowPoints = 0;
    private int mutationPoints = 0;

    private int constitution;
    private int hierro;
    private int agility;

    public void setHollowPoints(int amount) {
        this.hollowPoints = amount;
    }
    public int getHollowPoints()
    {
        return this.hollowPoints;
    }
    public void alterHollowPoints(int amount)
    {
        this.hollowPoints += amount;
    }

    public void setMutationPoints(int amount)
    {
        this.mutationPoints = amount;
    }
    public int getMutationPoints()
    {
        return this.mutationPoints;
    }
    public void alterMutationPoints(int amount)
    {
        this.mutationPoints += amount;
    }

    public void setConstitution(int amount)
    {
        this.constitution = amount;
    }
    public int getConstitution()
    {
        return this.constitution;
    }
    public void alterConstitution(int amount)
    {
        this.constitution += amount;
    }

    public void setHierro(int amount)
    {
        this.hierro = amount;
    }
    public int getHierro()
    {
        return this.hierro;
    }
    public void alterHierro(int amount)
    {
        this.hierro += amount;
    }

    public void setAgility(int amount)
    {
        this.agility = amount;
    }
    public int getAgility()
    {
        return this.agility;
    }
    public void alterAgility(int amount)
    {
        this.agility = amount;
    }

    public CompoundNBT save()
    {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("hollowPoints", this.getHollowPoints());
        compoundNBT.putInt("mutationPoints", this.getMutationPoints());

        compoundNBT.putInt("constitution", this.getConstitution());
        compoundNBT.putInt("hierro", this.getHierro());
        compoundNBT.putInt("agility", this.getAgility());
        return compoundNBT;
    }

    public void load (CompoundNBT compoundNBT)
    {
        this.hollowPoints = compoundNBT.getInt("hollowPoints");
        this.mutationPoints = compoundNBT.getInt("mutationPoints");

        this.constitution = compoundNBT.getInt("constitution");
        this.hierro = compoundNBT.getInt("hierro");
        this.agility = compoundNBT.getInt("agility");
    }
}
