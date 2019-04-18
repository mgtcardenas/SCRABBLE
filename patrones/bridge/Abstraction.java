package bridge;

public abstract class Abstraction
{
	Implementor imp;
	
	/**
	 * operación "de alto nivel" que conoce el cliente.
	 */
	public void operation()
	{
		imp.operationImp();
	}// end add
}// end Abstraction - class
