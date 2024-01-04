package com.yuanno.soulsawakening.api.challenges;

public enum ChallengeDifficulty {
	ULTIMATE,
	HARD,
	STANDARD;

	public String capitalize() {	
		ChallengeDifficulty diff = values()[ordinal()];
		return diff.name().substring(0, 1).toUpperCase() + diff.name().substring(1).toLowerCase();
	}
}
