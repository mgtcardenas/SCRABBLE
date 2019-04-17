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
	private int		xCoordinate;
	private int		yCoordinate;
	
	/**
	 * Class constructor
	 * 
	 * @param type either "simple", "double-letter", "triple-letter", "double-word" or "triple-word"
	 * @param y    the row this tile is located at, counting from 0
	 * @param x    the column this tile is located at, counting from 0
	 */
	public GridSpace(String type, int y, int x)
	{
		this.type			= type;
		this.used			= false; // when we create a GridSpace it couldn't have already been used
		this.tile			= null;
		this.xCoordinate	= x;
		this.yCoordinate	= y;
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
	
	public int getxCoordinate()
	{
		return xCoordinate;
	}// end getxCoordinate
	
	public int getyCoordinate()
	{
		return yCoordinate;
	}// end getyCoordinate
		// endregion Getters & Setters
}// end GridSpace - class
