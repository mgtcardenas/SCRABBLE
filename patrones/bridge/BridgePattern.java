package bridge;

/**
 * @author Gerardo Ayala
 *
 *         BRIDGE DESIGN PATTERN (code example)
 *
 *         PROPOSITO
 *         =========
 *
 *         Independencia entre la abstracción y su implementación
 *
 *         MOTIVACION
 *         ==========
 *
 *         El que una abstraccióm pueda tener mós de una implementación.
 *
 *         APLICABILIDAD
 *         =============
 *
 *         No se desea una unión permanente
 *         entre una abstracción y su implementación.
 *         Permitir subclases de la abstracción
 *         y de la implementación.
 *         Los cambios en la implementación deben ser transparentes
 *         a la aplicación cliente.
 *
 *         PARTICIPANTES
 *         ============
 *
 *         Abstraction:
 *         Define la Interface de la abstracción.
 *         Se indican las operaciones de alto nivel
 *         (basadas en operaciones primitivas)
 *
 *         RefinedAbstraction:
 *         Implementa la Interface Abstraction
 *         Mantiene referencia al objeto que implementa las operaciones.
 *
 *         Implementor:
 *         Interface para las clases de la implementación de la abstracción.
 *         Generalmente indica las operaciones primitivas.
 *
 *         ConcreteImplementor:
 *         Implementa la Interface Implementor y define las operaciones primitivas.
 *
 *
 *         COLABORACION
 *         ============
 *
 *         El cliente hace referencia a la Abstraction,
 *         la cual "dirige" las invocaciones del cliente
 *         al objeto de tipo Implementor
 *
 *
 *         CONSECUENCIAS
 *         =============
 *
 *         La implementación no está ligada permanentemente a la abstracción.
 *         Cambios en la implementación no implican
 *         recompilar la abstracción o la aplicación cliente
 */
public class BridgePattern
{
	public static void main(String[] asAlways)
	{
		Abstraction	abstraction;
		String		contexto;
		
		contexto	= "normal";
		abstraction	= new RefinedAbstraction(contexto);
		abstraction.operation();
		
		contexto	= "especial";
		abstraction	= new RefinedAbstraction(contexto);
		abstraction.operation();
		
		contexto	= "otra cosa";
		abstraction	= new RefinedAbstraction(contexto);
		abstraction.operation();
	}// end main
}// end BridgePattern - class
