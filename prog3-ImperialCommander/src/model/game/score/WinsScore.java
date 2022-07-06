package model.game.score;

import model.Side;

/**
 * Practica 5
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public class WinsScore extends Score<Integer>{

	public WinsScore(Side side) {
		super(side);
	}

	@Override
	public void score(Integer w) {
		if(w!=null) {
			if(w==1) {
				score++;
			}
		}
	}
	
	
}
