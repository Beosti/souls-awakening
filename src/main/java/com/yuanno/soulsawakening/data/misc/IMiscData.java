package com.yuanno.soulsawakening.data.misc;

public interface IMiscData {

    void setCanRenderOverlay(boolean flag);
    boolean getCanRenderOverlay();

    void setKan(int amount);
    void alterKan(int amount);
    int getKan();
}
