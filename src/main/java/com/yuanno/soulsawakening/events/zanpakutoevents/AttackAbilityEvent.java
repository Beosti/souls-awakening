package com.yuanno.soulsawakening.events.zanpakutoevents;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AttackAbilityEvent {

    @SubscribeEvent
    public static void onAttackEvent(AttackEntityEvent event)
    {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        if (target instanceof LivingEntity)
        {
            LivingEntity livingEntityTarget = (LivingEntity) target;
            if (player.getMainHandItem().getItem() instanceof ZanpakutoItem)
            {
                ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
                if (zanpakutoItem.getZanpakutoState().equals(ZanpakutoItem.STATE.SHIKAI)) // if the zanpakuto is in shikai state
                {
                    for (Ability ability : zanpakutoItem.getAbilities())
                    {
                        if (ability.getActivationType().equals(Ability.ActivationType.ATTACK))
                        {
                            ability.activate(livingEntityTarget);
                        }
                    }
                }
            }
        }
    }
}
