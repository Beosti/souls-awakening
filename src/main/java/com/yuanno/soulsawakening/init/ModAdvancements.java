package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.achievements.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModAdvancements {

    public static final ShinigamiTrigger SHINIGAMI = new ShinigamiTrigger();
    public static final FullbringerTrigger FULLBRINGER = new FullbringerTrigger();
    public static final SpiritTrigger SPIRIT = new SpiritTrigger();
    public static final HollowTrigger HOLLOW = new HollowTrigger();
    public static final RaceChangeTrigger RACE_CHANGE = new RaceChangeTrigger();


    public static void register(IEventBus eventBus)
    {
        CriteriaTriggers.register(SHINIGAMI);
        CriteriaTriggers.register(FULLBRINGER);
        CriteriaTriggers.register(SPIRIT);
        CriteriaTriggers.register(HOLLOW);
        CriteriaTriggers.register(RACE_CHANGE);
    }
}
