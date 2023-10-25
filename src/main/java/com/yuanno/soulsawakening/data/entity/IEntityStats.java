package com.yuanno.soulsawakening.data.entity;

public interface IEntityStats {

    void setRace(String race);
    String getRace();
    boolean hasRace();

    void setRank(String rank);
    String getRank();
    boolean hasRank();

    void setHollowPoints(int hollowPoints);
    void alterHollowPoints(int hollowPoints);
    int getHollowPoints();

    void setZanjutsuPoints(int zanjutsuPoints);
    void alterZanjutsuPoints(int zanjutsuPoints);
    int getZanjutsuPoints();

    void setHakudaPoints(double hakudaPoints);
    void alterHakudaPoints(double hakudaPoints);
    double getHakudaPoints();

    void setHohoPoints(int hohoPoints);
    void alterHohoPoints(int hohoPoints);
    int getHohoPoints();
}
