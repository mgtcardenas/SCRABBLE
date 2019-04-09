package memento;

public class Memento
{
	String state;
	
	public Memento(String aState)
	{
		state = aState;
	}// end Memento - constructor
	
	public String getState()
	{
		return state;
	}// end getState
	
	public void setState(String aState)
	{
		state = aState;
	}// end setState
}// end Memento - class
