package model;

import java.util.Objects;

import model.fighters.*;

/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public class FighterFactory{
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(mother);
		Fighter f=null;
		switch(type) {
		case "AWing":
								f= new AWing(mother);
								break;
		case "XWing":	
								f=new XWing(mother);
								break;
		case "YWing":	
								f=new YWing(mother);
								break;
		case "TIEBomber":	
								f=new TIEBomber(mother);
								break;
							
		case "TIEFighter":		
								f=new TIEFighter(mother);
								break;
		case "TIEInterceptor":	
								f=new TIEInterceptor(mother);
								break;
		default: 				
								break;
		}
		return f;
	}
}
