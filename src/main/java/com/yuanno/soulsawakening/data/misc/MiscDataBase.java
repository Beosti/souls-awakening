package com.yuanno.soulsawakening.data.misc;

import java.util.ArrayList;

public class MiscDataBase implements IMiscData {

    private boolean canRenderOverlay;
    private boolean renderRaceOverlay;
    private boolean renderZanpakutoOverlay;
    private int kan;
    private String rank = "";
    private int spiritChain;
    private String zanpakutoStyle = "";
    private ArrayList<String> unlockedZanpakutoStyle = new ArrayList<>();

    @Override
    public void setUnlockedZanpakutoStyle(ArrayList<String> styleList)
    {
        this.unlockedZanpakutoStyle = styleList;
    }

    @Override
    public void addUnlockedZanpakutoStyle(String addition)
    {
        this.unlockedZanpakutoStyle.add(addition);
    }

    @Override
    public void removeUnlockedZanpakutoStyle(String remove)
    {
        this.unlockedZanpakutoStyle.remove(remove);
    }

    @Override
    public ArrayList<String> getUnlockedZanpakutoStyle()
    {
        return this.unlockedZanpakutoStyle;
    }

    @Override
    public void setZanpakutoStyle(String zanpakutoStyle)
    {
        this.zanpakutoStyle = zanpakutoStyle;
    }

    @Override
    public String getZanpakutoStyle()
    {
        return this.zanpakutoStyle;
    }

    @Override
    public void setRenderZanpakutoOverlay(boolean setFlag)
    {
        this.renderZanpakutoOverlay = setFlag;
    }

    @Override
    public boolean getRenderZanpakutoOverlay()
    {
        return this.renderZanpakutoOverlay;
    }
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

    @Override
    public void setSpiritChain(int amount) {
        this.spiritChain = amount;
    }

    @Override
    public void alterSpiritChain(int amount) {
        this.spiritChain += amount;
    }

    @Override
    public int getSpiritChain() {
        return this.spiritChain;
    }

    @Override
    public void setRenderRaceOverlay(boolean overlay)
    {
        this.renderRaceOverlay = overlay;
    }

    @Override
    public boolean getRenderRaceOverlay()
    {
        return this.renderRaceOverlay;
    }
}
