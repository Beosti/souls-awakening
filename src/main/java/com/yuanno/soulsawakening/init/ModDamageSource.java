package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import net.minecraft.util.DamageSource;

public class ModDamageSource extends DamageSource {

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
}
