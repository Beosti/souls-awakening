package com.yuanno.soulsawakening.data.misc;

public class MiscDataBase implements IMiscData {

    private boolean canRenderOverlay;


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
}
