package com.yuanno.soulsawakening.items.armor.shinigami;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModArmorMaterials;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.items.CustomArmorItem;
import com.yuanno.soulsawakening.models.armor.shinigami.CaptainCapeModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;


public class CaptainCapeItem extends CustomArmorItem {

    public CaptainCapeItem()
    {
        super(ModArmorMaterials.REISHI_CLOTH, EquipmentSlotType.CHEST, new Properties().stacksTo(1).rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENING_ARMOR));
        this.miscItemInformation = "Cape from a legendary sub-section of a faction";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        A armorModel = (A) new CaptainCapeModel<>();

        return armorModel;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType equipmentSlotType, String string)
    {
        return String.format("%s:textures/armor/shinigami/captain_cape_texture.png", Main.MODID);
    }
}
