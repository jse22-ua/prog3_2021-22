package model.game;

import java.util.*;

import model.*;
import model.exceptions.*;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.*;

/**
 * Practica 4
 * @author Judit Serrano Espinosa, 74379872B
 *
 */

public class GameShip extends Ship{
	private WinsScore winsScore;
	private DestroyedFightersScore destroyedFightersScore;

	public GameShip(String name, Side side) {
		super(name, side);
		winsScore = new WinsScore(side);
		destroyedFightersScore = new DestroyedFightersScore(side);
	}
	
	public WinsScore getWinsScore() {
		return winsScore;
	}

	public DestroyedFightersScore getDestroyedFightersScore() {
		return destroyedFightersScore;
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
		Fighter f = null;
		boolean founded=false;
		if(fleet.isEmpty()) {
			throw new WrongFighterIdException(id);
		}
		if(id>fleet.get(fleet.size()-1).getId()) {
			throw new WrongFighterIdException(id);
		}
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
	
	public List<Integer> getFightersId(String where){
		List<Integer> ids= new ArrayList<Integer>();
		if(where.equals("board")) {
			for(Fighter f: fleet) {
				if(f.getPosition()!=null&&!f.isDestroyed()) {
					ids.add(f.getId());
				}
			}
		}
		else if(where.equals("ship")) {
			for(Fighter f: fleet) {
				if(f.getPosition()==null&&!f.isDestroyed()) {
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
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		Fighter f=getFighter(id);
		b.launch(c,f);
	}
	
	public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Objects.requireNonNull(b);
		Fighter f=getFighter(id);
		b.patrol(f);
		}
	
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException{
		Objects.requireNonNull(b);
		Fighter f=getFighter(id);
		try {
			b.removeFighter(f);
		} catch (FighterNotInBoardException e) {
			
		}finally{
			int mejora = qty/2;
			f.addAttack(mejora);
			f.addShield(qty-mejora);
		}
	}
	public void updateResults(int r,Fighter f) {
		super.updateResults(r, f);
		if(r==1) {
			winsScore.score(r);
			destroyedFightersScore.score(f);
		}
	}
}
