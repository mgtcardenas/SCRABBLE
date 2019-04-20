import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Application
{
	private static final double	SCENE_WIDTH		= 1500;
	private static final double	SCENE_HEIGHT	= 900;
	private static final double	OFFSET			= 15;
	
	AnchorPane					root;
	Scene						scene;
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
	
	public static void main(String[] args)
	{
		launch(args);
	}// end main
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.board	= Board.instance();
		this.bag	= Bag.instance();
		this.root	= buildComponents();
		this.scene	= new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		setEventHandlers();
		
		primaryStage.setTitle("Scrabble");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}// end start
	
	public AnchorPane buildComponents() throws Exception
	{
		this.root					= new AnchorPane();
		
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
		
		this.passButton				= new Button("Pass");
		this.exchangeTilesButton	= new Button("Exchange Tiles");
		this.cancelWordButton		= new Button("Cancel Word");
		this.placeWordButton		= new Button("Place Word");
		
		root.getChildren().addAll(this.playersTurn, this.playersScore);
		root.getChildren().addAll(this.doubleLetterLegend, this.tripleLetterLegend, this.doubleWordLegend, this.tripleWordLegend);
		root.getChildren().addAll(this.doubleLetterRectangle, this.tripleLetterRectangle, this.doubleWordRectangle, this.tripleWordRectangle);
		root.getChildren().addAll(this.passButton, this.exchangeTilesButton, this.cancelWordButton, this.placeWordButton);
		
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
		
		// Draw The Board
		GridSpace gridSpace;
		for (int y = 0; y < board.getGrid().length; y++)
		{
			for (int x = 0; x < board.getGrid()[y].length; x++)
			{
				gridSpace = board.getGrid()[y][x];
				gridSpace.setLayoutX((x + 1) * GridSpace.GRIDSPACE_SIZE);
				gridSpace.setLayoutY((y + 1) * GridSpace.GRIDSPACE_SIZE);
				gridSpace.setOnMouseClicked(e -> System.out.println("Abuelita de Batman"));
				root.getChildren().addAll(gridSpace);
			}// end for - x
		}// end for - y
		
		List<Tile>	tiles	= new LinkedList<>();
		Tile		tile;
		for (int i = 0; i < 100; i++)
		{
			tile = Bag.instance().takeTile();
			tile.setEffect(new DropShadow(10, 0f, 0d, Color.DEEPSKYBLUE));
			tile.setOnMouseClicked(e -> System.out.println("I'm a Tile"));
			tiles.add(tile);
		}// end for - i
		
		for (Tile t : tiles)
			Bag.instance().putTile(t);
		
		Player newPlayer = new Player("Marco");
		newPlayer.refillTiles();
		for (int i = 0; i < newPlayer.getTiles().size(); i++)
		{
			newPlayer.getTiles().get(i).setLayoutX((i + 18) * Tile.TILE_SIZE + i * 30);
			newPlayer.getTiles().get(i).setLayoutY(3 * Tile.TILE_SIZE);
			root.getChildren().add(newPlayer.getTiles().get(i));
		}// end for - i
		
		return root;
	}// end buildComponents
	
	public void setEventHandlers()
	{
		GridSpace gridSpace;
		for (int y = 0; y < board.getGrid().length; y++)
		{
			for (int x = 0; x < board.getGrid()[y].length; x++)
			{
				gridSpace = board.getGrid()[y][x];
				gridSpace.setOnMouseClicked(e -> System.out.println(((GridSpace) e.getSource()).getyCoordinate() + ", " + ((GridSpace) e.getSource()).getxCoordinate()));
			}// end for - x
		}// end for - y
		
		this.passButton.setOnAction(e -> System.out.println("I pass"));
		this.exchangeTilesButton.setOnAction(e -> System.out.println("I exchange tiles"));
		this.cancelWordButton.setOnAction(e -> System.out.println("I cancel"));
		this.placeWordButton.setOnAction(e -> System.out.println("I place word"));
	}// end setEventHandlers
}// end View - class