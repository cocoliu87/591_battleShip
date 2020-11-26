package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
		ship = new EmptySea();
		assertEquals(1, ship.getLength());
		
		
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		ocean.setupEmptyOcean();
		Ship cruiser = new Cruiser();
		row = 5;
		column = 0;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(row, cruiser.getBowRow());
		
		ocean.setupEmptyOcean();
		cruiser = new Cruiser();
		row = 5;
		column = 0;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		ocean.setupEmptyOcean();
		Ship destroyer = new Destroyer();
		row = 1;
		column = 0;
		horizontal = true;
		assertFalse(destroyer.okToPlaceShipAt(row, column, horizontal, ocean));
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(row, destroyer.getBowRow());
		
		ocean.setupEmptyOcean();
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = true;
		assertTrue(submarine.okToPlaceShipAt(row, column, horizontal, ocean));
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		
		
	}

	@Test
	void testGetBowColumn() {
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		ocean.setupEmptyOcean();
		Ship cruiser = new Cruiser();
		row = 5;
		column = 1;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(column, cruiser.getBowColumn());
		
		ocean.setupEmptyOcean();
		cruiser = new Cruiser();
		row = 5;
		column = 0;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		
		ocean.setupEmptyOcean();
		Ship destroyer = new Destroyer();
		row = 0;
		column = 1;
		horizontal = true;
		assertTrue(destroyer.okToPlaceShipAt(row, column, horizontal, ocean));
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
		
		ocean.setupEmptyOcean();
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = true;
		assertTrue(submarine.okToPlaceShipAt(row, column, horizontal, ocean));
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ocean.setupEmptyOcean();
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		ship = new Cruiser();
		hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		
		ship = new Destroyer();
		hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		int row = 0;
		int column = 5;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		ocean.setupEmptyOcean();
		ship = new Cruiser();
		hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		row = 4;
		column = 6;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);
		ship.shootAt(row, column-1);
		ship.shootAt(row, column-2);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
	}

	@Test
	void testIsHorizontal​() {
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		
		ocean.setupEmptyOcean();
		Ship cruiser = new Cruiser();
		row = 4;
		column = 0;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		
		ocean.setupEmptyOcean();
		Ship destroyer = new Destroyer();
		row = 4;
		column = 0;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.isHorizontal());
		
		ocean.setupEmptyOcean();
		Ship submarine = new Submarine();
		row = 4;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
		
		Ship destroyer = new Destroyer();
		row = 0;
		column = 4;
		horizontal = true;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
		
		Ship submarine = new Submarine();
		row = 0;
		column = 4;
		horizontal = true;
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		
		Ship cruiser = new Cruiser();
		row = 0;
		column = 6;
		horizontal = false;
		cruiser.setHorizontal(horizontal);
		assertFalse(cruiser.isHorizontal());
		
		Ship destroyer = new Destroyer();
		row = 4;
		column = 6;
		horizontal = false;
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());
		
		Ship submarine = new Submarine();
		row = 0;
		column = 6;
		horizontal = true;
		submarine.setHorizontal(horizontal);
		assertTrue(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		ocean.setupEmptyOcean();
		Ship cruiser = new Cruiser();
		row = 6;
		column = 0;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "OK to place ship here.");
		
		ocean.setupEmptyOcean();
		Ship destroyer = new Destroyer();
		row = 0;
		column = 6;
		horizontal = false;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "OK to place ship here.");
		
		ocean.setupEmptyOcean();
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		ocean.setupEmptyOcean();
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		Cruiser cruiser1 = new Cruiser();
		row = 1;
		column = 3;
		horizontal = true;
		boolean ok3 = cruiser1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship vertically adjacent below.");

		Cruiser cruiser2 = new Cruiser();
		row = 2;
		column = 3;
		horizontal = false;
		boolean ok4 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship vertically adjacent below.");

		Destroyer destroyer1 = new Destroyer();
		row = 6;
		column = 7;
		horizontal = false;
		boolean ok5 = destroyer1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok5, "Not OK to place ship vertically adjacent below.");
		
		Destroyer destroyer2 = new Destroyer();
		row = 0;
		column = 5;
		horizontal = false;
		boolean ok6 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6, "Not OK to place ship vertically adjacent below.");
		
		Submarine submarine1 = new Submarine();
		row = 2;
		column = 5;
		horizontal = false;
		boolean ok7 = submarine1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok7, "Not OK to place ship vertically adjacent below.");
	}

	@Test
	void testPlaceShipAt() {
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		ocean.setupEmptyOcean();
		Ship battleship1 = new Battleship();
		row = 6;
		column = 0;
		horizontal = false;
		battleship1.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship1.getBowRow());
		assertEquals(column, battleship1.getBowColumn());
		assertFalse(battleship1.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[2][0].getShipType());
		assertEquals(battleship1, ocean.getShipArray()[5][0]);
		assertEquals(battleship1, ocean.getShipArray()[4][0]);
		assertEquals(battleship1, ocean.getShipArray()[3][0]);
		
		ocean.setupEmptyOcean();
		Ship cruiser1 = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser1.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser1.getBowRow());
		assertEquals(column, cruiser1.getBowColumn());
		assertFalse(cruiser1.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[6][9].getShipType());
		assertEquals(cruiser1, ocean.getShipArray()[8][9]);
		assertEquals(cruiser1, ocean.getShipArray()[7][9]);
		
	}

	@Test
	void testShootAt() {
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		ocean.setupEmptyOcean();
		Ship battleship1 = new Battleship();
		row = 8;
		column = 0;
		horizontal = false;
		battleship1.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship1.shootAt(4, 0));
		boolean[] hitArray1 = {false, false, false, false};
		assertArrayEquals(hitArray1, battleship1.getHit());
		
		ocean.setupEmptyOcean();
		Ship battleship2 = new Battleship();
		row = 8;
		column = 0;
		horizontal = false;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(battleship2.shootAt(7, 0));
		boolean[] hitArray2 = {false, true, false, false};
		assertArrayEquals(hitArray2, battleship2.getHit());
	}
	
	@Test
	void testIsSunk() {
		ocean.setupEmptyOcean();
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		ocean.setupEmptyOcean();
		Ship destroyer = new Destroyer();
		row = 6;
		column = 6;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(destroyer.isSunk());
		assertFalse(destroyer.shootAt(6, 7));
		assertTrue(destroyer.shootAt(6, 6));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(6, 5));
		assertTrue(destroyer.isSunk());
		
		ocean.setupEmptyOcean();
		Ship cruiser = new Cruiser();
		row = 6;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.isSunk());
		assertFalse(cruiser.shootAt(6, 7));
		assertTrue(cruiser.shootAt(6, 6));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(5, 6));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(4, 6));
		assertTrue(cruiser.isSunk());
		
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		row = 6;
		column = 6;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.isSunk());
		assertFalse(battleship.shootAt(6, 7));
		assertTrue(battleship.shootAt(6, 6));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(5, 6));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(4, 6));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(3, 6));
		assertTrue(battleship.isSunk());
	}

	@Test
	void testToString() {
		ocean.setupEmptyOcean();
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		
		ocean.setupEmptyOcean();
		Ship cruiser = new Cruiser();
		assertEquals("x", cruiser.toString());
		
		row = 9;
		column = 1;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		cruiser.shootAt(9, 2);
		assertEquals("x", cruiser.toString());
		cruiser.shootAt(9, 1);
		assertEquals("x", cruiser.toString());
		cruiser.shootAt(8, 1);
		assertEquals("x", cruiser.toString());
		cruiser.shootAt(7, 1);
		assertEquals("s", cruiser.toString());
		
		ocean.setupEmptyOcean();
		Ship destroyer = new Destroyer();
		assertEquals("x", destroyer.toString());
		
		row = 9;
		column = 1;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		destroyer.shootAt(9, 2);
		assertEquals("x", destroyer.toString());
		destroyer.shootAt(9, 1);
		assertEquals("x", destroyer.toString());
		destroyer.shootAt(9, 0);
		assertEquals("s", destroyer.toString());
		
		ocean.setupEmptyOcean();
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());
		
		row = 9;
		column = 1;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(9, 2);
		assertEquals("x", submarine.toString());
		submarine.shootAt(9, 1);
		assertEquals("s", submarine.toString());
		
		
		
	}

}
