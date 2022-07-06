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
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

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
			List<String> tipos=new ArrayList<>();
			StringBuilder cadena=new StringBuilder();
			int n=0;
			if(gs.getSide().equals(Side.REBEL)) {
				tipos.add("XWing");
				tipos.add("YWing");
				tipos.add("AWing");
			}
			else if(gs.getSide().equals(Side.IMPERIAL)) {
				tipos.add("TIEFighter");
				tipos.add("TIEBomber");
				tipos.add("TIEInterceptor");
			}
			for(int i=0;i<tipos.size();i++) {
				n=RandomNumber.newRandomNumber(numFighter);
				if(n!=0) {
					cadena.append(n + "/" + tipos.get(i));
					if(i!=tipos.size()-1) {
						cadena.append(":");
					}
				}
			}
			if(cadena.length()!=0) {
				gs.addFighters(cadena.toString());
			}
	}
	public boolean isFleetDestroyed() {
		return gs.isFleetDestroyed();
	}
	
	public String showShip() {
		StringBuilder showIt = new StringBuilder();
		showIt.append(gs.toString());
		showIt.append("\n");
		if(gs.getFleetTest()!=null) {
			showIt.append(gs.showFleet());
		}
		return showIt.toString();
	}
	
	public void purgeFleet() {
		gs.purgeFleet();
	}
	
	public boolean nextPlay() {
		boolean next=true;
		int option=RandomNumber.newRandomNumber(100);
		if(option==99) {
			next=false;
		}
		else {
				if(option<=98&&option>=85) {
					try {
						List<Integer>list=gs.getFightersId("");
						if(list.isEmpty()) {
							System.out.println("ERROR: There are not fighters on the ship");
						}
						else {
							int position=RandomNumber.newRandomNumber(list.size());
							int id=list.get(position);
							gs.improveFighter(id, option, gb);
						}
					} catch (WrongFighterIdException e) {
						System.out.println("ERROR: "+ e.getMessage());
					}
				}
				else if(option<=84&&option>=25) {
					try {
						List<Integer>list=gs.getFightersId("ship");
						if(list.isEmpty()) {
							System.out.println("ERROR: There are not fighters on the ship");
						}
						else {
						int position=RandomNumber.newRandomNumber(list.size());
						int id=list.get(position);
						int x= RandomNumber.newRandomNumber(gb.getSize());
						int y= RandomNumber.newRandomNumber(gb.getSize());
						Coordinate c=new Coordinate(x,y);
						gs.launch(id, c, gb);}
					} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
						throw new RuntimeException(e);
					}
				}
				else if(option>=0&&option<=24) {
					try {
						List<Integer>list=gs.getFightersId("board");
						if(list.isEmpty()) {
							System.out.println("ERROR: There are not fighters on the ship");
						}
						else {
						int position=RandomNumber.newRandomNumber(list.size());
						int id=list.get(position);
						gs.patrol(id, gb);}
					} catch (WrongFighterIdException | FighterNotInBoardException e) {
						throw new RuntimeException(e);
					}
				}
		}
		return next;
	}

	@Override
	public WinsScore getWinsScore() {
		return gs.getWinsScore();
	}

	@Override
	public DestroyedFightersScore getDestroyedFightersScore() {
		return gs.getDestroyedFightersScore();
	}

}
