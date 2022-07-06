package model.game.exceptions;

/**
 * Practica 5
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public class WrongFighterIdException extends Exception{
	
	private int id;
	
	public WrongFighterIdException(int id) {
		super();
		this.id=id;
	}
	
	public String getMessage() {
		return "ERROR: The Fighter's id " + id + " is wrong";
	}
}
