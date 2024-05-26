package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.init.ModAdvancements;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CustomArmorItem extends ArmorItem {
    public String miscItemInformation;
    private String colorCode;

    public CustomArmorItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        super.use(world, player, hand);
        if (world.isClientSide)
            return ActionResult.fail(player.getItemInHand(hand));
        ModAdvancements.CLOTHING.trigger((ServerPlayerEntity) player);
        return ActionResult.success(player.getItemInHand(hand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        switch (this.getRarity(stack))
        {
            case COMMON:
                colorCode = "§7";
                break;
            case UNCOMMON:
                colorCode = "§2";
                break;
            case RARE:
                colorCode = "§6";
                break;
            case EPIC:
                colorCode = "§5";
                break;
        }
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent(colorCode + this.miscItemInformation));
        } else {
            tooltip.add(new TranslationTextComponent(colorCode + "Hold " + "§eSHIFT " + colorCode + "for more Information!"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}
