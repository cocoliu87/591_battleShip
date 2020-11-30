package battleship;

/**
 * Subclass of the Ship class defines Cruiser ship type.
 * @author Yingqiu
 */
public class Cruiser extends Ship{
	
	// static final variable
	/**
	 * Length of a Cruiser ship.
	 */
	static final int lengthOfCruiser = 3;

	/**
	 * Constructs a Cruiser ship which's length is 3.
	 */
	public Cruiser() {
		super(lengthOfCruiser);
	}

	/**
	 * Overrides getShopType() method
	 * @return the type of the cruiser.
	 */
	@Override
	public String getShipType() {
		String shiptype = "cruiser";
		return shiptype;
	}
}
