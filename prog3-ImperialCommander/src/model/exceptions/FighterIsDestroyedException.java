package model.exceptions;

@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	
	public FighterIsDestroyedException() {
		super();
	}
	
	public String getMessage() {
		return "ERROR: A Fighter is destroyed";
	}
	
}
