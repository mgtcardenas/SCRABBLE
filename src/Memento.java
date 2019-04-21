import java.util.List;

/**
 * @author Marco Cárdenas
 *
 *         A class that helps implements the Memento Design Pattern.
 *         Here, a Memento rememebers which tiles a player had before putting them down on the Board
 */
public class Memento
{
	private List<Tile> state; // the Tiles of a player
	
	/**
	 * Creates a Memento state given the Tiles of a player
	 * 
	 * @param aState the Tiles of a player
	 */
	public Memento(List<Tile> aState)
	{
		state = aState;
	}// end Memento - constructor
	
	public List<Tile> getState()
	{
		return state;
	}// end getState
	
	public void setState(List<Tile> aState)
	{
		state = aState;
	}// end setState
}// end Memento - class
