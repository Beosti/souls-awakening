package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TickAbilityEvent {

    @SubscribeEvent
    public static void onTickEventAbility(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IEntityStats entityStats = EntityStatsCapability.get(player);

        if (player.tickCount % 20 == 0)
        {
            if (entityStats.getRace().equals(ModValues.SHINIGAMI) && player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
            {
                ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
                for (Ability ability : zanpakutoItem.getAbilities())
                {
                    if (!ability.getPassive() && !ability.isReady() && ability.getCooldown() == 0)
                    {
                        ability.isReadySet(true);
                    }
                    else if (!ability.getPassive() && !ability.isReady() && ability.getCooldown() != 0)
                    {
                        ability.setCooldown(ability.getCooldown() - 1);
                    }
                }
            }
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }
    }
}
