package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.items.blueprints.MiscItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class DangleItem extends MiscItem {
    public DangleItem() {
        super(Rarity.COMMON);
        this.miscItemInformation = "Dangle with a specific cross imagery on it.";
    }
}
