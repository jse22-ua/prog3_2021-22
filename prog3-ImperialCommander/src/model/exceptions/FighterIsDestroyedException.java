package model.exceptions;

@SuppressWarnings("Serial")
public class FighterIsDestroyedException extends Exception{
	private boolean destroyed;
	
	public FighterIsDestroyedException(boolean destroyed) {
		super();
		this.destroyed=destroyed;
	}
	
	public String getMessage() {
		return "ERROR: A Fighter is destroyed";
	}
	
}
