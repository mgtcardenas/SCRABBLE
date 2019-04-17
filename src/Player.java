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
	private List<Tile>	playedTiles;
	// TODO: Add a playedTiles attribute
	
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
		for (int i = 0; i < 7; i++)
			this.takeTile();
	}// end retakeTiles
	
	/**
	 * The player performs a move that can either be forming a word, passing or retaking tiles
	 */
	public void makeMove() throws Exception
	{
		boolean	madeMove	= false;
		Scanner	sc			= new Scanner(System.in);
		
		do
		{
			System.out.println("What would you like to do " + this.getName() + " ?");
			System.out.println("1 -> Place Word, 2 -> Retake Tiles, 3 -> Pass");
			System.out.print("Your tiles are -> ");
			for (Tile t : this.tiles)
				System.out.print(t.getLetter() + "-" + t.getValue() + " ");
			
			switch (sc.nextInt())
			{
				case 1:
					madeMove = placeWord(); // placeWord return boolean if indeed the player tried to place a word
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
					System.out.println("Please make a move");
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
	private boolean placeWord()
	{
		boolean	madeMove	= false;
		boolean	placedWord	= false;
		Scanner	sc			= new Scanner(System.in);
		
		Caretaker.keep(this);
		
		do
		{
			System.out.println("What would you like to do " + this.getName() + " ?");
			System.out.println("1. Place Tile, 2. Confirm, 3. Cancel, 4. Exit");
			System.out.print("Your tiles are -> ");
			for (Tile t : this.tiles)
				System.out.print(t.getLetter() + "-" + t.getValue() + " ");
			
			switch (sc.nextInt())
			{
				case 1:
					placeTile();
					break;
				
				case 2:
					if (isValidWord())
						calculateWordScore();
					
					placedWord = true;
					madeMove = true;
					break;
				
				case 3:
					Caretaker.undo(this); // We undo the changes
					Caretaker.keep(this); // We prepare if the user makes another cancel
					break;
				
				case 4:
					Caretaker.undo(this);
					madeMove = true;
					break;
				
				default:
					System.out.println("Please make a move");
					break;
			}// end switch
		}while (!madeMove); // end do-while
		
		return placedWord;
	}// end placeWord
	
	/**
	 * Puts a single Tile on the Board. This removes it from the players Tile list
	 */
	private void placeTile()
	{
		int		x, y, index;
		
		// TODO: Put security so if the user makes a mistake, the game keeps running
		Scanner	sc	= new Scanner(System.in);
		System.out.println("Give me the coordinates...");
		System.out.print("X: ");
		x = sc.nextInt();
		System.out.print("Y: ");
		y = sc.nextInt();
		System.out.print("Which letter do you want to put there? (Give Index)");
		index = sc.nextInt();
		
		// Associate the Tile with the GridSpace and Viceversa
		this.tiles.get(index).setGridSpace(Board.instance().getGrid()[x][y]);
		Board.instance().getGrid()[x][y].setTile(this.tiles.get(index));
		
		System.out.println("The Tile " + this.tiles.get(index).getLetter() + " was put in " + x + "," + y);
		
		// Remove the Tile from the players tiles and put it in it's played tiles
		this.playedTiles.add(this.tiles.get(index));
		this.tiles.remove(index);
	}// end placeTile
	
	/**
	 * Checks whether the tiles just put in the board form a valid word
	 */
	private boolean isValidWord()
	{
		return false;
	}// end isValidWord
	
	/**
	 * Method that uses an Adder and a Counter classes that implement the Bridge software design pattern
	 */
	private void calculateWordScore()
	{
		
	}// end calculateWordScore
	
	/**
	 * The player does nothing, he / she waits for his / her opponent to make a move
	 */
	private void pass()
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
