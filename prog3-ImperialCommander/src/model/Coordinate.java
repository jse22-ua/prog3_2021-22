package model;

import java.util.Set;

/**
 * Practica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class Coordinate implements Comparable<Coordinate>{
	 private int x;
	 private int y;
	 /*
	  * @param x La componente x de las coordenadas
	  * @param y La componente y de las coordenadas
	  */
	 public Coordinate(int x, int y){
	    this.x = x;     
	    this.y = y;
	 }
	 /*
	  * Los atributos se llaman igual que los valores introducidos por ello se utiliza this.
	  */

	 public Coordinate(Coordinate c) {
	    x = c.x;     
	    y = c.y;
	 }
	 /*
	  * Aqui no hace falta
	  */

	    
	 public int getX() {
		return x;
	 }

	 public int getY() {
		return y;
	 }

	 public String toString(){
	    StringBuilder concatenation=new StringBuilder();
	    concatenation.append("["+ x + "," + y + "]");
	    return concatenation.toString();
	 }
	    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	
		
	public Coordinate add(Coordinate c){
		
        Coordinate c_new = new Coordinate(x+c.x,y+c.y);
	        
        return c_new;

	}

	public Coordinate add(int x,int y){
		
		Coordinate c_new = new Coordinate(this.x+x, this.y+y);
	                                           
	    return c_new;
	    
	}

	@Override
	public int compareTo(Coordinate otra) {
		int Comparado=0;
		
		Comparado=x-otra.x;
		
		if(Comparado==0) {
				Comparado=y-otra.y;
		}
		return Comparado;
	}
	
	public Set<Coordinate> getNeighborhood(){
		Set<Coordinate> Neighborhood= new HashSet<Coordinate>();
	}
	

	
}