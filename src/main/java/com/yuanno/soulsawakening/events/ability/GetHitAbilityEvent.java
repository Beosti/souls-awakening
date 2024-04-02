package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IGetHitAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class GetHitAbilityEvent {

    @SubscribeEvent
    public static void onHitPlayer(LivingHurtEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();
        if (!(livingEntity instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) livingEntity;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
            if (ability instanceof IContinuousAbility && !(ability.getState().equals(Ability.STATE.CONTINUOUS)))
                continue;
            if (!(ability instanceof IGetHitAbility))
                continue;
            if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
            {
                ItemStack zanpakutoItem = player.getMainHandItem();
                if (!zanpakutoItem.getItem().equals(ModItems.ZANPAKUTO.get().getItem()))
                    return;
                String state = zanpakutoItem.getTag().getString("zanpakutoState");
                if (!state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
                    return;
            }
            if (!(event.getSource().getEntity() instanceof LivingEntity))
                continue;
            LivingEntity livingEntityAttacker = (LivingEntity) event.getSource().getEntity();
            AbilityUseEvent.Per abilityUseEvent = new AbilityUseEvent.Per(player, ability, livingEntityAttacker);
            MinecraftForge.EVENT_BUS.post(abilityUseEvent);
            event.setCanceled(((IGetHitAbility) ability).getCancelEvent());
            return;
        }
    }
}
