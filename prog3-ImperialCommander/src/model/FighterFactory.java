package model;

import model.fighter.AWing;
import model.fighter.Fighter;
import model.fighter.TIEBomber;
import model.fighter.TIEFighter;
import model.fighter.TIEInterceptor;
import model.fighter.XWing;
import model.fighter.YWing;

public class FighterFactory{
	public static Fighter CreateFighter(String type, Ship mother) {
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
