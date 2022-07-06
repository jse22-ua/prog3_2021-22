package model.game.score;

/**
 * Practica 5
 * @author Judit Serrano Espinosa,74379872B
 *
 */

import model.Fighter;
import model.Side;

public class DestroyedFightersScore extends Score<Fighter>{

	public DestroyedFightersScore(Side side) {
		super(side);
	}

	@Override
	public void score(Fighter f) {
		if(f!=null) {
			score+=f.getValue();
		}
		else {}
	}

}
