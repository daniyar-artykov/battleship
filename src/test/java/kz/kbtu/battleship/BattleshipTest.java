package kz.kbtu.battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Nathan Newman
 * @author Miguel Ibarra
 * @author Zachary Smith
 * 
 */
public class BattleshipTest {
	private BattleshipModelInterface model = new BattleshipModel();
	private PlayerInterface[] players = model.getPlayers();
	private PlayerInterface p1 = players[0];
	private PlayerInterface p2 = players[1];

	// @Before
	// public void setUp() {
	// p1.setShipInputBoard();
	// p2.setGameBoard();
	// }

	@Test
	public void getName() {
		assertEquals("getName() did not return correct default name", "Player",
				p1.getName());
	}

	@Test
	public void setName() {
		p1.setName("Player 1");
		assertEquals("Name not set correctly.", "Player 1", p1.getName());
	}

	@Test
	public void setNameToNull() {
		p2.setName(null);
		assertEquals("Name not set correctly", p2.getName(), null);
	}

	@Test
	public void getShipsLeft() {
		assertEquals("getShipsLeft did not return 5", 5, p1.getShipsLeft());
	}

	@Test
	public void getShotsTaken() {
		assertEquals("Should have taken 0 shots", 0, p1.getShotsTaken());
	}

	@Test
	public void getHitsTaken() {
		assertEquals("Should have taken 0 hits", 0, p1.getHitsTaken());
	}

	// @Test
	// public void placeShips() {
	// p1.placeShip(ShipEnum.BATTLESHIP, Direction.VERTICAL, 0, 0);
	// p1.placeShip(ShipEnum.CARRIER, Direction.VERTICAL, 1, 0);
	// p1.placeShip(ShipEnum.CRUISER, Direction.VERTICAL, 2, 0);
	// p1.placeShip(ShipEnum.DESTROYER, Direction.VERTICAL, 3, 0);
	// p1.placeShip(ShipEnum.SUBMARINE, Direction.VERTICAL, 4, 0);
	//
	// assertEquals("Ship at 0,0 not correct.", ShipEnum.BATTLESHIP,
	// p1.getShip(0, 0));
	// }

}
