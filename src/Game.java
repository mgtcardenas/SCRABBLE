public class Game
{
	Player	firstPlayer, secondPlayer;
	Board	board;
	Bag		bag;
	
	/**
	 * Simulates the behavior when two players take a single tile from the Bag to
	 * decide who goes first in the game. Thus we will know who is the first player
	 * and who is the second player
	 */
	public void assignFirstAndSecondPlayer() throws Exception
	{
		//TODO: What if the letter is the same? We should do this all over again until it isn't
		Player	player1;
		Player	player2;
		
		player1	= new Player();
		player2	= new Player();
		
		// Both players take out one tile
		player1.takeTile();
		player2.takeTile();
		
		if (player1.getTiles().get(0).getLetter() < player2.getTiles().get(0).getLetter())
		{
			firstPlayer		= player1;
			secondPlayer	= player2;
		}
		else
		{
			firstPlayer		= player2;
			secondPlayer	= player1;
		}// end if - else
	}// end assignFirstAndSecondPlayer
	
	public void play() throws Exception
	{
		assignFirstAndSecondPlayer();
		
		// Players return the tile and take 7 tiles each
		firstPlayer.retakeTiles();
		secondPlayer.retakeTiles();
		
		// do
		// {
		// firstPlayer.makeMove();
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
