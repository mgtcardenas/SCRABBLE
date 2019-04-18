public class Adder extends AbstractAdder
{
	private String context;
	
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
