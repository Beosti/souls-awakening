package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.AbilityDataProvider;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.EntityStatsProvider;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.data.misc.MiscDataProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModCapabilities {

    public static void init()
    {
        EntityStatsCapability.register();
        AbilityDataCapability.register();
        MiscDataCapability.register();
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() == null)
            return;

        if (event.getObject() instanceof LivingEntity)
        {
            event.addCapability(new ResourceLocation(Main.MODID, "entity_stats"), new EntityStatsProvider());
            event.addCapability(new ResourceLocation(Main.MODID, "misc_data"), new MiscDataProvider());
        }
        if (event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Main.MODID, "ability_data"), new AbilityDataProvider());
        }
    }
}
