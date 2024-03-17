package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModTiers;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AsauchiItem extends SwordItem {
    public AsauchiItem() {
        super(ModTiers.WEAPON, 5, -2.55f, new Properties().rarity(Rarity.UNCOMMON).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }

    public AsauchiItem(int i, float v) {
        super(ModTiers.WEAPON, i, v, new Properties().rarity(Rarity.UNCOMMON).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("§6An empty sword, with no spirit"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}
