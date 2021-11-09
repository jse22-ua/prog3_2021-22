package model.fighter;

import model.Ship;

public class TIEInterceptor extends Fighter{
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
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
