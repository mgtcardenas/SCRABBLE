public class Game
{
	Player	firstPlayer, secondPlayer;
	Board	board;
	Bag		bag;
	
	public Game()
	{
		this.board	= Board.instance();
		this.bag	= Bag.instance();
	}// end Game - constructor
	
	/**
	 * Simulates the behavior when two players take a single tile from the Bag to
	 * decide who goes first in the game. Thus we will know who is the first player
	 * and who is the second player
	 */
	public void assignFirstAndSecondPlayer(String playerOneName, String playerTwoName) throws Exception
	{
		Player	player1;
		Player	player2;
		
		player1	= new Player(playerOneName);
		player2	= new Player(playerTwoName);
		
		do
		{
			// If the players had any tiles, they must return them
			player1.returnTiles();
			player2.returnTiles();
			
			// Both players take out one tile
			player1.takeTile();
			player2.takeTile();
			
			if (player1.getTiles().get(0).getLetter() < player2.getTiles().get(0).getLetter())
			{
				firstPlayer		= player1;
				secondPlayer	= player2;
			}
			else if (player1.getTiles().get(0).getLetter() > player2.getTiles().get(0).getLetter())
			{
				firstPlayer		= player2;
				secondPlayer	= player1;
			}// end if - else
			
		}while (player1.getTiles().get(0).getLetter() == player2.getTiles().get(0).getLetter()); // end do-while
		
		firstPlayer.returnTiles();
		secondPlayer.returnTiles();
	}// end assignFirstAndSecondPlayer
	
	public void play() throws Exception
	{
		assignFirstAndSecondPlayer("Marco", "Lilia");
		
		// Players take 7 tiles each
		firstPlayer.refillTiles();
		secondPlayer.refillTiles();
		
		System.out.println("First Player " + firstPlayer.getName() + "'s Turn");
		firstPlayer.makeMove();
		firstPlayer.makeMove();
		firstPlayer.makeMove();
		
		firstPlayer.removeTileTest();
		
		System.out.println("Second Player " + secondPlayer.getName() + "'s Turn");
		secondPlayer.makeMove();
		// do
		// {
		// System.out.println("First Player's Turn");
		// firstPlayer.makeMove();
		// System.out.println("Second Player's Turn");
		// secondPlayer.makeMove();
		// }while (!bag.isEmpty()); // end do-while
		//
		// // Final Round
		// firstPlayer.makeMove();
		// secondPlayer.makeMove();
		//
		// if (firstPlayer.getScore() > secondPlayer.getScore())
		// System.out.println(("The First Player Won!"));
		// else if (firstPlayer.getScore() < secondPlayer.getScore())
		// System.out.println(("The Second Player Won!"));
		// else
		// System.out.println(("It's a Draw!"));
	}// end play
	
}// end Game - class
