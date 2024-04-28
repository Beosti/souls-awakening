package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.achievements.*;
import com.yuanno.soulsawakening.achievements.hollow.AdjuchaTrigger;
import com.yuanno.soulsawakening.achievements.hollow.GillianTrigger;
import com.yuanno.soulsawakening.achievements.hollow.HollowTrigger;
import com.yuanno.soulsawakening.achievements.hollow.VastoLordeTrigger;
import com.yuanno.soulsawakening.achievements.quincy.QuincyTrigger;
import com.yuanno.soulsawakening.achievements.quincy.SpiritWeaponTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModAdvancements {

    public static final ShinigamiTrigger SHINIGAMI = new ShinigamiTrigger();
    public static final FullbringerTrigger FULLBRINGER = new FullbringerTrigger();
    public static final SpiritTrigger SPIRIT = new SpiritTrigger();
    public static final HollowTrigger HOLLOW = new HollowTrigger();
    public static final GillianTrigger GILLIAN = new GillianTrigger();
    public static final AdjuchaTrigger ADJUCHA = new AdjuchaTrigger();
    public static final VastoLordeTrigger VASTO_LORDE = new VastoLordeTrigger();

    public static final RaceChangeTrigger RACE_CHANGE = new RaceChangeTrigger();

    public static final QuincyTrigger QUINCY = new QuincyTrigger();
    public static final SpiritWeaponTrigger SPIRIT_WEAPON = new SpiritWeaponTrigger();

    public static void register(IEventBus eventBus)
    {
        CriteriaTriggers.register(SHINIGAMI);
        CriteriaTriggers.register(FULLBRINGER);
        CriteriaTriggers.register(SPIRIT);
        CriteriaTriggers.register(HOLLOW);
        CriteriaTriggers.register(RACE_CHANGE);
        CriteriaTriggers.register(GILLIAN);
        CriteriaTriggers.register(ADJUCHA);
        CriteriaTriggers.register(VASTO_LORDE);
        CriteriaTriggers.register(QUINCY);
        CriteriaTriggers.register(SPIRIT_WEAPON);
    }
}
