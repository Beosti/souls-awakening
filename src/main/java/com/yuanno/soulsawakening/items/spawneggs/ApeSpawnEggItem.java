package com.yuanno.soulsawakening.items.spawneggs;

import com.yuanno.soulsawakening.init.ModEntities;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.items.CustomSpawnEggItem;
import net.minecraft.item.Item;

public class ApeSpawnEggItem extends CustomSpawnEggItem {
    public ApeSpawnEggItem() {
        super(ModEntities.APE, 0x464F56, 0xD16336, new Item.Properties().stacksTo(64).tab(ModItemGroup.SOULS_AWAKENING_ENTITIES));
    }
}
