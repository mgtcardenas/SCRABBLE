import java.util.Stack;

/**
 * @author Marco Cárdenas
 *
 *         The Caretaker is an object that holds all the Memento objects on a stack.
 */
public class Caretaker
{
	static Stack<Memento> stack = new Stack<>();
	
	public static void keep(Player originator)
	{
		stack.push(originator.createMemento());
	}// end keep
	
	public static void undo(Player originator)
	{
		originator.setMemento(stack.pop());
	}// end undo
}// end Caretaker - class
