/**
 * @author Marco Cárdenas
 *
 *         A class that forms part of the Bridge Design Pattern.
 *         Specifically, it helps calculate the correct score of a word
 *         regarding only word multipliers if they are of "double-word" type
 */
public class DoubleMultiplier implements MultiplierImplementor
{
	/**
	 * Gives the value of the word multiplied by 2
	 *
	 * @param  sum the current sum of the word before the word multipliers
	 * @return     the score of the whole word multiplied by 2
	 */
	@Override
	public int multiplyImp(int sum)
	{
		return sum * 2;
	}// end multiplyImp
}// end DoubleMultiplier - class
