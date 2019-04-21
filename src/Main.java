import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * @author Marco Cárdenas
 *
 *         The class that represents the whole application and where all the control begins
 */
public class Main extends Application
{
	/**
	 * Obligatory method because of JavaFX
	 * 
	 * @param  primaryStage     the main 'window'
	 * @throws CheaterException if somehow the player wants to return a tile that is not missing from the bag
	 */
	@Override
	public void start(Stage primaryStage) throws CheaterException
	{
		Bag			bag;    // Model
		Board		board;  // Model
		Player		player1;// Model
		Player		player2;// Model
		View		view;   // View
		Game		game;   // Controller
		
		String[]	playerNames;
		
		bag			= Bag.instance();
		board		= Board.instance();
		playerNames	= getNames();
		player1		= new Player(playerNames[0]);
		player2		= new Player(playerNames[1]);
		view		= new View();
		game		= new Game(board, bag, player1, player2, view);
		
		game.alertOrderOfPlayers();
		view.setEventHandlersAndActionListeners(game);
		game.updateView();
		
		primaryStage.setTitle("Scrabble");
		primaryStage.setScene(new Scene(view, View.SCENE_WIDTH, View.SCENE_HEIGHT));
		primaryStage.setResizable(false);
		primaryStage.show();
	}// end start
	
	/**
	 * Performs a dialog to get the names of the two players of the game
	 * 
	 * @return an array of String objects of size 2 that contains the names of the players
	 */
	public static String[] getNames()
	{
		Optional<String>	playerOneName;
		Optional<String>	playerTwoName;
		
		TextInputDialog		dialog	= new TextInputDialog("Marco");
		
		dialog.setTitle("Welcome to Scrabble!");
		dialog.setHeaderText("Getting Everything Ready...");
		dialog.setContentText("Please Enter Player 1 Name ");
		
		do
		{
			playerOneName = dialog.showAndWait();
		}while (!playerOneName.isPresent() || playerOneName.get().equals("")); // end do-while
		
		dialog.setContentText("Please Enter Player 2 Name ");
		
		do
		{
			playerTwoName = dialog.showAndWait();
		}while (!playerTwoName.isPresent() || playerTwoName.get().equals("")); // end do-while
		
		return new String[] { playerOneName.get(), playerTwoName.get() };
	}// end getNames
	
	/**
	 * The method where all control begins
	 * 
	 * @param args the program arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}// end main
}// end Main - class
