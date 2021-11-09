package model.fighter;

import model.Ship;

public class TIEBomber extends Fighter {
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return new TIEBomber(this);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'b';
	}
	
	@Override
	public int getDamage(int n,Fighter enemy) {
		int damage=super.getDamage(n, enemy);
		if(enemy instanceof XWing || enemy instanceof YWing) {
			damage=damage/2;
		}
		else if(enemy instanceof AWing) {
			damage=damage/3;
		}
		return damage;
	}
}
