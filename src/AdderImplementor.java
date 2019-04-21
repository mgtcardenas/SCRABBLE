/**
 * @author Marco Cárdenas
 *
 *         Interface for the classes that implement the Bridge pattern regarding
 *         only letter multipliers like "triple-letter", "double-letter" or "simple"
 */
public interface AdderImplementor
{
	/**
	 * Gives the value of the given tile multiplied by something
	 *
	 * @param  tile the current tile for calculating the score
	 * @return      the score of the individual tile's letter
	 */
	int addImp(Tile tile);
}// end AdderImplementor - interface
