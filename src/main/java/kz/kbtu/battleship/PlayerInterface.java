package kz.kbtu.battleship;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Nathan Newman
 * 
 */
public interface PlayerInterface {

	/**
	 * Array used in labeling the columns of the player's game/input board.
	 */
	String[] LETTERS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	/**
	 * Array used in labeling the rows of the player's game/input board.
	 */
	String[] NUMBERS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

	/**
	 * Sets up a JButton grid to be used as a ship input panel. Creates a panel
	 * that the player can then place their ships on to prepare for the game.
	 * Uses PlacementHandler as the ButtonHandler in order to determine where
	 * and how a ship is placed.
	 * 
	 * @requires shipBoard != null, grid != null
	 * @ensures a 10x10 grid is created with the appropriate labels as
	 *          determined by Player.LETTERS and Player.NUMBERS
	 */
	void setShipInputBoard();

	/**
	 * Returns a reference to the ship input panel.
	 * 
	 * @return a reference to shipBoard.
	 */
	JPanel getShipInputBoard();

	/**
	 * Sets up a JButton grid to be used as a game board panel. Creates a panel
	 * that the player can use to choose where to 'fire' at their opponent. Uses
	 * SelectionHandler as the ButtonHandler in order to determine whether a
	 * ship was hit or not.
	 * 
	 * @requires gameBoard != null, grid != null
	 * @ensures a 10x10 grid is created with the appropriate labels as
	 *          determined by Player.LETTERS and Player.NUMBERS
	 */
	void setGameBoard();

	/**
	 * Returns a reference to the player's game board.
	 * 
	 * @return a reference to gameBoard.
	 */
	JPanel getGameBoard();

	/**
	 * Places a ship in this.shipPlacement at the starting vertical and
	 * horizontal co-ordinates and according to the ships type (length) and
	 * direction. The starting co-ordinates are read as such: Horizontal
	 * co-ordinates increase as you move to the right and vertical co-ordinates
	 * increase as you move down.
	 * 
	 * @param s
	 *            the enumerated type of the ship used to determine length
	 * @param d
	 *            the enumerated direction of the ship
	 * @param startX
	 *            the starting x (horizontal) co-ordinate
	 * @param startY
	 *            the starting y (vertical) co-ordinate
	 * 
	 * @requires s is an element of ShipEnum, d is an element of Direction, 0 <=
	 *           x,y <= 10
	 * @ensures if d = VERTICAL then shipPlacement[startX + i][startY] = s, for
	 *          0 <= i < s.length, if d = horizontal then
	 *          shipPlacement[startX][startY + i] = s, for 0 <= i < s.length
	 */
	void placeShip(ShipEnum s, Direction d, int startX, int startY);

	/**
	 * Removes all ships from the game board.
	 * 
	 * @requires shipPlacement != null, and ships != null
	 * @ensures all ships are removed from ship input board
	 */
	void clearShips();

	/**
	 * Returns the type of the ship at the passed in location in the array.
	 * 
	 * @param startX
	 *            the x co-ordinate of the ship's place in the array.
	 * @param startY
	 *            the y co-ordinate of the ship's place in the array.
	 * @return the type of the ship at the given spot in the array.
	 * 
	 * @requires shipPlacement != null
	 * @ensures if there is a ship at the given spot in the array then that type
	 *          is returned, else null is returned
	 */
	ShipEnum getShip(int startX, int startY);

	/**
	 * Initializes the hitOrMiss array according to whether or not there is a
	 * ship at shipPlacement[i][j] for all i,j s.t. 0 <= i,j <= 10.
	 * 
	 * @requires hitOrMiss != null and shipPlacement != null
	 * @ensures hitOrMiss[i][j] = (shipPlacement[i][j] != null), for all i,j
	 *          s.t. 0 <= i,j <= 10
	 */
	void setHitOrMiss();

	/**
	 * Returns whether the selection at hitOrMiss[startX][startY] is a hit or a
	 * miss.
	 * 
	 * @param startX
	 *            the x co-ordinate of the selected space's position in the
	 *            array (grid)
	 * @param startY
	 *            the y co-ordinate of the selected space's position in the
	 *            array (grid)
	 * 
	 * @return true or false depending on whether a ship is at the given point
	 *         in the grid
	 * 
	 * @requires this.hitOrMiss != null, 0 <= startX, startY <= 10
	 * @ensures the value returned is true if there is a ship at the given
	 *          co-ordinate in this.shipPlacement and false if that location is
	 *          null
	 */
	boolean getHitOrMiss(int startX, int startY);

	/**
	 * Returns an integer value representing the number of ships the player has
	 * remaining.
	 * 
	 * @return an integer value representing the player's remaining ships
	 * 
	 * @requires true
	 * @ensures this.shipsLeft is returned
	 */
	int getShipsLeft();

	/**
	 * Returns an integer value representing the number of shots the player has
	 * taken. Can be used for statistical purposes.
	 * 
	 * @return an integer value representing the player's number of shots taken
	 * 
	 * @requires true
	 * @ensures this.shotsTaken is returned
	 */
	int getShotsTaken();

	/**
	 * Returns an integer value representing the number of hits the player has
	 * taken. Can be used for statistical purposes.
	 * 
	 * @return an integer value representing the player's number of hits taken
	 * 
	 * @requires true
	 * @ensures this.hitsTaken is returned
	 */
	int getHitsTaken();

	/**
	 * Sets the player's name to the passed in string.
	 * 
	 * @param n
	 *            a String representing the player's "new" name
	 * 
	 * @requires n != null
	 * @ensures this.name = n
	 */
	void setName(String n);

	/**
	 * Returns the player's name.
	 * 
	 * @return a String value representing the player's name
	 * 
	 * @requires true
	 * @ensures the default "Player" is returned if the name is not set
	 *          otherwise using setName(n), else return n
	 */
	String getName();

	/**
	 * Returns a reference to the Player's ships array.
	 * 
	 * @return a reference to this.ships
	 * 
	 * @requires true
	 * @ensures returns null if the ships array is not instantiated, or returns
	 *          a reference to the ships array if it has
	 */
	ShipInterface[] getShips();

	/**
	 * Returns an integer value representing how many ships have been placed on
	 * the game board.
	 * 
	 * @return an integer representing the number of ships that have currently
	 *         been placed on the ship input board
	 * 
	 * @requires true
	 * @ensures the number returned is the correct number of ships currently
	 *          placed
	 */
	int getShipsPlaced();

	/**
	 * Increments the amount of ships that have been placed by 1.
	 * 
	 * @requires true
	 * @ensures shipsPlaced = #shipsPlaced + 1
	 */
	void incShipsPlaced();

	/**
	 * Returns whether or not the player is ready to deploy their ships.
	 * 
	 * @return a boolean value: true if ready to deploy, false if not
	 * 
	 * @requires true
	 * @ensures true is returned if and only if all ships are placed
	 */
	boolean getDeploy();

	/**
	 * Sets the player's active ship to the passed in parameter.
	 * 
	 * @param aS
	 *            Enumerated type representing a ship selected from the player's
	 *            ships combo box
	 * 
	 * @requires aS is in ShipEnum
	 * @ensures player.activeship = aS
	 */
	void setActiveShip(ShipEnum aS);

	/**
	 * Returns the value of the player's active ship.
	 * 
	 * @return Enumerated type representing the player's active ship
	 * 
	 * @requires true
	 * @ensures player.activeship is returned
	 */
	ShipEnum getActiveShip();

	/**
	 * Returns a reference to the JButton at the given location in the grid.
	 * 
	 * @param a
	 *            x co-ordinate of the JButton
	 * @param b
	 *            y co-ordinate of the JButton
	 * 
	 * @return a reference to a JButton
	 * 
	 * @requires grid != null, and a and b are in the appropriate range of the
	 *           grid
	 * @ensures the right JButton is returned
	 */
	JButton getGrid(int a, int b);

}
