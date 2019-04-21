/**
 * @author Marco Cárdenas
 *
 *         A class that represents the game board, which implements
 *         the Singleton design pattern, since there can only be one board for a game.
 *
 *         The board is represented by a grid of spaces which is treated as a bidimensional
 *         array of type GridSpace.
 */
public class Board
{
	private static Board	uniqueInstance;
	private GridSpace[][]	grid;
	
	/**
	 * There can only be one single board in a game.
	 * This constructor adds the 225 (15x15) grid spaces
	 * to their correct locations with their correct
	 * types and bonuses.
	 *
	 * @throws SingletonException if someone tries to create another bag
	 */
	private Board() throws SingletonException
	{
		if (uniqueInstance != null)
			throw new SingletonException("THERE CAN ONLY BE ONE BOARD!");
		
		// Create a board of 15 by 15 spaces (255 in total)
		this.grid = new GridSpace[15][15];
		
		// Fill the board with simple grid spaces
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				this.grid[i][j] = new GridSpace("simple", i, j);
			
		addDoubleLetterSpaces();
		addTripleLetterSpaces();
		addDoubleWordSpaces();
		addTripleWordSpaces();
	}// end Board - constructor
	
	/**
	 * Puts the double letter type GridSpace objects in their correct positions.
	 * It takes advantage of the symmetry in which the grid spaces are to be placed
	 */
	private void addDoubleLetterSpaces()
	{
		addSymmetric(0, 3, "double-letter");
		addSymmetric(2, 6, "double-letter");
		addSymmetric(3, 0, "double-letter");
		addSymmetric(3, 7, "double-letter");
		addSymmetric(6, 2, "double-letter");
		addSymmetric(6, 6, "double-letter");
		addSymmetric(7, 3, "double-letter");
	}// end addDoubleLetterSpaces
	
	/**
	 * Puts the triple letter type GridSpace objects in their correct positions.
	 * It takes advantage of the symmetry in which the grid spaces are to be placed
	 */
	private void addTripleLetterSpaces()
	{
		addSymmetric(1, 5, "triple-letter");
		addSymmetric(5, 1, "triple-letter");
		addSymmetric(5, 5, "triple-letter");
	}// end addTripleLetterSpaces
	
	/**
	 * Puts the double word type GridSpace objects in their correct positions
	 * It takes advantage of the symmetry in which the grid spaces are to be placed
	 */
	private void addDoubleWordSpaces()
	{
		this.grid[7][7] = new GridSpace("double-word", 7, 7);
		
		addSymmetric(1, 1, "double-word");
		addSymmetric(2, 2, "double-word");
		addSymmetric(3, 3, "double-word");
		addSymmetric(4, 4, "double-word");
	}// end addDoubleWordSpaces
	
	/**
	 * Puts the triple word type GridSpace objects in their correct positions
	 * It takes advantage of the symmetry in which the grid spaces are to be placed
	 */
	private void addTripleWordSpaces()
	{
		addSymmetric(0, 0, "triple-word");
		addSymmetric(0, 7, "triple-word");
		addSymmetric(7, 0, "triple-word");
	}// end addTripleWordSpaces
	
	/**
	 * Adds a GridSpace object to the grid in the given coordinates, it's symmetric coordinates respecting
	 * the 'x' axis, it's symmetric coordinates respecting the 'y' axis and it's symmetric coordinates
	 * respecting the origin/center
	 *
	 * @param x    the x coordinate
	 * @param y    the y coordinate
	 * @param type the type of grid space to be inserted
	 */
	private void addSymmetric(int x, int y, String type)
	{
		this.grid[x][y]				= new GridSpace(type, x, y);
		this.grid[14 - x][y]		= new GridSpace(type, 14 - x, y);        // the symmetric respecting the 'y' axis
		this.grid[x][14 - y]		= new GridSpace(type, x, 14 - y);        // the symmetric respecting the 'x' axis
		this.grid[14 - x][14 - y]	= new GridSpace(type, 14 - x, 14 - y);// the symmetric respecting the origin/center axis
	}// end addSymmetric
	
	/**
	 * Returns the unique instance of the board and if it doesn't exist yet, it creates it
	 *
	 * @return the unique instance of the bag
	 */
	public static synchronized Board instance()
	{
		if (uniqueInstance == null)
		{
			try
			{
				uniqueInstance = new Board(); // we try to create the Board
			}
			catch (SingletonException e)
			{
				e.printStackTrace();
			}// end try - catch
		}// end if
		
		return uniqueInstance;
	}// end instance
	
	/**
	 * Gives access to the grid on the board
	 */
	public GridSpace[][] getGrid()
	{
		return this.grid;
	}// end getGrid
}// end Board - class
