package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.*;

/**
 * Practica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class Board {
	private int size;
	protected Map<Coordinate,Fighter> board;
	/*
	 * @param size como de grande es el tablero
	 * @param board el tablero
	 */
	public Board(int size)throws InvalidSizeException {
		if(size<5) {
			throw new InvalidSizeException(size);
		}
		this.size=size;
		board = new HashMap<Coordinate,Fighter>();
	}
	
	/*
	 * @return el fighter que se encuentra en la posicion del tablero
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if(board.containsKey(c)) {
			return board.get(c).copy();
		}
		else {
			return null;
		}
	}
	
	/*
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * @return un boolean que nos dice si se ha eliminado el fighter del tablero
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException{	
		Objects.requireNonNull(f);
		if(!f.equals(board.get(f.getPosition()))) {
			throw new FighterNotInBoardException(f);
		}
		boolean removed=false;

		if(board.containsValue(f)) {
			Coordinate c = new Coordinate(f.getPosition());
			if(board.containsKey(c)&&board.get(c).equals(f)) {
					board.remove(c,f);
					f.setPosition(null);
					removed=true;
			}
		}
		return removed;
	}
	/*
	 * @return un boolean que nos dice si la coordenada está dentro del tablero
	 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		if(c.equals(null)){
			return false;
		}
		else if(c.getX()>=0&&c.getY()>=0&&c.getX()<=size-1&&c.getY()<=size-1) {
				return true;
		}
		else {
			return false;
		}
	}
	
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		Objects.requireNonNull(c);
		if(!inside(c)) {
			throw new OutOfBoundsException(c);
		}
		Set<Coordinate> Coord = new TreeSet<Coordinate>();
		Set<Coordinate> Coordinates=c.getNeighborhood();
		for(Coordinate cord: Coordinates) {
			if(inside(cord)) {
				Coord.add(cord);
			}
		}
		return Coord;

	}
 
	public int launch(Coordinate c, Fighter f)throws FighterAlreadyInBoardException, OutOfBoundsException{
		int r=0;
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		if(f.getPosition()!=null) {
			throw new FighterAlreadyInBoardException(f);
		}
		if(!inside(c)) {
			throw new OutOfBoundsException(c);
		}
		try {
				if(board.get(c)!=null&& board.get(c).getSide()!=f.getSide()) {
					r=f.fight(board.get(c));
					f.getMotherShip().updateResults(r,board.get(c));
					board.get(c).getMotherShip().updateResults(r*-1,f);
					if(r==1) {
						board.get(c).setPosition(null);
						board.remove(c);
						f.setPosition(c);
						board.put(c,f);
					}
				}
				else if(board.get(c)!=null&&board.get(c).getSide()==f.getSide()) {
				
				}
				else {
					board.put(c,f);
					f.setPosition(c);
				}
		}catch(FighterIsDestroyedException e){
			throw new RuntimeException("There was an "+e.getMessage());
		}
		return r;
	}
	
	public void patrol(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		try{
			if(f.getPosition()==null) {
				throw new FighterNotInBoardException(f);
			}
		 Set<Coordinate> Coord = getNeighborhood(f.getPosition());
			if(!f.equals(board.get(f.getPosition()))) {
				throw new FighterNotInBoardException(f);
			}
			for(Coordinate Coordenada: Coord) {
				Fighter fenemy=board.get(Coordenada);
				 if(board.get(Coordenada)!=null && board.get(Coordenada).getSide()!=f.getSide()) {
						 int r=f.fight(fenemy);
						 fenemy.getMotherShip().updateResults(r*-1,f);
						 f.getMotherShip().updateResults(r,fenemy);
						 if(r==1) {
							 fenemy.setPosition(null);
							 board.remove(Coordenada);
					 
						 }
						else {
							board.remove(f.getPosition());
							f.setPosition(null);
							break;
						}
					}
			 }
			}catch(FighterIsDestroyedException | OutOfBoundsException e) {
				throw new RuntimeException();
			}
	}
}
