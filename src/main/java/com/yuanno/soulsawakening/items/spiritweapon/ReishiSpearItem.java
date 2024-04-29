package com.yuanno.soulsawakening.items.spiritweapon;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.TridentItem;
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

public class ReishiSpearItem extends TridentItem {
    public ReishiSpearItem() {
        super(new Properties().tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).rarity(Rarity.RARE).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        if (Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6A spear made out of surrounding reishi"));
        }
        else if (!Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6Hold " + "§eSHIFT " + "§6" + "for more Information!"));
        }
    }

    @Mod.EventBusSubscriber(modid = Main.MODID)
    public static class ReishiSpearEvents
    {
        public static final UUID REISHI_SPEAR_ATTACK_RANGE_ID = UUID.fromString("61fb0140-0610-11ef-9262-0242ac120002");
        @SubscribeEvent
        public static void onReishiSpearRangeBonus(ItemAttributeModifierEvent event)
        {
            ItemStack itemStack = event.getItemStack();
            if (event.getSlotType() != EquipmentSlotType.MAINHAND)
                return;
            if (itemStack.getItem().asItem() instanceof ReishiSpearItem)
            {
                AttributeModifier mod = new AttributeModifier(REISHI_SPEAR_ATTACK_RANGE_ID, "Spear Bonus", 0.4, AttributeModifier.Operation.ADDITION);

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

                    if (!(livingEntity.getMainHandItem().getItem().asItem() instanceof ReishiSpearItem))
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
            if (event.getItemStack().getItem() instanceof ReishiSpearItem && entityStats.getRace().equals(ModValues.QUINCY) && entityStats.getReiatsuPoints() > 0)
            {
                StringTextComponent damageBonus = new StringTextComponent(TextFormatting.BLUE + "" + new TranslationTextComponent("Reiatsu Bonus Damage: " + Math.floor(entityStats.getReiatsuPoints() / 4)).getString());
                if (!event.getToolTip().contains(damageBonus)) {
                    event.getToolTip().add(new StringTextComponent(""));
                    event.getToolTip().add(damageBonus);
                }
            }
        }
    }
}
