/**
 * @author Marco Cárdenas
 *
 *         Abstract class that helps implement the Bridge Design Pattern.
 *         Adder type classes will extend this class, managing only
 *         letter multipliers
 */
public abstract class AbstractAdder
{
	AdderImplementor imp; // the object that will implement the operation given the context
	
	/**
	 * Gives the value of the given tile multiplied by something
	 *
	 * @param  tile the current tile for calculating the score
	 * @return      the score of the individual tile's letter
	 */
	public int add(Tile tile)
	{
		return imp.addImp(tile);
	}// end add
}// end AbstractAdder - class
