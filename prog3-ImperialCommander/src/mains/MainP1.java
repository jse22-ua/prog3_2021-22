package mains;

import model.Coordinate;
import model.Ship;
import model.Side;

public class MainP1 {

	public static void main(String[] args) {
		Coordinate c1 = new Coordinate(5,4);
		Coordinate c2 = new Coordinate(c1);
		Coordinate c3 = c1.add(3,1);
		Coordinate c4 = c1.add(c2);
		Coordinate c6 = new Coordinate(-70,-2);
		Ship s=new Ship("Som",Side.IMPERIAL);
		
		System.out.println("c1="+c1);
		System.out.println("c2="+c2);
		System.out.println("c3="+c3);
		System.out.println("c4="+c4);
		System.out.println("c6="+c6);
		System.out.println(c6.getX());
		System.out.println(c6.getY());
	}	
}
