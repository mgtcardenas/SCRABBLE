/**
 * @author Marco Cárdenas
 *
 *         A class that represents each individual space/box on the board.
 *         Each gridspace is of a particular type and has a multiplier
 */
public class GridSpace
{
	private String	type;
	private boolean	used;
	private Tile	tile;
	
	/**
	 * Class constructor
	 * 
	 * @param type either "simple", "double-letter", "triple-letter", "double-word" or "triple-word"
	 */
	public GridSpace(String type)
	{
		this.type	= type;
		this.used	= false; // when we create a GridSpace it couldn't have already been used
		this.tile	= null;
	}// end GridSpace - constructor
	
	// region Getters & Setters
	public String getType()
	{
		return type;
	}// end getType
	
	public boolean wasUsed()
	{
		return used;
	}// end wasUsed
	
	public void setUsed(boolean used)
	{
		this.used = used;
	}// end setUsed
	
	public Tile getTile()
	{
		return tile;
	}// end getTile
	
	public void setTile(Tile tile)
	{
		this.tile = tile;
	}// end setTile
		// endregion Getters & Setters
}// end GridSpace - class
