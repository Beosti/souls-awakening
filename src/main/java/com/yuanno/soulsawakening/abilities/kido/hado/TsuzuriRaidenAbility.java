package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.ability.api.KidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;

public class TsuzuriRaidenAbility extends KidoAbility implements IContinuousAbility {
    public static final TsuzuriRaidenAbility INSTANCE = new TsuzuriRaidenAbility();

    public TsuzuriRaidenAbility()
    {
        this.setName("Tsuzuri Raiden");
        this.setDescription("Envelops your weapon with lightning, dealing lightning damage and electrocuting the enemy");
        this.setMaxCooldown(10);
        this.setIncantation("Thy bless my weapon with lightning Hado number 11 Tsuzuri Raiden");
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {

        return true;
    }
    @Override
    public void duringContinuity(PlayerEntity player) {

    }
    @Override
    public boolean endContinuity(PlayerEntity player) {

        return true;
    }

}
