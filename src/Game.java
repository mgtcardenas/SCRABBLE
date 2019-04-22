import java.util.LinkedList;

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
 *         The rest of the classes, except the View, belong to the Model (Player, Bag, Board, Tile, GridSpace, etc.)
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
	private int		finalRoundCounter;
	private int		consecutivePasses;
	
	/**
	 * Some of these arguments are not strictly necessary, but they are there because they make sense.
	 * The important arguments are the player1, the player2 and the view
	 * 
	 * @param  board            a Scrabble board
	 * @param  bag              a Scrabble bag full of Scrabble Tiles
	 * @param  player1          a player
	 * @param  player2          a player
	 * @param  view             a pane with all the visual elements on it
	 * @throws CheaterException
	 */
	public Game(Board board, Bag bag, Player player1, Player player2, View view) throws CheaterException
	{
		this.board				= board;
		this.bag				= bag;
		this.view				= view;
		this.selectedTile		= null;
		this.finalRoundCounter	= 2;
		this.consecutivePasses	= 0;
		
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
	
	/**
	 * Update the current players score and whose turn currently is. Also, hide the current players tiles
	 * so the previous player can't see them and cheat. Used every time a players turn ends
	 */
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
	
	/**
	 * Add the only current player tiles to the view
	 */
	private void displayCurrentPlayerTiles()
	{
		for (int i = 0; i < currentPlayer.getTiles().size(); i++)
		{
			if (!view.getChildren().contains(currentPlayer.getTiles().get(i))) // The view may already contain the Tiles when the Controller may update the view
			{
				currentPlayer.getTiles().get(i).setLayoutX((i + 18) * Tile.TILE_SIZE + i * 30);
				currentPlayer.getTiles().get(i).setLayoutY(3 * Tile.TILE_SIZE);
				view.getChildren().add(currentPlayer.getTiles().get(i));
			}// end if
		}// end for - i
	}// end displayCurrentPlayerTiles
	
	/**
	 * Remove both the current player tiles and played tiles from the view
	 */
	private void hideCurrentPlayerTiles()
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
	private void assignFirstAndSecondPlayer(Player player1, Player player2) throws CheaterException
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
	
	/**
	 * Inform the players who will go first and who will go second
	 */
	public void alertOrderOfPlayers()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Order of Players");
		alert.setHeaderText("Guess What...");
		alert.setContentText(firstPlayer.getName() + " got a letter closer to 'A' so he / she will finish first and " + secondPlayer.getName() + " will finish second");
		alert.showAndWait();
	}// end alertOrderOfPlayers
	
	/**
	 * Finish the game by alerting the players who won or if it was a draw
	 */
	private void finish()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game Results");
		alert.setHeaderText("It looks like...");
		alert.setContentText("In The End");
		
		if (firstPlayer.getScore() > secondPlayer.getScore())
			alert.setContentText(firstPlayer.getName() + " Won!");
		else if (firstPlayer.getScore() < secondPlayer.getScore())
			alert.setContentText(secondPlayer.getName() + " Won!");
		else
			alert.setContentText("It's a Draw");
		
		alert.showAndWait();
	}// end finish
	
	/**
	 * Checks whether the tiles just put in the board form a valid word
	 * 
	 * @param currentPlayer the current player who just placed tiles
	 */
	private boolean placedValidWord(Player currentPlayer)
	{
		GridSpace connectedGridSpace;
		
		if (currentPlayer.getPlayedTiles().size() == 0) // There are no played Tiles
			return false;
		
		if ((connectedGridSpace = onlyOnePlayedTileIsConnected(currentPlayer)) == null) // The played Tiles are not connected to a valid played word
			return false;
		
		if (!allPlayedTilesAreAligned(currentPlayer, connectedGridSpace)) // Not all the played Tiles are vertical or horizontal with one another
			return false;
		
		return Dictionary.wordExists(getWord(currentPlayer)); // The formed word does not exist
	}// end placedValidWord
	
	/**
	 * It returns a string constructed from the chars of all the Tiles aligned with the recently played Tiles.
	 * It must only be used when we are sure all the played tiles are aligned with one another.
	 *
	 * @param  currentPlayer the current player who just placed tiles
	 * @return               the formed word as a String object
	 */
	private String getWord(Player currentPlayer)
	{
		char[]	wordChars;
		int		beginning, end, height;
		Tile	tile;
		
		tile		= currentPlayer.getPlayedTiles().get(0);
		
		// horizontal, the y coordinate will be the same for all tiles
		height		= tile.getGridSpace().getyCoordinate();
		beginning	= tile.getGridSpace().getxCoordinate();
		end			= tile.getGridSpace().getxCoordinate();
		while (beginning >= 1 && Board.instance().getGrid()[height][beginning - 1].getTile() != null)
			beginning--;
		while (end <= 13 && Board.instance().getGrid()[height][end + 1].getTile() != null)
			end++;
		
		if (beginning == end) // It was not horizontal
		{
			// vertical, the x coordinate will be the same for all tiles
			height		= tile.getGridSpace().getxCoordinate();
			beginning	= tile.getGridSpace().getyCoordinate();
			end			= tile.getGridSpace().getyCoordinate();
			while (beginning >= 1 && Board.instance().getGrid()[beginning - 1][height].getTile() != null)
				beginning--;
			while (end <= 13 && Board.instance().getGrid()[end + 1][height].getTile() != null)
				end++;
			
			wordChars = new char[end - beginning + 1]; // end - beginning + 1 -> number of letters in the word
			for (int i = 0, y = beginning; y <= end; i++, y++)
				wordChars[i] = Board.instance().getGrid()[y][height].getTile().getLetter();
		}
		else // It was horizontal
		{
			wordChars = new char[end - beginning + 1]; // end - beginning + 1 -> number of letters in the word
			for (int i = 0, x = beginning; x <= end; i++, x++)
				wordChars[i] = Board.instance().getGrid()[height][x].getTile().getLetter();
		}// end if - else
		
		return new String(wordChars);
	}// end getWord
	
	/**
	 * Gets the tiles of the recently played word.
	 * These are probably more than just the played tiles, thus the importance of this function
	 *
	 * @param  currentPlayer the current player who just placed tiles
	 * @return               an array of ordered Tile objects
	 */
	private Tile[] getWordTiles(Player currentPlayer)
	{
		int		beginning, end, height;
		Tile	tile;
		Tile[]	wordTiles;
		
		tile		= currentPlayer.getPlayedTiles().get(0);
		
		// horizontal, the y coordinate will be the same for all tiles
		height		= tile.getGridSpace().getyCoordinate();
		beginning	= tile.getGridSpace().getxCoordinate();
		end			= tile.getGridSpace().getxCoordinate();
		while (beginning >= 1 && Board.instance().getGrid()[height][beginning - 1].getTile() != null)
			beginning--;
		while (end <= 13 && Board.instance().getGrid()[height][end + 1].getTile() != null)
			end++;
		
		if (beginning == end) // It was not horizontal
		{
			// vertical, the x coordinate will be the same for all tiles
			height		= tile.getGridSpace().getxCoordinate();
			beginning	= tile.getGridSpace().getyCoordinate();
			end			= tile.getGridSpace().getyCoordinate();
			while (beginning >= 1 && Board.instance().getGrid()[beginning - 1][height].getTile() != null)
				beginning--;
			while (end <= 13 && Board.instance().getGrid()[end + 1][height].getTile() != null)
				end++;
			
			wordTiles = new Tile[end - beginning + 1]; // end - beginning + 1 -> number of letters in the word
			for (int i = 0, y = beginning; y <= end; i++, y++)
				wordTiles[i] = Board.instance().getGrid()[y][height].getTile();
		}
		else // It was horizontal
		{
			wordTiles = new Tile[end - beginning + 1]; // end - beginning + 1 -> number of letters in the word
			for (int i = 0, x = beginning; x <= end; i++, x++)
				wordTiles[i] = Board.instance().getGrid()[height][x].getTile();
		}// end if - else
		
		return wordTiles;
	}// end getWordTiles
	
	/**
	 * This function determines whether any of the recently played tiles of a current player
	 * is connected to a word that was already played on the board or was played crossing the center of the board.
	 *
	 * @param  currentPlayer the current player who just placed tiles
	 * @return               true if any played tile is connected / false no played tile is connected
	 */
	private GridSpace onlyOnePlayedTileIsConnected(Player currentPlayer)
	{
		GridSpace	connectionGridSpace;
		int			x, y;
		
		connectionGridSpace = null;
		
		for (Tile t : currentPlayer.getPlayedTiles())
		{
			x	= t.getGridSpace().getxCoordinate();
			y	= t.getGridSpace().getyCoordinate();
			
			if (Board.instance().getGrid()[y][(x == 14) ? x - 1 : x + 1].wasUsed()) // The word is connected right
				if (connectionGridSpace == null)
					connectionGridSpace = Board.instance().getGrid()[y][(x == 14) ? x - 1 : x + 1];
				else // This is the second time, this happens, so the word shan't be valid
					return null;
				
			if (Board.instance().getGrid()[y][Math.abs(x - 1)].wasUsed())           // The word is connected left
				if (connectionGridSpace == null)
					connectionGridSpace = Board.instance().getGrid()[y][Math.abs(x - 1)];
				else
					return null;
				
			if (Board.instance().getGrid()[Math.abs(y - 1)][x].wasUsed())           // The word is connected up
				if (connectionGridSpace == null)
					connectionGridSpace = Board.instance().getGrid()[Math.abs(y - 1)][x];
				else
					return null;
				
			if (Board.instance().getGrid()[(y == 14) ? y - 1 : y + 1][x].wasUsed()) // The word is connected down
				if (connectionGridSpace == null)
					connectionGridSpace = Board.instance().getGrid()[(y == 14) ? y - 1 : y + 1][x];
				else
					return null;
				
			if (x == 7 && y == 7)
				return Board.instance().getGrid()[7][7];
		}// end foreach
		
		return connectionGridSpace;
	}// end onlyOnePlayedTileIsConnected
	
	/**
	 * Determines whether all the played tiles of a current player are aligned vertically or horizontally
	 *
	 * @param  currentPlayer the current player who just placed tiles
	 * @return               true if the tiles are a aligned / false if they are not aligned
	 */
	private boolean allPlayedTilesAreAligned(Player currentPlayer, GridSpace connectedGridSpace)
	{
		int x, y;
		
		y = connectedGridSpace.getyCoordinate();// Horizontal
		for (Tile horizontalTile : currentPlayer.getPlayedTiles())
		{
			if (horizontalTile.getGridSpace().getyCoordinate() != y) // It is not horizontal
			{
				x = connectedGridSpace.getxCoordinate();// Vertical
				for (Tile verticalTile : currentPlayer.getPlayedTiles())
					if (verticalTile.getGridSpace().getxCoordinate() != x) // It is neither vertical
						return false;
					
				break;
			}// end if
		}// end foreach
		
		return true;
	}// end allPlayedTilesAreAligned
	
	/**
	 * Method that uses an Adder and a Multiplier classes that implement the Bridge software design pattern
	 * to calculate the correct score for a word given the context and situation
	 *
	 * @param  wordTiles the array of ordered Tile objects that represent the word of the current player played tiles
	 * @return           the score for playing the most recent word
	 */
	private int calculateWordScore(Tile[] wordTiles)
	{
		int					sum;
		AbstractAdder		adder;
		AbstractMultiplier	multiplier;
		String				highestBonus;
		String				currentBonus;
		
		sum = 0;
		
		for (Tile t : wordTiles)
		{
			adder	= new Adder(t.getGridSpace().wasUsed() ? "simple" : t.getGridSpace().getType());
			sum		+= adder.add(t);
		}// end foreach
		
		highestBonus = "simple";
		
		for (Tile t : wordTiles)
		{
			if (t.getGridSpace().getType().contains("word") && !t.getGridSpace().wasUsed())
			{
				currentBonus	= t.getGridSpace().getType();
				highestBonus	= highestBonus.equals("simple") ? currentBonus : highestBonus;
				highestBonus	= (highestBonus.compareTo(currentBonus) < 0) ? currentBonus : highestBonus;
			}// end if
			
			t.getGridSpace().setUsed(true);
		}// end foreach
		
		multiplier = new Multiplier(highestBonus);
		
		return multiplier.multiply(sum);
	}// end calculateWordScore
	
	/**
	 * Depending on the button pressed...
	 *
	 * Places the word of a player using his / her turn and giving points if the word is
	 * validly placed and exists in the dictionary and resets the consecutive passes counter to 0.
	 * If the bag is empty, the final round counter takes place and the game may end after placing the word.
	 *
	 * Cancels the word, taking the current players played tiles from the board and repositioning them
	 * in the normal tiles, also dissociating them from the GridSpaces on the Board.
	 *
	 * Passes the turn of the current player, incrementing the consecutive passes counter by 1.
	 * If the bag is empty, the final round counter takes place and the game may end after placing the word.
	 *
	 * Exchanges the current players Tiles for another set.
	 * If the bag is empty, the final round counter takes place and the game may end after placing the word
	 * 
	 * @param event the ActionEvent that occurs when a player clicks any of the four main buttons
	 */
	@Override
	public void handle(ActionEvent event)
	{
		if (event.getSource() == view.placeWordButton)
		{
			if (placedValidWord(currentPlayer))
			{
				currentPlayer.setScore(currentPlayer.getScore() + calculateWordScore(getWordTiles(currentPlayer)));
				currentPlayer.setPlayedTiles(new LinkedList<>());
			}
			else
			{
				Caretaker.undo(currentPlayer); // If the player had some tiles on the board, we dissociate them
			}// end if - else
			
			if (bag.isEmpty()) // Final Round
				finalRoundCounter--;
			
			if (finalRoundCounter == 0)
				finish();
			
			consecutivePasses = 0;
			hideCurrentPlayerTiles();
			currentPlayer.refillTiles();
			currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer; // We change players
			Caretaker.keep(currentPlayer);
			updateView();
		}// end if - player wants to place a word
		
		if (event.getSource() == view.cancelWordButton)
		{
			if (selectedTile != null)
				selectedTile.setEffect(null);
			
			selectedTile = null;
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
			
			if (bag.isEmpty()) // Final Round
				finalRoundCounter--;
			
			if (finalRoundCounter == 0)
				finish();
			
			consecutivePasses++;
			
			if (consecutivePasses == 4)
				finish();
		}// end if - player passed
		
		if (event.getSource() == view.exchangeTilesButton)
		{
			try
			{
				if (selectedTile != null)
					selectedTile.setEffect(null);
				
				selectedTile = null;
				hideCurrentPlayerTiles();
				Caretaker.undo(currentPlayer); // If the player had some tiles on the board, we dissociate them
				currentPlayer.retakeTiles();
				currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer; // We change players
				Caretaker.keep(currentPlayer); // We remember the initial state of the players tiles
				updateView();
				
				if (bag.isEmpty()) // Final Round
					finalRoundCounter--;
				
				if (finalRoundCounter == 0)
					finish();
			}
			catch (CheaterException e)
			{
				e.printStackTrace();
			}// end try - catch
		}// end if - player exchanged tiles
	}// end handle - ActionEvent
	
	/**
	 * Turns the current players tiles visible or invisible so the other player can't see them and cheat
	 *
	 * @param observable the on / off status of the toggle button
	 * @param oldValue   the previos on / off value of the toggle button
	 * @param newValue   the new on / off value of the toggle button
	 */
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
	
	/**
	 * Put the selected tile (if present) on top of the clicked GridSpace, associate both of them, remove the selectedTile
	 * from the current players normal tiles and put it in its played tiles. Finally, remove the selected DropShadow effect
	 * from the selected Tile and forget about the selected Tile
	 *
	 * @param event the MouseEvent that occurs when a player clicks on a GridSpace.
	 */
	public void handleGridSpaceClicks(MouseEvent event)
	{
		GridSpace clickedGridSpace = ((GridSpace) event.getSource());
		
		if (selectedTile != null)
		{
			if (clickedGridSpace.getTile() != null)
				System.out.println("You can't place a tile on top of another");
			else
			{
				selectedTile.setGridSpace(clickedGridSpace); // Associate the Tile with the GridSpace and Viceversa
				clickedGridSpace.setTile(selectedTile);
				
				currentPlayer.getPlayedTiles().add(selectedTile); // Remove the Tile from the players tiles and put it in it's played tiles
				currentPlayer.getTiles().remove(selectedTile);
				
				selectedTile.setLayoutX(clickedGridSpace.getLayoutX()); // Move the tile on top of the selected GridSpace
				selectedTile.setLayoutY(clickedGridSpace.getLayoutY());
				
				selectedTile.setEffect(null);
				selectedTile = null;
			}// end if - else
		}
		else
			System.out.println("You have not selected a tile");
		
	}// end handleGridSpaceClicks - MouseEvent
	
	/**
	 * Set the the clicked tile to be the selected tile and apply a DropShadow effect on it.
	 * Remove this effect from the previously selected Tile
	 *
	 * @param event the MouseEvent that occurs when a player clicks on a Tile
	 */
	public void handleTileClicks(MouseEvent event)
	{
		Tile clickedTile = ((Tile) event.getSource());
		
		if (selectedTile != null)
			selectedTile.setEffect(null);
		
		if (clickedTile.getGridSpace() == null)
		{
			selectedTile = clickedTile;
			selectedTile.setEffect(new DropShadow(10, 0f, 0d, Color.DEEPSKYBLUE));
		}// end if
	}// end handleTileClicks
}// end Game - class
