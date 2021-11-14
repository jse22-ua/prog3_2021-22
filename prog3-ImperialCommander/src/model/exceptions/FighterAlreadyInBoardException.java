package model.exceptions;

import model.Fighter;
/*
 * Practica 3
 * @author Judit Serrano Espinosa, 74379872B
 * 
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{ 
	private Fighter f;
	
	public FighterAlreadyInBoardException(Fighter f) {
		super();
		this.f=f;
	}
	public String getMessage() {
		return "ERROR: The Fighter"+ f +"is already positioned";
	}
}
