/**
 * @author Marco Cárdenas
 *
 *         A class that forms part of the Bridge Design Pattern.
 *         Specifically, it helps calculate the correct score of a word
 *         regarding only letter multipliers if they are of "triple-letter" type
 */
public class TripleAdder implements AdderImplementor
{
	/**
	 * Gives the value of the given tile multiplied by 3
	 *
	 * @param  tile the current tile for calculating the score
	 * @return      the score of the individual tile's letter
	 */
	@Override
	public int addImp(Tile tile)
	{
		return tile.getValue() * 3;
	}// end addImp
}// end TripleAdder - class
