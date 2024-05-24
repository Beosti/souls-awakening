package com.yuanno.soulsawakening.items.armor.shinigami;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.models.armor.shinigami.ShinigamiUniformChestModel;
import com.yuanno.soulsawakening.models.armor.shinigami.ShinigamiUniformModel;
import com.yuanno.soulsawakening.models.armor.shinigami.ShinigamiUniformPantsModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ShinigamiPantsItem extends ArmorItem {

    public ShinigamiPantsItem() {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.LEGS, new Properties().stacksTo(1).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        A armorModel = (A) new ShinigamiUniformPantsModel<>();

        return armorModel;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType equipmentSlotType, String string)
    {
        // TODO the issue might be that the chest and leggings texture is "empty" for the side that checks the legs, check the new textures
        return String.format("%s:textures/armor/shinigami/shinigami_pants_texture.png", Main.MODID);
    }
}
