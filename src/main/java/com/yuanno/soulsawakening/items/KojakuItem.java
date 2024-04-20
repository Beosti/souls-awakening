package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.item.BowItem;

public class KojakuItem extends BowItem implements ISpiritWeapon {
    public KojakuItem() {
        super(new Properties().tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }
}
