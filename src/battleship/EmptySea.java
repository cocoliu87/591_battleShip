package battleship;

/**
 * Subclass of the Ship class defines EmptySea type, unoccupied locations in an Ocean.
 * @author Yingqiu
 */
public class EmptySea extends Ship {
	/**
	 * Length of an EmptyOcean.
	 */
	static final int lengthOfEmptyOcean = 1;

	/** Constructs an EmptyOcean which's length is 1. */
	public EmptySea() {
		super(lengthOfEmptyOcean);
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
