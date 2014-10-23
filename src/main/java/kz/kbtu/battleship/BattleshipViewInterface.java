package kz.kbtu.battleship;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * The View side of the Battleship game, based on the MVC model. Establishes the
 * frames necessary for holding the various game boards. Also, sets up any
 * necessary menu bars, combo boxes, buttons, and provides methods to add action
 * listeners to those components.
 * 
 * @author Nathan Newman
 * 
 */
public interface BattleshipViewInterface {

	/**
	 * Index of the carrier in the ships combo box.
	 */
	int CBOXCARRINDEX = 0;

	/**
	 * Index of the battleship in the ships combo box.
	 */
	int CBOXBATTINDEX = 1;

	/**
	 * Index of the cruiser in the ships combo box.
	 */
	int CBOXCRUSINDEX = 2;

	/**
	 * Index of the submarine in the ships combo box.
	 */
	int CBOXSUBMINDEX = 3;

	/**
	 * Index of the destroyer in the ships combo box.
	 */
	int CBOXDESTINDEX = 4;

	/**
	 * Sets the ship input frame for player 1. Creates two drop down boxes to
	 * allow for ship and direction choices. Has a "deploy" button that can be
	 * clicked when (only when) the player has placed all their ships.
	 * 
	 * @requires frame1 is instantiated
	 * @ensures frame1 is set up so player 1 can place their ships
	 */
	void setShipInputFrame1();

	/**
	 * Returns a reference to player 1's input frame.
	 * 
	 * @return a reference to frame1
	 */
	JFrame getShipInputFrame1();

	/**
	 * Sets the ship input frame for player 2. Creates two drop down boxes to
	 * allow for ship and direction choices. Has a "deploy" button that can be
	 * clicked when (only when) the player has placed all their ships.
	 * 
	 * @requires frame2 is instantiated
	 * @ensures frame2 is set up so player 2 can place their ships
	 */
	void setShipInputFrame2();

	/**
	 * Returns a reference to player 2's input frame.
	 * 
	 * @return a reference to frame2
	 */
	JFrame getShipInputFrame2();

	/**
	 * Sets up the main frame with a menu bar. Readies the frame to have two
	 * game boards added.
	 * 
	 * @requires mainFrame is instantiated
	 * @ensures mainFrame has a working menu bar and is ready for the game
	 *          boards to be added.
	 */
	void setMainFrame();

	/**
	 * Returns a reference to the main frame.
	 * 
	 * @return a reference to mainFrame.
	 */
	JFrame getMainFrame();

	/**
	 * Prompts the user to input the name of Player 1 and returns the name
	 * inputed.
	 * 
	 * @return name inputed by the user.
	 */
	String getName1();

	/**
	 * Prompts the user to input the name of Player 2 and returns the name
	 * inputed.
	 * 
	 * @return name inputed by the user.
	 */
	String getName2();

	/**
	 * Prompts the players before it is time to input ships so the appropriate
	 * player can look away.
	 * 
	 * @param i
	 *            index of the player whose turn it is to input ships.
	 * @param p
	 *            array of players used to get the name of the player whose turn
	 *            it is to input ships.
	 * 
	 * @requires 0 <= i <= 1, p != null
	 * @ensures a dialog box pops up saying
	 *          "Please look away while p[i].getName() places their ships."
	 */
	void inputPrompt(int i, PlayerInterface[] p);

	/**
	 * Prompts the player that game is about to begin.
	 * 
	 * @requires true
	 * @ensures a dialog box pops up saying "Get ready to play BATTLESHIP!!!"
	 */
	void beginGamePrompt();

	/**
	 * Returns a reference to the ships combo box.
	 * 
	 * @return a reference to the shipsComboBox
	 * 
	 * @requires true
	 * @ensures the value returned is a combo box containing the choices of
	 *          ships for the player's input board.
	 */
	JComboBox<?> getShipComboBox();

	/**
	 * Returns a reference to the direction combo box.
	 * 
	 * @return a reference to the dirComboBox
	 * 
	 * @requires true
	 * @ensures the value returned is a combo box containing the choices of
	 *          directions for the ships for the player's input board.
	 */
	JComboBox<?> getDirComboBox();

	/**
	 * Adds the passed in ActionListener to the ships combo box.
	 * 
	 * @param shal
	 *            a shipsActionListener to be added to the ships combo box
	 * 
	 * @requires shipsComboBox != null
	 * @ensures shal -> shipsComboBox
	 */
	void addShipsListener(ActionListener shal);

	/**
	 * Adds the passed in ActionListener to the direction combo box.
	 * 
	 * @param diral
	 *            a directionActionListener to be added to the direction combo
	 *            box
	 * 
	 * @requires dirComboBox != null
	 * @ensures diral -> shipsComboBox
	 */
	void addDirectionListener(ActionListener diral);

	/**
	 * Adds the passed in ActionListener to the menu bar.
	 * 
	 * @param mal
	 *            a menuActionListener to be added to the menu bar
	 * 
	 * @requires menuBar != null
	 * @ensures mal -> menuBar
	 */
	void addMenuListener(ActionListener mal);

	/**
	 * Adds the passed in ActionListener to the deploy button.
	 * 
	 * @param dal
	 *            a deployActionListener to be added to the deploy button
	 * 
	 * @requires deploy != null
	 * @ensures dal -> deploy
	 */
	void addDeployListener(ActionListener dal);

	/**
	 * Adds the passed in ActionListener to the clear button.
	 * 
	 * @param cal
	 *            a clearActionListener to be added to the clear button
	 * 
	 * @requires clear != null
	 * @ensures cal -> clear
	 */
	void addClearListener(ActionListener cal);

}
