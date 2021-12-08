package model;

import java.util.Objects;

import model.exceptions.*;

/**
 * Practica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public abstract class Fighter {
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private static int nextId=1;
	private Coordinate position;
	private Ship mother;
	Side side;
	/*
	 * @param type el tipo del caza
	 * @param velocity su velocidad
	 * @param attack su ataque
	 * @param shield su defensa
	 * @param id su numero de identificación
	 * @param position la posición del tablero en la que se encuentra
	 * @param Ship su flota
	 * @param Side grupo de la flota a la que pertenece
	 */
	protected Fighter(Ship mother) {
		Objects.requireNonNull(mother);
		id=nextId;
		velocity=100;
		attack=80;
		shield=80;
		this.mother=mother;
		position=null;
		side=mother.getSide();
		nextId++;
	
		
	}
	public abstract Fighter copy();
	public abstract char getSymbol();
	/*
	 * @return la posicion del caza
	 */
	public Coordinate getPosition() {
		return position;
	}

	/*
	 * Crea una copia de un objeto Fighter
	 */
	protected Fighter (Fighter f) {
		Objects.requireNonNull(f);
		attack=f.attack;
		id=f.id;
		shield=f.shield;
		velocity=f.velocity;
		mother=f.mother;
		position=f.position;
		side=f.getSide();
	}
	
	/*
	 * Hace que NextId vuelva a valer 1
	 */
	public static void resetNextId() {
		nextId=1;
		
	}
	
	/*
	 * @return el tipo del caza
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	
	/*
	 * @return la velocidad del caza
	 */
	public int getVelocity() {
		return velocity;
	}
	
	/*
	 * @return el ataque que puede hacer el caza
	 */
	public int getAttack() {
		return attack;
	}
	
	/*
	 * @return la defensa del caza
	 */
	public int getShield() {
		return shield;
	}
	
	/*
	 * @return el identificador del caza
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * @return la flota a la que pertenece
	 */
	public Ship getMotherShip() {
		return mother;
	}
	
	public Side getSide() {
		return side;
	}


	public void setPosition(Coordinate p) {
		position=p;
	}

	public void addVelocity(int velocity) {
		this.velocity += velocity;
		if(this.velocity<0) {
			this.velocity=0;
		}
		
	}

	public void addAttack(int attack) {
		this.attack += attack;
		if(this.attack<0) {
			this.attack=0;
		}
	}

	public void addShield(int shield) {
		this.shield += shield;
	}
	/*
	 * @return true si el caza ha sido destruido y falso si no lo ha sido
	 */
	public boolean isDestroyed() {
		boolean destroyed=false;
		
		if(shield<=0) {
			destroyed=true;
		}
		
		return destroyed;
	}
	
	/*
	 * @return el damage que puede hacer el caza. Calculado a partir de un numero aleatorio y su ataque
	 */
	public int getDamage(int n,Fighter enemy) {
		Objects.requireNonNull(enemy);
		int damage;
		
		damage=(n*attack)/300;
		
		return damage;
	}
	
	/*
	 * @retrun un String con las caracteristicas del caza
	 */
	@Override
	public String toString() {
		String pos;
		if(position==null) {
			pos="null";
		}
		else {
			pos=position.toString();
		}
		return "(" + getType() + " " + id + " " + side + " " + pos
				+ " {" + velocity + "," + attack + "," + shield + "})";
	}
	
	/*
	 * Hace que dos cazas se pongan a pelear a partir de sus velocidades y de numeros aleatorios
	 * @return 0 se cuando alguno de los cazas ya estaba destruido al empezar el metodo
	 * @return 1 si gana nuestro caza y -1 si gana el caza enemigo
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException{
		Objects.requireNonNull(enemy);
		int destroyed=0,n, umbral;
		if(enemy.isDestroyed()) {
			throw new FighterIsDestroyedException(enemy);
		}
		else if(this.isDestroyed()) {
			throw new FighterIsDestroyedException(this);
		}
		umbral=100*velocity/(velocity+enemy.velocity);
		while(shield>0 && enemy.shield>0) {
			n=RandomNumber.newRandomNumber(100);
			if(umbral<=n) {
				enemy.shield-=getDamage(n, enemy);
				destroyed=1;
			}
			else {
				shield-=enemy.getDamage(100-n, enemy);
				destroyed=-1;
			}
		}
		return destroyed;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attack;
		result = prime * result + id;
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + shield;
		result = prime * result + ((side == null) ? 0 : side.hashCode());
		result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result + velocity;
		return result;
	}

	/*
	 * comprueba que dos objetos son iguales
	 * @return true si esos dos objetos son iguales y false si no lo son
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		if (attack != other.attack)
			return false;
		if (id != other.id)
			return false;
		if (mother == null) {
			if (other.mother != null)
				return false;
		} else if (!mother.equals(other.mother))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (shield != other.shield)
			return false;
		if (side != other.side)
			return false;
		if (getType() == null) {
			if (other.getType()!= null)
				return false;
		} else if (!getType().equals(other.getType()))
			return false;
		if (velocity != other.velocity)
			return false;
		return true;
	}
	

	
}
