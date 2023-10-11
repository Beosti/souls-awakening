package com.yuanno.soulsawakening.ability.elements.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class BiteAbility extends Ability {
    public static final BiteAbility INSTANCE = new BiteAbility();
    private final static DamageSource BITE_DAMAGE = new ModDamageSource("bite").setSourceTypes(SourceType.SPIKE).setSourceElement(SourceElement.NONE);
    public BiteAbility()
    {
        this.setName("Bite");
        this.setCooldown(5);
        this.setMaxCooldown(5);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setState(STATE.READY);
        this.setPassive(false);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity player)
    {
        target.hurt(BITE_DAMAGE, 6);
    }
}
