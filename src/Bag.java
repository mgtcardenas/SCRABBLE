import java.util.LinkedList;
import java.util.List;

/**
 * @author Marco Cárdenas
 *
 *         A class that represents the bag of tiles and that implements
 *         the Singleton design pattern
 *
 *         This class does not simply return it's data (the tiles).
 *         The only way to interact with the bag is to take out a tile
 *         or to put back a tile
 */
public class Bag
{
	private static Bag	uniqueInstance;
	private List<Tile>	tiles;
	private List<Tile>	missingTiles;
	
	/**
	 * There can only be one single bag in a game.
	 * This constructor adds 100 tiles with the correct characters
	 * and values to the bag, thus creating it
	 *
	 * @throws Exception if someone tries to create another bag
	 */
	private Bag() throws Exception
	{
		if (uniqueInstance != null)
			throw new Exception("THERE CAN ONLY BE ONE BAG!");
		
		this.missingTiles	= new LinkedList<>();
		this.tiles			= new LinkedList<>();
		// Add Wildcards Worth 0 Points
		addTiles(2, '_', 0);
		// Add Letters worth 1 Point
		addTiles(12, 'E', 1);
		addTiles(9, 'A', 1);
		addTiles(9, 'I', 1);
		addTiles(8, 'O', 1);
		addTiles(6, 'N', 1);
		addTiles(6, 'R', 1);
		addTiles(6, 'T', 1);
		addTiles(4, 'L', 1);
		addTiles(4, 'S', 1);
		addTiles(4, 'U', 1);
		// Add Letters worth 2 Point
		addTiles(4, 'D', 2);
		addTiles(3, 'G', 2);
		// Add Letters worth 3 Point
		addTiles(2, 'B', 3);
		addTiles(2, 'C', 3);
		addTiles(2, 'M', 3);
		addTiles(2, 'P', 3);
		// Add Letters worth 4 Point
		addTiles(2, 'F', 4);
		addTiles(2, 'H', 4);
		addTiles(2, 'V', 4);
		addTiles(2, 'W', 4);
		addTiles(2, 'Y', 4);
		// Add Letters worth 5 Point
		addTiles(1, 'K', 5);
		// Add Letters worth 8 Point
		addTiles(1, 'J', 8);
		addTiles(1, 'X', 8);
		// Add Letters worth 10 Point
		addTiles(1, 'Q', 10);
		addTiles(1, 'Z', 10);
	}// end Bag - constructor
	
	/**
	 * Adds a certain number of tiles with a given letter and a given value
	 *
	 * @param quantity - the number of tiles to be added to the bag
	 * @param letter   - the letter of the tiles added to the bag
	 * @param value    - the value of the tile added to the bag
	 */
	private void addTiles(int quantity, char letter, int value)
	{
		for (int i = 0; i < quantity; i++)
			this.tiles.add(new Tile(letter, value));
	}// end addTiles
	
	/**
	 * Returns the unique instance of the bag and if it doesn't exist yet, it creates it
	 *
	 * @return the unique instance of the bag
	 */
	public static synchronized Bag instance()
	{
		if (uniqueInstance == null)
		{
			try
			{
				uniqueInstance = new Bag(); // we try to create the bag
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}// end try - catch
		}// end if
		
		return uniqueInstance;
	}// end instance
	
	/**
	 * Returns and removes one random tile from the bag
	 *
	 * @return a random tile from the bag
	 */
	public Tile takeTile()
	{
		int		index;
		Tile	tile;
		
		index	= (int) (Math.random() * this.tiles.size());
		tile	= this.tiles.get(index);    // grab a random tile
		this.tiles.remove(index);           // the tile is no longer in the bag
		this.missingTiles.add(tile);        // remember which tile is missing, so players can't cheat
		
		return tile;
	}// end takeTile
	
	/**
	 * Adds one tile to the bag only if it is a missing tile.
	 * If it's not a missing tile, throws an exception to prevent cheating
	 * 
	 * @param tile the tile to be added
	 */
	public void putTile(Tile tile) throws Exception
	{
		if (missingTiles.contains(tile))
		{
			this.tiles.add(tile);
			this.missingTiles.remove(tile);
		}
		else
			throw new Exception("CHEATER, CHEATER, PUMPKIN EATER");
	}// end putTile
	
	/**
	 * Determine whether the bag is empty and has no more tiles in it.
	 * If the tiles list is size 0, then we can say that the bag is empty
	 * 
	 * @return true if the bag is empty / false if it is not
	 */
	public boolean isEmpty()
	{
		return this.tiles.size() == 0;
	}// end isEmpty
}// end Bag - class
