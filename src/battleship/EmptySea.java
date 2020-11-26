package battleship;

/**
 * subclass of the Ship class.
 * @author Yingqiu
 */


public class EmptySea extends Ship {
	
	// set the length of empty sea as 1.
	public EmptySea() {
		super(1);
	}
	
	/**
	 * @Override
	 * @param the bowRow of the ship
	 * @param the bowColumn of the ship
	 * @return true/false if there is shoot or not.
	 */
	boolean shootAt(int row, int column) {
		super.setBowRow(row);
		super.setBowColumn(column);
		return false;
	}
	
	/**
	 * @Override
	 * @return true/false if the ship is suck or not.
	 */
	boolean isSunk() {
		return false;
	}

	/**
	 * @Override
	 * @return the string for the print in the ocean class.
	 */
	public String toString() {
		if (this.getHit()[0]) {
			return "-";
		}
		else {
			return ".";
		}
		
	}

	/**
	 * @Override
	 * @return the type of empty sea.
	 */
	public String getShipType() {
		String shipType = "empty";
		return shipType;
	}
}
