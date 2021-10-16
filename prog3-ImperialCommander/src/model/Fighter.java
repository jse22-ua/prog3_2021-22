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
	}

	public void addAttack(int attack) {
		this.attack += attack;
	}

	public void addShield(int shield) {
		this.shield += shield;
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

	
}
