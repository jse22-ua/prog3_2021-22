package model.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShipPreTest {

	GameShip gameShip;
	List<Fighter>fleet ;
	GameBoard gameBoard;
	
	@Before
	public void setUp() throws Exception {
		 gameShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
		 Fighter.resetNextId();
	}

	/* Se comprueba que el constructor copia de GameShip es correcto y que
	   GameShip es una clase derivada de Ship (no se han duplicado los atributos)
	 */
	//TODO
	@Test
	public void testGameShip() {
		assertEquals ("Lanzadera T-4a", gameShip.getName());
		assertEquals (Side.IMPERIAL, gameShip.getSide());
		assertEquals (0,  gameShip.getWins());
		assertEquals (0,  gameShip.getLosses());
		fleet = (List<Fighter>) gameShip.getFleetTest();
		assertNotNull (fleet);
		assertTrue(gameShip instanceof Ship);
	}

	/* Se comprueba que isFleetDestroyed devuelve true si no hay cazas en
	 * la nave.
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedEmpty() {
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	/* Añade fighters a un GameShip. Luego destruyelos todos y comprueba que 
	 * isFleetDestroyed devuelve true.
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedAllDestroyed() {
		GameShip gameShip=new GameShip("Prototipo1", Side.REBEL);
		gameShip.addFighters("5/TIEInterceptor:4/TIEBomber:2/TIEFighter");
		for(Fighter f : gameShip.getFleetTest()) {
			f.addShield(-700);
		}
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	
	/* Añade cazas a una nave. Destruye todos menos uno y comprueba que 
	 * isFleetDestroyed() devuelve false
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedNotAllDestroyed() {
		GameShip gameShip=new GameShip("Prototipo2", Side.REBEL);
		gameShip.addFighters("2/XWing:5/YWing:1/XWing");
		for(Fighter f: gameShip.getFleetTest()) {
			if(!f.getType().equals("XWing")) {
				f.addShield(-500);
			}
		}
		assertFalse(gameShip.isFleetDestroyed());
	}
	
	/* Se comprueba que se obtiene una lista vacía de una nave sin cazas. Luego se
	 * añaden cazas, se destruyen todos y se comprueba que se sigue devolviendo una
	 * lista vacía.
	 */
	@Test
	public void testGetFightersIdListEmpty() {
		List<Integer> l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");
		for (Fighter f : gameShip.getFleetTest()) {
			f.addShield(-300);
		}
		l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
	}

	/* Añade cazas a un Ship. Comprueba que al invocar a getFightersId("ship") 
	 * se devuelve una lista con los 'id' de todos los cazas del la nave. 
	 * Además comprueba que al invocar a getFightesId("board") se devuelve una lista 
	 * vacía.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty1()  {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		
		List<Integer> l = gameShip.getFightersId("ship");
		List<Integer> l2= gameShip.getFightersId("board");
		List<Integer> lAux= new ArrayList<>();
		for(int i=1;i<51;i++) {
			lAux.add(i);
		}
		
		assertEquals(lAux,l);
		assertTrue(l2.isEmpty());
		
	}
	
	/* Se añaden cazas a un Ship. Se añaden todos a un tablero. Se comprueba que al invocar a  getFightersId("board") 
	 * se devuelve una lista con los id de todos los cazas del la nave.
	 * Además se comprueba que getFightersId("ship") devuelve una lista vacía
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty2() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		int position=0;
		List<Integer> l = new ArrayList<>();
		for(int i=0;i!=10;i++) {
			for(int j=0; j!=10;j++) {
				if(position<50) {
					l.add(position+1);
					gameBoard.launch(new Coordinate(i,j), fleet.get(position));
					position++;
					}
			}
		}
		List<Integer> l2= gameShip.getFightersId("board");
		List<Integer> l3= gameShip.getFightersId("ship");
		assertTrue(l3.isEmpty());
		assertEquals(l2,l);
	}
	
	/* Añade cazas a un Ship. Añade algunos a un tablero. Destruye otros. 
	 * Comprueba que al invocar a:
	 *  - getFightersId("board") se devuelve una lista solo con los que realmente
	 * 		están en el tablero. 
     *  - getFightersId("ship") se devuelve una lista solo con los que no están en el tablero y no están destruídos.
     *  - getFightersId("") se devuelve una lista con todos los no destruídos.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty3() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		GameShip gameShip= new GameShip("FlotaSuperChachi",Side.REBEL);
		GameBoard gameBoard=new GameBoard(10);
		gameShip.addFighters("4/AWing:5/XWing:3/YWing");
		fleet=gameShip.getFleetTest();
		
		gameBoard.launch(new Coordinate(9,0),fleet.get(5));
		gameBoard.launch(new Coordinate(6,9), fleet.get(9));
		gameBoard.launch(new Coordinate(4,7), fleet.get(10));
		List<Integer> lboard=new ArrayList<>();
		lboard.add(6);
		lboard.add(10);
		lboard.add(11);
		
		gameShip.getFleetTest().get(1).addShield(-400);
		gameShip.getFleetTest().get(3).addShield(-400);
		
		List<Integer> lAll= new ArrayList<>();
		List<Integer> lship=new ArrayList<>();
		
		for(int i=1;i<13;i++) {
			if(i!=4&&i!=2) {
				lAll.add(i);
				if(i!=11&&i!=10&&i!=6) {
					lship.add(i);
				}
			}
		}
		
		List<Integer> lb=gameShip.getFightersId("board");
		List<Integer> ls=gameShip.getFightersId("ship");
		List<Integer> lall=gameShip.getFightersId("");
		
		assertEquals(lb,lboard);
		assertEquals(ls,lship);
		assertEquals(lAll,lall);
	}
	
	/* Añade cazas a un GameShip e intenta poner uno, con launch, con una id que no existe. 
	 * Se comprueba que se lanza la excepción WrongFighterIdException y que no lo ha puesto.
	 * Luego destruye uno del GameShip e intenta ponerlo en el tablero. Comprueba que
	 * también se lanza la excepción WrongFighterIdException y que tampoco se ha puesto.
	 */
	//TODO
	@Test
	public void testLaunchWrongFighterIdException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		Coordinate c=new Coordinate(4,3);
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.launch(20, c, gameBoard);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		} catch (WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
		gameShip.getFleetTest().get(2).addShield(-300);
		try {
			gameShip.launch(3,new Coordinate(0,1),gameBoard);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		}catch(WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		}catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}
	}
	
	
	/* Añaden cazas a un GameShip e intenta poner uno, con launch, en una coordenada
	 * fuera del tablero. 
	 * Comprueba que se lanza la excepción OutOfBoundsException.
	 */
	//TODO
	@Test
	public void testLaunchOutOfBoundsException() throws InvalidSizeException {
		GameShip gameShip= new GameShip("Flota",Side.IMPERIAL);
		gameShip.addFighters("2/TIEBomber");
		GameBoard gb = new GameBoard (7);
		Coordinate coordinate=new Coordinate(6,9);
		try {
			gameShip.launch(1, coordinate, gb);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		
		}catch(OutOfBoundsException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameShip.getFleetTest().get(0).getPosition());
		}catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}
	}
	
	/* Se añaden cazas a un GameShip y se pone a patrullar a uno de ellos en un
	 * tablero. Como no está en él, se comprueba que se lanza la excepción 
	 * FighterNotInBoardException y no otra y que el mensaje empieza con la
	 * cadena "ERROR:"
	 */
	@Test
	public void testPatrolNotInBoardException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.patrol(13, gameBoard);
			fail("ERROR: Debió lanzar la excepción FighterNotInBoardException");
		} catch (FighterNotInBoardException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
	}

	/* Añade cazas a un GameShip y pon a patrullar a uno con una id que
	 * no existe en el tablero. Como no está en él, comprueba que se lanza 
	 * la excepción WrongFighterIdException y no otra.
	 */
	//TODO
	@Test
	public void testPatrolWrongFighterIdException() throws InvalidSizeException {
		GameShip gameShip = new GameShip("Cazas", Side.IMPERIAL);
		gameShip.addFighters("2/TIEFighter");
		GameBoard gameBoard = new GameBoard(9);
		
		try {
			gameShip.patrol(5, gameBoard);
			fail("no Salt� WrongFighterIdExpcetion");
		}catch(WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
		}catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}
	}
	
	/* Añade cazas a un GameShip y pon un TIEInterceptor en un tablero.
	 * Añade una mejora de 97 al caza del tablero. Comprueba que ya no está en
	 * el tablero y que el ataque ahora es de 133 y el escudo de 109.
	 */
	@Test
	public void testImproveFighter() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		gameShip.launch(5,new Coordinate(0,0),gameBoard);
		gameShip.improveFighter(5, 97, gameBoard);
		assertNull(gameShip.getFleetTest().get(4).getPosition());
		assertEquals(133,gameShip.getFleetTest().get(4).getAttack());
		assertEquals(109,gameShip.getFleetTest().get(4).getShield());
	}
	
	/* Se añaden cazas a un GameShip. Se intenta mejorar uno de los cazas del GameShip que
	 * no está en tablero alguno. Se comprueba que ha existido la mejora en dicho caza.
	 * Se intenta mejorar un id de un caza que no existe. Se comprueba que se lanza la excepción
	 * WrongFighterIdException y que lanza el mensaje con el inicio de 'ERROR:'
	 */
	//TODO
	@Test
	public void testImproveFighterExceptions() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());	
		try {
			gameShip.improveFighter(6, 97, gameBoard);
		} catch (WrongFighterIdException e) {
			fail("ERROR: No debió lanzar la excepción "+e.getClass().getSimpleName());
		}
		Fighter f=gameShip.getFleetTest().get(5);
		try {
			gameShip.improveFighter(14,97,gameBoard);
			fail("No se lanz� la exception");
		}catch(WrongFighterIdException e) {
			assertTrue(e.getMessage().startsWith("ERROR:"));
		}
		
	}
	
	/* Realiza las comprobaciones de los parámetros null en launch, patrol e improveFighter
	 * de GameShip */
	//TODO
	@Test
	public void testRequireNonNull() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException, FighterNotInBoardException  {
		
		try {
			gameShip.launch(2, null, new GameBoard(10));
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.launch(2, new Coordinate(3,2), null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.improveFighter(2, 1000, null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}

	}

}
