package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModLootTables {

    @SubscribeEvent
    public static void onVanillaLootLoading(LootTableLoadEvent event)
    {
        if (event.getName().toString().equals("minecraft:entities/witch"))
        {
            TableLootEntry.Builder lootEntry = TableLootEntry.lootTableReference(new ResourceLocation(Main.MODID, "entities/inject/witch"));
            event.getTable().removePool("main");
            event.getTable().addPool(LootPool.lootPool().add(lootEntry).build());
        }
        if (event.getName().equals(LootTables.DESERT_PYRAMID) || event.getName().equals(LootTables.PIGLIN_BARTERING) || event.getName().equals(LootTables.BURIED_TREASURE) || event.getName().equals(LootTables.VILLAGE_ARMORER))
        {
            LootPool souls = constructLootPool("souls", 0, 2F,
                    ItemLootEntry.lootTableItem(ModItems.REISHI.get()).setWeight(10),
                    ItemLootEntry.lootTableItem(ModItems.REISHI.get()).setWeight(10),
                    ItemLootEntry.lootTableItem(ModItems.REISHI_INGOT.get()).setWeight(5),
                    ItemLootEntry.lootTableItem(ModItems.DANGLE.get()).setWeight(1)
            );


            event.getTable().addPool(souls);
        }
        else if (event.getName().equals(LootTables.JUNGLE_TEMPLE) || event.getName().equals(LootTables.IGLOO_CHEST) || event.getName().equals(LootTables.BASTION_TREASURE) || event.getName().equals(LootTables.END_CITY_TREASURE))
        {
            LootPool souls = constructLootPool("souls", 1, 3F,
                    ItemLootEntry.lootTableItem(ModItems.REISHI.get()).setWeight(10),
                    ItemLootEntry.lootTableItem(ModItems.REISHI.get()).setWeight(10),
                    ItemLootEntry.lootTableItem(ModItems.REISHI.get()).setWeight(10),
                    ItemLootEntry.lootTableItem(ModItems.REISHI_INGOT.get()).setWeight(5),
                    ItemLootEntry.lootTableItem(ModItems.REISHI_INGOT.get()).setWeight(5),
                    ItemLootEntry.lootTableItem(ModItems.DANGLE.get()).setWeight(1)
            );


            event.getTable().addPool(souls);
        }
    }

    public static LootPool constructLootPool(String name, float minRolls, float maxRolls, LootEntry.Builder<?>... lootEntries)
    {
        LootPool.Builder poolBuilder = LootPool.lootPool().name(name).setRolls(RandomValueRange.between(minRolls, maxRolls));
        if (lootEntries != null)
        {
            for (LootEntry.Builder<?> e : lootEntries)
            {
                if (e != null)
                    poolBuilder.add(e);
            }
        }
        return poolBuilder.build();
    }
}
