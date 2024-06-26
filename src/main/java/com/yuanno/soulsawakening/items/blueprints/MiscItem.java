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
    private String colorCode;
    public MiscItem(Rarity rarity)
    {
        super(new Properties().rarity(rarity).tab(ModItemGroup.SOULS_AWAKENINGS_MISC).stacksTo(64));
    }

    public MiscItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        switch (this.getRarity(stack))
        {
            case COMMON:
                colorCode = "§7";
                break;
            case UNCOMMON:
                colorCode = "§2";
                break;
            case RARE:
                colorCode = "§6";
                break;
            case EPIC:
                colorCode = "§5";
                break;
        }
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent(colorCode + this.miscItemInformation));
        } else {
            tooltip.add(new TranslationTextComponent(colorCode + "Hold " + "§eSHIFT " + colorCode + "for more Information!"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}
