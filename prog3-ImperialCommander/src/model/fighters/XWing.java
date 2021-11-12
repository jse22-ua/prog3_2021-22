package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter{
	
	public XWing (Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
	}
	
	private XWing(XWing f) {
		super(f);
	}

	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return new XWing(this);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'X';
	}
}
