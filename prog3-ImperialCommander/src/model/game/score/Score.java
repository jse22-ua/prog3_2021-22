package model.game.score;

import model.Side;

/**
 * Practica 5
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public abstract class Score<T> implements Comparable<Score<T>> {
	protected int score;
	public Side side;
	
	public Score(Side side) {
		this.side = side;
		score = 0;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(Score<T> other) {
		int Compared;
		Compared = score - other.score;
		if(Compared<0) {
			return 1;
		}
		else if(Compared>0) {
			return -1;
		}
		else {
			return side.compareTo(other.side);
		}
	}

	@Override
	public String toString() {
		return "Player " + side + ": " + score;
	}
	
	public abstract void score(T sc);
}
