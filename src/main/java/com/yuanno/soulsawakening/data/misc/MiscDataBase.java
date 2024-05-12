package com.yuanno.soulsawakening.data.misc;

public class MiscDataBase implements IMiscData {

    private boolean canRenderOverlay;
    private boolean renderRaceOverlay;
    private int kan;
    private String rank = "";
    private int spiritChain;

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
