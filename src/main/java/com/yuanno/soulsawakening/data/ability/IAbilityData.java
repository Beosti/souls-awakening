package com.yuanno.soulsawakening.data.ability;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public interface IAbilityData {

    void addUnlockedAbility(Ability ability);
    void removeUnlockedAbility(Ability ability);
    List<Ability> getUnlockedAbilities();
    List<Ability> getActiveAbilities();
    <T extends Ability> List<T> clearUnlockedAbilities();
    boolean loadUnlockedAbility(Ability abl);
    <T extends Ability> T getUnlockedAbility(T abl);

    void setSelectedAbility(int selection);
    int getSelectionAbility();
    void addAbilityToBar(Ability ability);
    void removeAbilityFromBar(Ability ability);
    ArrayList<Ability> getAbilitiesInBar();
    <T extends Ability> T getAbilityInbar(T abl);
    boolean loadAbilityInBar(Ability abl);
}
