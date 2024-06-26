package com.yuanno.soulsawakening.data.entity;

import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
import com.yuanno.soulsawakening.data.entity.quincy.QuincyStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;

public interface IEntityStats {

    void setRace(String race);
    String getRace();
    boolean hasRace();

    void setRank(String rank);
    String getRank();
    boolean hasRank();

    void setStackedExperience(int amount);

    void alterStackedExperience(int amount);

    int getStackedExperience();

    void setShinigamiStats(ShinigamiStats shinigamiStats);
    boolean hasShinigamiStats();
    ShinigamiStats getShinigamiStats();

    void setHollowStats(HollowStats hollowStats);

    boolean hasHollowStats();

    HollowStats getHollowStats();

    void setReiatsuPoints(double reiatsuPoints);
    void alterReiatsuPoints(double reiatsuPoints);
    double getReiatsuPoints();

    void setQuincyStats(QuincyStats quincyStats);

    boolean hasQuincyStats();

    QuincyStats getQuincyStats();

    int getSpeedStat();
}
