import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author Marco Cárdenas
 *
 *         This class reperesents a Game of Scrabble and is the Controller in this MVC application.
 *         The rest of the classes form the Model (Player, Bag, Board, Tile, GridSpace, etc.)
 */
public class Game implements EventHandler<ActionEvent>
{
	private Player	firstPlayer;
	private Player	secondPlayer;
	private Player	currentPlayer;
	private Board	board;
	private Bag		bag;
	private View	view;
	private Tile	selectedTile;
	
	public Game(Board board, Bag bag, Player player1, Player player2, View view) throws Exception
	{
		this.board			= board;
		this.bag			= bag;
		this.view			= view;
		this.selectedTile	= null;
		
		assignFirstAndSecondPlayer(player1, player2);
		
		firstPlayer.refillTiles(); // Players take 7 tiles each
		secondPlayer.refillTiles();
		
		this.currentPlayer = firstPlayer;
		Caretaker.keep(currentPlayer);
	}// end Game - constructor
	
	// region Getters
	public Player getFirstPlayer()
	{
		return firstPlayer;
	}// end getFirstPlayer
	
	public Player getSecondPlayer()
	{
		return secondPlayer;
	}// end getSecondPlayer
		// endregion Getters
	
	public void updateView()
	{
		view.playersTurn.setText("Turn: " + currentPlayer.getName()); // update the status labels
		view.playersScore.setText("Score: " + firstPlayer.getName() + " - " + firstPlayer.getScore() + "    " + secondPlayer.getName() + " - " + secondPlayer.getScore());
		displayCurrentPlayerTiles();
		for (Tile t : currentPlayer.getTiles()) // Hide all the current player's tiles
			t.setVisible(false);
		view.toggleVisibleButton.setSelected(false); // The toggle button will be off
		view.toggleVisibleButton.setText("Show Tiles");
	}// end updateView
	
	public void displayCurrentPlayerTiles()
	{
		for (int i = 0; i < currentPlayer.getTiles().size(); i++)
		{
			if (!view.getChildren().contains(currentPlayer.getTiles().get(i)))
			{
				currentPlayer.getTiles().get(i).setLayoutX((i + 18) * Tile.TILE_SIZE + i * 30);
				currentPlayer.getTiles().get(i).setLayoutY(3 * Tile.TILE_SIZE);
				view.getChildren().add(currentPlayer.getTiles().get(i));
			}// end if
		}// end for - i
	}// end displayCurrentPlayerTiles
	
	public void hideCurrentPlayerTiles()
	{
		for (Tile t : currentPlayer.getTiles())
			view.getChildren().remove(t);
		
		for (Tile t : currentPlayer.getPlayedTiles())
			view.getChildren().remove(t);
	}// end hideCurrentPlayerTiles
	
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
				this.firstPlayer	= player1;
				this.secondPlayer	= player2;
			}
			else if (player1.getTiles().get(0).getLetter() > player2.getTiles().get(0).getLetter())
			{
				this.firstPlayer	= player2;
				this.secondPlayer	= player1;
			}// end if - else
			
		}while (player1.getTiles().get(0).getLetter() == player2.getTiles().get(0).getLetter()); // end do-while
		
		this.firstPlayer.returnTiles();
		this.secondPlayer.returnTiles();
	}// end assignFirstAndSecondPlayer
	
	public void play() throws Exception
	{
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
	
	// TODO: Function that makes click on tile be the selected tile, so also add a selected Tile to the Game
	public void handleTileClicks(MouseEvent event)
	{
		if (selectedTile != null)
			selectedTile.setEffect(null);
		
		selectedTile = ((Tile) event.getSource());
		selectedTile.setEffect(new DropShadow(10, 0f, 0d, Color.DEEPSKYBLUE));
		System.out.println(selectedTile);
	}// end handleTileClicks
	
	@Override
	public void handle(ActionEvent event)
	{
		if (event.getSource() == view.placeWordButton)
		{
			// TODO: Fill this thing, you are almost there
			/*
			 * Al final de aqui habra que volver a poner un Caretaker.keep(currentplayer)
			 * esto, después de haber cambiado al current player
			 */
		}// end if - player wants to place a word
		if (event.getSource() == view.cancelWordButton)
		{
			hideCurrentPlayerTiles();
			Caretaker.undo(currentPlayer);
			Caretaker.keep(currentPlayer);
			updateView();
		}// end if - player canceled word
		
		if (event.getSource() == view.passButton)
		{
			if (selectedTile != null)
				selectedTile.setEffect(null);
			
			selectedTile = null;
			hideCurrentPlayerTiles();
			Caretaker.undo(currentPlayer); // If the player had some tiles on the board, we dissociate them
			currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer; // We change players
			Caretaker.keep(currentPlayer); // We remember the initial state of the players tiles
			updateView();
		}// end if - player passed
		
		if (event.getSource() == view.exchangeTilesButton)
		{
			try
			{
				if (selectedTile != null)
					selectedTile.setEffect(null);
				
				selectedTile = null;
				hideCurrentPlayerTiles();
				currentPlayer.retakeTiles();
				Caretaker.undo(currentPlayer); // If the player had some tiles on the board, we dissociate them
				currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer; // We change players
				Caretaker.keep(currentPlayer); // We remember the initial state of the players tiles
				updateView();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}// end try - catch
		}// end if - player exchanged tiles
	}// end handle - ActionEvent
	
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
	{
		if (newValue) // since new value is a boolean, then if the new value is true / active button, then we set them visible
		{
			for (Tile t : currentPlayer.getTiles())
				t.setVisible(true);
			view.toggleVisibleButton.setText("Hide Tiles");
		}
		else
		{
			for (Tile t : currentPlayer.getTiles())
				t.setVisible(false);
			view.toggleVisibleButton.setText("Show Tiles");
		}// end if - else
	}// end changed
	
	public void handleGridSpaceClicks(MouseEvent event)
	{
		GridSpace clickedGridSpace = ((GridSpace) event.getSource());
		
		if (selectedTile != null)
		{
			if (clickedGridSpace.getTile() != null)
			{
				System.out.println("You can't place a tile on top of another");
			}
			else
			{
				selectedTile.setGridSpace(clickedGridSpace); // Associate the Tile with the GridSpace and Viceversa
				clickedGridSpace.setTile(selectedTile);
				
				currentPlayer.getPlayedTiles().add(selectedTile); // Remove the Tile from the players tiles and put it in it's played tiles
				currentPlayer.getTiles().remove(selectedTile);
				
				selectedTile.setLayoutX(clickedGridSpace.getLayoutX()); // Move the tile on top of the selected GridSpace
				selectedTile.setLayoutY(clickedGridSpace.getLayoutY());
				
				System.out.println("I will get " + selectedTile.getLetter() + " - " + selectedTile.getValue());
				
				selectedTile.setEffect(null);
				selectedTile = null;
			}// end if - else
		}
		else
			System.out.println("You have not selected a tile");
		
		System.out.println(clickedGridSpace.getyCoordinate() + ", " + clickedGridSpace.getxCoordinate());
	}// end handleGridSpaceClicks - MouseEvent
}// end Game - class
