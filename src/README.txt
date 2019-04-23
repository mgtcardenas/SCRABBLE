The class that contains the main method is Scrabble (if you want to run it). I REQUIRE you to use Java8 since this
is the version that includes the JavaFX GUI framework on the JDK.

This source code implements my version of the Scrabble table top game, since not all the rules of that game are
strictly followed. This game does not support saving.

For knowing Scrabble rules I recommend watching one or both of the following videos:
- https://www.youtube.com/watch?v=UYzRHhFLeGA (12:03 min length)
- https://www.youtube.com/watch?v=swlg3vQXboE&t=4s (2:33 min length)

Some of the most notable differences are...

1. This game can only be played by two players (not one player, nor 3, nor 4)
2. Only one word will be counted per move, so you can't have something like two words with one tile
3. When the game ends, the remaining tiles' values won't be subtracted from the total score of players

Aside from these things, this Scrabble has the same letter distributions and values.

The Software Design patterns used in this project were:

1. Singleton: for the Bag and Board objects of the game
2. Memento: when the player wants to cancel placing some Tiles on the board, a memento is set beforehand and is used to get to this state
3. Bridge: when calculating the score of a given word, the context of each tile and of the word is used, so the behavior of the function changes

The dictionary file comes from a text file of twenty thousand common english words
that can be found at: https://github.com/first20hours/google-10000-english/blob/master/20k.txt