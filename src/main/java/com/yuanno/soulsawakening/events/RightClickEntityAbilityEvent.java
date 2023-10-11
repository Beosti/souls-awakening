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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RightClickEntityAbilityEvent {

    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event)
    {
        PlayerEntity player = event.getPlayer();
        if (!(event.getTarget() instanceof  LivingEntity))
            return;
        if (player.level.isClientSide)
            return;
        LivingEntity target = (LivingEntity) event.getTarget();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);

        // do something when the player is a shinigami and has shikai in hand
        if (entityStats.getRace().equals(ModValues.SHINIGAMI) && player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
        {
            ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
            if (zanpakutoItem.getZanpakutoState().equals(ZanpakutoItem.STATE.SHIKAI))
            {
                for (Ability ability : zanpakutoItem.getAbilities())
                {
                    if (ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_EMPTY))
                        ability.onRightClickEntity(target, player);
                }
            }
        }
        // do something when the player is a hollow
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
        {
            for (Ability ability : abilityData.getActiveAbilities())
            {
                System.out.println(ability);
                if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_ENTITY) || !ability.getState().equals(Ability.STATE.READY))
                    return;
                System.out.println(ability.getState());
                ability.onRightClickEntity(target, player);
                ability.setState(Ability.STATE.COOLDOWN);
                ability.setCooldown(ability.getMaxCooldown() / 20);

            }
        }


    }
}
