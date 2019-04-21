import java.util.Stack;

/**
 * @author Marco Cárdenas
 *
 *         The Caretaker is an object that holds all the Memento objects on a stack
 */
public class Caretaker
{
	static Stack<Memento> stack = new Stack<>(); // The Stack of Mementos
	
	/**
	 * Remembers the state of a player's Tiles
	 * 
	 * @param originator a player
	 */
	public static void keep(Player originator)
	{
		stack.push(originator.createMemento());
	}// end keep
	
	/**
	 * Returns a player tiles to the previous state from the stack
	 * 
	 * @param originator a player
	 */
	public static void undo(Player originator)
	{
		originator.setMemento(stack.pop());
	}// end undo
}// end Caretaker - class
