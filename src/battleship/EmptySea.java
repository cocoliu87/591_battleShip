package battleship;

/**
 * subclass of the Ship class.
 * @author Yingqiu
 */


public class EmptySea extends Ship {
	
	/** set the length of empty sea as 1. */
	public EmptySea() {
		super(1);
	}
	
	/**
	 * Overrides shootAt() method.
	 * @param row
	 *        the bowRow of the ship
	 * @param column
	 *        the bowColumn of the ship
	 * @return true/false if there is shoot or not.
	 */
	@Override
	boolean shootAt(int row, int column) {
		super.setBowRow(row);
		super.setBowColumn(column);
		return false;
	}
	
	/**
	 * Overrides isSunk() method.
	 * @return true/false if the ship is suck or not.
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * Overrides toString() method
	 * @return the string for the print in the ocean class.
	 */
	@Override
	public String toString() {
		if (this.getHit()[0]) {
			return "-";
		}
		else {
			return ".";
		}
		
	}

	/**
	 * Overrides toString() method.
	 * @return the type of empty sea.
	 */
	@Override
	public String getShipType() {
		String shipType = "empty";
		return shipType;
	}
}
