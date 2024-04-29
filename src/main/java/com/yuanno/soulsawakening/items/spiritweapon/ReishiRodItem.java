package com.yuanno.soulsawakening.items.spiritweapon;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.misc.ReishiFishingBobberEntity;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.ZanpakutoWakizashiItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ReishiRodItem extends FishingRodItem {
    public ReishiRodItem() {
        super(new Properties().tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).rarity(Rarity.RARE).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        if (Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6A fishing rod made out of surrounding reishi"));
        }
        else if (!Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6Hold " + "§eSHIFT " + "§6" + "for more Information!"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack lvt_4_1_ = player.getItemInHand(hand);
        int enchantmentSpeed;
        if (player.fishing != null) {
            if (!world.isClientSide) {
                enchantmentSpeed = player.fishing.retrieve(lvt_4_1_);
                lvt_4_1_.hurtAndBreak(0, player, (p_220000_1_) -> {
                    p_220000_1_.broadcastBreakEvent(hand);
                });
            }

            world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!world.isClientSide) {
                enchantmentSpeed = EnchantmentHelper.getFishingSpeedBonus(lvt_4_1_);
                int enchantmentBonus = EnchantmentHelper.getFishingLuckBonus(lvt_4_1_);
                world.addFreshEntity(new ReishiFishingBobberEntity(player, world, enchantmentBonus, enchantmentSpeed));
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return ActionResult.sidedSuccess(lvt_4_1_, world.isClientSide());
    }

    @Mod.EventBusSubscriber(modid = Main.MODID)
    public static class ReishiFishingRodEvents
    {
        public static final UUID FISHING_ROD_ATTACK_RANGE_ID = UUID.fromString("61fb0140-0610-11ef-9262-0242ac120002");

        @SubscribeEvent
        public static void fishingRodAttackRangeBonus(ItemAttributeModifierEvent event)
        {
            ItemStack itemStack = event.getItemStack();
            if (event.getSlotType() != EquipmentSlotType.MAINHAND)
                return;
            if (itemStack.getItem().asItem() instanceof ReishiRodItem)
            {
                AttributeModifier mod = new AttributeModifier(FISHING_ROD_ATTACK_RANGE_ID, "Rod Bonus", 0.4, AttributeModifier.Operation.ADDITION);

                event.addModifier(ModAttributes.ATTACK_RANGE.get(), mod);
            }
        }
        @SubscribeEvent
        public static void onAttackWithRod(LivingDamageEvent event)
        {
            if (event.getEntityLiving() != null && !event.getEntityLiving().level.isClientSide)
            {
                if (event.getSource().getDirectEntity() != null)
                {
                    if (!(event.getSource().getDirectEntity() instanceof LivingEntity))
                        return;

                    LivingEntity livingEntity = (LivingEntity) event.getSource().getDirectEntity();
                    IEntityStats entityStats = EntityStatsCapability.get(livingEntity);
                    if (!entityStats.getRace().equals(ModValues.QUINCY))
                        return;

                    if (!(livingEntity.getMainHandItem().getItem().asItem() instanceof ReishiRodItem))
                        return;

                    if (entityStats.getReiatsuPoints() <= 0)
                        return;
                    event.setAmount((float) (event.getAmount() + entityStats.getReiatsuPoints() / 4));
                }
            }
        }
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void onToolTipRender(ItemTooltipEvent event)
        {
            PlayerEntity player = event.getPlayer();

            if (player == null) {
                return;
            }
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (event.getItemStack().getItem() instanceof ReishiRodItem && entityStats.getRace().equals(ModValues.QUINCY) && entityStats.getReiatsuPoints() > 0)
            {
                StringTextComponent damageBonusHook = new StringTextComponent(TextFormatting.BLUE + "" + new TranslationTextComponent("Reiatsu Hook In Bonus Damage: " + Math.floor(entityStats.getReiatsuPoints() / 4)).getString());
                if (!event.getToolTip().contains(damageBonusHook)) {
                    event.getToolTip().add(new StringTextComponent(""));
                    event.getToolTip().add(damageBonusHook);
                }
                StringTextComponent damageBonus = new StringTextComponent(TextFormatting.BLUE + "" + new TranslationTextComponent("Reiatsu Bonus Damage: " + Math.floor(entityStats.getReiatsuPoints() / 4)).getString());
                if (!event.getToolTip().contains(damageBonus)) {
                    event.getToolTip().add(new StringTextComponent(""));
                    event.getToolTip().add(damageBonus);
                }
            }
        }
    }
}
