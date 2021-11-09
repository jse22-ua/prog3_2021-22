package model.fighter;

import model.Ship;

public class TIEBomber extends Fighter {
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}

	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return 0;
	}
}
