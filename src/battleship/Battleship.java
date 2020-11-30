package battleship;

/**
 * Subclass of the Ship class defines Battleship ship type.
 * @author Yingqiu
 */
public class Battleship extends Ship{
	// static final variable
	/**
	 * Length of a Battleship ship.
	 */
	static final int lengthOfBattleship = 4;

	/**
	 * Constructs a Battleship ship which's length is 4.
	 */
	public Battleship() {
		super(lengthOfBattleship);
		};
	
	/**
	 * Overrides getShipType() method.
	 * @return the type of the battleship.
	 */
	@Override
	public String getShipType() {
		String shiptype = "battleship";
		return shiptype;
	}
}