package model.fighter;

import model.Ship;

public class TIEFighter extends Fighter{
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(10);
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
