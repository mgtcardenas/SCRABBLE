/**
 * @author Marco Cárdenas
 *
 *         Class that represents each of the tiles a player must use to form words.
 *         Each tile has a letter and a value
 */
public class Tile
{
	private char	letter;
	private int		value;
	
	public Tile(char letter, int value)
	{
		this.letter	= letter;
		this.value	= value;
	}// end Tile - constructor
}// end Tile - class
