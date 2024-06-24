package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.items.*;
import com.yuanno.soulsawakening.items.armor.quincy.QuincyBootsItem;
import com.yuanno.soulsawakening.items.armor.quincy.QuincyChestItem;
import com.yuanno.soulsawakening.items.armor.quincy.QuincyCloakItem;
import com.yuanno.soulsawakening.items.armor.quincy.QuincyPantsItem;
import com.yuanno.soulsawakening.items.armor.rogue.RogueCapeItem;
import com.yuanno.soulsawakening.items.armor.rogue.RogueChestItem;
import com.yuanno.soulsawakening.items.armor.shinigami.CaptainCapeItem;
import com.yuanno.soulsawakening.items.armor.shinigami.ShinigamiChestItem;
import com.yuanno.soulsawakening.items.armor.shinigami.ShinigamiPantsItem;
import com.yuanno.soulsawakening.items.armor.shinigami.ShinigamiSandalsItem;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.items.spawneggs.*;
import com.yuanno.soulsawakening.items.misc.KanItem;
import com.yuanno.soulsawakening.items.misc.ReishiItem;
import com.yuanno.soulsawakening.items.misc.ReishiIngotItem;
import com.yuanno.soulsawakening.items.spiritweapon.*;
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
    public static final RegistryObject<Item> KAN = ITEMS.register("kan", KanItem::new);

    // WEAPONS
    public static final RegistryObject<Item> ZANPAKUTO = ITEMS.register("zanpakuto", ZanpakutoItem::new);
    public static final RegistryObject<Item> ZANPAKUTO_WAKIZASHI = ITEMS.register("zanpakuto_wakizashi", ZanpakutoWakizashiItem::new);
    public static final RegistryObject<Item> SHINAI = ITEMS.register("shinai", ShinaiItem::new);
    public static final RegistryObject<Item> DANGLE = ITEMS.register("dangle", DangleItem::new);
    public static final RegistryObject<Item> KOJAKU = ITEMS.register("kojaku", KojakuItem::new);
    public static final RegistryObject<Item> GINREI_KOJAKU = ITEMS.register("ginrei_kojaku_bow", GinreiKojaku::new);
    public static final RegistryObject<Item> FISHING_ROD_REISHI = ITEMS.register("fishing_rod_reishi", ReishiRodItem::new);
    public static final RegistryObject<Item> SPEAR_REISHI = ITEMS.register("spear_reishi", ReishiSpearItem::new);
    public static final RegistryObject<Item> TRIDENT_REISHI = ITEMS.register("trident_reishi", ReishiTridentItem::new);
    public static final RegistryObject<Item> SWORD_REISHI = ITEMS.register("sword_reishi", ReishiSwordItem::new);

    // ARMORS
    // shinigami
    public static final RegistryObject<Item> SHINIGAMI_SANDALS = ITEMS.register("shinigami_sandals", ShinigamiSandalsItem::new);
    public static final RegistryObject<Item> SHINIGAMI_PANTS = ITEMS.register("shinigami_pants", ShinigamiPantsItem::new);
    public static final RegistryObject<Item> SHINIGAMI_CHEST = ITEMS.register("shinigami_chest", ShinigamiChestItem::new);
    public static final RegistryObject<Item> CAPTAIN_CAPE = ITEMS.register("captain_cape", CaptainCapeItem::new);
    // rogue
    public static final RegistryObject<Item> ROGUE_CHEST = ITEMS.register("rogue_chest", RogueChestItem::new);
    public static final RegistryObject<Item> ROGUE_CAPE = ITEMS.register("rogue_cape", RogueCapeItem::new);
    // quincy
    public static final RegistryObject<Item> QUINCY_ROBE = ITEMS.register("quincy_robe", QuincyCloakItem::new);
    public static final RegistryObject<Item> QUINCY_CHEST = ITEMS.register("quincy_chest", QuincyChestItem::new);
    public static final RegistryObject<Item> QUINCY_PANTS = ITEMS.register("quincy_pants", QuincyPantsItem::new);
    public static final RegistryObject<Item> QUINCY_BOOTS = ITEMS.register("quincy_boots", QuincyBootsItem::new);

    // spawn eggs
    public static final RegistryObject<Item> APE_SPAWN_EGG = ITEMS.register("ape_spawn_egg", ApeSpawnEggItem::new);
    public static final RegistryObject<Item> GOLEM_SPAWN_EGG = ITEMS.register("golem_spawn_egg", GolemSpawnEggItem::new);
    public static final RegistryObject<Item> SPIDER_SPAWN_EGG = ITEMS.register("spider_spawn_egg", SpiderSpawnEggItem::new);
    public static final RegistryObject<Item> THORNS_SPAWN_EGG = ITEMS.register("thorns_spawn_egg", ThornsSpawnEggItem::new);
    public static final RegistryObject<Item> FLYING_SPAWN_EGG = ITEMS.register("flying_spawn_egg", FlyingSpawnEggItem::new);

    public static final RegistryObject<Item> PLUS_SPAWN_EGG = ITEMS.register("plus_spawn_egg", PlusSpawnEggItem::new);
    public static final RegistryObject<Item> SHINIGAMI_SPAWN_EGG = ITEMS.register("shinigami_spawn_egg", ShinigamiSpawnEggItem::new);
    public static final RegistryObject<Item> TRADER_SPAWN_EGG = ITEMS.register("trader_spawn_egg", TraderSpawnEggItem::new);
    public static final RegistryObject<Item> SHINIGAMI_TEACHER_SPAWN_EGG = ITEMS.register("shinigami_teacher_spawn_egg", ShinigamiTeacherSpawnEggItem::new);
    public static final RegistryObject<Item> KIDO_TEACHER_SPAWN_EGG = ITEMS.register("kido_teacher_spawn_egg", KidoTeacherSpawnEggItem::new);
    public static final RegistryObject<Item> BAKUDO_TEACHER_SPAWN_EGG = ITEMS.register("bakudo_teacher_spawn_egg", BakudoTeacherSpawnEggItem::new);

}
