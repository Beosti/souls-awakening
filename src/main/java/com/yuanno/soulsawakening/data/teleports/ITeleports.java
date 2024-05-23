package com.yuanno.soulsawakening.data.teleports;


import com.yuanno.soulsawakening.teleport.TeleportPosition;

import java.util.ArrayList;

public interface ITeleports {

    ArrayList<TeleportPosition> getTeleportPositions();

    void setTeleportPositions(ArrayList<TeleportPosition> teleportPositions);

    void addTeleportsPosition(TeleportPosition teleportPosition);

    void removeTeleportPosition(TeleportPosition teleportPosition);

    void clearTeleportPositions();
}
