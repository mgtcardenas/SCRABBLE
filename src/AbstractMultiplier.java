/**
 * @author Marco Cárdenas
 *
 *         Abstract class that helps implement the Bridge Design Pattern.
 *         Multiplier type classes will extend this class, managing only
 *         word multipliers
 */
public abstract class AbstractMultiplier
{
	MultiplierImplementor imp; // the object that will implement the operation given the context
	
	/**
	 * Gives the value of the word multiplied by something
	 *
	 * @param  sum the current sum of the word before the word multipliers
	 * @return     the score of the whole word multiplied by something
	 */
	public int multiply(int sum)
	{
		return imp.multiplyImp(sum);
	}// end multiply
}// end AbstractMultiplier - class
