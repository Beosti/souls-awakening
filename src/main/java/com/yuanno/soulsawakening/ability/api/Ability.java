package com.yuanno.soulsawakening.ability.api;

import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Locale;

// TODO make a continuous ability
public class Ability<T> extends ForgeRegistryEntry<Ability<?>> {
    private String name;
    private double cooldown;
    private ActivationType activationType;
    private boolean isPassive;
    private boolean isReady = true;
    private double maxCooldown;
    private STATE state;
    private ModResources.STATE zanpakutoState;
    private boolean isShown = true;

    public Ability(String name, int cooldown, int maxCooldown, ActivationType activationType) {
        this.name = name;
        this.cooldown = cooldown;
        this.maxCooldown = maxCooldown;
        this.activationType = activationType;
    }
    public Ability(String name, int cooldown, int maxCooldown, ActivationType activationType, boolean isPassive) {
        this.name = name;
        this.cooldown = cooldown;
        this.maxCooldown = maxCooldown;
        this.activationType = activationType;
        this.isPassive = isPassive;
    }
    public Ability(String name, int cooldown, int maxCooldown, ActivationType activationType, boolean isPassive, boolean isShown) {
        this.name = name;
        this.cooldown = cooldown;
        this.maxCooldown = maxCooldown;
        this.activationType = activationType;
        this.isPassive = isPassive;
        this.isShown = isShown;
    }

    public Ability() {
        this.setState(STATE.READY);
    }


    public void setZanpakutoState(ModResources.STATE state)
    {
        this.zanpakutoState = state;
    }

    public ModResources.STATE getZanpakutoState()
    {
        return this.zanpakutoState;
    }

    public void duringCooldown()
    {
        if (this.isPassive || this.state.equals(STATE.READY))
            return;
        if (this.getCooldown() <= 0)
            this.setState(STATE.READY);
        else if (this.state.equals(STATE.COOLDOWN))
            this.alterCooldown(- 1);
    }

    public boolean isReady()
    {
        return this.isReady;
    }
    public void isReadySet(boolean isReady)
    {
        this.isReady = isReady;
    }
    public boolean getPassive()
    {
        return this.isPassive;
    }
    public void setPassive(boolean passive)
    {
        this.isPassive = passive;
    }
    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public double getMaxCooldown()
    {
        return maxCooldown;
    }
    public void setMaxCooldown(double maxCooldown)
    {
        this.maxCooldown = maxCooldown * 20;
    }
    public double getCooldown() {
        return cooldown;
    }
    public void alterCooldown(double cooldown)
    {
        this.cooldown += cooldown;
    }
    public void setCooldown(double cooldown)
    {
        this.cooldown = cooldown * 20;
    }

    public ActivationType getActivationType() {
        return activationType;
    }
    public void setActivationType(ActivationType activationType)
    {
        this.activationType = activationType;
    }

    public STATE getState()
    {
        return this.state;
    }

    public void setState(STATE state)
    {
        this.state = state;
    }

    public void setShown(boolean shown)
    {
        this.isShown = shown;
    }

    public boolean getShown()
    {
        return this.isShown;
    }
    public enum STATE {
        COOLDOWN, READY, PASSIVE
    }

    public CompoundNBT save() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("id", this.getRegistryName().toString());
        compoundNBT.putString("displayname", this.getName());
        compoundNBT.putDouble("cooldown", this.getCooldown());
        compoundNBT.putDouble("maxcooldown", this.getMaxCooldown());
        if (!(this instanceof IContinuousAbility))
            compoundNBT.putString("type", this.getActivationType().toString());
        compoundNBT.putString("state", this.getState().toString());

        return compoundNBT;
    }

    public void load(CompoundNBT compoundNBT)
    {
        this.setName(compoundNBT.getString("displayname"));
        int cooldown = (int) (compoundNBT.getDouble("cooldown") / 20);
        this.setCooldown(cooldown);
        int maxCooldown = (int) (compoundNBT.getDouble("maxcooldown") / 20);
        this.setMaxCooldown(maxCooldown);
        if (!(this instanceof IContinuousAbility))
            this.setActivationType(Ability.ActivationType.valueOf(compoundNBT.getString("type")));
        this.setState(Ability.STATE.valueOf(compoundNBT.getString("state")));
    }

    public enum ActivationType {
        RIGHT_CLICK_EMPTY,
        ATTACK,
        RIGHT_CLICK_BLOCK,
        SHIFT_RIGHT_CLICK,
        RIGHT_CLICK_ENTITY;  // Add more activation types as needed

        // You can add more information or methods to the enum values if needed
    }

}
