import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
	
	/**
	 * When we create a new player, his/her score is 0
	 * and he has no tiles to play with
	 */
	public Player(String name)
	{
		this.name	= name;
		this.score	= 0;
		this.tiles	= new LinkedList<Tile>();
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
		for (Tile t : this.tiles)
		{
			Bag.instance().putTile(t);
			this.tiles.remove(t); // very important! If not, a tile will be duplicated
		}// end foreach
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
		for (int i = 0; i < 7; i++)
			this.takeTile();
	}// end retakeTiles
	
	/**
	 * The player performs a move that can either be forming a word, passing or retaking tiles
	 */
	public void makeMove() throws Exception
	{
		int		option		= 0;
		boolean	madeMove	= false;
		Scanner	sc			= new Scanner(System.in);
		
		System.out.println("What would you like to do " + getClass().getSimpleName() + " ?");
		System.out.println("1 -> Put Down Word");
		System.out.println("2 -> Retake Tiles");
		System.out.println("3 -> Pass");
		
		do
		{
			option = sc.nextInt();
			
			switch (option)
			{
				case 1:
					placeWord();
					madeMove = true;
					break;
				
				case 2:
					retakeTiles();
					madeMove = true;
					break;
				
				case 3:
					pass();
					madeMove = true;
					break;
				
				default:
					System.out.println("Please make a move, your tiles are...");
					for (int i = 0; i < this.tiles.size(); i++)
						System.out.print(i + ". " + this.tiles.get(i).getLetter() + " - " + this.tiles.get(i).getValue());
					break;
			}// end switch
		}while (!madeMove); // end do-while
	}// end makeMove
	
	/**
	 * The player attempts to form a word with it's tiles by putting them
	 * on a specific GridSpace of the Board. A player can place one of his / her
	 * Tiles, cancel the word (causing a memento to be used) or confirm the word
	 * (which will test if the placed Tiles form a word)
	 */
	public void placeWord()
	{
		int		x, y, index;
		
		Scanner	sc	= new Scanner(System.in);
		System.out.println("Give me the coordinates...");
		System.out.println("X: ");
		x = sc.nextInt();
		System.out.println("Y: ");
		y = sc.nextInt();
		System.out.println("Which letter do you want to put there?");
		index = sc.nextInt();
		
		this.tiles.get(index).setGridSpace(Board.instance().getGrid()[x][y]);
		Board.instance().getGrid()[x][y].setTile(this.tiles.get(index));
	}// end placeWord
	
	/**
	 * Puts a single Tile on the Board
	 */
	public void placeTile()
	{
	
	}// end placeTile
	
	/**
	 * The player does nothing, he / she waits for his / her opponent to make a move
	 */
	public void pass()
	{
		System.out.println(this.getName() + " passed");
	}// end pass
	
	/**
	 * Creates a Memento object with the current titles of the player
	 * 
	 * @return a new Memento object built from the tiles of the player
	 */
	public Memento createMemento()
	{
		return new Memento(this.tiles);
	}// end createMemento
	
	/**
	 * Sets the state of the tiles based on a memento previously
	 * 
	 * @param memento the memento with which the state of the tiles is rebuilt
	 */
	public void setMemento(Memento memento)
	{
		// TODO: Make this also put away the tiles from the board or something
		this.tiles = memento.getState();
	}// end setMemento
}// end Player - class
