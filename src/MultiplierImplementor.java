/**
 * @author Marco Cárdenas
 *
 *         Interface for the classes that implement the Bridge pattern regarding
 *         only word multipliers like "triple-word", "double-word" or "simple"
 */
public interface MultiplierImplementor
{
	/**
	 * Gives the value of the word multiplied by something
	 * 
	 * @param  sum the current sum of the word before the word multipliers
	 * @return     the score of the whole word multiplied by something
	 */
	int multiplyImp(int sum);
}// end MultiplierImplementor - interface
