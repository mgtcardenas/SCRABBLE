/**
 * @author Marco Cárdenas
 *
 *         A class that forms part of the Bridge Design Pattern.
 *         Specifically, it helps calculate the correct score of a word
 *         regarding only word multipliers if they are of "triple-word" type
 */
public class TripleMultiplier implements MultiplierImplementor
{
	/**
	 * Gives the value of the word multiplied by 3
	 *
	 * @param  sum the current sum of the word before the word multipliers
	 * @return     the score of the whole word multiplied by 3
	 */
	@Override
	public int multiplyImp(int sum)
	{
		return sum * 3;
	}// end multiplyImp
}// end TripleMultiplier - class
