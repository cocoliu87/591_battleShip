package battleship;

/**
 * Subclass of the Ship class defines Submarine ship type.
 * @author Yingqiu
 */
public class Submarine extends Ship{
	
	// static final variable
	/**
	 * Length of a Submarine ship.
	 */
	static final int lengthOfSubmarine = 1;
	
	
	// get the length of submarine.
	/**
	 * Constructs a Submarine ship which's length is 3.
	 */
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
