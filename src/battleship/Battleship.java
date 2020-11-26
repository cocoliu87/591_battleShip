package battleship;

/**
 * subclass of the Ship class.
 * @author Yingqiu
 */


public class Battleship extends Ship{
	
	// static final variable
	static final int lengthOfBattleship = 4;
	
	// get the length of battleship.
	public Battleship() {
		super(lengthOfBattleship);
		};
	
	/**
	 * @Override
	 * @return the type of the battleship.
	 */
	public String getShipType() {
		String shiptype = "battleship";
		return shiptype;
	}
}