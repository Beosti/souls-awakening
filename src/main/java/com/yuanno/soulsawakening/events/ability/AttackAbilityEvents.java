package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.Entity;
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
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
            if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
            {
                ItemStack zanpakutoItem = player.getMainHandItem();
                if (!zanpakutoItem.getItem().equals(ModItems.ZANPAKUTO.get().getItem()))
                    return;
                String state = zanpakutoItem.getTag().getString("zanpakutoState");
                if (!state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
                    return;
            }
            if (!(ability instanceof IAttackAbility)) // check if the ability is a right click ability
                continue;
            Entity entity = event.getTarget();
            if (!(entity instanceof LivingEntity))
                return;
            LivingEntity livingEntity = (LivingEntity) entity;
            AbilityUseEvent.Per abilityUseEventPer = new AbilityUseEvent.Per(player, ability, livingEntity);
            MinecraftForge.EVENT_BUS.post(abilityUseEventPer);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            return;
        }
    }
}
