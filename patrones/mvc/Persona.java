package mvc;

/**
 * Modelo computacional del concepto Persona.
 *
 * La clase es abstracta, pues no habran objetos de clase Persona.
 *
 * @author Gerardo Ayala
 * @version 1.1
 * @since December 2014
 */

public abstract class Persona
{
	protected String nombre; //Nombre de la persona.
	protected int    edad;   //Edad de la persona

	protected String getNombre()
	{
		return nombre;
	}//end getNombre

	protected void setNombre(String unNombre)
	{
		nombre = unNombre;
	}//end setNombre

	protected int getEdad()
	{
		return edad;
	}//end getEdad

	protected void setEdad(int unaEdad)
	{
		if ((unaEdad > 0) && (unaEdad < 100))
		    edad = unaEdad;
		//end if
		// else TODO: Handle Exception
	}//end setEdad
}//end Persona - class