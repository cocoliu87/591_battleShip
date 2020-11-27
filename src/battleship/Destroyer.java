package battleship;

/**
 * subclass of the Ship class.
 * @author Yingqiu
 */

public class Destroyer extends Ship{
	
	// static final variable
	static final int lengthOfDestroyer = 2;
	
	// get the length of destroyer。
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



