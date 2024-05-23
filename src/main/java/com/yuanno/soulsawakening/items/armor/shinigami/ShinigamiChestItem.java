package com.yuanno.soulsawakening.items.armor.shinigami;

import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class ShinigamiChestItem extends ArmorItem {

    public ShinigamiChestItem() {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.CHEST, new Properties().stacksTo(1).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
    }
}
