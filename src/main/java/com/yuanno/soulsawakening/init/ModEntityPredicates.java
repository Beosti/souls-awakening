package com.yuanno.soulsawakening.init;

import com.google.common.base.Predicates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.EntityPredicates;

import java.util.function.Predicate;

public class ModEntityPredicates {

    public static Predicate<Entity> getEnemyFactions(LivingEntity entity) {

        // Should probably never be null but just in case some thrower disconnected or some other random issue happens, make sure to got it covered
        if(entity == null) {
            return Predicates.alwaysTrue();
        }

        return target -> {

            if (!(target instanceof LivingEntity)) {
                return false;
            }

            if (entity == target) {
                return false;
            }

            // Mounted entities are considered allies because of the amount of fuck ups that it can cause
            if(target.getVehicle() != null && target.getVehicle().equals(entity)) {
                return false;
            }

            boolean isSpectating = !EntityPredicates.NO_SPECTATORS.test(target);
            if (isSpectating) {
                return false;
            }

            /*
            IEntityStats livingProps = EntityStatsCapability.get(entity);
            if (livingProps.isRogue()) {
                return true;
            }

            // Tamed entities by the entity should be counted as friendlies
            if(isTamedBy(entity).test(target)) {
                return false;
            }

            ExtendedWorldData worldData = ExtendedWorldData.get(entity.level);
            IEntityStats targetProps = EntityStatsCapability.get((LivingEntity) target);

            if (livingProps.isPirate() && targetProps.isPirate()) {
                // TODO - Open World NPCs don't share a crew at the moment so they're all attack
                // each other, needs a better solution
                if (entity instanceof AbstractPirateEntity && target instanceof AbstractPirateEntity) {
                    return false;
                }
                if (entity instanceof OPEntity && target instanceof OPEntity) {
                    if(((OPEntity)entity).getCrew().equals(((OPEntity)target).getCrew())) {
                        return false;
                    }
                }
                Crew livingCrew = worldData.getCrewWithMember(entity.getUUID());
                Crew targetCrew = worldData.getCrewWithMember(target.getUUID());
                return livingCrew == null || targetCrew == null || !livingCrew.equals(targetCrew);
            }

            boolean isCivilian = target instanceof AbstractVillagerEntity || targetProps.isCivilian();

            if (livingProps.isMarine() || livingProps.isBountyHunter()) {
                if (targetProps.isMarine() || targetProps.isBountyHunter() || isCivilian) {
                    return false;
                }
            }

            if (livingProps.isRevolutionary()) {
                if (targetProps.isRevolutionary() || isCivilian) {
                    return false;
                }
            }

            if (livingProps.isBandit()) {
                // There are no bandit players so this is just for npcs of the same type
                if (targetProps.isBandit()) {
                    return false;
                }
            }

             */

            return true;
        };
    }
}
