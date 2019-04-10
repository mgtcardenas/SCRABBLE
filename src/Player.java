import java.util.LinkedList;
import java.util.List;

/**
 * @author Marco Cárdenas
 *
 *         Class that represent a Player
 *         A player may have a score and some tiles to play with
 */
public class Player
{
	private int			score;
	private List<Tile>	tiles;
	
	/**
	 * When we create a new player, his/her score is 0
	 * and he has no tiles to play with
	 */
	public Player()
	{
		this.score	= 0;
		this.tiles	= new LinkedList<Tile>();
	}// end Player - constructor
}// end Player - class
