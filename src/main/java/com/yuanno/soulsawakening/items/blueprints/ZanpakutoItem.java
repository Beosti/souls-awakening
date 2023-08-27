package com.yuanno.soulsawakening.items.blueprints;

import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModTiers;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class ZanpakutoItem extends SwordItem {
    public ZanpakutoItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }
}
