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
	 * @Override
	 * @return the type of the submarine.
	 */
	public String getShipType() {
		String shiptype = "submarine";
		return shiptype;
	}
}
