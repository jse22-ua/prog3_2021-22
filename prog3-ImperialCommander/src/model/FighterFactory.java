package model;

public class FighterFactory {
	public static Fighter CreateFighter(String type, Ship mother) {
		Fighter f=null;
		switch(type) {
		case "AWing":break;
		case "XWing":break;
		case "YWing":break;
		case "TIEBomber":break;
		case "TIEFighter":break;
		case "TIEInterceptor":break;
		default: break;
		}
		return f;
	}
}
