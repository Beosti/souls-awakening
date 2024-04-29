package com.yuanno.soulsawakening.items.spiritweapon;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.ZanpakutoWakizashiItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
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

public class ReishiSwordItem extends SwordItem {
    public ReishiSwordItem() {
        super(ItemTier.IRON, 5, -1f, new Item.Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        if (Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6A sword made out of surrounding reishi"));
        }
        else if (!Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("§6Hold " + "§eSHIFT " + "§6" + "for more Information!"));
        }
    }
    @Mod.EventBusSubscriber(modid = Main.MODID)
    public static class ReishiSwordEvents
    {

        @SubscribeEvent
        public static void onAttackWithSword(LivingDamageEvent event)
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

                    if (!(livingEntity.getMainHandItem().getItem().asItem() instanceof ReishiSwordItem))
                        return;

                    if (entityStats.getReiatsuPoints() <= 0)
                        return;
                    event.setAmount((float) (event.getAmount() + entityStats.getReiatsuPoints() / 2));
                }
            }
        }
        /**
         * Adds zanjutsu tooltip to all the swords
         * @param event
         */
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void onToolTipRender(ItemTooltipEvent event)
        {
            PlayerEntity player = event.getPlayer();

            if (player == null) {
                return;
            }
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (event.getItemStack().getItem() instanceof ReishiSwordItem && entityStats.getRace().equals(ModValues.QUINCY) && entityStats.getReiatsuPoints() > 0)
            {
                StringTextComponent damageBonus = new StringTextComponent(TextFormatting.BLUE + "" + new TranslationTextComponent("Reiatsu Bonus Damage: " + Math.floor(entityStats.getReiatsuPoints() / 2)).getString());
                if (!event.getToolTip().contains(damageBonus)) {
                    event.getToolTip().add(new StringTextComponent(""));
                    event.getToolTip().add(damageBonus);
                }
            }
        }
    }
}
