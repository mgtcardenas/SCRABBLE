package memento;

public class MementoPattern
{
	static Originator object;
	
	public static void main(String[] args)
	{
		object			= new Originator();
		object.state	= "disponible";
		Caretaker.keep(object);
		
		object.state = "tambienDisponible";
		Caretaker.keep(object);
		
		object.state = "queSiempreNo";
		
		System.out.println(object.state);
		Caretaker.undo(object);
		System.out.println(object.state);
		
		Caretaker.undo(object);
		System.out.println(object.state);
	}// end main
}// end MementoPattern - class
