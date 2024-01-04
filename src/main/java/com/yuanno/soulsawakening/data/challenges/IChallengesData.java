package com.yuanno.soulsawakening.data.challenges;

import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;

import java.util.List;

public interface IChallengesData {
	boolean addChallenge(Challenge challenge);
	boolean addChallenge(ChallengeCore<?> core);
	boolean removeChallenge(ChallengeCore<?> core);
	boolean hasChallenge(ChallengeCore<?> core);
	boolean isChallengeCompleted(ChallengeCore<?> core);
	<T extends Challenge> T getChallenge(ChallengeCore<?> core);
	List<Challenge> getChallenges();
	void clearChallenges(); 
	int countChallenges();
}
