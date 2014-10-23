package kz.kbtu.battleship;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * The View side of the Battleship game, based on the MVC model. Establishes the
 * frames necessary for holding the various game boards. Also, sets up any
 * necessary menu bars, combo boxes, buttons, and provides methods to add action
 * listeners to those components.
 * 
 * @author Nathan Newman
 * 
 * @convention
 * @correspondence Main GUI of the Battleship game
 * 
 */
public class BattleshipView implements BattleshipViewInterface {

	/**
	 * Frame used to hold the ship input panel for player 1.
	 */
	private JFrame frame1;

	/**
	 * Frame used to hold the ship input panel for player 2.
	 */
	private JFrame frame2;

	/**
	 * Frame used to hold the main game board.
	 */
	private JFrame mainFrame;

	/**
	 * String array containing the names of the ships used for the drop down box
	 * on the ship input panel.
	 */
	private static String[] ships = { "Carrier", "Battleship", "Cruiser",
			"Submarine", "Destroyer" };

	/**
	 * String containing the directions used for the drop down box on the ship
	 * input panel.
	 */
	private static String[] directions = { "Vertical", "Horizontal" };

	/**
	 * Combo Box used to hold the selection of ships for the input boards.
	 */
	private JComboBox shipChoice;

	/**
	 * Combo Box used to hold the selection of directions for the input boards.
	 */
	private JComboBox dirChoice;

	/**
	 * Deploy button for the input boards.
	 */
	private JButton deploy;

	/**
	 * Clear button for the input boards.
	 */
	private JButton clear;

	/**
	 * Player vs Player menu option for the menu bar.
	 */
	private JMenuItem pvp;

	/**
	 * Constructor that instantiates the three frames used for the main views.
	 */
	public BattleshipView() {
		frame1 = new JFrame();
		frame2 = new JFrame();
		mainFrame = new JFrame("Battleship!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setShipInputFrame1() {
		frame1.setLayout(new GridLayout());

		JPanel inputPanel = new JPanel();
		JTextField tField = new JTextField();
		tField.setText("Choose a ship and its direction");
		tField.setEditable(false);
		inputPanel.add(tField);

		shipChoice = new JComboBox(ships);
		shipChoice.setSelectedIndex(0);
		// shipChoice.addActionListener(new ShipsListener());

		TitledBorder tBorder;
		tBorder = BorderFactory.createTitledBorder("Ships");
		shipChoice.setBorder(tBorder);
		inputPanel.add(shipChoice);

		dirChoice = new JComboBox(directions);
		dirChoice.setSelectedIndex(0);
		// dirChoice.addActionListener(new DirectionListener());

		tBorder = BorderFactory.createTitledBorder("Direction");
		dirChoice.setBorder(tBorder);
		inputPanel.add(dirChoice);

		deploy = new JButton("Deploy");
		deploy.setEnabled(true);
		// deploy.addActionListener(new DeployListener());
		inputPanel.add(deploy);

		clear = new JButton("Clear");
		deploy.setEnabled(true);
		// clear.addActionListener(new ClearListener());
		inputPanel.add(clear);

		frame1.add(inputPanel);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.pack();
		// frame1.setSize(500, 250);
		frame1.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JFrame getShipInputFrame1() {
		return frame1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setShipInputFrame2() {
		frame2.setLayout(new GridLayout());

		JPanel inputPanel = new JPanel();
		JTextField tField = new JTextField();
		tField.setText("Choose a ship and its direction");
		tField.setEditable(false);
		inputPanel.add(tField);

		shipChoice = new JComboBox(ships);
		shipChoice.setSelectedIndex(0);
		// shipChoice.addActionListener(new ShipsListener());

		TitledBorder tBorder;
		tBorder = BorderFactory.createTitledBorder("Ships");
		shipChoice.setBorder(tBorder);
		inputPanel.add(shipChoice);

		dirChoice = new JComboBox(directions);
		dirChoice.setSelectedIndex(0);
		// dirChoice.addActionListener(new DirectionListener());

		tBorder = BorderFactory.createTitledBorder("Direction");
		dirChoice.setBorder(tBorder);
		inputPanel.add(dirChoice);

		deploy = new JButton("Deploy");
		deploy.setEnabled(true);
		// deploy.addActionListener(new DeployListener());
		inputPanel.add(deploy);

		clear = new JButton("Clear");
		deploy.setEnabled(true);
		// clear.addActionListener(new ClearListener());
		inputPanel.add(clear);

		frame2.add(inputPanel);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.pack();
		// frame2.setSize(500, 250);
		frame2.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JFrame getShipInputFrame2() {
		return frame2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMainFrame() {
		mainFrame.setLayout(new GridLayout());

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menuBar.add(menu);
		JMenuItem m = new JMenu("New Game");
		menu.add(m);
		pvp = new JMenuItem("Player vs Player");
		// pvp.addActionListener(new MenuListener());
		m.add(pvp);

		mainFrame.setJMenuBar(menuBar);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		// mainFrame.setSize(750, 500);
		mainFrame.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName1() {
		JFrame frame = new JFrame();
		String s = (String) JOptionPane.showInputDialog(frame,
				"Please enter the name of Player 1:", "Input Name",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		return s;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName2() {
		JFrame frame = new JFrame();
		String s = (String) JOptionPane.showInputDialog(frame,
				"Please enter the name of Player 2:", "Input Name",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		return s;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void inputPrompt(int i, PlayerInterface[] p) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,
				"Please look away while " + p[i].getName()
						+ " places their ships.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void beginGamePrompt() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Get ready to play BATTLESHIP!!!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JComboBox getShipComboBox() {
		return shipChoice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JComboBox getDirComboBox() {
		return dirChoice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addShipsListener(ActionListener shal) {
		shipChoice.addActionListener(shal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addDirectionListener(ActionListener diral) {
		dirChoice.addActionListener(diral);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addMenuListener(ActionListener mal) {
		pvp.addActionListener(mal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addDeployListener(ActionListener dal) {
		deploy.addActionListener(dal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addClearListener(ActionListener cal) {
		clear.addActionListener(cal);
	}

}
