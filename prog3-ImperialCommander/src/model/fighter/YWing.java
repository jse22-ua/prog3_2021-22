package model.fighter;

import model.Ship;

public class YWing extends Fighter{
	
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
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
