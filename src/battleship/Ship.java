package battleship;

/**
 * Describes the characteristics common to all ships.
 * @author Yingqiu
 */
public abstract class Ship {
	/**
	 * Defining instance variables 
	 */
	
	/** The row that contains the bow (front part of the ship) */
	private int bowRow;
	
	/** The column that contains the bow (front part of the ship) */
	private int bowColumn;
	
	/** The length of the ship */
	private int length;

	/**
	 *	A boolean that represents whether the ship is going to be placed horizontally or vertically.
 	 */
	private boolean horizontal;
	
	/** An array of 4 booleans that indicate whether that part of the ship has been hit or not */
	private boolean[] hit = new boolean[4];
	
	/**
	 * Constructs a new Ship object
	 * @param length
	 *        the length of a ship
	 */
	public Ship(int length) {
		this.length = length;
		this.hit[0] = false;
		this.hit[1] = false;
		this.hit[2] = false;
		this.hit[3] = false;
	}
	
	/**
	 * Gets the ship length.
	 * @return the ship length.
	 */
	public int getLength() {
		return this.length;	
	}
	
	 
	/**
	 * Gets the row corresponding to the position of the bow.
	 * @return the position of the bow.
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	
	
	/**
	 * Gets the bow column location.
	 * @return the bow column location.
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	

	/**
	 * Gets the hit array.
	 * @return the hit array.
	 */
	public boolean[] getHit() {
		return this.hit;
	}
	
	/**
	 * Gets whether the ship is horizontal or not.
	 * @return whether the ship is horizontal or not.
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * Sets the value of bowRow
	 * @param row
	 *        the value of bowRow.
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Sets the value of bowColumn
	 * @param column
	 *        the value of bowColumn.
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Sets the value of the instance variable horizontal
	 * @param horizontal
	 *        the value of the instance variable horizontal.
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal; 
	}
	
	/**
	 * Get the specific type of ship as a String.
	 * @return the every specific type of ship as a String.
	 */
	public abstract String getShipType(); 
	
	/**
	 * Check if it is okay to put a ship of this length with its bow in this location.
	 * @param row
	 *        the bowRow of the ship
	 * @param column
	 *        the bowColumn of the ship
	 * @param horizontal
	 *        the horizontal of the ship
	 * @param ocean
	 *        the ocean
	 * @return true/false if there can be place a ship.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		// check if the given row and column are out the range of 10X10 
		if (row < 0 || row > 9 || column < 0 || column > 9) {
			return false;
		}
		
		// check if there are another ship on the given row and column.
		else if (ocean.isOccupied(row, column)) {
        	return false;
        }
		
		// check if the ship of the given row and column is out of 10X10.
		if (horizontal) {
			for (int i = column - (this.length-1); i <= column; i++) {
				if (i < 0) {
					return false;
				}
				else if (!okOfRange(row, i, ocean)) {
	        		return false;
	        	}
			}
		}
		else {
			for (int i = row - (this.length-1); i <= row; i++) {
				if (i < 0) {
					return false;
				}
        		if (!okOfRange(i, column, ocean)) {
        			return false;
        		};
	        }
		}
		return true;
	}
	
	/**
	 * Check if there are another ships around of the given row and column.
	 * @param row
	 *        the bowRow of the ship
	 * @param column
	 *        the bowColumn of the ship
	 * @param ocean
	 *        the ocean
	 * @return true/false if there is another ship around.
	 */
	boolean okOfRange(int row, int column, Ocean ocean) {
		int[][] array = {{0,1}, {0,-1}, {1,0}, {1,-1}, {1,1}, {-1,1}, {-1,0}, {-1,-1}};
		for (int[] a: array) {
			int newRow = row+a[0];
			int newCol = column+a[1];
			if (newRow < 0 || newRow > 9 || newCol < 0 || newCol > 9) {
				continue;
				}
			else {
				if (ocean.isOccupied(newRow, newCol)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Puts the ship in the ocean.
	 * @param row
	 *        the bowRow of the ship
	 * @param column
	 *        the bowColumn of the ship
	 * @param horizontal
	 *        the horizontal of the ship
	 * @param ocean
	 *        the ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (okToPlaceShipAt(row, column, horizontal, ocean)) {
			this.bowRow = row;
			this.bowColumn = column;
			this.horizontal = horizontal;
			Ship[][] body = ocean.getShipArray();
			for (int i = 0; i < this.length; i++) {
				if (horizontal) {
					body[row][column-i] = this;
				}
				else if (!horizontal) {
					body[row-i][column] = this;
				}
			}
		}
	}
	
	/**
	 * Check if hit a part of the ship occupies the given row and column, and the ship hasn’t been sunk.
	 * @param row
	 *        the bowRow of the ship
	 * @param column
	 *        the bowColumn of the ship
	 * @return true/false if there is shoot or not.
	 */
	boolean shootAt(int row, int column) {
		if (!this.isSunk() && this.horizontal && this.bowRow == row) {
			if (column <= this.bowColumn && column > this.bowColumn - this.length) {
				this.hit[this.bowColumn - column] = true;
				return true;
			}
		}
		if (!this.isSunk() && !this.horizontal && this.bowColumn == column) {
			if (row <= this.bowRow && row > this.bowRow - this.length) {
				this.hit[this.bowRow - row] = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if every part of the ship has been hit.
	 * @return true/false if every part of the ship has been hit. or not.
	 */
	boolean isSunk() {
		for (int i = 0; i < this.length; i++) {
			if (!(hit[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Overrides toString() method
	 * @return a single-character String to use in the Ocean’s print method.
	 */
	@Override
	public String toString() {
		
		// return ”s” if the ship has been sunk and ”x” if it has not been sunk.
		if (this.isSunk()) {
			return "s";
		}
		else {
			return "x";
		}
	}

}
