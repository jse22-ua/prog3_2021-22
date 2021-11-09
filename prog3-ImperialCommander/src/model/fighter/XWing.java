package model.fighter;

import model.Ship;

public class XWing extends Fighter{
	
	public XWing (Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
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
