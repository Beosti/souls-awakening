package com.yuanno.soulsawakening.events.hollow;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.CeroAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HollowSpecificEvents {

    @SubscribeEvent
    public static void hollowEvolutionEvent(HollowEvolutionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);

        switch (entityStats.getRank()) {
            case (ModValues.BASE):
                entityStats.setRank(ModValues.GILLIAN);
                abilityData.addUnlockedAbility(CeroAbility.INSTANCE);
                break;
                case (ModValues.GILLIAN):
                    entityStats.setRank(ModValues.ADJUCHA);
                    break;
                    case (ModValues.ADJUCHA):
                        entityStats.setRank(ModValues.VASTO_LORDE);
                        break;
                        case (ModValues.VASTO_LORDE):
                            entityStats.setRank(ModValues.ARRANCAR);
                            break;
            }
            entityStats.setHollowPoints(0);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);


    }
}
