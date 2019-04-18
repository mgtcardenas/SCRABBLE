public class TripleAdder implements AdderImplementor
{
	@Override
	public int addImp(Tile tile)
	{
		return tile.getValue() * 3;
	}// end addImp
}// end TripleAdder - class
