package model.game;
/**
 * Practica 4
 * @author Judit Serrano Espinosa,74379872B
 *
 */
import java.util.*;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerRandom implements IPlayer{
	private GameBoard gb;
	private GameShip gs;
	private int numFighter;
	
	public PlayerRandom(Side side, int numFighter) {
		Objects.requireNonNull(side);
		StringBuilder compostname=new StringBuilder();
		compostname.append("PlayerRandom");
		
		if(side.equals(Side.REBEL)) {
		compostname.append(" REBEL ");
		}
		else {
			compostname.append(" IMPERIAL ");
		}
		compostname.append("Ship");
		String name=compostname.toString();
		gs=new GameShip(name,side);
		this.numFighter=numFighter;
	}
	
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		this.gb=gb;
	}
	
	public GameShip getGameShip() {
		return gs;
	}
	
	public void initFighters() {
		if(!gs.getFleetTest().isEmpty()) {
			List<Fighter> f=gs.getFleetTest();
			List<String> tipos=new ArrayList<>();
			StringBuilder cadena=new StringBuilder();
			int n=0;
			if(gs.getSide().equals(Side.IMPERIAL)) {
				tipos.add("TIEFighter");
				tipos.add("TIEBomber");
				tipos.add("TIEInterceptor");
			}
			else if(gs.getSide().equals(Side.REBEL)) {
				tipos.add("XWing");
				tipos.add("YWing");
				tipos.add("AWing");
			}
			if(tipos.size()!=0) {
				for(int i=0;i<tipos.size();i++) {
					for(Fighter fighter:f)
						if(fighter.getType().equals(tipos.get(i))) {
							n=RandomNumber.newRandomNumber(numFighter-1);
							if(n!=0) {
								cadena.append(n + "/" + tipos.get(i));
								if(i!=tipos.size()-1) {
									cadena.append(":");
								}
							}
							break;
						}
				}
			}
			if(!cadena.equals(null)) {
				gs.addFighters(cadena.toString());
			}
		}
	}
	public boolean isFleetDestroyed() {
		return gs.isFleetDestroyed();
	}
	
	public String showShip() {
		return gs.toString() + gs.showFleet();
	}
	
	public void purgeFleet() {
		gs.purgeFleet();
	}
	
	public boolean nextPlay() {
		boolean next=true;
		int option =RandomNumber.newRandomNumber(100);
		if(option==99) {
			next=false;
		}
		else {
			List<Integer>list=gs.getFightersId("Ship");
			int id=RandomNumber.newRandomNumber(list.size());
			if(list.isEmpty()) {
				throw new RuntimeException("ERROR: There are not fighters on the ship");
			}
			if(option<99&&option>84) {
				try {
					gs.improveFighter(id, option, gb);
				} catch (WrongFighterIdException e) {
					throw new RuntimeException(e);
				}
			}
			else if(option<85&&option>24) {
				int x= RandomNumber.newRandomNumber(gb.getSize());
				int y= RandomNumber.newRandomNumber(gb.getSize());
				Coordinate c=new Coordinate(x,y);
				try {
					gs.launch(id, c, gb);
				} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
					throw new RuntimeException(e);
				}
			}
			else if(option<0&&option<25) {
				try {
					gs.patrol(id, gb);
				} catch (WrongFighterIdException | FighterNotInBoardException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return next;
	}

}
