package kz.kbtu.battleship;

/**
 * Class used to represent the ships used in the battleship game. Holds the
 * various information for the ship: length, name, hits taken, start and end
 * points, direction the ship is facing, etc.
 * 
 * @author Nathan Newman
 * 
 */
public interface ShipInterface {

	/**
	 * Static variable representing the length of the Carrier.
	 */
	int CARRIERLENGTH = 5;

	/**
	 * Static variable representing the length of the Battleship.
	 */
	int BATTLESHIPLENGTH = 4;

	/**
	 * Static variable representing the length of the Cruiser.
	 */
	int CRUISERLENGTH = 3;

	/**
	 * Static variable representing the length of the Submarine.
	 */
	int SUBMARINELENGTH = 3;

	/**
	 * Static variable representing the length of the Destroyer.
	 */
	int DESTROYERLENGTH = 2;

	/**
	 * Returns the name of the ship.
	 * 
	 * @return this.name
	 * 
	 * @requires true
	 * @ensures the ship's name is returned
	 */
	String getName();

	/**
	 * Returns the type of the ship.
	 * 
	 * @return this.type
	 * 
	 * @requires true
	 * @ensures the ship's enumerated type is returned
	 */
	ShipEnum getType();

	/**
	 * Returns the ship's direction.
	 * 
	 * @return this.dir
	 * 
	 * @requires true
	 * @ensures the enumerated direction of the ship is returned
	 */
	Direction getDirection();

	/**
	 * Returns the start position of the ship.
	 * 
	 * @return this.vertStart or this.horzStart
	 * 
	 * @requires true
	 * @ensures the start position of the ship is returned depending on the
	 *          ship's direction
	 */
	int getStart();

	/**
	 * Returns the end position of the ship.
	 * 
	 * @return this.vertEnd or this.horzEnd
	 * 
	 * @requires true
	 * @ensures the end position of the ship is returned depending on the ship's
	 *          direction
	 */
	int getEnd();

	/**
	 * Returns the length of the ship.
	 * 
	 * @return this.length
	 * 
	 * @requires true
	 * @ensures the length of the ship is returned
	 */
	int getLength();

	/**
	 * Returns the number of hits the ship has taken.
	 * 
	 * @return this.hitsTaken
	 * 
	 * @requires true
	 * @ensures the number of hits the ship has taken is returned
	 */
	int getHitsTaken();

	/**
	 * Returns the number of hits the ship has remaining.
	 * 
	 * @return this.hitsLeft
	 * 
	 * @requires true
	 * @ensures the number of hits the ship has remaining is returned
	 */
	int getHitsLeft();

	/**
	 * Sets the direction of the ship to the passed in direction.
	 * 
	 * @param d
	 *            an enumerated direction
	 * 
	 * @requires d is in Direction
	 * @ensures this.dir = d
	 */
	void setDirection(Direction d);

	/**
	 * Set the start and end positions of the ship.
	 * 
	 * @param d
	 *            an enumerated direction
	 * @param vStart
	 *            y co-ordinate for the ship's starting position
	 * @param hStart
	 *            x co-ordinate for the ship's starting position
	 * 
	 * @requires d is in Direction, 0 <= hStart, vStart <= 10
	 * @ensures this.dir = d and sets the ships start and end positions
	 *          accordingly
	 */
	void setStartAndEnd(Direction d, int vStart, int hStart);

	/**
	 * Returns a boolean value representing whether or not the ship has been
	 * placed yet or not on the input board.
	 * 
	 * @return a boolean value based on the placement of the ship
	 * 
	 * @requires true
	 * @ensures true is returned if the ship has been placed, else false is
	 */
	boolean isPlaced();

	/**
	 * Sets the value of ship.isplaced to true. Says that the ship has been
	 * placed.
	 * 
	 * @requires that the ship has been placed
	 * @ensures ship.isPlaced = true
	 */
	void place();

	/**
	 * 'Removes' the ship from the input board.
	 * 
	 * @requires true
	 * @ensures ship.isPlaced = false
	 */
	void unPlace();

	/**
	 * Returns whether or not the ship has been sunk.
	 * 
	 * @return a boolean value representing whether the ship has been sunk
	 * 
	 * @requires true
	 * @ensures true is returned if the ship is sunk, else false is returned
	 */
	boolean isSunk();

	/**
	 * Increments the number of hits the ship has taken.
	 * 
	 * @requires the ship has been 'attacked'
	 * @ensures hitstaken = #hitstaken + 1
	 */
	void incHitsTaken();

}
