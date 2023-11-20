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

    void setClassLevel(int classLevel);
    void alterClassLevel(int alterLevel);
    int getClassLevel();

    void setClassPoints(int classPoints);
    void alterClassPoints(int alterPoints);
    int getClassPoints();

    void setClassExperience(int classExperience);
    void alterClassExperience(int classExperience);
    int getClassExperience();

    void setZanjutsuPoints(double zanjutsuPoints);
    void alterZanjutsuPoints(double zanjutsuPoints);
    double getZanjutsuPoints();

    void setHakudaPoints(double hakudaPoints);
    void alterHakudaPoints(double hakudaPoints);
    double getHakudaPoints();

    void setHohoPoints(double hohoPoints);
    void alterHohoPoints(double hohoPoints);
    double getHohoPoints();
}
