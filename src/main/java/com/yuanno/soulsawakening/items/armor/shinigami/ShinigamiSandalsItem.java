package com.yuanno.soulsawakening.items.armor.shinigami;

import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class ShinigamiSandalsItem extends ArmorItem {

    public ShinigamiSandalsItem() {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.FEET, new Properties().stacksTo(1).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
    }
}
