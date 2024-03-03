package com.yuanno.soulsawakening.ability.api;

import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * Base class for all abilities, same for all (kido) spells
 *
 */
public class Ability extends ForgeRegistryEntry<Ability> {
    private String name;
    private String description;
    private double cooldown;
    private ActivationType activationType;
    private boolean isPassive = false;
    private boolean isReady = true;
    private double maxCooldown;
    private STATE state;
    private Category category = null;
    private SubCategory subCategory = null;
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

    public void duringCooldown(PlayerEntity player)
    {
        if (this.isPassive || this.state.equals(STATE.READY))
            return;
        if (this.getCooldown() <= 0 && !this.getState().equals(STATE.READY)) {
            this.setState(STATE.READY);
            if (this instanceof IPunchAbility)
                ((IPunchAbility) this).startContinuity(player);
        }
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
        this.setState(Ability.STATE.valueOf(compoundNBT.getString("state")));
    }

    public enum ActivationType {
        RIGHT_CLICK_EMPTY,
        ATTACK,
        RIGHT_CLICK_BLOCK,
        SHIFT_RIGHT_CLICK,
        RIGHT_CLICK_ENTITY,
        SCROLL;
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
        KAIDO(Category.KIDO);
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



    public SubCategory getSubCategory()
    {
        return this.subCategory;
    }
    public void setSubCategory(SubCategory category)
    {
        this.subCategory = category;
    }
    public Category setCategory(Category category)
    {
        return this.category = category;
    }
    public Category getCategory()
    {
        return this.subCategory.getCategory();
    }
}
