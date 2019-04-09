package memento;

public class Originator
{
	String state;
	
	public void setMemento(Memento m)
	{
		state = m.getState();
	}// end setMemento
	
	public Memento createMemento()
	{
		Memento unMemento;
		
		unMemento = new Memento(state);
		return unMemento;
	}// end createMemento
}// end Originator - class
