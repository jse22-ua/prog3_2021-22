package model.exceptions;
/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	private String type;
	
	public NoFighterAvailableException(String type) {
		super();
		this.type=type;
	}
	
	public String getMessage() {
		return "ERROR: no found destroyed fighter of" + type;
	}
}
