package com.yuanno.soulsawakening.items.spiritweapon;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.quincy.ReishiArrow;
import com.yuanno.soulsawakening.events.util.CustomArrowLooseEvent;
import com.yuanno.soulsawakening.init.ModItemGroup;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.List;

public class KojakuItem extends BowItem {
    public KojakuItem() {
        super(new Properties().tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).rarity(Rarity.RARE).stacksTo(1));
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        if (Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6A bow made out of surrounding reishi"));
        }
        else if (!Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6Hold " + "§eSHIFT " + "§6" + "for more Information!"));
        }
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
        if ((double) power < 0.1)
            return;
        ReishiArrow reishiArrow = new ReishiArrow(player.level, player);
        float velocity = 5 * power;
        float inaccuracy = 1 / power;
        int reishi = (int) EntityStatsCapability.get(player).getReiatsuPoints();
        int addedDamage = (int) ((reishi * power) / 2);
        reishiArrow.setAddedDamage(addedDamage);
        reishiArrow.alterMaxLife(addedDamage);
        CustomArrowLooseEvent event = new CustomArrowLooseEvent(player, itemStack, world, power, reishiArrow);
        MinecraftForge.EVENT_BUS.post(event);
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

    public static float getPowerForTime(int time) {
        float power = (float)time / 40.0F;
        power = (power * power + power * 2.0F) / 3.0F;
        if (power > 1.0F) {
            power = 1.0F;
        }

        return power;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 72000;
    }
}
