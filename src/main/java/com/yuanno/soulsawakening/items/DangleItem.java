package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.items.blueprints.MiscItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DangleItem extends MiscItem {
    public DangleItem() {
        super(new Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_MISC).stacksTo(1));
        this.miscItemInformation = "Dangle with a specific cross imagery on it.";
    }
}
