package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Practica 5
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public class Ranking<ScoreType extends Score<?>>{
	private SortedSet<ScoreType> scoreSet;
	
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}

	public SortedSet<ScoreType> getSortedRanking() {
		return scoreSet;
	}
	
	public ScoreType getWinner() {
		if(scoreSet.isEmpty()) {
			throw new RuntimeException("ERROR: scoreSet is empty");
		}
		return scoreSet.first();
	}

	@Override
	public String toString() {
		StringBuilder rank= new StringBuilder();
		
		rank.append("|");
		
		for(ScoreType score : scoreSet) {
			rank.append(score);
			rank.append("|");
			
		}
		
		return rank.toString();
	}
	
	
}
