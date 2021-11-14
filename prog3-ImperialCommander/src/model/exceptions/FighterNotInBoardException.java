package model.exceptions;

import model.Fighter;
/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception {
	private Fighter f;
	
	public FighterNotInBoardException(Fighter f) {
		
		super();
		this.f=f;
	}
	
	public String getMessage() {
		
		return "ERROR:The Figther's position" + f + "is wrong";
	}
}
