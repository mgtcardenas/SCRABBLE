import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Marco Cárdenas
 *
 *         Class that represents each of the tiles a player must use to form words.
 *         Each tile has a letter, a value and is represented as a StackPane that has
 *         a Rectangle and a Label on top of it respectively. Each Tile can have one
 *         GridSpace associated to it
 */
public class Tile extends StackPane
{
	public static final double	TILE_SIZE	= 50; // for the view
	private char				letter;
	private int					value;
	private GridSpace			gridSpace;
	private Rectangle			rectangle;
	private Label				label;
	
	/**
	 * To create a Tile, only a letter and a value are necessary
	 * 
	 * @param letter the letter this tile represents
	 * @param value  how many points this tile will give when played by itself
	 */
	public Tile(char letter, int value)
	{
		this.letter		= letter;
		this.value		= value;
		this.gridSpace	= null;
		this.label		= new Label(this.getLetter() + " - " + this.getValue());
		this.rectangle	= new Rectangle();
		
		this.setWidth(TILE_SIZE);
		this.setHeight(TILE_SIZE);
		
		this.rectangle.setWidth(TILE_SIZE);
		this.rectangle.setHeight(TILE_SIZE);
		
		this.rectangle.setFill(Color.LEMONCHIFFON);
		this.rectangle.setStroke(Color.BLACK);
		
		this.rectangle.xProperty().bind(this.layoutXProperty());
		this.rectangle.yProperty().bind(this.layoutYProperty());
		
		getChildren().addAll(this.rectangle, this.label);
	}// end Tile - constructor
	
	// region Getters & Setters
	public char getLetter()
	{
		return letter;
	}// end getLetter
	
	public int getValue()
	{
		return value;
	}// end getValue
	
	public GridSpace getGridSpace()
	{
		return gridSpace;
	}// end getGridSpace
	
	public void setGridSpace(GridSpace gridSpace)
	{
		this.gridSpace = gridSpace;
	}// end setGridSpace
		// endregion Getters & Setters
}// end Tile - class