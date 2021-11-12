package model;

import java.util.ArrayList;
import java.util.List;

import model.fighters.Fighter;

/**
 * Practica 1
 * @author Judit Serrano Espinosa,74379872B
 *
 */
public class Ship {
	private String name;
	private int wins;
	private int losses;
	private Side side;
	private List<Fighter> fleet;
	/*
	 * @param name nombre de la nave
	 * @param wins victorias obtenidas
	 * @param losses perdidas obtenidas
	 * @param side tipo de la nave
	 * @param flotas de la nave
	 */
	
	public Ship(String name, Side side) {
		wins=0;
		losses=0;
		this.name=name;
		this.side=side;
		fleet=new ArrayList<Fighter>();
	}

	/*
	 * @return el nombre de la nave
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	 * @return sus victorias
	 */
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
	/*
	 * @return el bando al que pertenece la nave
	 */
	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	/*
	 * @return las flotas que pertenecen a la nave
	 */
	public List<Fighter> getFleetTest() {
		return fleet;
	}
	
	/*
	 * se encarga de anadir las flotas de una nave a partir de una cadena de caracteres
	 */
	public void addFighters(String fd) {
		String[] ships= fd.split("\\:");
		for(int i=0;i<ships.length;i++){
			String[] partes=ships[i].split("\\/");
			for(int j=0;j<Integer.parseInt(partes[0]);j++) {
				Fighter f=FighterFactory.createFighter(partes[1],this);
				fleet.add(f);
			}
		}
	}
	
	/*
	 * Contabiliza las perdidas y victorias dependiedo de si r es positivo o negativo
	 */
	public void updateResults(int r) {
		if(r==-1) {
			losses++;
		}
		else if(r==1) {
			wins++;
		}
	}
	/*
	 * Te devuelve el primer caza del tipo del String pasado por valor
	 */
	public Fighter getFirstAvailableFighter(String t) {
		boolean founded=false;
		Fighter f= FighterFactory.createFighter(t,this);
		if(fleet.size()==0) {
			return null;
		}
		else if(t=="") {
			for(int i=0; i<fleet.size()&&!founded;i++) {
				if(!fleet.get(i).isDestroyed()) {
					founded=true;
					f=fleet.get(i);	
				}
			}
		}
		else{
			for(int i=0;i<fleet.size()&&!founded;i++) {
				if(t.equals(fleet.get(i).getType())&&!fleet.get(i).isDestroyed()) {
					founded=true;
					f= fleet.get(i);
				}
			}
		}
		if(founded) {
			return f;
		}
		else {
			return null;
		}
		
	}
	/*
	 * Elimina los cazas destruidos
	 */
	public void purgeFleet() {
		if(!fleet.isEmpty()) {
			int i=0;
			while(i!=fleet.size()) {
				if(fleet.get(i).isDestroyed()) {
					fleet.remove(i);
				}
				else {
					i++;
				}
			}
		}
	}
	/*
	 * @return una cadena que muestra los datos de los cazas pertenecientes a la nave
	 */
	public String showFleet() {
		StringBuilder concatenation= new StringBuilder();
		for(Fighter ship : fleet) {
			if(ship.isDestroyed()) {
				concatenation.append(ship+" (X)");
				concatenation.append("\n");
			}
			else {
				concatenation.append(ship);
				concatenation.append("\n");
			}
		}
		return concatenation.toString();
	}
	/*
	 * @return una cadena de caracteres sobre la cantidad de flotas que hay de un tipo especifico
	 */
	public String myFleet() {
		StringBuilder concatenation = new StringBuilder();
		List<String> tipos= new ArrayList<>();
		
		for(int i=0;i<fleet.size();i++) {
			if(tipos.isEmpty()) {
				tipos.add(fleet.get(i).getType());
			}
			else if(!(tipos.get(tipos.size()-1)).equals(fleet.get(i).getType())) {
				if(!tipos.contains(fleet.get(i).getType())) {
					tipos.add(fleet.get(i).getType());
				}
			}
		}
		for(String tipo: tipos) {
			int contador=0;
			for(int j=0;j<fleet.size();j++) {
				if(tipo.equals(fleet.get(j).getType())&&!fleet.get(j).isDestroyed()) {
					contador++;
				}
			}
			if(contador!=0) {
				if(!tipo.equals(tipos.get(0))) {
					concatenation.append(":");
				}
				concatenation.append(contador +"/" + tipo);
			}
		}
		
		return concatenation.toString();
	}

	/*
	 * @return los datos generales de la nave (su nombre, sus victorias, sus perdidas y sus flotas)
	 */
	@Override
	public String toString() {
		
		return "Ship [" + name + " " + wins + "/" + losses + "] " + myFleet();
	}
	
}

