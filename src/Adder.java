/**
 * @author Marco Cárdenas
 *
 *         Class that implements the Bridge Design Pattern and that
 *         depending on the context has a diferent implementation of the same function
 */
public class Adder extends AbstractAdder
{
	private String context; // The type of GridSpace we are currently on
	
	/**
	 * Creates a Adder object with a different implementation of
	 * the same operation (multiplyImp) depending on the context
	 *
	 * @param aContext the context, specifically, the type of GridSpace we are currently on
	 */
	public Adder(String aContext)
	{
		context = aContext;
		
		switch (context)
		{
			case "double-letter":
				imp = new DoubleAdder();
				break;
			
			case "triple-letter":
				imp = new TripleAdder();
				break;
			
			default:
				imp = new SimpleAdder();
				break;
		}// end switch context
	}// end Adder - constructor
}// end Adder - class
