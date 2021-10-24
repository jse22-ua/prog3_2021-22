package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
		board= new HashMap<Coordinate,Fighter>();
	}
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f=board.get(c);
		return f;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean removeFighter(Fighter f) {
		boolean removed=false;
		Set<Coordinate> coordinates=board.keySet();
		for(Coordinate c : coordinates) {
			Fighter f2 = board.get(c);
			if(f.getPosition().equals(f2.getPosition())) {
				removed=true;
				board.remove(c);
			}
		}
		return removed;
	}
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		if(board.isEmpty()) {
			return false;
		}
		else {
			int r=0;
			Map.Entry<Coordinate,Fighter> entry = board.entrySet().iterator().next();
			Coordinate coord= entry.getKey();
			r=c.compareTo(coord);
			if(r>0) {
				 return true;
			}
			else {
				return false;
			}
		}
	}
	
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		Set<Coordinate> Cboard= board.keySet();
		Set<Coordinate> Coord = c.getNeighborhood();
		for(Coordinate cboard: Cboard) {
			for(Coordinate coord: Coord) {
				if(cboard.compareTo(coord)<0) {
					Coord.remove(coord);
				}
			}
		}
		return Coord;
	}
 
	public int launch(Coordinate c, Fighter f) {
		int r=0;
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		if(inside(c)) {
			if(board.get(c)!=null&& !board.get(c).getSide().equals(f.getSide())) {
				r=f.fight(board.get(c));
				f.getMotherShip().updateResults(r);
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
				 if(inside(Coordenada)) {
					 if(board.get(Coordenada)!=null && !board.get(Coordenada).getSide().equals(f.getSide())) {
						 int r=f.fight(board.get(Coordenada));
						 f.getMotherShip().updateResults(r);
						 
					 }
				 }
			 }
			 f.getMotherShip().purgeFleet();
		}
	}
}
