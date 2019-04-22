import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Marco Cárdenas
 *
 *         The View class which only job is to build the components that the user will see when interacting with the applicaiton
 */
public class View extends AnchorPane
{
	public static final double	SCENE_WIDTH		= 1500;
	public static final double	SCENE_HEIGHT	= 900;
	public static final double	OFFSET			= 15; // A value that helps move visual elements just a bit so they look right
	
	Board						board;
	Bag							bag;
	
	Label						playersScore;
	Label						playersTurn;
	Label						doubleLetterLegend;
	Label						tripleLetterLegend;
	Label						doubleWordLegend;
	Label						tripleWordLegend;
	
	Rectangle					doubleLetterRectangle;
	Rectangle					tripleLetterRectangle;
	Rectangle					doubleWordRectangle;
	Rectangle					tripleWordRectangle;
	
	Button						passButton;
	Button						exchangeTilesButton;
	Button						cancelWordButton;
	Button						placeWordButton;
	
	ToggleButton				toggleVisibleButton;
	
	/**
	 * It is not necessary to give the Bag or the Board as arguments since they are singletons, but the
	 * View does use them to build itself
	 */
	public View()
	{
		this.board	= Board.instance();
		this.bag	= Bag.instance();
		buildComponents();
	}// end View
	
	/**
	 * Place all of the visual elements where they should go
	 */
	private void buildComponents()
	{
		this.playersTurn			= new Label("First Players Turn");
		this.playersScore			= new Label("Points: ");
		
		this.doubleLetterLegend		= new Label("Double Letter");
		this.tripleLetterLegend		= new Label("Triple Letter");
		this.doubleWordLegend		= new Label("Double Word");
		this.tripleWordLegend		= new Label("Triple Word");
		
		this.doubleLetterRectangle	= new Rectangle(GridSpace.GRIDSPACE_SIZE, GridSpace.GRIDSPACE_SIZE, Color.CYAN);
		this.tripleLetterRectangle	= new Rectangle(GridSpace.GRIDSPACE_SIZE, GridSpace.GRIDSPACE_SIZE, Color.BLUE);
		this.doubleWordRectangle	= new Rectangle(GridSpace.GRIDSPACE_SIZE, GridSpace.GRIDSPACE_SIZE, Color.HOTPINK);
		this.tripleWordRectangle	= new Rectangle(GridSpace.GRIDSPACE_SIZE, GridSpace.GRIDSPACE_SIZE, Color.RED);
		
		this.doubleLetterRectangle.setStroke(Color.BLACK);
		this.tripleLetterRectangle.setStroke(Color.BLACK);
		this.doubleWordRectangle.setStroke(Color.BLACK);
		this.tripleWordRectangle.setStroke(Color.BLACK);
		
		this.passButton				= new Button("Pass");
		this.exchangeTilesButton	= new Button("Exchange Tiles");
		this.cancelWordButton		= new Button("Cancel Word");
		this.placeWordButton		= new Button("Place Word");
		this.toggleVisibleButton	= new ToggleButton("Show Tiles");
		
		getChildren().addAll(this.playersTurn, this.playersScore);
		getChildren().addAll(this.doubleLetterLegend, this.tripleLetterLegend, this.doubleWordLegend, this.tripleWordLegend);
		getChildren().addAll(this.doubleLetterRectangle, this.tripleLetterRectangle, this.doubleWordRectangle, this.tripleWordRectangle);
		getChildren().addAll(this.passButton, this.exchangeTilesButton, this.cancelWordButton, this.placeWordButton, this.toggleVisibleButton);
		
		// Align Turn & Score
		AnchorPane.setBottomAnchor(this.playersTurn, GridSpace.GRIDSPACE_SIZE);
		AnchorPane.setLeftAnchor(this.playersTurn, GridSpace.GRIDSPACE_SIZE);
		
		AnchorPane.setBottomAnchor(this.playersScore, GridSpace.GRIDSPACE_SIZE);
		AnchorPane.setRightAnchor(this.playersScore, GridSpace.GRIDSPACE_SIZE);
		
		// Align Legend Labels
		AnchorPane.setBottomAnchor(this.doubleLetterLegend, GridSpace.GRIDSPACE_SIZE * 3 + OFFSET);
		AnchorPane.setRightAnchor(this.doubleLetterLegend, GridSpace.GRIDSPACE_SIZE * 2);
		
		AnchorPane.setBottomAnchor(this.tripleLetterLegend, GridSpace.GRIDSPACE_SIZE * 5 + OFFSET);
		AnchorPane.setRightAnchor(this.tripleLetterLegend, GridSpace.GRIDSPACE_SIZE * 2);
		
		AnchorPane.setBottomAnchor(this.doubleWordLegend, GridSpace.GRIDSPACE_SIZE * 3 + OFFSET);
		AnchorPane.setRightAnchor(this.doubleWordLegend, GridSpace.GRIDSPACE_SIZE * 8);
		
		AnchorPane.setBottomAnchor(this.tripleWordLegend, GridSpace.GRIDSPACE_SIZE * 5 + OFFSET);
		AnchorPane.setRightAnchor(this.tripleWordLegend, GridSpace.GRIDSPACE_SIZE * 8);
		
		// Align Legend Rectangles
		AnchorPane.setBottomAnchor(this.doubleLetterRectangle, GridSpace.GRIDSPACE_SIZE * 3);
		AnchorPane.setRightAnchor(this.doubleLetterRectangle, GridSpace.GRIDSPACE_SIZE * 4 + OFFSET);
		
		AnchorPane.setBottomAnchor(this.tripleLetterRectangle, GridSpace.GRIDSPACE_SIZE * 5);
		AnchorPane.setRightAnchor(this.tripleLetterRectangle, GridSpace.GRIDSPACE_SIZE * 4 + OFFSET);
		
		AnchorPane.setBottomAnchor(this.doubleWordRectangle, GridSpace.GRIDSPACE_SIZE * 3);
		AnchorPane.setRightAnchor(this.doubleWordRectangle, GridSpace.GRIDSPACE_SIZE * 10 + OFFSET);
		
		AnchorPane.setBottomAnchor(this.tripleWordRectangle, GridSpace.GRIDSPACE_SIZE * 5);
		AnchorPane.setRightAnchor(this.tripleWordRectangle, GridSpace.GRIDSPACE_SIZE * 10 + OFFSET);
		
		// Align Buttons
		AnchorPane.setBottomAnchor(this.passButton, GridSpace.GRIDSPACE_SIZE * 7);
		AnchorPane.setRightAnchor(this.passButton, GridSpace.GRIDSPACE_SIZE * 4 + OFFSET);
		
		AnchorPane.setBottomAnchor(this.exchangeTilesButton, GridSpace.GRIDSPACE_SIZE * 9);
		AnchorPane.setRightAnchor(this.exchangeTilesButton, GridSpace.GRIDSPACE_SIZE * 4 + OFFSET);
		
		AnchorPane.setBottomAnchor(this.cancelWordButton, GridSpace.GRIDSPACE_SIZE * 7);
		AnchorPane.setRightAnchor(this.cancelWordButton, GridSpace.GRIDSPACE_SIZE * 10 + OFFSET);
		
		AnchorPane.setBottomAnchor(this.placeWordButton, GridSpace.GRIDSPACE_SIZE * 9);
		AnchorPane.setRightAnchor(this.placeWordButton, GridSpace.GRIDSPACE_SIZE * 10 + OFFSET);
		
		AnchorPane.setTopAnchor(this.toggleVisibleButton, GridSpace.GRIDSPACE_SIZE * 2);
		AnchorPane.setRightAnchor(this.toggleVisibleButton, GridSpace.GRIDSPACE_SIZE * 6 + OFFSET);
		
		// Draw The Board
		GridSpace gridSpace;
		for (int y = 0; y < board.getGrid().length; y++)
		{
			for (int x = 0; x < board.getGrid()[y].length; x++)
			{
				gridSpace = board.getGrid()[y][x];
				gridSpace.setLayoutX((x + 1) * GridSpace.GRIDSPACE_SIZE);
				gridSpace.setLayoutY((y + 1) * GridSpace.GRIDSPACE_SIZE);
				getChildren().add(gridSpace);
			}// end for - x
		}// end for - y
	}// end buildComponents
	
	/**
	 * Gives functionality to the visual elements by placing action listeners or event handlers
	 *
	 * @param  controller       a Game of Scrabble
	 * @throws CheaterException if somehow a player tries to return a Tile that is not part of the set of tiles of the Bag
	 */
	public void setEventHandlersAndActionListeners(Game controller) throws CheaterException
	{
		listenForAllGridSpaces(controller);
		listenForAllTiles(controller);
		
		this.passButton.setOnAction(controller);
		this.exchangeTilesButton.setOnAction(controller);
		this.cancelWordButton.setOnAction(controller);
		this.placeWordButton.setOnAction(controller);
		this.toggleVisibleButton.selectedProperty().addListener(controller::changed);
	}// end setEventHandlersAndActionListeners
	
	/**
	 * Give functionality only to the GridSpaces of the Board
	 * 
	 * @param controller a Game of Scrabble
	 */
	private void listenForAllGridSpaces(Game controller)
	{
		GridSpace gridSpace; // Event Handlers for GridSpaces
		for (int y = 0; y < board.getGrid().length; y++)
		{
			for (int x = 0; x < board.getGrid()[y].length; x++)
			{
				gridSpace = board.getGrid()[y][x];
				gridSpace.setOnMouseClicked(controller::handleGridSpaceClicks);
			}// end for - x
		}// end for - y
	}// end listenForAllGridSpaces
	
	/**
	 * Give functionality only to the Tiles of the Bag
	 *
	 * @param controller a Game of Scrabble
	 */
	private void listenForAllTiles(Game controller) throws CheaterException
	{
		Tile		tile		= null;
		List<Tile>	tilesMemory	= new LinkedList<>();
		while (!bag.isEmpty()) // To All Tiles in Bag
		{
			tile = bag.takeTile();
			tile.setOnMouseClicked(controller::handleTileClicks);
			tilesMemory.add(tile);
		}// end while
		
		for (Tile t : tilesMemory)
			bag.putTile(t);
		
		/*
		 * At this point of the application, the players have already taken some
		 * tiles and they will not be in the bag, but we must listen for those tiles too
		 */
		for (Tile t : controller.getFirstPlayer().getTiles())
			t.setOnMouseClicked(controller::handleTileClicks);
		
		for (Tile t : controller.getSecondPlayer().getTiles())
			t.setOnMouseClicked(controller::handleTileClicks);
	}// end listenForAllTiles
}// end View - class