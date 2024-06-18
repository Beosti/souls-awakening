package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.SoulSocietyKeyAbility;
import com.yuanno.soulsawakening.abilities.elements.fire.*;
import com.yuanno.soulsawakening.abilities.elements.heal.*;
import com.yuanno.soulsawakening.abilities.elements.lunar.*;
import com.yuanno.soulsawakening.abilities.elements.normal.NormalBuffAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.AdrenalineCloudAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.VenomousCloudAbility;
import com.yuanno.soulsawakening.abilities.elements.shadow.*;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderStepAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderStrikeAbility;
import com.yuanno.soulsawakening.abilities.elements.water.AquaSlashAbility;
import com.yuanno.soulsawakening.abilities.elements.water.TidalWaveAbility;
import com.yuanno.soulsawakening.abilities.elements.water.WaterPrisonAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.GaleForceAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.WhirldWindDanceAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.WindAttackAbility;
import com.yuanno.soulsawakening.abilities.hollow.*;
import com.yuanno.soulsawakening.abilities.kido.bakudo.HainawaAbility;
import com.yuanno.soulsawakening.abilities.kido.bakudo.SaiAbility;
import com.yuanno.soulsawakening.abilities.kido.bakudo.SekiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ByakuraiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShakkahoAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.TsuzuriRaidenAbility;
import com.yuanno.soulsawakening.abilities.quincy.*;
import com.yuanno.soulsawakening.abilities.quincy.bow.PiercingArrowAbility;
import com.yuanno.soulsawakening.abilities.quincy.bow.StrongArrowAbility;
import com.yuanno.soulsawakening.abilities.quincy.rod.ExplodingBobberAbility;
import com.yuanno.soulsawakening.abilities.quincy.rod.WeakeningBobberAbility;
import com.yuanno.soulsawakening.abilities.quincy.spear.SpearStrikeAbility;
import com.yuanno.soulsawakening.abilities.quincy.spear.SpearThrustAbility;
import com.yuanno.soulsawakening.abilities.quincy.sword.SwordConcentrationAbility;
import com.yuanno.soulsawakening.abilities.quincy.sword.SwordSlashAbility;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.abilities.elements.poison.PoisonAttackAbility;
import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

import java.util.Arrays;
import java.util.Objects;

public class ModAbilities {

    public static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(ModRegistries.ABILITIES, Main.MODID);
    public static final Ability[] DARK_ZANPAKUTO = new Ability[] {DarkStepAbility.INSTANCE, ShadowAttackAbility.INSTANCE, UmbralCloakAbility.INSTANCE, DarkBallAbility.INSTANCE, ShadowCloneAbility.INSTANCE};
    public static final Ability[] FIRE_ZANPAKUTO = new Ability[] {FireAttackAbility.INSTANCE, FireWaveAbility.INSTANCE, FireBallAbility.INSTANCE, FireCoatAbility.INSTANCE, FireProwessAbility.INSTANCE};
    public static final Ability[] HEAL_ZANPAKUTO = new Ability[] {HealingTouchAbility.INSTANCE, RevitilazingAuraAbility.INSTANCE, SelfHealingAbility.INSTANCE, SecondChanceAbility.INSTANCE, DamageHealAbility.INSTANCE};
    public static final Ability[] THUNDER_ZANPAKUTO = new Ability[] {ThunderStepAbility.INSTANCE, ThunderAttackAbility.INSTANCE, ThunderStrikeAbility.INSTANCE};
    public static final Ability[] LUNAR_ZANPAKUTO = new Ability[] {LunarBlessingAbility.INSTANCE, LunarCrescentAbility.INSTANCE, LunarWaveAbility.INSTANCE, LunarBlindnessAbility.INSTANCE, MoonLightAbility.INSTANCE};
    public static final Ability[] NORMAL_ZANPAKUTO = new Ability[] {NormalBuffAbility.INSTANCE};
    public static final Ability[] POISON_ZANPAKUTO = new Ability[] {PoisonAttackAbility.INSTANCE, VenomousCloudAbility.INSTANCE, AdrenalineCloudAbility.INSTANCE};
    public static final Ability[] WATER_ZANPAKUTO = new Ability[] {AquaSlashAbility.INSTANCE, TidalWaveAbility.INSTANCE, WaterPrisonAbility.INSTANCE};
    public static final Ability[] WIND_ZANPAKUTO = new Ability[] {GaleForceAbility.INSTANCE, WhirldWindDanceAbility.INSTANCE, WindAttackAbility.INSTANCE};
    public static final Ability[] HOLLOW = new Ability[] {SlashAbility.INSTANCE, BiteAbility.INSTANCE, CeroAbility.INSTANCE, HollowRegenerationAbility.INSTANCE, GargantaAbility.INSTANCE, VastoHollowAbility.INSTANCE};
    public static final Ability[] SHINIGAMI = new Ability[] {SoulSocietyKeyAbility.INSTANCE};
    public static final Ability[] HADO = new Ability[] {ShoAbility.INSTANCE, ByakuraiAbility.INSTANCE, ShakkahoAbility.INSTANCE, TsuzuriRaidenAbility.INSTANCE};
    public static final Ability[] BAKUDO = new Ability[] {SaiAbility.INSTANCE, HainawaAbility.INSTANCE, SekiAbility.INSTANCE};
    public static final Ability[] QUINCY = new Ability[] {StrongArrowAbility.INSTANCE, PiercingArrowAbility.INSTANCE, BlutStrengthAbility.INSTANCE, ExplodingBobberAbility.INSTANCE, WeakeningBobberAbility.INSTANCE, SpearThrustAbility.INSTANCE, SpearStrikeAbility.INSTANCE, SwordSlashAbility.INSTANCE, SwordConcentrationAbility.INSTANCE, ShadowSoulSocietyAbility.INSTANCE};
    public static void register(IEventBus eventBus)
    {
        registerAbilities(DARK_ZANPAKUTO);
        registerAbilities(FIRE_ZANPAKUTO);
        registerAbilities(HEAL_ZANPAKUTO);
        registerAbilities(THUNDER_ZANPAKUTO);
        registerAbilities(LUNAR_ZANPAKUTO);
        registerAbilities(NORMAL_ZANPAKUTO);
        registerAbilities(POISON_ZANPAKUTO);
        registerAbilities(WATER_ZANPAKUTO);
        registerAbilities(WIND_ZANPAKUTO);
        registerAbilities(HOLLOW);
        registerAbilities(HADO);
        registerAbilities(BAKUDO);
        registerAbilities(SHINIGAMI);
        registerAbilities(QUINCY);
    }

    private static void registerAbilities(Ability[] abilities)
    {
        Arrays.stream(abilities).filter(Objects::nonNull).forEach(abl -> registerAbility(abl));
    }

    public static <T extends Ability> Ability registerAbility(Ability ability)
    {
        String resourceName = Beapi.getResourceName(ability.getName());
        BeRegistry.getLangMap().put("ability." + Main.MODID + "." + resourceName, ability.getName());

        final ResourceLocation key = new ResourceLocation(Main.MODID, resourceName);
        RegistryObject<Ability> ret = RegistryObject.of(key, ModRegistries.ABILITIES);
        if(!ABILITIES.getEntries().contains(ret))
        {
            ABILITIES.register(resourceName, () -> ability);
        }

        return ability;
    }


}
