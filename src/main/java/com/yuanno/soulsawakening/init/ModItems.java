package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.items.misc.ReishiEssenceItem;
import com.yuanno.soulsawakening.items.misc.ReishiIngotItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

    public static final RegistryObject<Item> REISHI_ESSENCE = ITEMS.register("reishi_essence", ReishiEssenceItem::new);
    public static final RegistryObject<Item> REISHI_INGOT = ITEMS.register("reishi_ingot", ReishiIngotItem::new);

    public static void register(IEventBus eventBus)
    {
    }

}
