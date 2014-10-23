package kz.kbtu.battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Nathan Newman
 * 
 */
public class Player implements PlayerInterface {

	/**
	 * Panel used to hold the grid of JButtons used for inputting the player's
	 * ships.
	 */
	private JPanel shipBoard = new JPanel();

	/**
	 * Panel used to hold the grid of JButtons used for playing Battleship.
	 */
	private JPanel gameBoard = new JPanel();

	/**
	 * Grid of JButtons used for the game board.
	 */
	private JButton[][] grid;

	/**
	 * Double array used to store the locations of the player's ships.
	 */
	private ShipEnum[][] shipPlacement = new ShipEnum[PlayerInterface.NUMBERS.length][PlayerInterface.LETTERS.length];

	/**
	 * Double array used to store whether the chosen button was a hit or a miss.
	 */
	private boolean[][] hitOrMiss = new boolean[PlayerInterface.NUMBERS.length][PlayerInterface.LETTERS.length];

	/**
	 * 
	 */
	// private int x = 0, y = 0, i = 0, j = 0;

	/**
	 * Integer used to keep track of how many ships the player has left.
	 */
	private int shipsLeft;

	/**
	 * Integer used to keep track of how many shots the player has taken. Could
	 * be used for statistical purposes.
	 */
	private int shotsTaken;

	/**
	 * Integer used to keep track of how many hits the player has taken. Could
	 * be used for statistical purposes.
	 */
	private int hitsTaken;

	/**
	 * String holding the player's name.
	 */
	private String name = "Player";

	/**
	 * Array holding the player's ships.
	 */
	private ShipInterface[] ships;

	/**
	 * Integer used to keep track of how many ships the player has placed on the
	 * input board in preparation of the game.
	 */
	private int shipsPlaced = 0;

	/**
	 * Boolean value that keeps track of whether the player is ready to deploy
	 * their ships.
	 */
	private boolean deploy = false;

	/**
	 * 'Active ship' used when placing ships on the input board. Default value
	 * corresponds to the default of the ships combo box.
	 */
	private ShipEnum activeShip = ShipEnum.CARRIER;

	/**
	 * Constructor used to set up the player's ships array, and number of shifts
	 * left.
	 * 
	 * @requires true
	 * @ensures shipsLeft = battleshipmodelinterface.numships and ships[0] =
	 *          destroyer, ships[1] = submarine, ships[2] = cruiser, ships[3] =
	 *          battleship, ships[4] = carrier
	 */
	public Player() {
		shipsLeft = BattleshipModelInterface.NUMSHIPS;
		ships = new ShipInterface[BattleshipModelInterface.NUMSHIPS];

		ships[BattleshipModelInterface.DESTINDEX] = new Ship(ShipEnum.DESTROYER);
		ships[BattleshipModelInterface.SUBMINDEX] = new Ship(ShipEnum.SUBMARINE);
		ships[BattleshipModelInterface.CRUSINDEX] = new Ship(ShipEnum.CRUISER);
		ships[BattleshipModelInterface.BATTINDEX] = new Ship(
				ShipEnum.BATTLESHIP);
		ships[BattleshipModelInterface.CARRINDEX] = new Ship(ShipEnum.CARRIER);

	}

	/**
	 * Constructor used to set up the player's ships array, and number of shifts
	 * left. Also, sets the player's name to the passed in string.
	 * 
	 * @param n
	 *            name of the player
	 * 
	 * @requires true
	 * @ensures shipsLeft = battleshipmodelinterface.numships and ships[0] =
	 *          destroyer, ships[1] = submarine, ships[2] = cruiser, ships[3] =
	 *          battleship, ships[4] = carrier, and player.name = n
	 */
	public Player(String n) {
		name = n;
		shipsLeft = BattleshipModelInterface.NUMSHIPS;
		ships = new ShipInterface[BattleshipModelInterface.NUMSHIPS];

		ships[BattleshipModelInterface.DESTINDEX] = new Ship(ShipEnum.DESTROYER);
		ships[BattleshipModelInterface.SUBMINDEX] = new Ship(ShipEnum.SUBMARINE);
		ships[BattleshipModelInterface.CRUSINDEX] = new Ship(ShipEnum.CRUISER);
		ships[BattleshipModelInterface.BATTINDEX] = new Ship(
				ShipEnum.BATTLESHIP);
		ships[BattleshipModelInterface.CARRINDEX] = new Ship(ShipEnum.CARRIER);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setShipInputBoard() {
		shipBoard.setLayout(new GridLayout(PlayerInterface.LETTERS.length,
				PlayerInterface.NUMBERS.length));
		grid = new JButton[PlayerInterface.LETTERS.length][PlayerInterface.NUMBERS.length];
		for (int y = 0; y < PlayerInterface.LETTERS.length; y++) {

			for (int x = 0; x < PlayerInterface.NUMBERS.length; x++) {

				if (x != 0 && y != 0) {
					grid[x][y] = new JButton();
					shipBoard.add(grid[x][y]);
					grid[x][y].addActionListener(new PlacementButton());
				}
				if (x == 0) {
					if (y != 0) {
						JTextField t = new JTextField(NUMBERS[y]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						shipBoard.add(t);
					} else {
						JTextField t = new JTextField();
						t.setEditable(false);
						shipBoard.add(t);
					}
				} else if (y == 0) {
					JTextField t = new JTextField(LETTERS[x]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					shipBoard.add(t);
				}
			}
		}
		// shipBoard.setSize(250, 250);
		shipBoard.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JPanel getShipInputBoard() {
		return shipBoard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setGameBoard() {
		gameBoard.setLayout(new GridLayout(PlayerInterface.LETTERS.length,
				PlayerInterface.NUMBERS.length));
		grid = new JButton[PlayerInterface.LETTERS.length][PlayerInterface.NUMBERS.length];
		for (int j = 0; j < PlayerInterface.LETTERS.length; j++) {

			for (int i = 0; i < PlayerInterface.NUMBERS.length; i++) {

				if (i != 0 && j != 0) {
					grid[i][j] = new JButton();
					gameBoard.add(grid[i][j]);
					grid[i][j].addActionListener(new BoardButton());
				}
				if (i == 0) {
					if (j != 0) {
						JTextField t = new JTextField(NUMBERS[j]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						gameBoard.add(t);
					} else {
						JTextField t = new JTextField();
						t.setEditable(false);
						gameBoard.add(t);
					}
				} else if (j == 0) {
					JTextField t = new JTextField(LETTERS[i]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					gameBoard.add(t);
				}
			}
		}
		// gameBoard.setSize(250, 250);
		gameBoard.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JPanel getGameBoard() {
		return gameBoard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void placeShip(ShipEnum s, Direction d, int startX, int startY) {

		int length = 0;
		int index = 0;

		switch (s) {
		case CARRIER: {
			length = ShipInterface.CARRIERLENGTH;
			index = BattleshipModel.CARRINDEX;
			break;
		}
		case BATTLESHIP: {
			length = ShipInterface.BATTLESHIPLENGTH;
			index = BattleshipModel.BATTINDEX;
			break;
		}
		case CRUISER: {
			length = ShipInterface.CRUISERLENGTH;
			index = BattleshipModel.CRUSINDEX;
			break;
		}
		case SUBMARINE: {
			length = ShipInterface.SUBMARINELENGTH;
			index = BattleshipModel.SUBMINDEX;
			break;
		}
		case DESTROYER: {
			length = ShipInterface.DESTROYERLENGTH;
			index = BattleshipModel.DESTINDEX;
			break;
		}
		default: {

		}
		}

		switch (d) {
		case VERTICAL: {
			for (int k = 0; k < length; k++) {
				shipPlacement[startX][startY + k] = s;
				grid[startX][startY + k].setBackground(Color.ORANGE);
			}
			break;
		}
		case HORIZONTAL: {
			for (int k = 0; k < length; k++) {
				shipPlacement[startX + k][startY] = s;
				grid[startX + k][startY].setBackground(Color.ORANGE);
			}
			break;
		}
		default: {

		}
		}

		ships[index].place();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void clearShips() {
		ships[BattleshipModel.CARRINDEX].unPlace();
		ships[BattleshipModel.BATTINDEX].unPlace();
		ships[BattleshipModel.CRUSINDEX].unPlace();
		ships[BattleshipModel.SUBMINDEX].unPlace();
		ships[BattleshipModel.DESTINDEX].unPlace();

		shipsPlaced = 0;

		for (int j = 0; j < Player.LETTERS.length; j++) {
			for (int i = 0; i < Player.NUMBERS.length; i++) {
				shipPlacement[i][j] = null;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ShipEnum getShip(int startX, int startY) {
		return shipPlacement[startX][startY];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setHitOrMiss() {
		// True => hit, false => miss
		for (int j = 0; j < PlayerInterface.LETTERS.length; j++) {
			for (int i = 0; i < PlayerInterface.NUMBERS.length; i++) {
				hitOrMiss[i][j] = (shipPlacement[i][j] != null);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean getHitOrMiss(int startX, int startY) {
		return hitOrMiss[startX][startY];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getShipsLeft() {
		return shipsLeft;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getShotsTaken() {
		return shotsTaken;
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
	public final void setName(String n) {
		name = n;
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
	public final ShipInterface[] getShips() {
		return ships;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getShipsPlaced() {
		return shipsPlaced;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void incShipsPlaced() {
		shipsPlaced++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean getDeploy() {
		deploy = false;
		if (shipsPlaced >= BattleshipModelInterface.NUMSHIPS) {
			deploy = true;
		}
		return deploy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setActiveShip(ShipEnum aS) {
		activeShip = aS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ShipEnum getActiveShip() {
		return activeShip;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JButton getGrid(int a, int b) {
		return grid[a][b];
	}

	/**
	 * ActionListener that responds when a player places a ship on the input
	 * board. If ship fits on the board and there isn't already a ship there
	 * then the board is placed, else a dialog box pops up telling the player
	 * why the ship cannot be placed in that spot.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class PlacementButton implements ActionListener {

		/**
		 * Responds to a player's attempt to place a ship on the input board.
		 * 
		 * @param e
		 *            ActionEvent that corresponds to a player attempting to
		 *            place a ship on the input board
		 * 
		 * @requires grid != null, shipPlacement != null
		 * @ensures if the ship can be placed at the selected spot then it is,
		 *          else a dialog box pops up saying why it cannot
		 */
		@Override
		public final void actionPerformed(ActionEvent e) {
			// First determine which button was pressed
			Object source = (JButton) e.getSource();
			int i = 0, j = 0;
			for (int b = 1; b < PlayerInterface.LETTERS.length; b++) {
				for (int a = 1; a < PlayerInterface.NUMBERS.length; a++) {
					if (source == getGrid(a, b)) {
						i = a;
						j = b;
						a = PlayerInterface.NUMBERS.length + 1;
						b = PlayerInterface.LETTERS.length + 1;
					}
				}
			}

			// Then determine the index of the active ship
			int index = 0;

			switch (activeShip) {
			case CARRIER: {
				index = BattleshipModel.CARRINDEX;
				break;
			}
			case BATTLESHIP: {
				index = BattleshipModel.BATTINDEX;
				break;
			}
			case CRUISER: {
				index = BattleshipModel.CRUSINDEX;
				break;
			}
			case SUBMARINE: {
				index = BattleshipModel.SUBMINDEX;
				break;
			}
			case DESTROYER: {
				index = BattleshipModel.DESTINDEX;
				break;
			}
			default: {

			}
			}

			// Now, if the ship hasn't already been placed, then try and place
			// it at the selected location.
			if (!ships[index].isPlaced()) {
				Direction d = ships[index].getDirection();
				switch (d) {
				// Horizontal case
				case HORIZONTAL: {
					// If it is too long to be placed then notify the user
					if ((ships[index].getLength() + i) > shipPlacement.length) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Sorry, a "
								+ ships[index].getName()
								+ " can't be placed here, its too long.");
					} else {
						boolean test = false;
						for (int k = 0; k < ships[index].getLength(); k++) {
							if (shipPlacement[i + k][j] != null) {
								test = true;
							}
						}
						// Else, if there is a ship already there, then notify
						// the user
						if (test) {
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(
									frame,
									"Sorry, you can't put a "
											+ ships[index].getName()
											+ " there because there is"
											+ " already a ship there!");
						} else {
							// Finally, if the ship is not too long and there is
							// no ship there yet then place the ship
							placeShip(activeShip, ships[index].getDirection(),
									i, j);
							shipsPlaced++;
							// If, all the player's hips have been placed then
							// notify them that it is time to deploy.
							if (shipsPlaced >= BattleshipModelInterface.NUMSHIPS) {
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame,
										"All your ships have "
												+ "been placed. Deploy now!");
							}
						}
					}
					break;
				}
				// Vertical case
				case VERTICAL: {
					// If it is too long to be placed then notify the user
					if ((ships[index].getLength() + j) > shipPlacement.length) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Sorry, a "
								+ ships[index].getName()
								+ " can't be placed here.");
					} else {
						boolean test = false;
						for (int k = 0; k < ships[index].getLength(); k++) {
							if (shipPlacement[i][j + k] != null) {
								test = true;
							}
						}
						// Else, if there is a ship already there, then notify
						// the user
						if (test) {
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(
									frame,
									"Sorry, you can't put a "
											+ ships[index].getName()
											+ " there because there is "
											+ "already a ship there!");
						} else {
							// Finally, if the ship is not too long and there is
							// no ship there yet then place the ship
							placeShip(activeShip, ships[index].getDirection(),
									i, j);
							shipsPlaced++;
							// If, all the player's hips have been placed then
							// notify them that it is time to deploy.
							if (shipsPlaced >= BattleshipModelInterface.NUMSHIPS) {
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame,
										"All your ships have "
												+ "been placed. Deploy now!");
							}
						}
					}
					break;
				}
				default: {

				}
				}
			}
		}
	}

	/**
	 * ActionListener used to respond to the main game board. Used to determine
	 * whether the intended target of an 'attack' is a hot or miss. Depending on
	 * whether it is a hit or miss, changes the color of the button. If it is
	 * hit then it changes to red, else it changes to black.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class BoardButton implements ActionListener {

		/**
		 * Responds to a player's attempt to 'attack' a player's game board.
		 * 
		 * @param e
		 *            ActionEvent that corresponds to an 'attack'
		 * 
		 * @requires grid != null, shipPlacement != null, hitOrMiss != null
		 * @ensures the color of the grid is changed to signal whether a hit was
		 *          made (red) or a miss (black). Also, if a hit was made,
		 *          increments the amount of hits the associated ship has taken.
		 *          Notifies the player if a certain ship has been sunk, and if
		 *          all ships have been sunk then ends the game.
		 */
		@Override
		public final void actionPerformed(ActionEvent e) {
			// First find the button on the grid associated with the 'attack'
			Object source = (JButton) e.getSource();
			int i = 1, j = 1;
			for (int b = 1; b < PlayerInterface.LETTERS.length; b++) {
				for (int a = 1; a < PlayerInterface.NUMBERS.length; a++) {
					if (source == getGrid(a, b)) {
						i = a;
						j = b;
						a = PlayerInterface.NUMBERS.length + 1;
						b = PlayerInterface.LETTERS.length + 1;
					}
				}
			}

			// Then if there is a ship there, determine which ship, and
			// increment how many times it has been hit
			if (hitOrMiss[i][j]) {
				int index = 0;
				getGrid(i, j).setBackground(Color.RED);
				activeShip = shipPlacement[i][j];

				switch (activeShip) {
				case CARRIER: {
					index = BattleshipModel.CARRINDEX;
					break;
				}
				case BATTLESHIP: {
					index = BattleshipModel.BATTINDEX;
					break;
				}
				case CRUISER: {
					index = BattleshipModel.CRUSINDEX;
					break;
				}
				case SUBMARINE: {
					index = BattleshipModel.SUBMINDEX;
					break;
				}
				case DESTROYER: {
					index = BattleshipModel.DESTINDEX;
					break;
				}
				default: {

				}
				}

				ships[index].incHitsTaken();

				// If it has been sunk, then decrement the number of remaining
				// ships, and notify the player
				if (ships[index].isSunk()) {
					shipsLeft--;
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, name + "'s "
							+ ships[index].getName() + " has been sunk!");
				}

				// If a Player has no remaining ships then the game is over
				if (shipsLeft == 0) {
					BattleshipController.gameOver();
				}
				hitOrMiss[i][j] = false;
			} else {
				// Else, the attack is a miss
				getGrid(i, j).setBackground(Color.BLACK);
			}
		}
	}
}
