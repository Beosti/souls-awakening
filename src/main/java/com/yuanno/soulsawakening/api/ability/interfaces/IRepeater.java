package com.yuanno.soulsawakening.api.ability.interfaces;

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
