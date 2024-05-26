package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
