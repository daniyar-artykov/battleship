package kz.kbtu.battleship;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Controller side of the Battleship game, based on the MVC model. This class
 * takes in a reference to both the model and the view and uses the necessary
 * methods to put call the view components and put together the GUI in a way
 * that is easy for the players to work with. Also, updates and changes the
 * model in accordance to the various interactions the players have with the
 * GUI.
 * 
 * @author Nathan Newman
 * 
 * @convention
 * @correspondence A set of interactions the players have with the GUI and the
 *                 Model
 * 
 */
public class BattleshipController implements BattleshipControllerInterface {

	/**
	 * Reference to the BattleshipModel.
	 */
	private static BattleshipModelInterface mModel;

	/**
	 * Reference to the BattleshipView.
	 */
	private BattleshipViewInterface mView;

	/**
	 * Extra Frames used for holding various panels of the GUI.
	 */
	private JFrame a, b, c;

	/**
	 * Constructor that encompasses most of the work done by the Battleship
	 * game. Holds a reference to the model and the view of the Battleship game,
	 * and uses those references in order to access the methods necessary to
	 * bring the game together.
	 * 
	 * @param m
	 *            a reference to BattleshipModel
	 * @param v
	 *            a reference to BattleshipView
	 * 
	 * @requires m == null and v == null
	 * @ensures The game, Battleship, runs.
	 */
	public BattleshipController(BattleshipModelInterface m,
			BattleshipViewInterface v) {
		mModel = m;
		mView = v;

		// Get the names of the players
		mModel.getPlayers()[BattleshipModelInterface.PLAYER1].setName(mView
				.getName1());
		mModel.getPlayers()[BattleshipModelInterface.PLAYER2].setName(mView
				.getName2());

		// Prompt player 2 to look away while player 1 places their ships
		mView.inputPrompt(BattleshipModelInterface.PLAYER1, mModel.getPlayers());

		// Sets up player 1's ship input frame, adds necessary listeners
		mView.setShipInputFrame1();
		mView.addDeployListener(new DeployListener1());
		mView.addShipsListener(new ShipsListener1());
		mView.addDirectionListener(new DirectionListener1());
		mView.addClearListener(new ClearListener1());

		// Adds player 1's input panel to the frame so that he/she can place
		// their ships
		b = mView.getShipInputFrame1();
		mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
				.setShipInputBoard();
		b.add(mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
				.getShipInputBoard());
		b.pack();

	}

	/**
	 * Handles the end of the game situation. Displays a dialog box with the
	 * name of the winner, and colors the winner's ships that have not been hit
	 * yet blue.
	 */
	public final static void gameOver() {
		// Game is over! Determine the winner
		PlayerInterface[] players = mModel.getPlayers();
		int winner = mModel.winner();

		// Show the remaining ships

		for (int b = 1; b < PlayerInterface.LETTERS.length; b++) {
			for (int a = 1; a < PlayerInterface.NUMBERS.length; a++) {
				if (players[winner].getHitOrMiss(a, b) == true
						&& players[winner].getGrid(a, b).getBackground() != Color.RED) {
					players[winner].getGrid(a, b).setBackground(Color.BLUE);
				}

			}
		}

		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "The game is over! "
				+ players[winner].getName() + " wins. Good bye!");
		System.exit(0);
	}

	/**
	 * Action listener that responds when the deploy button of player 1's input
	 * board is pressed. After player 1 presses their deploy button then the
	 * controller moves onto player 2's input board.
	 * 
	 * @author Nathan Newman
	 * @author Miguel Ibarra
	 * @author Zachary Smith
	 * 
	 */
	private class DeployListener1 implements ActionListener {

		/**
		 * Responds to player 1's deploy button by progressing the game onto
		 * player 2's input board.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the pressing of player 1's
		 *            deploy button
		 * 
		 * @requires the DeployListener1 is added to the deploy button and
		 *           player1.getDeploy() = true
		 * @ensures player 1's input frame is hidden and player 2's input frame
		 *          is then shown
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Sets up player 1's hit or miss double array to be used for game
			// play.
			mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
					.setHitOrMiss();

			// If player 1.getDeploy() == true then advance to the next part
			if (mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
					.getDeploy()) {
				// Hide player 1's input frame
				b.setVisible(false);
				// Prompt player 1 to look away while player 2 places their
				// ships
				mView.inputPrompt(BattleshipModelInterface.PLAYER2,
						mModel.getPlayers());

				// Sets up player 2's ship input frame, adds necessary listeners
				mView.setShipInputFrame2();
				mView.addDeployListener(new DeployListener2());
				mView.addShipsListener(new ShipsListener2());
				mView.addDirectionListener(new DirectionListener2());
				mView.addClearListener(new ClearListener2());

				// Adds player 2's input panel to the frame so that he/she can
				// place
				// their ships
				c = mView.getShipInputFrame2();
				mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
						.setShipInputBoard();
				c.add(mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
						.getShipInputBoard());
				c.pack();
			}
		}
	}

	/**
	 * Action listener that responds when the deploy button of player 2's input
	 * board is pressed. After player 2 presses their deploy button then the
	 * controller moves onto the main game board, and continues with playing the
	 * game.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class DeployListener2 implements ActionListener {

		/**
		 * Responds to player 2's deploy button by progressing the game onto the
		 * main game board.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the pressing of player 2's
		 *            deploy button
		 * 
		 * @requires that DeployListener2 was added to player 2's deploy button,
		 *           and player2.getDeploy() = true
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Sets up player 2's hit or miss double array to be used for game
			// play.
			mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
					.setHitOrMiss();

			// If player 2.getDeploy() = true then hide player 2's input board
			// and bring up the game board
			if (mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
					.getDeploy()) {
				// Hide player 2's input board
				c.setVisible(false);
				Random gen = new Random();
				int rand = gen.nextInt(2);

				// Prompts the players that the game is about to begin then
				// randomly chooses who will play first.
				mView.beginGamePrompt();
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						mModel.getPlayers()[rand].getName() + " goes first!");

				// Sets the player's game boards in preparation for the game.
				mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
						.setGameBoard();
				mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
						.setGameBoard();

				// Sets up the main frame according to the specs in the View,
				// and adds appropriate listeners
				mView.setMainFrame();
				mView.addMenuListener(new MenuListener());

				a = mView.getMainFrame();
				a.setTitle("Battleship: "
						+ mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
								.getName()
						+ "'s board is on the left and "
						+ mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
								.getName() + "'s board is on the right");
				// Adds the player's game boards to the main frame.
				a.add(mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
						.getGameBoard());
				a.add(mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
						.getGameBoard());

				a.pack();

			}
		}
	}

	/**
	 * ActionListener that responds when the clear on player 1's input board is
	 * pressed. Can be used to clear on placed ships in order to start over or
	 * rearrange placed ships.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class ClearListener1 implements ActionListener {

		/**
		 * Responds to player 1's clear button by clearing all of player 1's
		 * ships.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the event when player 1's
		 *            clear button is pressed
		 * 
		 * @requires true
		 * @ensures all placed ships are cleared
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Removes player 1's input panel from the input frame and clears
			// all ships
			mModel.getPlayers()[BattleshipModel.PLAYER1].getShipInputBoard()
					.removeAll();
			mModel.getPlayers()[BattleshipModel.PLAYER1].clearShips();

			// Reset player 1's input panel
			b = mView.getShipInputFrame1();
			mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
					.setShipInputBoard();
			b.add(mModel.getPlayers()[BattleshipModelInterface.PLAYER1]
					.getShipInputBoard());
			b.pack();
		}

	}

	/**
	 * ActionListener that responds when the clear on player 2's input board is
	 * pressed. Can be used to clear on placed ships in order to start over or
	 * rearrange placed ships.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class ClearListener2 implements ActionListener {

		/**
		 * Responds to player 2's clear button by clearing all of player 1's
		 * ships.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the event when player 1's
		 *            clear button is pressed
		 * 
		 * @requires true
		 * @ensures all placed ships are cleared
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			mModel.getPlayers()[BattleshipModel.PLAYER2].getShipInputBoard()
					.removeAll();
			mModel.getPlayers()[BattleshipModel.PLAYER2].clearShips();

			c = mView.getShipInputFrame2();
			mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
					.setShipInputBoard();
			c.add(mModel.getPlayers()[BattleshipModelInterface.PLAYER2]
					.getShipInputBoard());
			c.pack();
		}

	}

	/**
	 * ActionListener that responds to the direction combo box of player 1 and
	 * changes the direction of player 1's currently selected ship.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class DirectionListener1 implements ActionListener {

		/**
		 * Responds to player 1's direction combo box and alters the direction
		 * of player 1's currently selected ship.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the selection made in the
		 *            direction combo box.
		 * 
		 * @requires true
		 * @ensures currentship.direction = selected direction from the comb box
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the selected index of the combo box
			int dirIndex = mView.getDirComboBox().getSelectedIndex();
			System.out.println(dirIndex);
			Direction d = null;
			switch (dirIndex) {
			case 0: {
				d = Direction.VERTICAL;
				break;
			}
			case 1: {
				d = Direction.HORIZONTAL;
				break;
			}
			default: {

			}
			}
			// Figure out the index of the currently selected ship in the ships
			// array
			int i = 0;
			ShipEnum aS = mModel.getPlayers()[BattleshipModel.PLAYER1]
					.getActiveShip();
			switch (aS) {
			case CARRIER: {
				i = BattleshipModel.CARRINDEX;
				break;
			}
			case BATTLESHIP: {
				i = BattleshipModel.BATTINDEX;
				break;
			}
			case CRUISER: {
				i = BattleshipModel.CRUSINDEX;
				break;
			}
			case SUBMARINE: {
				i = BattleshipModel.SUBMINDEX;
				break;
			}
			case DESTROYER: {
				i = BattleshipModel.DESTINDEX;
				break;
			}
			default: {

			}
			}
			// Set the selected direction to the currently selected ship
			mModel.getPlayers()[BattleshipModel.PLAYER1].getShips()[i]
					.setDirection(d);
		}

	}

	/**
	 * ActionListener that responds to the direction combo box of player 2 and
	 * changes the direction of player 2's currently selected ship.
	 * 
	 * @author Nathan Newman
	 */
	private class DirectionListener2 implements ActionListener {

		/**
		 * Responds to player 2's direction combo box and alters the direction
		 * of player 2's currently selected ship.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the selection made in the
		 *            direction combo box.
		 * 
		 * @requires true
		 * @ensures currentship.direction = selected direction from the comb box
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the selected index of the combo box
			int dirIndex = mView.getDirComboBox().getSelectedIndex();
			Direction d = null;
			switch (dirIndex) {
			case 0: {
				d = Direction.VERTICAL;
				break;
			}
			case 1: {
				d = Direction.HORIZONTAL;
				break;
			}
			default: {

			}
			}
			// Figure out the index of the currently selected ship in the ships
			// array
			int i = 0;
			ShipEnum aS = mModel.getPlayers()[BattleshipModel.PLAYER2]
					.getActiveShip();
			switch (aS) {
			case CARRIER: {
				i = BattleshipModel.CARRINDEX;
				break;
			}
			case BATTLESHIP: {
				i = BattleshipModel.BATTINDEX;
				break;
			}
			case CRUISER: {
				i = BattleshipModel.CRUSINDEX;
				break;
			}
			case SUBMARINE: {
				i = BattleshipModel.SUBMINDEX;
				break;
			}
			case DESTROYER: {
				i = BattleshipModel.DESTINDEX;
				break;
			}
			default: {

			}
			}
			// Set the selected direction to the currently selected ship
			mModel.getPlayers()[BattleshipModel.PLAYER2].getShips()[i]
					.setDirection(d);
		}

	}

	/**
	 * ActionListener that responds when a choice in the menu bar is selected.
	 * Currently not used due to the design of the game.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private static class MenuListener implements ActionListener {

		/**
		 * Responds to the choice in the menu bar corresponding to beginning a
		 * new game.
		 * 
		 * @param e
		 *            ActionEvent corresponding to the various selections in the
		 *            menu bar
		 * 
		 * @requires the menu bar exists and is set up
		 * @ensures a new game is begun and all data is reset
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Not implemented, not needed right now
			// Due to the design of the game this method is not needed, but will
			// be used later in order to allow for the starting of a new game to
			// occur while the main frame is still open.
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame,
					"You pressed: " + e.getActionCommand());
		}

	}

	/**
	 * ActionListener that responds when a choice is made from the ships combo
	 * box. Sets the current (activeShip) of player 1 to the one selected from
	 * the box and sets it direction to whatever the selected direction of the
	 * direction combo box is.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class ShipsListener1 implements ActionListener {

		/**
		 * Responds to a choice made from the ship combo box of player 1.
		 * 
		 * @param e
		 *            ActionEvent that corresponds to the selection made from
		 *            the ship combo box of player 1
		 * 
		 * @requires true
		 * @ensures currentship = selected ship, and currentship.direction =
		 *          selected direction
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the index of the selected ship from the combo box
			int shipIndex = mView.getShipComboBox().getSelectedIndex();
			int i = shipIndex;
			ShipEnum aS = null;
			// Figure out which ship is associated with the index of the combo
			// box
			switch (shipIndex) {
			case BattleshipViewInterface.CBOXCARRINDEX: {
				i = BattleshipModel.CARRINDEX;
				aS = ShipEnum.CARRIER;
				break;
			}
			case BattleshipViewInterface.CBOXBATTINDEX: {
				i = BattleshipModel.BATTINDEX;
				aS = ShipEnum.BATTLESHIP;
				break;
			}
			case BattleshipViewInterface.CBOXCRUSINDEX: {
				i = BattleshipModel.CRUSINDEX;
				aS = ShipEnum.CRUISER;
				break;
			}
			case BattleshipViewInterface.CBOXSUBMINDEX: {
				i = BattleshipModel.SUBMINDEX;
				aS = ShipEnum.SUBMARINE;
				break;
			}
			case BattleshipViewInterface.CBOXDESTINDEX: {
				i = BattleshipModel.DESTINDEX;
				aS = ShipEnum.DESTROYER;
				break;
			}
			default: {

			}
			}
			// Figure out the current direction selected in the direction combo
			// box
			mModel.getPlayers()[BattleshipModel.PLAYER1].setActiveShip(aS);
			int dirIndex = mView.getDirComboBox().getSelectedIndex();
			Direction d = null;
			switch (dirIndex) {
			case 0: {
				d = Direction.VERTICAL;
				break;
			}
			case 1: {
				d = Direction.HORIZONTAL;
				break;
			}
			default: {

			}
			}
			// Set the currently selected ship's direction to the currently
			// selected direction
			mModel.getPlayers()[BattleshipModel.PLAYER1].getShips()[i]
					.setDirection(d);

		}

	}

	/**
	 * ActionListener that responds when a choice is made from the ships combo
	 * box. Sets the current (activeShip) of player 2 to the one selected from
	 * the box and sets it direction to whatever the selected direction of the
	 * direction combo box is.
	 * 
	 * @author Nathan Newman
	 * 
	 */
	private class ShipsListener2 implements ActionListener {

		/**
		 * Responds to a choice made from the ship combo box of player 2.
		 * 
		 * @param e
		 *            ActionEvent that corresponds to the selection made from
		 *            the ship combo box of player 2
		 * 
		 * @requires true
		 * @ensures currentship = selected ship, and currentship.direction =
		 *          selected direction
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the index of the selected ship from the combo box
			int shipIndex = mView.getShipComboBox().getSelectedIndex();
			int i = 0;
			ShipEnum aS = null;
			// Figure out which ship is associated with the index of the combo
			// box
			switch (shipIndex) {
			case BattleshipViewInterface.CBOXCARRINDEX: {
				i = BattleshipModel.CARRINDEX;
				aS = ShipEnum.CARRIER;
				break;
			}
			case BattleshipViewInterface.CBOXBATTINDEX: {
				i = BattleshipModel.BATTINDEX;
				aS = ShipEnum.BATTLESHIP;
				break;
			}
			case BattleshipViewInterface.CBOXCRUSINDEX: {
				i = BattleshipModel.CRUSINDEX;
				aS = ShipEnum.CRUISER;
				break;
			}
			case BattleshipViewInterface.CBOXSUBMINDEX: {
				i = BattleshipModel.SUBMINDEX;
				aS = ShipEnum.SUBMARINE;
				break;
			}
			case BattleshipViewInterface.CBOXDESTINDEX: {
				i = BattleshipModel.DESTINDEX;
				aS = ShipEnum.DESTROYER;
				break;
			}
			default: {

			}
			}
			// Figure out the current direction selected in the direction combo
			// box
			mModel.getPlayers()[BattleshipModel.PLAYER2].setActiveShip(aS);
			int dirIndex = mView.getDirComboBox().getSelectedIndex();
			Direction d = null;
			switch (dirIndex) {
			case 0: {
				d = Direction.VERTICAL;
				break;
			}
			case 1: {
				d = Direction.HORIZONTAL;
				break;
			}
			default: {

			}
			}
			// Set the currently selected ship's direction to the currently
			// selected direction
			mModel.getPlayers()[BattleshipModel.PLAYER2].getShips()[i]
					.setDirection(d);
		}

	}

}
