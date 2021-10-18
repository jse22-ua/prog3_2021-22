package model;

import java.util.ArrayList;

public class Ship {
	private String name;
	private int wins;
	private int losses;
	private Side side;
	private ArrayList<Fighter> fleet;
	
	public Ship(String name, Side side) {
		wins=0;
		losses=0;
		this.name=name;
		this.side=side;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public ArrayList<Fighter> getFleetTest() {
		return fleet;
	}
	public void addFighter(String fd) {
		String[] ships= fd.split("\\:");
		for(int i=0;i<ships.length;i++){
			String[] partes=ships[i].split("\\/");
			//Falta añadir
		}
	}
	public void updateResults(int r) {
		if(r<0) {
			losses++;
		}
		else if(r>0) {
			wins++;
		}
	}
	public Fighter getFistAvailableFighter(String t) {
		if(!t.isEmpty()) {
			boolean founded=false;
			for(int i=0;i<fleet.size()&&!founded;i++) {
				if(t==fleet.get(i).getType()) {
					founded=true;
					return fleet.get(i);
				}
			}
		}
		else {
			return null;
		}
		
	}
	public void purgeFleet() {
		for(int i=0;i<fleet.size();i++) {
			if(fleet.get(i).isDestroyed()) {
				fleet.remove(i);
			}
		}
	}
	public String showFleet() {
		StringBuilder concatenation= new StringBuilder();
		for(int i=0;i<fleet.size();i++) {
			if(fleet.get(i).isDestroyed()) {
				concatenation.append(fleet.get(i)+"(X)");
			}
			else {
				concatenation.append(fleet.get(i));
			}
		}
		return concatenation.toString();
	}
	public String myFleet() {
		StringBuilder concatenation = new StringBuilder();
		for(int i=0;i<fleet.size();i++) {
			concatenation.append(fleet.get(i).getId()+"/" + fleet.get(i).getType());
			if(i!=fleet.size()-1) {
				concatenation.append(":");
			}
		}
		
		return concatenation.toString();
	}

	@Override
	public String toString() {
		
		return "Ship [" + name + " " + wins + "/" + losses + "]" + myFleet();
	}
	
}

