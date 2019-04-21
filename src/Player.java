import java.util.LinkedList;
import java.util.List;

/**
 * @author Marco Cárdenas
 *
 *         Class that represent a Player
 *         A player may have a score and some tiles to finish with
 */
public class Player
{
	private String		name;
	private int			score;
	private List<Tile>	tiles;
	private List<Tile>	playedTiles;
	
	/**
	 * When we create a new player, his/her score is 0
	 * and he has no tiles to finish with
	 */
	public Player(String name)
	{
		this.name			= name;
		this.score			= 0;
		this.tiles			= new LinkedList<>();
		this.playedTiles	= new LinkedList<>();
	}// end Player - constructor
	
	// region Getters & Setters
	public String getName()
	{
		return name;
	}// end getName
	
	public int getScore()
	{
		return score;
	}// end getScore
	
	public void setScore(int score)
	{
		this.score = score;
	}// end setScore
	
	public List<Tile> getTiles()
	{
		return tiles;
	}// end getTiles
	
	public List<Tile> getPlayedTiles()
	{
		return playedTiles;
	}// end getPlayedTiles
	
	public void setPlayedTiles(List<Tile> playedTiles)
	{
		this.playedTiles = playedTiles;
	}// end setPlayedTiles
		// endregion Getters & Setters
	
	/**
	 * Makes the player take a tile from the Bag
	 */
	public void takeTile()
	{
		if (!Bag.instance().isEmpty())
			this.tiles.add(Bag.instance().takeTile());
	}// end takeTile
	
	/**
	 * Makes the player return all of his/her tiles to the bag
	 *
	 * @throws Exception if somehow the player wants to return a tile that is not missing from the bag
	 */
	public void returnTiles() throws Exception
	{
		for (int i = this.tiles.size() - 1; i >= 0; i--) // reverse is very important since size() will be changing as we remove
		{
			Bag.instance().putTile(this.tiles.get(i));
			this.tiles.remove(this.tiles.get(i)); // very important! If not, a tile will be duplicated
		}// end for - i
	}// end returnTiles
	
	/**
	 * The player refills his tiles so he / she now has 7 as long as the bag is not empty
	 */
	public void refillTiles()
	{
		while (this.tiles.size() != 7 && !Bag.instance().isEmpty())
			this.takeTile();
	}// end refillTiles
	
	/**
	 * The player changes all of his/her tiles to the bag and takes another 7 if possible
	 *
	 * @throws Exception - If the player attempts to cheat by returning tiles that don't belong to the set
	 */
	public void retakeTiles() throws Exception
	{
		this.returnTiles();
		this.refillTiles();
	}// end retakeTiles
	
	/**
	 * Creates a Memento object with the current titles of the player
	 *
	 * @return a new Memento object built from the tiles of the player
	 */
	public Memento createMemento()
	{
		return new Memento((LinkedList<Tile>) ((LinkedList<Tile>) this.tiles).clone()); // Gives a copy of the Linked List
	}// end createMemento
	
	/**
	 * Sets the state of the tiles based on a memento previously
	 *
	 * @param memento the memento with which the state of the tiles is rebuilt
	 */
	public void setMemento(Memento memento)
	{
		for (int i = this.playedTiles.size() - 1; i >= 0; i--)
		{
			this.playedTiles.get(i).getGridSpace().setTile(null); // Dissociate the GridSpace with the Tile
			this.playedTiles.get(i).setGridSpace(null);           // Dissociate the Tile with the GridSpace
			this.playedTiles.remove(i);
		}// end for - i
		
		this.tiles = memento.getState();
	}// end setMemento
}// end Player - class
