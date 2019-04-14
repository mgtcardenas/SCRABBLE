import java.util.List;

public class Memento
{
	List<Tile> state;
	
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
