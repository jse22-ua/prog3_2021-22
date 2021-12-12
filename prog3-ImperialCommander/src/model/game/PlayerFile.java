package model.game;

/**
 * Practica 4
 * @author Judit Serrano Espinosa,74379872B
 *
 */

import java.io.*;
import java.util.Objects;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerFile implements IPlayer{
	private BufferedReader br;
	private GameBoard board;
	private GameShip ship;
	
	PlayerFile(Side side,BufferedReader br){
		Objects.requireNonNull(br);
		StringBuilder compostname=new StringBuilder();
		compostname.append("PlayerFile");
		
		if(side.equals(Side.REBEL)) {
		compostname.append(" REBEL ");
		}
		else {
			compostname.append(" IMPERIAL ");
		}
		compostname.append("Ship");
		String name=compostname.toString();
		
		ship= new GameShip(name,side);
		this.br=br;
	}

	public GameShip getGameShip() {
		return ship;
	}

	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		this.board = gb;
		
	}

	@Override
	public void initFighters() {
		try {
			String t=br.readLine();
			ship.addFighters(t);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}
	
	public String showShip() {
		return ship.toString() + ship.showFleet();
	}
	
	public void purgeFleet() {
		ship.purgeFleet();
	}
	@Override
	public boolean nextPlay() {
		boolean next=true;
		try {
			String t=br.readLine();
			String[] instruction=t.split("\\ ");
			switch(instruction[0]) {
			case "exit":
				next=false;
				break;
			case "improve":
				if(instruction.length!=3||Integer.parseInt(instruction[2])<100) {
					throw new RuntimeException("ERROR: Some components are missing");
				}
				try {
					ship.improveFighter(Integer.parseInt(instruction[1]),Integer.parseInt(instruction[2]),board);
				} catch (NumberFormatException | WrongFighterIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "patrol":
				if(instruction.length!=2) {
					throw new RuntimeException("ERROR: it doesn't know id");
				}
				try {
					ship.patrol(Integer.parseInt(instruction[1]), board);
				} catch (NumberFormatException | WrongFighterIdException | FighterNotInBoardException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			case "launch":
				if(instruction.length<3|| instruction.length>4) {
					throw new RuntimeException("ERROR: Incomplete information");
				}
				try {
					Fighter f= ship.getFirstAvailableFighter("");
					int x=Integer.parseInt(instruction[1]);
					int y=Integer.parseInt(instruction[2]);
				if(instruction.length==3) {
					try {
						ship.launch(f.getId(),new Coordinate(x,y), board);
					} catch (WrongFighterIdException | FighterAlreadyInBoardException |OutOfBoundsException  e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
				}
				else if(instruction.length==4) {
					try {
						int id=Integer.parseInt(instruction[3]);
						try {
							ship.launch(id,new Coordinate(x,y),board);
						} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}catch(NumberFormatException e) {
						f=ship.getFirstAvailableFighter(instruction[3]);
						try {
							ship.launch(f.getId(),new Coordinate(x,y),board);
						} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			} catch (NoFighterAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			default:throw new RuntimeException("ERROR: no instruction readed");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return next;
	}
}
