package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdrenalineCloudAbility extends Ability implements IRightClickAbility, IWaveAbility {

    public static final AdrenalineCloudAbility INSTANCE = new AdrenalineCloudAbility();
    public static final EffectInstance MOVEMENT_SPEED = new EffectInstance(Effects.MOVEMENT_SPEED, 240, 1);
    public static final EffectInstance DAMAGE_BOOST = new EffectInstance(Effects.DAMAGE_BOOST, 240, 1);
    List<EffectInstance> effectInstances = new ArrayList<>(
            Arrays.asList(MOVEMENT_SPEED, DAMAGE_BOOST)
    );
    public AdrenalineCloudAbility()
    {
        this.setName("Adrenaline Cloud");
        this.setMaxCooldown(17);
        this.setState(STATE.READY);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public int getRadius()
    {
        return 10;
    }
    @Override
    public boolean getShift()
    {
        return true;
    }

    @Override
    public ArrayList<EffectInstance> getEffectInstances() {
        return (ArrayList<EffectInstance>) effectInstances;
    }
}
