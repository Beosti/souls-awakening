package com.yuanno.soulsawakening.data.misc;

public class MiscDataBase implements IMiscData {

    private boolean canRenderOverlay;
    private int kan;
    private String rank;

    @Override
    public void setCanRenderOverlay(boolean setFlag)
    {
        this.canRenderOverlay = setFlag;
    }

    @Override
    public boolean getCanRenderOverlay()
    {
        return this.canRenderOverlay;
    }

    @Override
    public void setKan(int amount) {
        this.kan = amount;
    }

    @Override
    public void alterKan(int amount) {
        this.kan += amount;
    }

    @Override
    public int getKan() {
        return this.kan;
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
}
