package com.yuanno.soulsawakening.items.misc;

import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.items.blueprints.MiscItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class KanItem extends MiscItem {
    public KanItem() {
        super(Rarity.COMMON);
        this.miscItemInformation = "The currency of the world(s)";
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClientSide)
            return ActionResult.fail(player.getItemInHand(hand));
        IMiscData miscData = MiscDataCapability.get(player);
        int itemStackCount = player.getItemInHand(hand).getCount();
        miscData.alterKan(itemStackCount);
        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
        ItemStack itemStack = player.getItemInHand(hand);
        itemStack.shrink(itemStackCount);
        return ActionResult.success(player.getItemInHand(hand));
    }
}
