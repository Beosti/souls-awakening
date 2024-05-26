package com.yuanno.soulsawakening.items.armor.shinigami;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.models.armor.shinigami.ShinigamiUniformPantsModel;
import com.yuanno.soulsawakening.models.armor.shinigami.ShinigamiUniformSandalsModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ShinigamiSandalsItem extends ArmorItem {

    public ShinigamiSandalsItem() {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.FEET, new Properties().stacksTo(1).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        A armorModel = (A) new ShinigamiUniformSandalsModel<>();

        return armorModel;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType equipmentSlotType, String string)
    {
        return String.format("%s:textures/armor/shinigami/shinigami_sandals_texture.png", Main.MODID);
    }
}
