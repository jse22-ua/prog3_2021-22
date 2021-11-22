package model.exceptions;

import model.Fighter;

/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	private Fighter f;
	
	public FighterIsDestroyedException(Fighter f) {
		super();
		this.f=f;
	}
	
	public String getMessage() {
		return "ERROR: The Fighter" + f + " is destroyed";
	}
	
}
