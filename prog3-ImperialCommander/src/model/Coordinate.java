package model;

/**
 * Práctica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class Coordinate {
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
	  * hay que usar "this." porque el parámetro 'x' se llama igual que el atributo 'x', y queremos asignar al atributo
	  * el valor del parámetro
	  */

	 public Coordinate(Coordinate c) {
	    x = c.x;     
	    y = c.y;
	 }
	 /*
	  * no hace falta this. , no hay nombres coincidentes
	  */

	    
	 public int getX() {
		return x;
	 }

	 public int getY() {
		return y;
	 }

	 public String to_string(){
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

		/*public boolean equals(Object obj) {
			
			if(obj instanceof Coordinate) {
				
				Coordinate other=(Coordinate)obj;
				
				if(this.x==other.x && this.y==other.y) {
					
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}*/
		
	public Coordinate add(Coordinate c){
        Coordinate c_new = new Coordinate(x+c.x,y+c.y);
	        
        return c_new;

	}
	    

	public Coordinate add(int x,int y){
		Coordinate c_new = new Coordinate(this.x+x, this.y+y);
	                                           
	    return c_new;
	}

}