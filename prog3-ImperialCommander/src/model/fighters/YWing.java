package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class YWing extends Fighter{
	
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
	}
	
	private YWing(YWing f) {
		super(f);
	}

	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return new YWing(this);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'Y';
	}
	
	@Override
	public int getDamage(int n,Fighter enemy) {
		int damage=super.getDamage(n, enemy);
		
		if(enemy instanceof TIEFighter ||enemy instanceof TIEInterceptor) {
			damage=damage/3;
		}
		else if(enemy instanceof TIEBomber) {
			damage=damage/2;
		}
		return damage;
	}
}
