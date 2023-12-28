package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.items.ZanpakutoWakizashiItem;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.items.misc.ReishiItem;
import com.yuanno.soulsawakening.items.misc.ReishiIngotItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

    // MISC
    public static final RegistryObject<Item> REISHI = ITEMS.register("reishi", ReishiItem::new);
    public static final RegistryObject<Item> REISHI_INGOT = ITEMS.register("reishi_ingot", ReishiIngotItem::new);


    // WEAPONS
    public static final RegistryObject<Item> ZANPAKUTO = ITEMS.register("zanpakuto", ZanpakutoItem::new);
    public static final RegistryObject<Item> ZANPAKUTO_WAKIZASHI = ITEMS.register("zanpakuto_wakizashi", ZanpakutoWakizashiItem::new);

}
