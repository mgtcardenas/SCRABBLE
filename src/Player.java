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
	private String		name;
	private int			score;
	private List<Tile>	tiles;
	private List<Tile>	playedTiles;
	
	/**
	 * When we create a new player, his/her score is 0
	 * and he has no tiles to play with
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
		// TODO: Delete this
		// for (int i = 0; i < 7; i++)
		// this.takeTile();
	}// end retakeTiles
	
	/**
	 * Checks whether the tiles just put in the board form a valid word
	 */
	private boolean isValidWord()
	{
		if (playedTiles.size() == 0) // There are no played Tiles
			return false;
		
		if (!anyPlayedTileIsConnected()) // The played Tiles are not connected to a valid played word
			return false;
		
		if (!allPlayedTilesAreAligned()) // Not all the played Tiles are vertical or horizontal with one another
			return false;
		
		return Dictionary.wordExists(getWord()); // The formed word does not exist
	}// end isValidWord
	
	/**
	 * It returns a string constructed from the chars of all the Tiles aligned with the recently played Tiles.
	 * It must only be used when we are sure all the played tiles are aligned with one another.
	 * 
	 * @return the formed word as a String object
	 */
	private String getWord()
	{
		char[]	wordChars;
		int		beginning, end, height;
		Tile	tile;
		
		tile		= this.playedTiles.get(0);
		
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
	 * Gets the tiles of the recently played word. These are probably more than just the played tiles, thus the importance
	 * of this function
	 * 
	 * @return an array of Tile objects ordered
	 */
	private Tile[] getWordTiles()
	{
		int		beginning, end, height;
		Tile	tile;
		Tile[]	wordTiles;
		
		tile		= this.playedTiles.get(0);
		
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
	 * This function determines whether any of the recently played tiles is connected to a word that
	 * was already played on the board or was played on the center of the board.
	 *
	 * @return true if any played tile is connected / false no played tile is connected
	 */
	private boolean anyPlayedTileIsConnected()
	{
		int x, y;
		
		for (Tile t : playedTiles)
		{
			x	= t.getGridSpace().getxCoordinate();
			y	= t.getGridSpace().getyCoordinate();
			
			if (x == 7 && y == 7)
				return true;
			
			if (Board.instance().getGrid()[y][(x == 14) ? x - 1 : x + 1].wasUsed()) // The word is connected right
				return true;
			
			if (Board.instance().getGrid()[y][Math.abs(x - 1)].wasUsed())           // The word is connected left
				return true;
			
			if (Board.instance().getGrid()[Math.abs(y - 1)][x].wasUsed())           // The word is connected up
				return true;
			
			if (Board.instance().getGrid()[(y == 14) ? y - 1 : y + 1][x].wasUsed()) // The word is connected down
				return true;
		}// end foreach
		
		return false;
	}// end anyPlayedTileIsConnected
	
	/**
	 * Determines whether all the played tiles are aligned vertically or horizontally
	 *
	 * @return true if the tiles are a aligned / false if they are not aligned
	 */
	private boolean allPlayedTilesAreAligned()
	{
		int x, y;
		
		y = this.playedTiles.get(0).getGridSpace().getyCoordinate();// Horizontal
		for (Tile horizontalTile : playedTiles)
		{
			if (horizontalTile.getGridSpace().getyCoordinate() != y) // It is not horizontal
			{
				
				x = this.playedTiles.get(0).getGridSpace().getxCoordinate();// Vertical
				for (Tile verticalTile : playedTiles)
				{
					if (verticalTile.getGridSpace().getxCoordinate() != x) // It is neither vertical
						return false;
				}// end foreach
				
				break;
			}// end if
		}// end foreach
		
		return true;
	}// end allPlayedTilesAreAligned
	
	/**
	 * Method that uses an Adder and a Multiplier classes that implement the Bridge software design pattern
	 *
	 * @return the score for playing the most recent word
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
