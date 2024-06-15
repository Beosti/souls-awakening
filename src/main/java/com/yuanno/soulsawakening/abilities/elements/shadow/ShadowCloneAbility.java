package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.ISummonAbility;
import com.yuanno.soulsawakening.entities.summons.shadowsummons.ShadowCloneEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class ShadowCloneAbility extends Ability implements IRightClickAbility, ISummonAbility {
    public static final ShadowCloneAbility INSTANCE = new ShadowCloneAbility();

    public ShadowCloneAbility()
    {
        this.setName("Shadow Clone");
        this.setDescription("Creates a few shadow clones attacking your enemies");
        this.setMaxCooldown(12);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean getShift() {
        return true;
    }

    @Override
    public float getSummonAmount() {
        return 4;
    }

    @Override
    public Entity getSummon(PlayerEntity player) {
        return new ShadowCloneEntity(player.level);
    }
}
