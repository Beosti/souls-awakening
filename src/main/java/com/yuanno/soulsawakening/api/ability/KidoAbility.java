package com.yuanno.soulsawakening.api.ability;

public class KidoAbility extends Ability {
    private String incantation = "";
    private boolean canUseWithoutIncantation = false;


    public void setCanUseWithoutIncantation(boolean canUseWithoutIncantation)
    {
        this.canUseWithoutIncantation = canUseWithoutIncantation;
    }
    public boolean getCanUseWithoutIncantation()
    {
        return this.canUseWithoutIncantation;
    }
    public void setIncantation(String incantation)
    {
        this.incantation = incantation;
    }
    public String getIncantation()
    {
        return this.incantation;
    }
}
