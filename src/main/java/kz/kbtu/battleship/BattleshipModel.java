package kz.kbtu.battleship;

/**
 * Model side of the Battleship game, based on the MVC model. Holds the data
 * representations of the game needed in order to play. Stores that data in a
 * two Player array in order to the keep the data for each player separate.
 * 
 * @author Nathan Newman
 * 
 * @convention players.length = 2
 * @correspondence player1 = players[0] and player2 = players[1]
 * 
 */
public class BattleshipModel implements BattleshipModelInterface {

	/**
	 * Array of Player, holding the information for both players. Player 1 -> 0,
	 * Player 2 -> 1
	 */
	private PlayerInterface[] players = new PlayerInterface[2];

	/**
	 * Constructor instantiating the two elements of the players array.
	 * 
	 * @requires players.length = 2
	 * @ensures players[0] = player 1 and players[1] = player 2
	 */
	public BattleshipModel() {
		players[BattleshipModelInterface.PLAYER1] = new Player();
		players[BattleshipModelInterface.PLAYER2] = new Player();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final PlayerInterface[] getPlayers() {
		return players;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPlayerName(String s, int nameIndex) {
		players[nameIndex - 1].setName(s);
	}

	public final int winner() {
		if (players[0].getShipsLeft() == 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
