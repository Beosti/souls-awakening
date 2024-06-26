package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AttackAbilityEvents {

    @SubscribeEvent
    public static void onAttackEvent(AttackEntityEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;

        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++) {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
            if (!(ability instanceof IAttackAbility)) // check if the ability is a right click ability
                continue;
            if (ability instanceof IContinuousAbility && !ability.getState().equals(Ability.STATE.CONTINUOUS))
                continue;
            else if (!(ability instanceof IContinuousAbility) && !((IAttackAbility) ability).getAttackPassive() && !ability.getState().equals(Ability.STATE.READY))
                continue;
            if (!ability.getDependency().check(player))
                continue;
            if (event.getTarget() instanceof LivingEntity)
            {
                LivingEntity livingEntity = (LivingEntity) event.getTarget();
                AbilityUseEvent.Per abilityUseEventPer = new AbilityUseEvent.Per(player, ability, livingEntity);
                MinecraftForge.EVENT_BUS.post(abilityUseEventPer);
            }
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            return;
        }


    }
}
