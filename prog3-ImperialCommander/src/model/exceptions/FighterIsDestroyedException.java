package model.exceptions;
/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	
	public FighterIsDestroyedException() {
		super();
	}
	
	public String getMessage() {
		return "ERROR: A Fighter is destroyed";
	}
	
}
