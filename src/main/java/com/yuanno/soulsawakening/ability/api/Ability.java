package com.yuanno.soulsawakening.ability.api;

import com.yuanno.soulsawakening.ability.api.interfaces.IPunchAbility;
import com.yuanno.soulsawakening.api.SourceElement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.Serializable;

/**
 * Base class for all abilities
 *
 */
public class Ability extends ForgeRegistryEntry<Ability> {
    private String name;
    private String description;
    private double cooldown;
    private double maxCooldown;
    private STATE state;
    private SubCategory subCategory = null;
    private boolean isShown = true;
    private SourceElement sourceElement;
    public IDependence dependency = (player) -> {return true;};
    private int timer;

    public Ability() {
        this.setState(STATE.READY);
    }

    public void duringCooldown(PlayerEntity player)
    {
        if (this.state.equals(STATE.READY) || this.state.equals(STATE.CONTINUOUS))
            return;
        if (this.getCooldown() <= 0 && !this.getState().equals(STATE.READY)) {
            this.setState(STATE.READY);
            if (this instanceof IPunchAbility)
                ((IPunchAbility) this).startContinuity(player);
        }
        else if (this.state.equals(STATE.COOLDOWN))
            this.alterCooldown(- 1);
    }

    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public double getMaxCooldown()
    {
        return this.maxCooldown;
    }
    public void setMaxCooldown(double maxCooldown)
    {
        this.maxCooldown = maxCooldown;
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
        this.cooldown = cooldown;
    }

    public int getTimer() {
        return this.timer;
    }
    public void alterTimer(int amount)
    {
        this.timer += amount;
    }
    public void setTimer(int amount)
    {
        this.timer = amount;
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
        COOLDOWN, READY, CONTINUOUS, UNUSABLE, DISABLED
    }

    public CompoundNBT save() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("id", this.getRegistryName().toString());
        compoundNBT.putString("displayname", this.getName());
        compoundNBT.putDouble("cooldown", this.getCooldown());
        compoundNBT.putDouble("maxcooldown", this.getMaxCooldown());

        compoundNBT.putString("state", this.getState().toString());

        compoundNBT.putInt("timer", this.getTimer());

        return compoundNBT;
    }

    public void load(CompoundNBT compoundNBT)
    {
        this.setName(compoundNBT.getString("displayname"));
        int cooldown = (int) (compoundNBT.getDouble("cooldown"));
        this.setCooldown(cooldown);
        int maxCooldown = (int) (compoundNBT.getDouble("maxcooldown"));
        this.setMaxCooldown(maxCooldown);
        this.setState(Ability.STATE.valueOf(compoundNBT.getString("state")));

        this.setTimer(compoundNBT.getInt("timer"));
    }

    public enum Category {
        ZANPAKUTO,
        HOLLOW,
        KIDO,
        QUINCY;
    }

    public enum SubCategory {
        // HOLLOW
        BASE(Category.HOLLOW),
        GILLIAN(Category.HOLLOW),
        ADJUCHA(Category.HOLLOW),
        VASTO_LORDE(Category.HOLLOW),

        // ZANPAKUTO
        SEALED(Category.ZANPAKUTO),
        SHIKAI(Category.ZANPAKUTO),
        BANKAI(Category.ZANPAKUTO),
        BAKUDO(Category.KIDO),
        HADO(Category.KIDO),
        KAIDO(Category.KIDO),
        BLUT(Category.QUINCY),
        SPIRIT_WEAPON(Category.QUINCY),
        SHADOW(Category.QUINCY),
        REISHI(Category.QUINCY);
        private Category category;
        SubCategory(Category category)
        {
            this.category = category;
        }

        Category getCategory()
        {
            return this.category;
        }
    }

    public SourceElement getSourceElement()
    {
        return this.sourceElement;
    }
    public void setSourceElement(SourceElement sourceElement)
    {
        this.sourceElement = sourceElement;
    }

    public SubCategory getSubCategory()
    {
        return this.subCategory;
    }
    public void setSubCategory(SubCategory category)
    {
        this.subCategory = category;
    }
    public Category getCategory()
    {
        return this.subCategory.getCategory();
    }

    public void setDependency(IDependence dependency) {
        this.dependency = dependency;
    }
    public IDependence getDependency()
    {
        return this.dependency;
    }
    public interface IDependence extends Serializable
    {
        boolean check(PlayerEntity player);
    }
}
