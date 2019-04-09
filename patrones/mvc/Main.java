package mvc;

/**
 * Created by gerardoayala on 1/21/16.
 */
public class Main
{
	public static void main(String[] args)
	{
		Interfaz			view;
		EstructuraDeDatos	model;
		Mediador			controller;
		
		model		= new EstructuraDeDatos();   // Se crea el model.
		view		= new Interfaz();            // Se crea el view.
		controller	= new Mediador(model, view); // Se crea el controller,
		
		view.setActionListener(controller); // y se asocia al view.
		controller.actualizaElView();       // se inicia el contenido del view
		view.inicia();                      // Se inicia la ejecuci�n de la aplicaci�n.
	}// end main
}// end Main - class
