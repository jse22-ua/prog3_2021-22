package model.exceptions;

import model.Coordinate;

/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	private Coordinate c;
	
	public OutOfBoundsException(Coordinate c) {
		super();
		this.c=c;
		
	}
	
	public String getMessage() {
		return "ERROR: this coordinate"+ c + "is out of board";
	}
}
