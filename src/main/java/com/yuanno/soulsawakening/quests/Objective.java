package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.nbt.CompoundNBT;

public abstract class Objective {

    public String title = "";
    public String description = "";
    public int progress = 0;
    public int maxProgress;

    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle()
    {
        return this.title;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setProgress(int progress)
    {
        this.progress = progress;
    }
    public int getProgress()
    {
        return this.progress;
    }
    public void alterProgress(int amount)
    {
        if (this.progress < this.maxProgress)
            this.progress += amount;
    }
    public void setMaxProgress(int maxProgress)
    {
        this.maxProgress = maxProgress;
    }
    public int getMaxProgress()
    {
        return this.maxProgress;
    }

    public CompoundNBT save()
    {
        CompoundNBT compoundNBT = new CompoundNBT();

        compoundNBT.putString("id", Beapi.getResourceName(this.title));
        compoundNBT.putString("title", this.title);
        compoundNBT.putString("description", this.description);
        compoundNBT.putInt("progress", this.progress);
        compoundNBT.putInt("maxProgress", this.maxProgress);

        return compoundNBT;
    }

    public void load(CompoundNBT nbt)
    {
        this.title = nbt.getString("title");
        this.description = nbt.getString("description");
        this.progress = nbt.getInt("progress");
        this.maxProgress = nbt.getInt("maxProgress");
    }
}
