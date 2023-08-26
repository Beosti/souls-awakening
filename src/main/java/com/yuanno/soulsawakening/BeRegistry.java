package com.yuanno.soulsawakening;

import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class BeRegistry {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);


    public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block)
    {
        return registerBlock(localizedName, block, (ItemGroup) null);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block, @Nullable ItemGroup tab)
    {
        Item.Properties blockItemProps = new Item.Properties();
        if(tab != null)
            blockItemProps.tab(tab);

        return registerBlock(localizedName, block, blockItemProps);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block, Item.Properties props)
    {
        String resourceName = Beapi.getResourceName(localizedName);
        Beapi.getLangMap().put("block." + Main.MODID + "." + resourceName, localizedName);

        RegistryObject<T> reg = BLOCKS.register(resourceName, block);

        registerItem(localizedName, () -> new BlockItem(reg.get(), props));

        return reg;
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block, Function<T, BlockItem> blockItemFunc)
    {
        String resourceName = Beapi.getResourceName(localizedName);
        Beapi.getLangMap().put("block." + Main.MODID + "." + resourceName, localizedName);

        RegistryObject<T> reg = BLOCKS.register(resourceName, block);

        registerItem(localizedName, () -> blockItemFunc.apply(reg.get()));

        return reg;
    }
    public static <T extends Item> RegistryObject<T> registerItem(String localizedName, Supplier<T> item)
    {
        String resourceName = Beapi.getResourceName(localizedName);
        Beapi.getLangMap().put("item." + Main.MODID + "." + resourceName, localizedName);

        RegistryObject<T> reg = ITEMS.register(resourceName, item);

        return reg;
    }

}
