package model.fighter;

import model.Ship;

public class TIEFighter extends Fighter{
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(10);
	}
	
	private TIEFighter(TIEFighter f) {
		super(f);
	}

	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return new TIEFighter(this);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'f';
	}
}
