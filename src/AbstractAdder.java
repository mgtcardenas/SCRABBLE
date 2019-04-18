public abstract class AbstractAdder
{
	AdderImplementor imp;
	
	public int add(Tile tile)
	{
		return imp.addImp(tile);
	}// end add
}// end AbstractAdder - class
