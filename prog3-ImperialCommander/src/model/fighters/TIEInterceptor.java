package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class TIEInterceptor extends Fighter{
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
	}
	
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return new TIEInterceptor(this);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'i';
	}
	
	@Override
	public int getDamage(int n,Fighter enemy) {
		int damage=super.getDamage(n, enemy);
		
		if(enemy instanceof YWing) {
			damage=damage*2;
		}
		else if(enemy instanceof AWing) {
			damage=damage/2;
		}
		return damage;
	}
}
