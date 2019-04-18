public class DoubleAdder implements AdderImplementor
{
	@Override
	public int addImp(Tile tile)
	{
		return tile.getValue() * 2;
	}// end addImp
}// end DoubleAdder - class
