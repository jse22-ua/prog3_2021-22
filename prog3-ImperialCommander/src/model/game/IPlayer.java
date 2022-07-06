package model.game;

import model.game.score.*;

/**
 * Practica 4
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public interface IPlayer {
	public void setBoard(GameBoard gb);
	public GameShip getGameShip();
	public WinsScore getWinsScore();
	public DestroyedFightersScore getDestroyedFightersScore();
	public void initFighters();
	public boolean isFleetDestroyed();
	public String showShip();
	public void purgeFleet();
	public boolean nextPlay();
}
