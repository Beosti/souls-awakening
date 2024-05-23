package com.yuanno.soulsawakening.items.armor.shinigami;

import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class ShinigamiPantsItem extends ArmorItem {

    public ShinigamiPantsItem() {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.LEGS, new Properties().stacksTo(1).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
    }
}
