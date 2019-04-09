package mvc;

/**
 * Modelo computacional del concepto Estudiante.
 *
 * La clase hereda de la clase Persona e implementa la Interface Comparable.
 *
 * @author Gerardo Ayala
 * @version 1.1
 * @since December 2014
 */
public class Estudiante extends Persona implements Comparable<Estudiante>
{
	private int    unidades;    // Numero de unidades que cursa el estudiante.
	private String carrera;     // Carrera que cursa el estudiante.
	private double colegiatura; // Colegiatura a cubrir por el estudiante.

	/**
	 * M�todo constructor.
	 *
	 * @param unNombre Nombre a asignar al estudiante.
	 * @param unaEdad  Edad a asignar al estudiante.
	 */
	public Estudiante(String unNombre, int unaEdad)
	{
		// Se invoca, antes que nada, al constructor de la superclase
		super();
		setNombre(unNombre);
		setEdad(unaEdad);
		unidades    = -1;
		colegiatura = -1;
		carrera     = "";
	}//end Estudiante - constructor

	public int getUnidades()
	{
		return unidades;
	}//end getUnidades

	public void setUnidades(int unidades)
	{
		this.unidades = unidades;
	}//end setUnidades

	public String getCarrera()
	{
		return carrera;
	}//end getCarrera

	public void setCarrera(String carrera)
	{
		this.carrera = carrera;
	}//end setCarrera

	public double getColegiatura()
	{
		return colegiatura;
	}//end getColegiatura

	public void setColegiatura(double colegiatura)
	{
		this.colegiatura = colegiatura;
	}//end setColegiatura

	/**
	 * Implementaci�n de la Interface Comparable.
	 *
	 * @param otroEstudiante Otro objeto de clase Estudiante, con el cual se va a comparar.
	 *
	 * @return 1 si el nombre del objeto es mayor al del otro objeto;
	 * 0 si son iguales; -1 si el nombre del objeto es menor al del otro objeto.
	 */
	public int compareTo(Estudiante otroEstudiante)
	{
		int otraEdad;
		otraEdad = otroEstudiante.getEdad(); //otroEstudiante = (Estudiante) otroObjeto;

		if      (edad > otraEdad)
		    return 1;
		else if (edad < otraEdad)
		    return -1;
		else
		    return 0;
	}//end compareTo

}//end Estudiante - class