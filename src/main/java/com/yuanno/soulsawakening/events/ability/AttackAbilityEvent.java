package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        if (event.getPlayer().level.isClientSide)
            return;
        if (target instanceof LivingEntity)
        {
            LivingEntity livingEntityTarget = (LivingEntity) target;
            IAbilityData abilityData = AbilityDataCapability.get(player);
            for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
            {
                Ability ability = abilityData.getUnlockedAbilities().get(i);
                if (!(ability.getState().equals(Ability.STATE.READY))) // check if the ability is read
                    return;
                if (!(ability instanceof IAttackAbility)) // check if the ability is an attack ability
                    return;
                if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
                {
                    ItemStack zanpakutoItem = player.getMainHandItem();
                    String state = zanpakutoItem.getTag().getString("zanpakutoState");
                    if (state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
                        continue;
                    else // if not return
                        return;
                }
                IAttackAbility attackAbility = (IAttackAbility) abilityData.getUnlockedAbilities().get(i);
                attackAbility.activate(livingEntityTarget, player);
                ability.setState(Ability.STATE.COOLDOWN);
                ability.setCooldown(ability.getMaxCooldown() / 20);

            }
            // when a player is a shinigami check the zanpakuto
            /*
            if ((entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)) && (player.getMainHandItem().getItem() instanceof ZanpakutoItem))
            {

                ItemStack zanpakutoItem = player.getMainHandItem();
                String state = zanpakutoItem.getTag().getString("zanpakutoState"); // specifically get the stack tag to avoid bugs
                if (state.equals(ModValues.STATE.SHIKAI.name())) // if the zanpakuto is in shikai state
                    {
                        for (Ability ability : abilityData.getUnlockedAbilities())
                        {
                            if (!ability.getZanpakutoState().equals(ModValues.STATE.SHIKAI))
                                continue;
                            if (ability instanceof IAttackAbility)
                                ((IAttackAbility) ability).activate(livingEntityTarget, player);
                            else if (ability instanceof IPunchAbility && ability.getState().equals(Ability.STATE.READY))
                            {
                                ((IPunchAbility) ability).onHitEntity(livingEntityTarget, player);
                                ((IPunchAbility) ability).startCooldown(player);
                                ability.setState(Ability.STATE.COOLDOWN);
                                ability.setCooldown(ability.getMaxCooldown() / 20);
                            }
                        }
                    }
            }



            // when a player is a hollow
            else if (entityStats.getRace().equals(ModValues.HOLLOW))
            {
                for (Ability ability : abilityData.getUnlockedAbilities())
                {
                    if ((ability instanceof IAttackAbility)) {
                        ((IAttackAbility) ability).activate(livingEntityTarget, player);
                        double knockback = 1.5;
                        ((LivingEntity)livingEntityTarget).knockback((float)knockback * 0.5F, (double) MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.yRot * ((float)Math.PI / 180F))));
                    }
                }
            }

             */
        }
    }
}
