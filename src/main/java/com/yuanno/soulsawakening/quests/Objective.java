package com.yuanno.soulsawakening.quests;

public abstract class Objective {

    private String title = "";
    private String description = "";
    private int progress;
    private int maxProgress;


    public void setProgress(int progress)
    {
        this.progress = progress;
    }
    public int getProgress()
    {
        return this.progress;
    }
    public void setMaxProgress(int maxProgress)
    {
        this.maxProgress = maxProgress;
    }
    public int getMaxProgress()
    {
        return this.maxProgress;
    }
}
