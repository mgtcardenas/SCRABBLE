import java.util.Stack;

public class Caretaker
{
	static Stack<Memento> stack = new Stack<>();
	
	public static void keep(Player originator)
	{
		stack.push(originator.createMemento());
	}// end start
	
	public static void undo(Player originator)
	{
		originator.setMemento(stack.pop());
	}// end undo
}// end Caretaker - class
