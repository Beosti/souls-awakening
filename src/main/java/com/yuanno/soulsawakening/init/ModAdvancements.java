package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.achievements.*;
import com.yuanno.soulsawakening.achievements.dimensions.HuecoMundoTrigger;
import com.yuanno.soulsawakening.achievements.dimensions.OverworldTrigger;
import com.yuanno.soulsawakening.achievements.dimensions.SoulSocietyShadowTrigger;
import com.yuanno.soulsawakening.achievements.dimensions.SoulSocietyTrigger;
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
    public static final ShikaiTrigger SHIKAI = new ShikaiTrigger();
    public static final FullbringerTrigger FULLBRINGER = new FullbringerTrigger();
    public static final SpiritTrigger SPIRIT = new SpiritTrigger();
    public static final HollowTrigger HOLLOW = new HollowTrigger();
    public static final GillianTrigger GILLIAN = new GillianTrigger();
    public static final AdjuchaTrigger ADJUCHA = new AdjuchaTrigger();
    public static final VastoLordeTrigger VASTO_LORDE = new VastoLordeTrigger();

    public static final RaceChangeTrigger RACE_CHANGE = new RaceChangeTrigger();

    public static final QuincyTrigger QUINCY = new QuincyTrigger();
    public static final SpiritWeaponTrigger SPIRIT_WEAPON = new SpiritWeaponTrigger();

    public static final OverworldTrigger OVERWORLD = new OverworldTrigger();
    public static final HuecoMundoTrigger HUECO_MUNDO = new HuecoMundoTrigger();
    public static final SoulSocietyTrigger SOUL_SOCIETY = new SoulSocietyTrigger();
    public static final SoulSocietyShadowTrigger SOUL_SOCIETY_SHADOW = new SoulSocietyShadowTrigger();

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
        CriteriaTriggers.register(SHIKAI);

        CriteriaTriggers.register(OVERWORLD);
        CriteriaTriggers.register(HUECO_MUNDO);
        CriteriaTriggers.register(SOUL_SOCIETY);
        CriteriaTriggers.register(SOUL_SOCIETY_SHADOW);
    }
}
