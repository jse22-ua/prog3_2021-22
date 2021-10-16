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
}
