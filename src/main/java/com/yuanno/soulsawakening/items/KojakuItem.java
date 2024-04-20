package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.quincy.ReishiArrow;
import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class KojakuItem extends BowItem implements ISpiritWeapon {
    public KojakuItem() {
        super(new Properties().tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }

    /**
     * Does something when the bow is released depending on charge time
     * @param itemStack the bow itself
     * @param world world the bow is used
     * @param livingEntity entity using the bow
     * @param duration how long the bow is hold
     */
    @Override
    public void releaseUsing(ItemStack itemStack, World world, LivingEntity livingEntity, int duration)
    {
        if (!(livingEntity instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) livingEntity;
        if (player.level.isClientSide)
            return;
        int timeUsed = this.getUseDuration(itemStack) - duration;
        float power = getPowerForTime(timeUsed);
        System.out.println(power);
        if ((double) power < 0.1)
            return;
        ReishiArrow reishiArrow = new ReishiArrow(player.level, player);
        float velocity = 5 * power;
        float inaccuracy = 1 / power;
        int reishi = (int) EntityStatsCapability.get(player).getReiatsuPoints();
        int addedDamage = (int) (reishi * power);
        reishiArrow.alterDamage(addedDamage);
        reishiArrow.alterMaxLife(addedDamage);
        reishiArrow.shootFromRotation(player, player.xRot, player.yRot, 0, velocity, inaccuracy);
        player.level.addFreshEntity(reishiArrow);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, world, player, hand, true);
        if (ret != null)
            return ret;
        player.startUsingItem(hand);
        return ActionResult.consume(itemstack);
    }
}
