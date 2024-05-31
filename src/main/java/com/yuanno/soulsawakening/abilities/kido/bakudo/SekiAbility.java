package com.yuanno.soulsawakening.abilities.kido.bakudo;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.KidoAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class SekiAbility extends KidoAbility implements IContinuousAbility, IGetHitAbility {
    public static final SekiAbility INSTANCE = new SekiAbility();

    public SekiAbility()
    {
        this.setName("Seki");
        this.setDescription("Creates a blue orb, protecting you from the next physical attack and knocking back the enemy");
        this.setMaxCooldown(10);
        this.setIncantation("thy hands will not touch my skin bakudo number 8 seki");
        this.setSubCategory(SubCategory.BAKUDO);
    }

    @Override
    public void getHit(PlayerEntity player, LivingEntity attacker) {
        attacker.knockback(3f, 3f, 3f);
    }

    @Override
    public boolean getEndAfterUse() {
        return true;
    }

    @Override
    public boolean getCancelEvent() {
        return true;
    }
}
