package singleton;

/**
 * @author Gerardo Ayala
 *
 *         SINGLETON DESIGN PATTERN (code example)
 *
 *         PROPOSITO
 *         =========
 *
 *         Asegurar que unicamente podamos tener una instancia
 *         de una clase dada,
 *         y proveer un punto conocido para su acceso.
 *
 *         MOTIVACION
 *         ==========
 *
 *         En ocasiones es necesario contar con unicamente
 *         una instancia de una clase.
 *
 *         APLICABILIDAD
 *         =============
 *
 *         Se usa cuando es necesario asegurar que
 *         unicamente habra una instancia de una clase,
 *         y que sea accesible para cualquiera desde un punto conocido.
 *
 *         PARTICIPANTES
 *         ============
 *
 *         singleton.Singleton: clase que define un metodo para obtener la instancia.
 *         Es responsable de la creacion de la instancia.
 *
 *         COLABORACION
 *         ============
 *
 *         Los clientes tienen acceso a crear y accesar la instancia
 *         unicamente a traves de singleton.Singleton.
 *
 *         CONSECUENCIAS
 *         =============
 *
 *         Control extricto de acceso a la instancia unica,
 *         en la clase singleton.Singleton.
 *
 *
 *         IMPLEMENTACION
 *         ==============
 *
 *         Implementar la instancia como un atributo de clase (static),
 *         asi como tambien el metodo que construye la instancia
 *         debe ser metodo de la clase (static).
 *
 *         USOS
 *         ====
 *
 *         Contar con solamente un componente de software
 *         cuyo acceso y solicitudes puedan estar controladas.
 */
public class SingletonPattern
{
	public static void main(String[] args)
	{
		Singleton	primeraInstancia;
		Singleton	segundaInstancia;
		Singleton	terceraInstancia;
		
		primeraInstancia = Singleton.instance();
		primeraInstancia.setSingletonData("la vaca");
		primeraInstancia.singletonOperation();
		
		segundaInstancia = Singleton.instance();
		segundaInstancia.singletonOperation();
		
		terceraInstancia = Singleton.instance();
		terceraInstancia.setSingletonData("la misma vaca");
		terceraInstancia.singletonOperation();
		
		primeraInstancia.singletonOperation();
		segundaInstancia.singletonOperation();
	}// end main
}// end SingletonPattern - class
