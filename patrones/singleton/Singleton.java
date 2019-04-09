package singleton;

public class Singleton
{
	private static Singleton	uniqueInstance;
	private String				singletonData;
	
	/**
	 * El constructor debe asegurar que se lance una excepción
	 * en caso de que se intente crear otra instancia adicional
	 */
	private Singleton() throws Exception
	{
		if (uniqueInstance != null)
			throw new Exception("No podemos tener otra instancia, baboso!!!");
	}// end Singleton - constructor
	
	/**
	 * método para obtener la instancia
	 * 
	 * @return
	 */
	public static synchronized Singleton instance()
	{
		if (uniqueInstance == null)
		{
			try
			{
				uniqueInstance = new Singleton();// debe tratarse de crear
			}
			catch (Exception e)
			{
				System.out.println(e);
			}// end try - catch
		}// end if
		
		return uniqueInstance;
	}// end Instance
	
	public void singletonOperation()
	{
		System.out.println(singletonData);
	}// end singletonOperation
	
	public String getSingletonData()
	{
		return singletonData;
	}// end getSingletonData
	
	public void setSingletonData(String unDato)
	{
		singletonData = unDato;
	}// end setSingletonData
}// end Singleton - class
