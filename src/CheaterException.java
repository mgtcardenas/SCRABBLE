/**
 * @author Marco Cárdenas
 *
 *         Class that manages exceptions when a Player somehow wants to return a Tile to the Bag
 *         that doesn't belong to it
 */
public class CheaterException extends Exception
{
	/**
	 * Creates a new CheaterException with a given message
	 * 
	 * @param message a message stating what was the mistake or problem
	 */
	public CheaterException(String message)
	{
		super(message);
	}// end CheaterException - constructor
}// end CheaterException - class
