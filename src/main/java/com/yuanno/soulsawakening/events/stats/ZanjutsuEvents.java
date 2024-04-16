package com.yuanno.soulsawakening.events.stats;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

/**
 * Class that handles everything related to zanjutsu
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class ZanjutsuEvents {

    /**
     * Handles everything that happens with zanjutsu when attacking
     * I use this event and not {@link net.minecraftforge.event.entity.living.LivingAttackEvent} because you can't change the damage in the other event
     * @param event
     */
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
                if (!entityStats.getRace().equals(ModValues.SHINIGAMI))
                    return;

                if (!(livingEntity.getMainHandItem().getItem().asItem() instanceof SwordItem))
                    return;

                LivingEntity target = event.getEntityLiving();
                if (livingEntity instanceof PlayerEntity)
                    experienceHandler((PlayerEntity) livingEntity, entityStats);
                if (entityStats.getShinigamiStats().getZanjutsuPoints() <= 0)
                    return;
                event.setAmount((float) (event.getAmount() + entityStats.getShinigamiStats().getZanjutsuPoints() / 2));
            }
        }
    }

    // handles the experience gain of the player for zanjutsu
    public static void experienceHandler(PlayerEntity player, IEntityStats entityStats)
    {
        double currentZanjutsu = entityStats.getShinigamiStats().getZanjutsuPoints();
        int amount = 3; // upping this value will make it slower, the lower this value the faster it'll be
        double amountToAdd = (0.5 * Math.pow(0.5, (currentZanjutsu - 2) / amount));
        entityStats.getShinigamiStats().alterZanjutsuPoints(amountToAdd);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
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
        if (event.getItemStack().getItem() instanceof SwordItem && (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)) && entityStats.getShinigamiStats().getZanjutsuPoints() > 0)
        {
            StringTextComponent damageBonus = new StringTextComponent(TextFormatting.WHITE + "" + new TranslationTextComponent("Zanjutsu Bonus Damage: " + Math.floor(entityStats.getShinigamiStats().getZanjutsuPoints() / 2)).getString());
            if (!event.getToolTip().contains(damageBonus)) {
                event.getToolTip().add(new StringTextComponent(""));
                event.getToolTip().add(damageBonus);
            }
        }
    }
}
