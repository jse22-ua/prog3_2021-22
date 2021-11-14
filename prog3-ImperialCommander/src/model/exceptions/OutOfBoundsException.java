package model.exceptions;

@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	public OutOfBoundsException() {
		super();
	}
	public String getMessage() {
		return "ERROR: this coordinate is out of board";
	}
}
