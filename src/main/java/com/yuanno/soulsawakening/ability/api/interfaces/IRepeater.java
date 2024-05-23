package com.yuanno.soulsawakening.ability.api.interfaces;

public interface IRepeater {

    default int getAmount(int amount)
    {
        return amount;
    }
    default float getDelay(float delay)
    {
        return delay;
    }
}
