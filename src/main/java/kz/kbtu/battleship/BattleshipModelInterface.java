package kz.kbtu.battleship;

/**
 * Model side of the Battleship game, based on the MVC model. Holds the data
 * representations of the game needed in order to play. Stores that data in a
 * two Player array in order to the keep the data for each player separate.
 * 
 * @author Nathan Newman
 * 
 */
public interface BattleshipModelInterface {

	/**
	 * Integer used to denote the number of ships used per player.
	 */
	int NUMSHIPS = 5;
	/**
	 * Index of the Carrier in the ships array.
	 */
	int CARRINDEX = 4;
	/**
	 * Index of the Battleship in the ships array.
	 */
	int BATTINDEX = 3;
	/**
	 * Index of the Cruiser in the ships array.
	 */
	int CRUSINDEX = 2;
	/**
	 * Index of the Submarine in the ships array.
	 */
	int SUBMINDEX = 1;
	/**
	 * Index of the Destroyer in the ships array.
	 */
	int DESTINDEX = 0;
	/**
	 * Index of player 1 in the players array.
	 */
	int PLAYER1 = 0;
	/**
	 * Index of player 2 in the players array.
	 */
	int PLAYER2 = 1;

	/**
	 * Returns a reference to the players array.
	 * 
	 * @requires true
	 * @ensures a reference to players array is returned
	 * 
	 * @return a reference to the players array.
	 */
	PlayerInterface[] getPlayers();

	/**
	 * An alternate way of setting a players name.
	 * 
	 * @param s
	 *            passed in name to give to the player
	 * @param nameIndex
	 *            index of the players whose name you wish to change
	 * 
	 * @requires s is a valid string and nameIndex = 1 or 2
	 */
	void setPlayerName(String s, int nameIndex);

	/**
	 * Determines the winner of the game.
	 * 
	 * @requires the game actually be over, ie a player has run out of ships.
	 * 
	 * @return the index of the winning player in the player array
	 */
	int winner();

}
