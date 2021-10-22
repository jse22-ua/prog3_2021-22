package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Board {
	private int size;
	private Map<Coordinate,Fighter> board;
 
	public Board(int size) {
		this.size=size;
		board= new HashMap<Coordinate,Fighter>();
	}
	public Fighter getFighter(Coordinate c) {
		Fighter f=new Fighter();
		return f;
	}
	public int getSize() {
		return size;
	}
	
	public boolean removeFighter(Fighter f) {
		boolean removed=false;
		
		return removed;
	}
	public boolean inside(Coordinate c) {
		
		return true;
	}
	public Set<Coordinate> getNeighborhood(Coordinate c){
		return c.getNeighborhood();
	}
 
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		return 0;
	}
	public void patrol(Fighter f) {
		
	}
}
