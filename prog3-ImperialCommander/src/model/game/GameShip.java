package model.game;

import java.util.*;

import model.*;
import model.exceptions.*;
import model.game.exceptions.WrongFighterIdException;

/**
 * Practica 4
 * @author Judit Serrano Espinosa, 74379872B
 *
 */

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
		if(where.equals("board")) {
			for(Fighter f: fleet) {
				if(!f.getPosition().equals(null)&&!f.isDestroyed()) {
					ids.add(f.getId());
				}
			}
		}
		else if(where.equals("Ship")) {
			for(Fighter f: fleet) {
				if(f.getPosition().equals(null)&&!f.isDestroyed()) {
					ids.add(f.getId());
				}
			}
		}
		else {
			for(Fighter f: fleet) {
				if(!f.isDestroyed()) {
					ids.add(f.getId());
				}
			}
		}
		return ids;
	}
	
	public void launch(int id,Coordinate c,Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Fighter f=getFighter(id);
		b.launch(c,f);
	}
	
	public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Fighter f=getFighter(id);
		b.patrol(f);
	}
	
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException, FighterNotInBoardException{
		Fighter f=getFighter(id);
		if(!b.inside(f.getPosition())) {
			throw new FighterNotInBoardException(f);
		}
		int mejora = qty/2;
		f.addAttack(mejora);
		f.addShield(qty-mejora);
		//revisar porque el enunciado dice
		//si no estaba en el tablero se lanzará la FighterNotInBoardException, 
		//que se debe capturar para no hacer nada
	}
}
