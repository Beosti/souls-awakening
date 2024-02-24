package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.blocks.HollowSandBlock;
import com.yuanno.soulsawakening.blocks.ReishiBricksBlock;
import com.yuanno.soulsawakening.blocks.ReishiOreBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
    public List<Supplier<Block>> blocks = new ArrayList<>();

    public static final RegistryObject<Block> REISHI_ORE = registerBlock("reishi_ore", ReishiOreBlock::new);
    public static final RegistryObject<Block> HOLLOW_SAND = registerBlock("hollow_sand", HollowSandBlock::new);
    public static final RegistryObject<Block> REISHI_BRICKS = registerBlock("reishi_bricks", ReishiBricksBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.SOULS_AWAKENINGS_BLOCKS)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
