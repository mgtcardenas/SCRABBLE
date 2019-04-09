package memento;

import java.util.Stack;

public class Caretaker
{
	static Stack<Memento> stack = new Stack<Memento>();
	
	public static void keep(Originator anOriginator)
	{
		Memento aMemento;
		
		aMemento = anOriginator.createMemento();
		stack.push(aMemento);
	}// end start
	
	public static void undo(Originator anOriginator)
	{
		Memento aMemento;
		
		aMemento = stack.pop();
		anOriginator.setMemento(aMemento);
	}// end undo
}// end Caretaker - class