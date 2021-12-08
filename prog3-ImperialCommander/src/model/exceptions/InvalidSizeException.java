package model.exceptions;
/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception{
	private int size;
	
	public InvalidSizeException(int size) {
		super();
		this.size=size;
	}
	
	public String getMessage() {
		return "ERROR: not valid size: " + size ;
		
	}
}
