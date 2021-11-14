package model.exceptions;

/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	public OutOfBoundsException() {
		super();
	}
	
	public String getMessage() {
		return "ERROR: this coordinate is out of board";
	}
}
