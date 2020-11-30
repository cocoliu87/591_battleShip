package battleship;

/**
 * Subclass of the Ship class defines Destroyer ship type.
 * @author Yingqiu
 */
public class Destroyer extends Ship{
	// static final variable
	/**
	 * Length of a Destroyer ship.
	 */
	static final int lengthOfDestroyer = 2;

	/**
	 * Constructs a Destroyer ship which's length is 2.
	 */
	public Destroyer() {
		super(lengthOfDestroyer);
	}

	/**
	 * Overrides getShipType() method
	 * @return the type of the destroyer.
	 */
	@Override
	public String getShipType() {
		String shiptype = "destroyer";
		return shiptype;
	}
}



