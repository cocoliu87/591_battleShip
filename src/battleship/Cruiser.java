package battleship;

/**
 * subclass of the Ship class.
 * @author Yingqiu
 */

public class Cruiser extends Ship{
	
	// static final variable
	static final int lengthOfCruiser = 3;
	
	// get the length of cruiser
	public Cruiser() {
		super(lengthOfCruiser);
	}

	/**
	 * @Override
	 * @return the type of the cruiser.
	 */
	public String getShipType() {
		String shiptype = "cruiser";
		return shiptype;
	}
}
