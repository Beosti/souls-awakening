package com.yuanno.soulsawakening.items.armor.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.items.CustomArmorItem;
import com.yuanno.soulsawakening.models.armor.quincy.QuincyCloakModel;
import com.yuanno.soulsawakening.models.armor.quincy.QuincyUniformPantsModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class QuincyCloakItem extends CustomArmorItem {

    public QuincyCloakItem() {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.CHEST, new Properties().stacksTo(1).rarity(Rarity.UNCOMMON).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
        this.miscItemInformation = "A very solid looking robe... Looks like it's a military outfit";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        A armorModel = (A) new QuincyCloakModel<>();

        return armorModel;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType equipmentSlotType, String string)
    {
        return String.format("%s:textures/armor/quincy/quincy_cloak_texture.png", Main.MODID);
    }
}
