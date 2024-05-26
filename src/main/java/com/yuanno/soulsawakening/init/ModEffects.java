package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.effects.*;
import com.yuanno.soulsawakening.particles.api.BindParticleEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);
    public static final RegistryObject<Effect> SAI = EFFECTS.register("sai", SaiEffect::new);
    public static final RegistryObject<Effect> ELECTROCUTED = EFFECTS.register("electrocuted", ElectrocutedEffect::new);
    public static final RegistryObject<Effect> VANISH_INVISIBILITY = EFFECTS.register("vanish", VanishEffect::new);
    public static final RegistryObject<Effect> HOLLOW_ACID = EFFECTS.register("hollow_acid", HollowAcidEffect::new);
    public static final RegistryObject<Effect> HAINAWA = EFFECTS.register("hainawa", HainawaEffect::new);
    public static final RegistryObject<Effect> SPEAR_THRUST = EFFECTS.register("spear_thrust", SpearThrustEffect::new);
    public static final RegistryObject<Effect> SPEAR_STRIKE = EFFECTS.register("spear_strike", SpearStrikeEffect::new);
    public static final RegistryObject<Effect> SWORD_CONCENTRATION = EFFECTS.register("sword_concentration", SwordConcentrationEffect::new);
    public static final RegistryObject<Effect> IN_EVENT = EFFECTS.register("in_event", InEventEffect::new);
    public static final RegistryObject<Effect> PASSIF = EFFECTS.register("passive", PassiveEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);

    }
}
