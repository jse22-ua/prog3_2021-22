package model.fighter;

import model.Ship;

public class AWing extends Fighter{

public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(50);
	}

	@Override
	public Fighter copy() {
		return null;
		
	}
	@Override
	public int getSymbol() {
		return 'A';
	}
}