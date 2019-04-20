import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * @author Marco Cárdenas
 *
 *         This class reperesents a Game of Scrabble and is the Controller in this MVC application.
 *         The rest of the classes form the Model (Player, Bag, Board, Tile, GridSpace, etc.)
 */
public class Game implements EventHandler<ActionEvent>
{
	private Player	firstPlayer, secondPlayer;
	private Board	board;
	private Bag		bag;
	private View	view;
	
	public Game(Board board, Bag bag, Player player1, Player player2, View view) throws Exception
	{
		this.board	= board;
		this.bag	= bag;
		this.view	= view;
		assignFirstAndSecondPlayer(player1, player2);
	}// end Game - constructor
	
	/**
	 * Simulates the behavior when two players take a single tile from the Bag to
	 * decide who goes first in the game. Thus we will know who is the first player
	 * and who is the second player
	 */
	public void assignFirstAndSecondPlayer(Player player1, Player player2) throws Exception
	{
		do
		{
			player1.returnTiles(); // If the players had any tiles, they must return them
			player2.returnTiles();
			
			player1.takeTile(); // Both players take out one tile
			player2.takeTile();
			
			if (player1.getTiles().get(0).getLetter() < player2.getTiles().get(0).getLetter())
			{
				firstPlayer		= player1;
				secondPlayer	= player2;
			}
			else if (player1.getTiles().get(0).getLetter() > player2.getTiles().get(0).getLetter())
			{
				firstPlayer		= player2;
				secondPlayer	= player1;
			}// end if - else
			
		}while (player1.getTiles().get(0).getLetter() == player2.getTiles().get(0).getLetter()); // end do-while
		
		firstPlayer.returnTiles();
		secondPlayer.returnTiles();
	}// end assignFirstAndSecondPlayer
	
	public void play() throws Exception
	{
		firstPlayer.refillTiles(); // Players take 7 tiles each
		secondPlayer.refillTiles();
		
		do
		{
			view.playersTurn.setText("First Player " + firstPlayer.getName() + "'s Turn");
			firstPlayer.makeMove();
			firstPlayer.refillTiles();
			view.playersScore.setText("Score: " + firstPlayer.getName() + " - " + firstPlayer.getScore() + secondPlayer.getName() + " - " + secondPlayer.getScore());
			
			view.playersTurn.setText("Second Player " + secondPlayer.getName() + "'s Turn");
			secondPlayer.makeMove();
			secondPlayer.refillTiles();
			view.playersScore.setText("Score: " + firstPlayer.getName() + " - " + firstPlayer.getScore() + secondPlayer.getName() + " - " + secondPlayer.getScore());
		}while (!bag.isEmpty()); // end do-while
		
		// Final Round
		firstPlayer.makeMove();
		secondPlayer.makeMove();
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game Results");
		alert.setHeaderText("It looks like...");
		alert.setContentText("I have a great message for you!");
		
		if (firstPlayer.getScore() > secondPlayer.getScore())
			alert.setContentText(firstPlayer.getName() + "Won!");
		else if (firstPlayer.getScore() < secondPlayer.getScore())
			alert.setContentText(secondPlayer.getName() + "Won!");
		else
			alert.setContentText("It's a Draw");
		
		alert.showAndWait();
	}// end play
	
	public void alertOrderOfPlayers()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Order of Players");
		alert.setHeaderText("Guess What...");
		alert.setContentText(firstPlayer.getName() + " got a letter closer to 'A' so he / she will play first and " + secondPlayer.getName() + " will play second");
		alert.showAndWait();
	}// end alertOrderOfPlayers
	
	@Override
	public void handle(ActionEvent event)
	{
		if (event.getSource() == view.placeWordButton)
			System.out.println("I will place the word");
		if (event.getSource() == view.cancelWordButton)
			System.out.println("I will cancel");
		if (event.getSource() == view.passButton)
			System.out.println("I will pass");
		if (event.getSource() == view.exchangeTilesButton)
			System.out.println("I will exchange the tiles");
	}// end handle - ActionEvent
	
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
	{
		if (newValue) // since new value is a boolean, then if the new value is true / active button, then we set them visible
			for (Tile t : View.currentPlayerTiles)
				t.setVisible(true);
		else
			for (Tile t : View.currentPlayerTiles)
				t.setVisible(false);
	}// end changed
	
	public void handleGridSpaceClicks(MouseEvent event)
	{
		System.out.println(((GridSpace) event.getSource()).getyCoordinate() + ", " + ((GridSpace) event.getSource()).getxCoordinate());
	}// end handleGridSpaceClicks - MouseEvent
}// end Game - class
