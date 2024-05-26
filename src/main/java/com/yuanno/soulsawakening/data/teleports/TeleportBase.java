package com.yuanno.soulsawakening.data.teleports;

import com.yuanno.soulsawakening.teleport.TeleportPosition;

import java.util.ArrayList;

public class TeleportBase implements ITeleports {

    private ArrayList<TeleportPosition> teleportPositions = new ArrayList<>();

    @Override
    public ArrayList<TeleportPosition> getTeleportPositions()
    {
        return teleportPositions;
    }

    @Override
    public void setTeleportPositions(ArrayList<TeleportPosition> teleportPositions)
    {
        this.teleportPositions = teleportPositions;
    }

    @Override
    public void addTeleportsPosition(TeleportPosition teleportPosition)
    {
        this.teleportPositions.add(teleportPosition);
    }

    @Override
    public void removeTeleportPosition(TeleportPosition teleportPosition)
    {
        this.teleportPositions.remove(teleportPositions);
    }

    @Override
    public void clearTeleportPositions()
    {
        this.teleportPositions.clear();
    }
}
