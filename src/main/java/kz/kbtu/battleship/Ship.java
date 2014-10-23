package kz.kbtu.battleship;

/**
 * Class used to represent the ships used in the battleship game. Holds the
 * various information for the ship: length, name, hits taken, start and end
 * points, direction the ship is facing, etc.
 * 
 * @author Nathan Newman
 * 
 */
public class Ship implements ShipInterface {

	/**
	 * Variable used to store the vertical start and end of the ship.
	 */
	private int vertStart, vertEnd;

	/**
	 * Variable used to store the horizontal start and end of the ship.
	 */
	private int horzStart, horzEnd;

	/**
	 * Variable to store the length of the ship.
	 */
	private int length;

	/**
	 * Variable used to store the amount of hits the ship has taken.
	 */
	private int hitsTaken;

	/**
	 * Variable used to store the hits the ship has remaining.
	 */
	private int hitsLeft;

	/**
	 * Variable storing the name of the ship.
	 */
	private String name;

	/**
	 * Variable storing the type of the ship.
	 */
	private ShipEnum type;

	/**
	 * Variable storing the direction of the ship.
	 */
	private Direction dir = Direction.VERTICAL;

	/**
	 * 
	 */
	private boolean isPlaced = false;

	/**
	 * 
	 */
	private boolean isSunk = false;

	/**
	 * Constructor used to initialize the ship according to the passed in type.
	 * 
	 * @param s
	 *            type of ship to initialize the object to
	 * 
	 * @requires s is in ShipEnum
	 * @ensures this = s
	 */
	public Ship(ShipEnum s) {
		type = s;
		switch (type) {
		case CARRIER: {
			name = "Carrier";
			length = ShipInterface.CARRIERLENGTH;
			hitsLeft = length;
			break;
		}
		case BATTLESHIP: {
			name = "Battleship";
			length = ShipInterface.BATTLESHIPLENGTH;
			hitsLeft = length;
			break;
		}
		case CRUISER: {
			name = "Cruiser";
			length = ShipInterface.CRUISERLENGTH;
			hitsLeft = length;
			break;
		}
		case SUBMARINE: {
			name = "Submarine";
			length = ShipInterface.SUBMARINELENGTH;
			hitsLeft = length;
			break;
		}
		case DESTROYER: {
			name = "Destroyer";
			length = ShipInterface.DESTROYERLENGTH;
			hitsLeft = length;
			break;
		}
		default: {

		}
		}
	}

	/**
	 * Constructor used to initialize the ship according to the passed in type
	 * and at the starting points passed in.
	 * 
	 * @param s
	 *            type of ship to initialize the object to
	 * @param d
	 *            direction to place the ship in
	 * @param vStart
	 *            the y co-ordinate of the ships starting position
	 * @param hStart
	 *            the x co-ordinate of the ships starting position
	 * 
	 * @requires s is in ShipEnum,
	 * @ensures this = s
	 */
	public Ship(ShipEnum s, Direction d, int vStart, int hStart) {
		type = s;
		dir = d;
		switch (type) {
		case CARRIER: {
			name = "Carrier";
			length = ShipInterface.CARRIERLENGTH;
			hitsLeft = length;
			switch (dir) {
			case VERTICAL: {
				vertStart = vStart;
				vertEnd = vStart + (length - 1);
				horzStart = hStart;
				horzEnd = hStart;
				break;
			}
			case HORIZONTAL: {
				vertStart = vStart;
				vertEnd = vStart;
				horzStart = hStart;
				horzEnd = hStart + +(length - 1);
				break;
			}
			default: {

			}
			}
			break;
		}
		case BATTLESHIP: {
			name = "Battleship";
			length = ShipInterface.BATTLESHIPLENGTH;
			hitsLeft = length;
			switch (dir) {
			case VERTICAL: {
				vertStart = vStart;
				vertEnd = vStart + (length - 1);
				horzStart = hStart;
				horzEnd = hStart;
				break;
			}
			case HORIZONTAL: {
				vertStart = vStart;
				vertEnd = vStart;
				horzStart = hStart;
				horzEnd = hStart + +(length - 1);
				break;
			}
			default: {

			}
			}
			break;
		}
		case CRUISER: {
			name = "Cruiser";
			length = ShipInterface.CRUISERLENGTH;
			hitsLeft = length;
			switch (dir) {
			case VERTICAL: {
				vertStart = vStart;
				vertEnd = vStart + (length - 1);
				horzStart = hStart;
				horzEnd = hStart;
				break;
			}
			case HORIZONTAL: {
				vertStart = vStart;
				vertEnd = vStart;
				horzStart = hStart;
				horzEnd = hStart + +(length - 1);
				break;
			}
			default: {

			}
			}
			break;
		}
		case SUBMARINE: {
			name = "Submarine";
			length = ShipInterface.SUBMARINELENGTH;
			hitsLeft = length;
			switch (dir) {
			case VERTICAL: {
				vertStart = vStart;
				vertEnd = vStart + (length - 1);
				horzStart = hStart;
				horzEnd = hStart;
				break;
			}
			case HORIZONTAL: {
				vertStart = vStart;
				vertEnd = vStart;
				horzStart = hStart;
				horzEnd = hStart + +(length - 1);
				break;
			}
			default: {

			}
			}
			break;
		}
		case DESTROYER: {
			name = "Destroyer";
			length = ShipInterface.DESTROYERLENGTH;
			hitsLeft = length;
			switch (dir) {
			case VERTICAL: {
				vertStart = vStart;
				vertEnd = vStart + (length - 1);
				horzStart = hStart;
				horzEnd = hStart;
				break;
			}
			case HORIZONTAL: {
				vertStart = vStart;
				vertEnd = vStart;
				horzStart = hStart;
				horzEnd = hStart + +(length - 1);
				break;
			}
			default: {

			}
			}
			break;
		}
		default: {

		}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ShipEnum getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Direction getDirection() {
		return dir;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getStart() {
		int retVal = 0;
		switch (dir) {
		case VERTICAL: {
			retVal = vertStart;
			break;
		}
		case HORIZONTAL: {
			retVal = horzStart;
			break;
		}
		default: {

		}
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getEnd() {
		int retVal = 0;
		switch (dir) {
		case VERTICAL: {
			retVal = vertEnd;
			break;
		}
		case HORIZONTAL: {
			retVal = horzEnd;
			break;
		}
		default: {

		}
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getLength() {
		return length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getHitsTaken() {
		return hitsTaken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getHitsLeft() {
		return hitsLeft;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDirection(Direction d) {
		dir = d;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setStartAndEnd(Direction d, int vStart, int hStart) {
		switch (dir) {
		case VERTICAL: {
			vertStart = vStart;
			vertEnd = vertStart + (length - 1);
			horzStart = hStart;
			horzEnd = hStart;
			break;
		}
		case HORIZONTAL: {
			horzStart = hStart;
			horzEnd = horzStart + (length - 1);
			vertStart = vStart;
			vertEnd = vStart;
			break;
		}
		default: {

		}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPlaced() {
		return isPlaced;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void place() {
		isPlaced = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void unPlace() {
		isPlaced = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isSunk() {
		if (hitsTaken >= length) {
			isSunk = true;
		}
		return isSunk;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void incHitsTaken() {
		hitsTaken++;
	}

}
