package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.fighters.Fighter;

/**
 * Practica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class Board {
	private int size;
	private Map<Coordinate,Fighter> board;
 
	public Board(int size) {
		this.size=size;
		board = new HashMap<Coordinate,Fighter>();
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if(board.containsKey(c)) {
			return board.get(c).copy();
		}
		else {
			return null;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean removeFighter(Fighter f) {	
		Objects.requireNonNull(f);
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
	
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		
		Set<Coordinate> Coord = new TreeSet<Coordinate>();
		Set<Coordinate> Coordinates=c.getNeighborhood();
		for(Coordinate cord: Coordinates) {
			if(inside(cord)) {
				Coord.add(cord);
			}
		}
		return Coord;
	}
 
	public int launch(Coordinate c, Fighter f) {
		int r=0;
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		if(inside(c)) {
			if(board.get(c)!=null&& board.get(c).getSide()!=f.getSide()) {
				r=f.fight(board.get(c));
				f.getMotherShip().updateResults(r);
				board.get(c).getMotherShip().updateResults(r*-1);
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
		}
		return r;
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		if(board.containsValue(f)) {
			 Set<Coordinate> Coord= getNeighborhood(f.getPosition());
			 for(Coordinate Coordenada: Coord) {
				 Fighter fenemy=board.get(Coordenada);
					 if(board.get(Coordenada)!=null && !board.get(Coordenada).getSide().equals(f.getSide())) {
						 int r=f.fight(fenemy);
						 fenemy.getMotherShip().updateResults(r*-1);
						 f.getMotherShip().updateResults(r);
						 if(r==1) {
							 fenemy.setPosition(null);
							 board.remove(Coordenada);
							 
						 }
						 else {
							 board.remove(f.getPosition());
							 f.setPosition(null);
						 }
						 
				 }
			 }
			 f.getMotherShip().purgeFleet();
		}
	}
}
