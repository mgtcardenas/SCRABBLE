public class Multiplier extends AbstractMultiplier
{
	private String context;
	
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
