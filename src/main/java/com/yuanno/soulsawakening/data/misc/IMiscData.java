package com.yuanno.soulsawakening.data.misc;

import java.util.ArrayList;

public interface IMiscData {

    void setUnlockedZanpakutoStyle(ArrayList<String> styleList);

    void addUnlockedZanpakutoStyle(String addition);

    void removeUnlockedZanpakutoStyle(String remove);

    ArrayList<String> getUnlockedZanpakutoStyle();

    void setZanpakutoStyle(String zanpakutoStyle);

    String getZanpakutoStyle();

    void setRenderZanpakutoOverlay(boolean setFlag);

    boolean getRenderZanpakutoOverlay();

    void setCanRenderOverlay(boolean flag);
    boolean getCanRenderOverlay();

    void setKan(int amount);
    void alterKan(int amount);
    int getKan();

    void setRank(String rank);

    String getRank();

    void setSpiritChain(int amount);
    void alterSpiritChain(int amount);
    int getSpiritChain();

    void setRenderRaceOverlay(boolean overlay);

    boolean getRenderRaceOverlay();
}
