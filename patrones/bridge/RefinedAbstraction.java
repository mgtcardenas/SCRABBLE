package bridge;

/**
 * mantiene la referencia a los objetos de tipo Implementor.
 */
public class RefinedAbstraction extends Abstraction
{
	String contexto;
	
	public RefinedAbstraction(String unContexto)
	{
		contexto = unContexto;
		if (contexto.equals("normal"))
			imp = new ConcreteImplementorA(); // se crea el objeto de implementaci�n con el constructor e implementaci�n por defecto
		else if (contexto.equals("especial"))
			imp = new ConcreteImplementorB();
		else                                  // bajo otras condiciones ---------------------------
			imp = new ConcreteImplementorC(); // se crea otro objeto de implementaci�n con otro constructor e implementaci�n
	}// end constructor
}// end RefinedAbstraction - class
