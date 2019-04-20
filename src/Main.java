import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
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
		
		primaryStage.setTitle("Scrabble");
		primaryStage.setScene(new Scene(view, View.SCENE_WIDTH, View.SCENE_HEIGHT));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		game.play();
	}// end start
	
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
	
	public static void main(String[] args)
	{
		launch(args);
	}// end main
}// end Main - class
