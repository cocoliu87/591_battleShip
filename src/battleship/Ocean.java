package battleship;

import java.util.Random;

/**
 * Ocean is represented by a 10x10 array, named as Ships, and maintains some methods to run the game with ships.
 * @author Yingqiu
 */
public class Ocean {
	/**
	 * Defining instance variables 
	*/
	
	/** determine which ship is in any given location. */
	private Ship[][]ships;

	/**
	 * use this array to remember which place has been hit in the ocean.
	 */
	private boolean[][] hits;
	
	/** The total number of shots fired by the user */
	private int shotsFired;
	
	/** The number of times a shot hit a ship. */
	private int hitCount;
	
	/** The number of ships sunk (10 ships in all) */
	private int shipsSunk;
	
	/** The number of Battleship will be created */
	static int NUM_BATTLESHIPS = 1;

	/** The number of Cruiser will be created */
	static int NUM_CRUISERS = 2;

	/** The number of Destroyer will be created */
	static int NUM_DESTROYERS = 3;

	/** The number of Submarine will be created */
	static int NUM_SUBMARINES = 4;

	/** The size of Ocean, represented by a 2-D array */
	static int OCEAN_SIZE = 10;
	
	// constructor
	/**
	 * Constructs an ocean with a a 10x10 array of Ships
	 * setup up an empty ocean.
	 */
	public Ocean() {
		this.ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];
		setupEmptyOcean();
	}
	
	/**
	 *  helper function. set up the empty ocean and initialize variables.
	 */
	public void setupEmptyOcean() {
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				EmptySea es = new EmptySea();
				es.setBowRow(i);
				es.setBowColumn(j);
				ships[i][j] = es;
			}
		}
		this.hitCount = 0;
		this.shotsFired = 0;
		this.shipsSunk = 0;
		this.hits = new boolean[ships.length][ships[0].length];
	}


	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 */
	void placeAllShipsRandomly() {
		randomOfPlace(NUM_BATTLESHIPS, 4);
		randomOfPlace(NUM_CRUISERS, 3);
		randomOfPlace(NUM_DESTROYERS, 2);
		randomOfPlace(NUM_SUBMARINES, 1);
		printForReview();
	}

	private void printForReview() {
		System.out.print("  ");
		for (int c = 0; c < this.ships[0].length; c++)
			System.out.print(c+" ");
		System.out.println();
		for (int x = 0; x < this.ships.length; x++)
		{
			System.out.print(x+" ");
			for (int y = 0; y < this.ships[0].length; y++)
			{
				char c = this.ships[x][y].getShipType().charAt(0);
				System.out.print((c=='e'?c:Character.toUpperCase(c))+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Determine the random number as the place in the 10x10 array.
	 * @param count
	 *        the count of place of the ship
	 * @param lengthOfShip
	 *        the length of the ship
	 */
	public void randomOfPlace(int count, int lengthOfShip) {

		Random rand =new Random();
		Ship ship = null;

		while (count > 0) {
			int j;
			j = rand.nextInt(100);
			int x = j / 10;
			int y = j % 10;

			if (lengthOfShip == 4) {
				Battleship battleShip = new Battleship();
				battleShip.setHorizontal(rand.nextInt(2)%2==0);
				battleShip.placeShipAt(x, y, battleShip.isHorizontal(), this);
				ship = battleShip;
			}
			else if (lengthOfShip == 3) {
				Cruiser cruiser = new Cruiser();
				cruiser.setHorizontal(rand.nextInt(2)%2==0);
				cruiser.placeShipAt(x, y, cruiser.isHorizontal(), this);
				ship = cruiser;
			}
			else if (lengthOfShip == 2) {
				Destroyer destroyer = new Destroyer();
				destroyer.setHorizontal(rand.nextInt(2)%2==0);
				destroyer.placeShipAt(x, y, destroyer.isHorizontal(), this);
				ship = destroyer;
			}
			else if (lengthOfShip == 1) {
				Submarine submarine = new Submarine();
				submarine.setHorizontal(rand.nextInt(2)%2==0);
				submarine.placeShipAt(x, y, submarine.isHorizontal(), this);
				ship = submarine;
			}
			if (this.getShipArray()[x][y] == ship) {
				count--;
			}

		}
	}
	
	/**
	 * Check if the given location contains a ship
	 * @param row
	 *        the row of the location.
	 * @param column
	 *        column of the location.
	 * @return true/false if the given location contains a ship or not.
	 */
	boolean isOccupied(int row, int column) {
		return !(ships[row][column] instanceof EmptySea);
	}
	
	/**
	 * Check if  the given location contains a ”real” ship
	 * @param row
	 *        the row of the location.
	 * @param column
	 *        column of the location.
	 * @return true/false if  the given location contains a ”real” ship or not.
	 */
	boolean shootAt(int row, int column) {
		
		// updates the number of shots that have been fired.
		this.shotsFired += 1;
		this.hits[row][column] = true;
		
		// if the given location contains a ”real” ship and not sunk.
		if (!(ships[row][column] instanceof EmptySea)) {
			if (!(ships[row][column].isSunk())) {
				
				// updates the number of hits.
				if (this.hits[row][column]) {
					this.hitCount += 1;
				}
				boolean hit = ships[row][column].shootAt(row, column);
				if (hit) {
					System.out.println("You hit a ship.");
				}
				if (hit && ships[row][column].isSunk()) {
					this.shipsSunk ++;
					System.out.println("You just sank a ship - "+ships[row][column].getShipType());
				}
				return hit;
			}
			else {
				return false;
			}
			
		}
		else {
			ships[row][column].getHit()[0] = true;
			System.out.println("You missed.");
			return false;
		}
	}
	
	/**
	 * Gets the number of shots fired in the game.
	 * @return the number of shots fired.
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * Gets the number of hits recorded. All hits are counted, not just the first time a given square is hit.
	 * @return the number of hits recorded.
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * Gets the number of ships sunk
	 * @return the number of ships sunk.
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * Check if all ships have been sunk and if game is over.
	 * @return true/false if if all ships have been sunk or not.
	 */
	boolean isGameOver() {
		if (this.shipsSunk == 10) {
			return true;
		} else {
			return false;
		}
	}
	
	// the 10x10 array of Ships。
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	// Prints the Ocean. 
	void print() {
		
		// Numbers the row numbers and the column numbers. 
		System.out.print(" " + "\t");
		for (int i = 0; i <= 9; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		for (int x = 0; x <= 9; x++) {
			System.out.print(x + "\t");
			for (int y = 0; y <= 9; y++) {
				System.out.print(shipToString(x, y) + "\t");
			}
			System.out.println();
		}
		
	}

	/**
	 * @param r
	 *        the row of ship
	 * @param c
	 *        column of the ship.
	 * @return a single-character String
	 */
	private String shipToString(int r, int c) {
		Ship ship = ships[r][c];
		return hits[r][c]? ship.toString() : ".";
	}

	/**
	 * Check if the coordinates are valid to the ocean
	 * @param r
	 * 		  the row of the location.
	 * @param c
	 *        the column of the location.
	 * @return boolean
	 */
	public boolean isValidLocationInOcean(int r, int c) {
		return !(r < 0 || c < 0 || r >= this.ships.length || c >= this.ships[0].length);
	}
	
}
