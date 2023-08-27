package com.yuanno.soulsawakening.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup SOULS_AWAKENINGS_BLOCKS = new ItemGroup("soulsawakeningModTabBlocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.REISHI_ORE.get());
        }
    };
    public static final ItemGroup SOULS_AWAKENINGS_MISC = new ItemGroup("soulsawakeningModTabMisc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.REISHI_ESSENCE.get());
        }
    };
    public static final ItemGroup SOULS_AWAKENINGS_WEAPONS = new ItemGroup("soulsawakeningModTabWeapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ZANPAKUTO.get());
        }
    };
}
