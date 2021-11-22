package model.game;

import java.util.*;

import model.*;
import model.exceptions.InvalidSizeException;

/**
 * Practica 4
 * @author Judit Serrano Espinosa, 74379872B
 */

public class GameBoard extends Board{
	
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	
	public int numFighters(Side side) {
		Set<Coordinate>coordenadas=board.keySet();
		int count=0;
		for(Coordinate coord : coordenadas) {
			if(board.get(coord).getSide().equals(side)) {
				count++;
			}
		}
		return count;
	}
	
	public String toString() {
		StringBuilder concatenation=new StringBuilder();
		for(int x=0;x<=getSize();x++) {
			concatenation.append(x);
		}
		concatenation.append("\n");
		for(int y=0;y<=getSize();y++) {
			concatenation.append("-");
		}
		concatenation.append("\n");
		for(int i=0;i<=getSize();i++) {
			concatenation.append(i+"|");
			for(int j=0;i<=getSize();j++) {
				if(!board.get(new Coordinate(i,j)).equals(null)) {
					concatenation.append(board.get(new Coordinate(i,j)).getSymbol());
				}
				else {
				concatenation.append(" ");
				}
			}
			concatenation.append("\n");
		}
		return concatenation.toString();
	}
}
