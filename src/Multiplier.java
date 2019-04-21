/**
 * @author Marco Cárdenas
 *
 *         Class that implements the Bridge Design Pattern and that
 *         depending on the context has a diferent implementation of the same function
 */
public class Multiplier extends AbstractMultiplier
{
	private String context; // The type of GridSpace we are currently on
	
	/**
	 * Creates a Multiplier object with a different implementation of
	 * the same operation (multiplyImp) depending on the context
	 * 
	 * @param aContext the context, specifically, the type of GridSpace we are currently on
	 */
	public Multiplier(String aContext)
	{
		context = aContext;
		
		switch (context)
		{
			case "double-word":
				imp = new DoubleMultiplier();
				break;
			
			case "triple-word":
				imp = new TripleMultiplier();
				break;
			
			default:
				imp = new SimpleMultiplier();
				break;
		}// end switch context
	}// end Multiplier - constructor
}// end Multiplier - class
