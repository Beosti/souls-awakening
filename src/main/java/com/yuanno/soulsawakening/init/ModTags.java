package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static void init()
    {
        Items.init();
    }

    public static class Items
    {
        private static void init() {}

        public static final Tags.IOptionalNamedTag<Item> CONDUCTOR = tag("conductor");
        private static Tags.IOptionalNamedTag<Item> tag(String id)
        {
            return ItemTags.createOptional(new ResourceLocation(Main.MODID, id));
        }
    }
}
