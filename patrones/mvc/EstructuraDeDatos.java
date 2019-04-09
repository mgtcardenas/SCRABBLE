package mvc; /**
 * Created by gerardoayala on 1/21/16.
 */

import java.util.LinkedList;
import java.util.Collections;

public class EstructuraDeDatos extends LinkedList<Estudiante> implements Model
{
	private static final String         filePath    = "C:/Users/mgtca/Desktop/";
	private static final String         fileName    = "archivo";
	                     SequentialFile repositorio;

	public EstructuraDeDatos()
	{
		cargaDatosDelRepositorioALaEstructura();
	}//end constructor

	//region M�todos de Cargado de Datos y Almacenamiento de Datos
	public void cargaDatosDelRepositorioALaEstructura()
	{
		int        numeroDeLineas;
		int        numeroDeRegistros;
		int        i;
		Estudiante dato;
		String     nombre;
		int        edad;
		String     carrera;
		int        unidades;

		repositorio = new SequentialFile(filePath, fileName, "txt");
		repositorio.open();
		numeroDeLineas    = repositorio.getNumberOfLines();
		numeroDeRegistros = numeroDeLineas / 4;
		i                 = 0;
		while (i < numeroDeRegistros)
		{
			nombre   = repositorio.readString();
			edad     = repositorio.readInt   ();
			carrera  = repositorio.readString();
			unidades = repositorio.readInt   ();
			dato     = new Estudiante(nombre, edad);
			dato.setCarrera(carrera);
			dato.setUnidades(unidades);
			add(dato);
			i = i + 1;
		}//end while
	}//end cargaDatosDelRepositorioALaEstructura


	public void salvaDatosDeLaEstructuraAlRepositorio()
	{
		int    i;
		String nombre;
		int    edad;
		String carrera;
		int    unidades;

		repositorio = new SequentialFile(filePath, fileName, "txt");
		repositorio.create();
		i = 0;
		while (i < size())
		{
			nombre   = get(i).getNombre();
			edad     = get(i).getEdad();
			carrera  = get(i).getCarrera();
			unidades = get(i).getUnidades();

			repositorio.writeString(nombre  );
			repositorio.writeInt   (edad    );
			repositorio.writeString(carrera );
			repositorio.writeInt   (unidades);

			i = i + 1;
		}//end while
	}//end salvaDatosDeLaEstructuraAlRepositorio
	//endregion M�todos de Cargado de Datos y Almacenamiento de Datos

	//region M�todos de Actualizaci�n de Datos
	public void agregaDatosALaEstructura(int indice, Object unDato)
	{
		Estudiante dato;

		dato = (Estudiante) unDato;
		add(indice, dato);
	}//end agregaDatosALaEstructura

	public void modificaDatosEnLaEstructura(int indice, Object unDato)
	{
		Estudiante dato;

		dato = (Estudiante) unDato;
		remove(indice);
		add(indice, dato);
	}//end modificaDatosEnLaEstructura

	public void eliminaDatosDeLaEstructura(int indice)
	{
		if (indice < size() && indice >= 0)
		    remove(indice);
	}//end eliminaDatosDeLaEstructura

	public void ordenaLaEstructura()
	{
		Collections.sort(this);
	}//end ordenaLaEstructura
	//endregion M�todos de Actualizaci�n de Datos

	//region M�todos de Procesamiento de Datos
	public double procesa(int indice)
	{
		double     colegiatura;
		Estudiante dato;
		double     COSTO_UNIDAD = 2460.00;

		dato        = get(indice);
		colegiatura = dato.getUnidades() * COSTO_UNIDAD;
		dato.setColegiatura(colegiatura);
		return colegiatura;
	}//end procesa

	public boolean hayDatos()
	{
		if   (size() > 0)
		    return true;
		else
		    return false;
	}//end hayDatos
	//endregion M�todos de Procesamiento de Datos

}//end EstructuraDeDatos - class
