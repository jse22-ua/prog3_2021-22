package model;

import java.util.Set;
import java.util.TreeSet;

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

	 /*
	  * @return la coordenada x	    
	  */
	 public int getX() {
		return x;
	 }

	 /*
	  * @return la coordenada y
	  */
	 public int getY() {
		return y;
	 }

	 /*
	  * @return una cadena que te muestra la coordenada x e y entre corchetes 
	  * de esta manera no necesitas llamar a ningún metodo 
	  * simplemente con un "System.out.println(coordenada declarada)" llamar al metodo toString()
	  */
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
	
	 /*
	  * @return true los objetos son iguales y false si no lo son
	  */
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
	 
	 /*
	  * @return la suma de esta coordenada con la pasada por parametro
	  */
	 public Coordinate add(Coordinate c){
		
        Coordinate c_new = new Coordinate(x+c.x,y+c.y);
	        
        return c_new;

	}
	/*
	 * @return lo mismo pero pasando por parametro los valores
	 */
	public Coordinate add(int x,int y){
		
		Coordinate c_new = new Coordinate(this.x+x, this.y+y);
	                                           
	    return c_new;
	    
	}
	/*
	 * Compara dos coordenadas
	 * @return 1 si esta es mayor que la pasada por parametro, 0 si son iguales y -1 si la otra es mayor
	 */
	@Override
	public int compareTo(Coordinate otra) {
		int Comparado=0;
		
		Comparado=x-otra.x;
		
		if(Comparado==0) {
				Comparado=y-otra.y;
		}
		return Comparado;
	}
	
	/*
	 * Calcula las coordenadas que hay alrededor dicha coordenada
	 * @return una lista de coordenadas que rodean a la coordenada
	 */

	public Set<Coordinate> getNeighborhood(){
		Set<Coordinate> Neighborhood= new TreeSet<Coordinate>();
		Neighborhood.add(new Coordinate(this.x+1,this.y+1));
		Neighborhood.add(new Coordinate(this.x+1,this.y));
		Neighborhood.add(new Coordinate(this.x+1,this.y-1));
		Neighborhood.add(new Coordinate(this.x,this.y+1));
		Neighborhood.add(new Coordinate(this.x,this.y-1));
		Neighborhood.add(new Coordinate(this.x-1,this.y+1));
		Neighborhood.add(new Coordinate(this.x-1,this.y));
		Neighborhood.add(new Coordinate(this.x-1,this.y-1));

		return Neighborhood;
	}
	

	
}