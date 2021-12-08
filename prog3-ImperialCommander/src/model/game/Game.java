package model.game;
/**
 * Practica 4
 * @author Judit Serrano Espinosa,74379872B
 *
 */
import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private GameBoard gb;
	private static final int BOARD_SIZE=10;
	private IPlayer imperial;
	private IPlayer rebel;
	
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		this.imperial=imperial;
		this.rebel=rebel;
		try {
			gb= new GameBoard(BOARD_SIZE);
		} catch (InvalidSizeException e) {
			throw new RuntimeException("ERROR: there are a programming error");
		}
	}

	public GameBoard getGameBoard() {
		return gb;
	}
	
	private String infoPlayer(IPlayer one) {
		StringBuilder information= new StringBuilder();
		information.append(one.getGameShip().toString());
		information.append("\n");
		information.append(one.getGameShip().showFleet());
		information.append("\n");
		return information.toString();
	}
	
	private void ShowInfo() {
		gb.toString();
		System.out.println(infoPlayer(imperial));
		System.out.println(infoPlayer(rebel));	
	}
	
	private void cleaning() {
		imperial.purgeFleet();
		rebel.purgeFleet();
	}
	
	public Side play() {
		imperial.initFighters();
		rebel.initFighters();
		boolean keepPlaying;
		do {
			System.out.println("BEFORE IMPERIAL");
			ShowInfo();
			System.out.println("IMPERIAL("+ gb.numFighters(Side.IMPERIAL)+"):");
			keepPlaying=imperial.nextPlay();
			if(!keepPlaying) {
				break;
			}
			System.out.println("AFTER IMPERIAL, BEFORE REBEL");
			ShowInfo();
			if(imperial.isFleetDestroyed()||rebel.isFleetDestroyed()) {
				break;
			}
			System.out.println("REBEL("+ gb.numFighters(Side.REBEL)+"):");
			keepPlaying=rebel.nextPlay();
			if(!keepPlaying) {
				break;
			}
			System.out.println("AFTER REBEL");
			ShowInfo();
			cleaning();
		}while(imperial.isFleetDestroyed()||rebel.isFleetDestroyed());
		cleaning();
		if(imperial.isFleetDestroyed()) {
			return Side.REBEL;
		}
		else {
			return Side.IMPERIAL;
		}
	}
	
}
