/**
 * @author Marco Cárdenas
 *
 *         A class that forms part of the Bridge Design Pattern.
 *         Specifically, it helps calculate the correct score of a word
 *         regarding only word multipliers if they are of "simple" type
 */
public class SimpleMultiplier implements MultiplierImplementor
{
	/**
	 * Gives the value of the word multiplied by 1
	 *
	 * @param  sum the current sum of the word before the word multipliers
	 * @return     the score of the whole word multiplied by 1
	 */
	@Override
	public int multiplyImp(int sum)
	{
		return sum;
	}// end multiplyImp
}// end SimpleMultiplier - class
