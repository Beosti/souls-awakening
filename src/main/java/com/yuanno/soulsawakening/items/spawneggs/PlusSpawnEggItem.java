package com.yuanno.soulsawakening.items.spawneggs;

import com.yuanno.soulsawakening.init.ModEntities;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.items.CustomSpawnEggItem;

public class PlusSpawnEggItem extends CustomSpawnEggItem {
    public PlusSpawnEggItem() {
        super(ModEntities.PLUS, 0x464F56, 0xD16336, new Properties().stacksTo(64).tab(ModItemGroup.SOULS_AWAKENING_ENTITIES));
    }
}
