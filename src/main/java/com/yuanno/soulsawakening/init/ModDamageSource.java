package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.AbilityDamageSource;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class ModDamageSource extends DamageSource {

    public static final DamageSource ACID = new ModDamageSource("acid").setSourceElement(SourceElement.ACID).bypassArmor().setMagic();
    private SourceElement element = SourceElement.NONE;
    private SourceType[] sourceTypes = {};

    public ModDamageSource(DamageSource damageSource) {
        super(damageSource.msgId);

        if (damageSource instanceof ModDamageSource)
        {
            ModDamageSource modDamageSource = ((ModDamageSource) damageSource);
            modDamageSource.setSourceElement(this.element);
            modDamageSource.setSourceTypes(this.sourceTypes);
        }
    }
    public ModDamageSource(String damageTypeIn, boolean bypassArmor, boolean isFireDamage, boolean isExplosive)
    {
        super(damageTypeIn);

        if (bypassArmor)
            this.bypassArmor();
        if (isFireDamage)
            this.setIsFire();
        if (isExplosive)
            this.setExplosion();
    }
    public ModDamageSource(String damageType)
    {
        this(damageType, false, false, false);
    }
    public ModDamageSource setSourceElement(SourceElement element)
    {
        this.element = element;

        return this;
    }
    public ModDamageSource setSourceTypes(SourceType... sourceTypes)
    {
        this.sourceTypes = sourceTypes;
        return this;
    }



    public static AbilityDamageSource causeAbilityDamage(LivingEntity player, Ability ability)
    {
        return new AbilityDamageSource("ability", player, ability);
    }

}
