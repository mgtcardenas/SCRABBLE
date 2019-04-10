/**
 * @author Marco Cárdenas
 *
 *         Class that represents each of the tiles a player must use to form words.
 *         Each tile has a letter and a value
 */
public class Tile
{
	private char		letter;
	private int			value;
	private GridSpace	gridSpace;
	
	public Tile(char letter, int value)
	{
		this.letter	= letter;
		this.value	= value;
		gridSpace	= null;
	}// end Tile - constructor
	
	// region Getters & Setters
	public char getLetter()
	{
		return letter;
	}// end getLetter
	
	public int getValue()
	{
		return value;
	}// end getValue
	
	public GridSpace getGridSpace()
	{
		return gridSpace;
	}// end getGridSpace
	
	public void setGridSpace(GridSpace gridSpace)
	{
		this.gridSpace = gridSpace;
	}// end setGridSpace
		// endregion Getters & Setters
}// end Tile - class
