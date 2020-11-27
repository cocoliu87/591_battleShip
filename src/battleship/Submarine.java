package battleship;

/**
 * subclass of the Ship class.
 * @author Yingqiu
 */

public class Submarine extends Ship{
	
	// static final variable
	static final int lengthOfSubmarine = 1;
	
	
	// get the length of submarine.
	public Submarine() {
		super(lengthOfSubmarine);
	}

	/**
	 * Overrides getShipType() method
	 * @return the type of the submarine.
	 */
	@Override
	public String getShipType() {
		String shiptype = "submarine";
		return shiptype;
	}
}
