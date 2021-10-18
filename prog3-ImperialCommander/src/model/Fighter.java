package model;

public class Fighter {
	private String type;
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private static int nextId=1;
	private Coordinate position;
	private Ship mother;
	Side side;
	
	Fighter(String type, Ship mother) {
		id=nextId;
		velocity=100;
		attack=80;
		shield=80;
		this.type=type;
		this.mother=mother;
		position=null;
		side=mother.getSide();
		nextId++;
		
	}
	
	
	public Coordinate getPosition() {
		return position;
	}


	public Fighter (Fighter f) {
		type=f.type;
		attack=f.attack;
		id=f.id;
		shield=f.shield;
		velocity=f.velocity;
		mother=f.mother;
		position=f.position;
		side=f.getSide();
	}
	
	public static void resetNextId() {
		nextId=1;
		
	}
	
	public String getType() {
		return type;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getShield() {
		return shield;
	}
	
	public int getId() {
		return id;
	}
	
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
		if(this.shield<0) {
			this.shield=0;
		}
	}
	
	public boolean isDestroyed() {
		boolean destroyed=false;
		
		if(shield<=0) {
			destroyed=true;
		}
		
		return destroyed;
	}
	
	public int getDamage(int n,Fighter enemy) {
		int damage;
		
		damage=(n*attack)/300;
		
		return damage;
	}

	@Override
	public String toString() {
		String pos;
		if(position==null) {
			pos="null";
		}
		else {
			pos=position.toString();
		}
		return "(" + type + " " + id + " " + side + " " + pos
				+ " {" + velocity + "," + attack + "," + shield + "})";
	}
	
	public int fight(Fighter enemy) {
		int destroyed=0,n, umbral;
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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + velocity;
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (velocity != other.velocity)
			return false;
		return true;
	}
	

	
}
