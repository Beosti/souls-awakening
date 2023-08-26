package com.yuanno.soulsawakening.items.blueprints;

import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class MiscItem extends Item {
    public String miscItemInformation;
    public MiscItem(Rarity rarity)
    {
        super(new Properties().rarity(rarity).tab(ModItemGroup.SOULS_AWAKENINGS_MISC));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("§7" + this.miscItemInformation));
        } else {
            tooltip.add(new TranslationTextComponent("§7Hold " + "§eSHIFT" + " §7for more Information!"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}
