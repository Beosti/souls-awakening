package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.items.blueprints.MiscItem;
import net.minecraft.item.Rarity;

public class DangleItem extends MiscItem {
    public DangleItem() {
        super(new Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_MISC).stacksTo(1));
        this.miscItemInformation = "Dangle with a specific cross imagery on it.";
    }
}
