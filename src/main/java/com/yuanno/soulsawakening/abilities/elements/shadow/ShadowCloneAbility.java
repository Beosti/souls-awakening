package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.ISummonAbility;
import com.yuanno.soulsawakening.entity.CloneEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ShadowCloneAbility extends Ability implements IRightClickAbility, ISummonAbility {

    public static final ShadowCloneAbility INSTANCE = new ShadowCloneAbility();

    public ShadowCloneAbility()
    {
        this.setName("Shadow Clone");
        this.setDescription("Spawns a bunch of shadow clones to confuse the enemy");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public float getSummonAmount() {
        return 4;
    }

    @Override
    public Entity getSummon(PlayerEntity player) {
        CloneEntity cloneEntity = new CloneEntity(EntityType., player.level);
        return cloneEntity;
    }
}
