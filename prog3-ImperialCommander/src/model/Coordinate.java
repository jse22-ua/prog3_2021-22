package model;

/**
 * Practica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class Coordinate {
	 private int x;
	    private int y;
		
	    public Coordinate(int x, int y){
		    this.x = x;      // hay que usar this-> porque el parámetro 'x' se llama
	                        // igual que el atributo 'x', y queremos asignar al atributo
	                        // el valor del parámetro
	       this.y = y;
	    }

	    public Coordinate(Coordinate c){
	        x = c.x;     // no hace falta this-> , no hay nombres coincidentes
	        y = c.y;
	    }

	    public int getX() {return x; }
	    public int getY() { return y; }

	    public String to_string(){
	        StringBuilder concatenation=new StringBuilder();
	        concatenation.append("["+ x + "," + y + "]");
	        return concatenation.toString();
	    }

	    public Coordinate add(Coordinate c){
	        Coordinate c_new = new Coordinate(x+c.x,y+c.y);

	        return c_new;

	    }
	    

	    public Coordinate add(int x,int y){
	        Coordinate c_new = new Coordinate(this.x+x, this.y+y);  // nuevamente hay que usar this->
	                                            // porque los nombres coinciden 
	        return c_new;
	    }
}