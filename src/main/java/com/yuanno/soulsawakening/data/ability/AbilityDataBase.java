package com.yuanno.soulsawakening.data.ability;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class AbilityDataBase implements IAbilityData {

    private LivingEntity player;

    private List<Ability> unlockedAbilities = new ArrayList<Ability>();

    private Ability previouslyUsedAbility;

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
    public <T extends Ability<T>> List<T> clearUnlockedAbilities() {
        List removed = (List) this.unlockedAbilities;
        this.unlockedAbilities.clear();
        return removed;
    }
}
