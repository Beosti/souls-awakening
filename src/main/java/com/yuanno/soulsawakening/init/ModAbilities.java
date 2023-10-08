package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.elements.fire.FireAttackAbility;
import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

import java.util.Arrays;
import java.util.Objects;

public class ModAbilities {


    public static final DeferredRegister<Ability<?>> ABILITIES= DeferredRegister.create(ModRegistries.ABILITIES, Main.MODID);

    public static final Ability[] FIRE_ZANPAKUTO = new Ability[] {FireAttackAbility.INSTANCE};

    public static void register(IEventBus eventBus)
    {
        registerAbilities(FIRE_ZANPAKUTO);
    }

    private static void registerAbilities(Ability[] abilities)
    {
        Arrays.stream(abilities).filter(Objects::nonNull).forEach(abl -> registerAbility(abl));

    }

    public static <T extends Ability<T>> Ability<T> registerAbility(Ability<T> ability)
    {
        String resourceName = Beapi.getResourceName(ability.getName());
        BeRegistry.getLangMap().put("ability." + Main.MODID + "." + resourceName, ability.getName());

        final ResourceLocation key = new ResourceLocation(Main.MODID, resourceName);
        RegistryObject<Ability<?>> ret = RegistryObject.of(key, ModRegistries.ABILITIES);
        if(!ABILITIES.getEntries().contains(ret))
        {
            ABILITIES.register(resourceName, () -> ability);
            //ability.setIcon(key);
        }

        return ability;
    }


}
