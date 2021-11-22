package model.game;

import java.util.*;

import model.*;
import model.game.exceptions.WrongFighterIdException;

public class GameShip extends Ship{

	public GameShip(String name, Side side) {
		super(name, side);
	}
	
	public boolean isFleetDestroyed() {
		boolean destroyed=true;
		if(fleet.size()==0) {
			destroyed=true;
		}
		else {
			for(Fighter f: fleet) {
				if(!f.isDestroyed()) {
					destroyed=false;
					break;
				}
			}
		}
		return destroyed;
	}
	
	private Fighter getFighter(int id) throws WrongFighterIdException {
		Fighter f=null;
		boolean founded=false;
		for(int i=0;i<fleet.size();i++) {
			if(fleet.get(i).getId()==id&&!fleet.get(i).isDestroyed()) {
				f=fleet.get(i);
				founded=true;
				break;
			}
		}
		if(!founded) {
			throw new WrongFighterIdException(id);
		}
		return f;
	}
	
	public List<Integer> getFighterId(String where){
		List<Integer> ids= new ArrayList<Integer>();
		
		return ids;
	}
}
