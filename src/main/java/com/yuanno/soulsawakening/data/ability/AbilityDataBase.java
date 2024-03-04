package com.yuanno.soulsawakening.data.ability;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IPassiveAbility;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbilityDataBase implements IAbilityData {

    private LivingEntity player;

    private List<Ability> unlockedAbilities = new ArrayList<Ability>();

    private Ability previouslyUsedAbility;
    private int selectedAbilityInteger;
    private ArrayList<Ability> abilitiesInBar = new ArrayList<>();
    @Override
    public boolean loadUnlockedAbility(Ability abl)
    {
        Ability ogAbl = this.getUnlockedAbility(abl);
        if (ogAbl == null)
        {
            this.unlockedAbilities.add(abl);
            return true;
        }
        return false;
    }
    @Override
    public <T extends Ability> T getUnlockedAbility(T abl)
    {
        this.unlockedAbilities.removeIf(ability -> ability == null);
        return (T) this.unlockedAbilities.stream().filter(ability -> ability.equals(abl)).findFirst().orElse(null);
    }
    @Override
    public void addUnlockedAbility(Ability ability) {
        this.unlockedAbilities.add(ability);
    }

    @Override
    public void removeUnlockedAbility(Ability ability) {
        this.unlockedAbilities.remove(ability);
    }

    @Override
    public List<Ability> getUnlockedAbilities() {
        return this.unlockedAbilities;
    }

    @Override
    public List<Ability> getActiveAbilities() {
        return this.unlockedAbilities.stream()
                .filter(ability -> !(ability instanceof IPassiveAbility))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends Ability> List<T> clearUnlockedAbilities() {
        List removed = (List) this.unlockedAbilities;
        this.unlockedAbilities.clear();
        return removed;
    }

    @Override
    public void setSelectedAbility(int selection)
    {
        if (this.selectedAbilityInteger < this.abilitiesInBar.size())
            this.selectedAbilityInteger = selection;
    }

    @Override
    public int getSelectionAbility()
    {
        return this.selectedAbilityInteger;
    }

    @Override
    public void addAbilityToBar(Ability ability)
    {
        this.abilitiesInBar.add(ability);
    }

    @Override
    public void removeAbilityFromBar(Ability ability)
    {
        this.abilitiesInBar.remove(ability);
    }

    @Override
    public ArrayList<Ability> getAbilitiesInBar()
    {
        return this.abilitiesInBar;
    }

    @Override
    public <T extends Ability> T getAbilityInbar(T abl)
    {
        this.abilitiesInBar.removeIf(ability -> ability == null);
        return (T) this.abilitiesInBar.stream().filter(ability -> ability.equals(abl)).findFirst().orElse(null);
    }

    @Override
    public boolean loadAbilityInBar(Ability abl)
    {
        Ability ogAbl = this.getAbilityInbar(abl);
        if (ogAbl == null)
        {
            this.abilitiesInBar.add(abl);
            return true;
        }
        return false;
    }
}
