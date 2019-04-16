This source code implements my version of the Scrabble table top game, since not all the rules of that game are
strictly followed. This game can only be played by two players.

Some of the most notable differences are...

1. This game can only be played by two players (not one player, nor 3, nor 4)
2. After the bag is empty, one more round will be played and the game will end
3. Only one word will be counted per move, so you can't have something like two words with one tile

Aside from these things, this Scrabble has the same letter distributions and values.

The Software Design patterns used in this project were:

1. Singleton: for the Bag and Board objects of the game
2. Memento: when the player wants to cancel placing some Tiles on the board, a memento is set beforehand and is used to get to this state
3. Bridge: when calculating the score of a given word, the context of each tile and of the word is used, so the behavior of the function changes