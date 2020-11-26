package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		
		ocean.setupEmptyOcean();
		Destroyer destroyer1 = new Destroyer();
		row = 2;
		column = 6;
		horizontal = true;
		destroyer1.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(2, 5));
		assertTrue(ocean.isOccupied(2, 6));
		assertFalse(ocean.isOccupied(3, 5));
		
		ocean.setupEmptyOcean();
		Cruiser cruiser = new Cruiser();
		row = 2;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(2, 4));
		assertTrue(ocean.isOccupied(2, 5));
		assertTrue(ocean.isOccupied(2, 6));
		assertFalse(ocean.isOccupied(3, 5));
		
		ocean.setupEmptyOcean();
		Battleship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(9, 9));
		assertTrue(ocean.isOccupied(8, 9));
		assertTrue(ocean.isOccupied(7, 9));
		assertTrue(ocean.isOccupied(6, 9));
		assertFalse(ocean.isOccupied(5, 9));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests
		
		Submarine submarine = new Submarine();
		
		row = 0;
		column = 1;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(0, 1));
		assertTrue(submarine.isSunk());
		
		Cruiser cruiser = new Cruiser();
		row = 2;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(2, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(2, 8));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(2, 7));
		assertTrue(cruiser.isSunk());
		
		Battleship battleship = new Battleship();
		row = 4;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(4, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 4));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 3));
		assertTrue(battleship.isSunk());
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		
		ocean.setupEmptyOcean();
		assertFalse(ocean.shootAt(7, 1));
		assertFalse(ocean.shootAt(1, 8));
		assertFalse(ocean.shootAt(2, 5));
		assertEquals(3, ocean.getShotsFired());
		
		Cruiser cruiser = new Cruiser();
		row = 3;
		column = 7;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		Destroyer destroyer1 = new Destroyer();
		row = 7;
		column = 4;
		horizontal = true;
		destroyer1.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 7));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(2, 7));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(1, 7));
		assertTrue(cruiser.isSunk());
		assertEquals(6, ocean.getShotsFired());
		assertTrue(ocean.shootAt(7, 4));
		assertFalse(destroyer1.isSunk());
		assertTrue(ocean.shootAt(7, 3));
		assertTrue(destroyer1.isSunk());
		assertEquals(8, ocean.getShotsFired());
		
		
		ocean.setupEmptyOcean();
		assertFalse(ocean.shootAt(7, 1));
		assertFalse(ocean.shootAt(1, 8));
		assertFalse(ocean.shootAt(2, 5));
		assertEquals(3, ocean.getShotsFired());
		Battleship battleship = new Battleship();
		row = 5;
		column = 6;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		Submarine submarine1 = new Submarine();
		row = 2;
		column = 3;
		horizontal = true;
		submarine1.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(5, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(3, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(2, 6));
		assertTrue(battleship.isSunk());
		assertEquals(7, ocean.getShotsFired());
		assertTrue(submarine1.shootAt(2, 3));
		assertEquals(8, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		ocean.setupEmptyOcean();
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		
		Battleship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(9, 8));
		assertFalse(battleship.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertTrue(ocean.shootAt(9, 9));
		assertFalse(battleship.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertTrue(ocean.shootAt(8, 9));
		assertFalse(battleship.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertTrue(ocean.shootAt(7, 9));
		assertFalse(battleship.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 9));
		assertTrue(battleship.isSunk());
		assertEquals(6, ocean.getHitCount());
		
		Cruiser cruiser = new Cruiser();
		row = 6;
		column = 3;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(7, 3));
		assertFalse(cruiser.isSunk());
		assertEquals(6, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 3));
		assertFalse(cruiser.isSunk());
		assertEquals(7, ocean.getHitCount());
		assertTrue(ocean.shootAt(5, 3));
		assertEquals(8, ocean.getHitCount());
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(5, 3));
		assertEquals(9, ocean.getHitCount());
		assertTrue(ocean.shootAt(4, 3));
		assertTrue(battleship.isSunk());
		assertEquals(10, ocean.getHitCount());
		assertFalse(ocean.shootAt(5, 3));
		assertEquals(10, ocean.getHitCount());
		
	}
	
	@Test
	void testGetShipsSunk() {
		
		ocean.setupEmptyOcean();
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		Cruiser cruiser = new Cruiser();
		row = 6;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		Battleship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(6, 3));
		assertFalse(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(6, 2));
		assertFalse(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(6, 1));
		assertTrue(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 0));
		assertTrue(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(6, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(7, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(cruiser.isSunk());
		assertFalse(battleship.isSunk());
		assertEquals(8, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(6, 9));
		assertTrue(cruiser.isSunk());
		assertTrue(battleship.isSunk());
		assertEquals(9, ocean.getHitCount());
		assertEquals(3, ocean.getShipsSunk());
		assertFalse(ocean.shootAt(5, 9));
		assertTrue(cruiser.isSunk());
		assertTrue(battleship.isSunk());
		assertEquals(9, ocean.getHitCount());
		assertEquals(3, ocean.getShipsSunk());
		
		
	}

	@Test
	void testGetShipArray() {
		
		ocean.setupEmptyOcean();
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Cruiser cruiser = new Cruiser();
		row = 6;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		Battleship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		
		assertEquals("destroyer", shipArray[0][5].getShipType());
		assertEquals("cruiser", shipArray[6][3].getShipType());
		assertEquals("battleship", shipArray[7][9].getShipType());
		
		
		
	}

}
