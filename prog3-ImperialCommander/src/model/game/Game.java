package model.game;
/**
 * Practica 4
 * @author Judit Serrano Espinosa,74379872B
 *
 */
import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.*;

public class Game {
	private GameBoard gb;
	private static final int BOARD_SIZE=10;
	private IPlayer imperial;
	private IPlayer rebel;
	private Ranking<WinsScore> winranking;
	private Ranking <DestroyedFightersScore> destroyedrank;
	
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		this.imperial=imperial;
		this.rebel=rebel;
		try {
			gb= new GameBoard(BOARD_SIZE);
			imperial.setBoard(gb);
			rebel.setBoard(gb);
		} catch (InvalidSizeException e) {
			throw new RuntimeException("ERROR: there are a programming error");
		}
		
	}

	public GameBoard getGameBoard() {
		return gb;
	}
	
	private void ShowInfo() {
		System.out.print(gb.toString());
		System.out.print("\n");
		System.out.print(imperial.showShip());
		System.out.print("\n");
		System.out.print(rebel.showShip());	
		System.out.print("\n");
	}
	
	private void cleaning() {
		imperial.purgeFleet();
		rebel.purgeFleet();
	}
	
	private void showRanking() {
		winranking = null;
		destroyedrank=null;
		winranking = new Ranking<>();
		destroyedrank = new Ranking<>();
		winranking.addScore(imperial.getWinsScore());
		winranking.addScore(rebel.getWinsScore());
		destroyedrank.addScore(imperial.getDestroyedFightersScore());
		destroyedrank.addScore(rebel.getDestroyedFightersScore());
		
		System.out.print("RANKING WINS: ");
		System.out.print(winranking.toString() + "\n");
		System.out.print("RANKING DESTROYED: ");
		System.out.print(destroyedrank.toString()+ "\n");	
		
	}
	public Side play() {
		imperial.initFighters();
		rebel.initFighters();
		boolean keepPlaying=true;
		boolean exitImperial=false;
		
		do {
			showRanking();
			System.out.print("BEFORE IMPERIAL\n");
			ShowInfo();
			System.out.print("IMPERIAL("+ gb.numFighters(Side.IMPERIAL)+"):");
			keepPlaying=imperial.nextPlay();
			if(!keepPlaying) {
				exitImperial=true;
				break;
			}
			System.out.print("AFTER IMPERIAL, BEFORE REBEL\n");
			ShowInfo();
			if(imperial.isFleetDestroyed()||rebel.isFleetDestroyed()) {
				break;
			}
			System.out.print("REBEL("+ gb.numFighters(Side.REBEL)+"):");
			keepPlaying=rebel.nextPlay();
			if(!keepPlaying) {
				break;
			}
			System.out.print("AFTER REBEL\n");
			ShowInfo();
			cleaning();
		}while(!imperial.isFleetDestroyed()&&!rebel.isFleetDestroyed());
		cleaning();
		
		showRanking();
		if(imperial.isFleetDestroyed()||exitImperial||imperial.isFleetDestroyed()) {
			return Side.REBEL;
		}
		else {
			return Side.IMPERIAL;
		}
	}
	
}
