import java.util.List;

/**
 * @author Marco Cárdenas
 *
 *         A Memento rememebers which tiles a player had before putting them down on the Board
 */
public class Memento
{
	private List<Tile> state;
	
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
