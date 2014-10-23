package kz.kbtu.battleship;

/**
 * Utility class used to run the Battleship game. Establishes a new Model, new
 * View, and new Controller with a reference to the model and view.
 * 
 * @author Nathan Newman
 * 
 */
public final class BattleshipMVC {

	/**
	 * Private constructor to ensure that this class cannot be instantiated.
	 */
	private BattleshipMVC() {

	}

	/**
	 * Main method, runs the program.
	 * 
	 * @param args
	 *            command line arguments, not needed for this program.
	 */
	public static void main(String[] args) {
		BattleshipModelInterface m = new BattleshipModel();
		BattleshipViewInterface v = new BattleshipView();
		new BattleshipController(m, v);
	}

}

// Sunday, November 28th: Continued tweaking the MVC idea...Got the code to
// create three different frames: 1 for player one's ship input, 1 for player
// two's ship input, and 1 for playing the game. Now obviously we wouldn't want
// all three of these frames open at once so we need to work on getting the user
// to interact with each different frame. Also need to work on placing ships. I
// will work on this more later, and I will start adding documentation soon.
// Finally, the code hasn't been checked using findbugs or checkstyle. -Nathan

// Monday, November 29th: Added a lot of javadoc comments and extracted some
// interfaces. Also, added dialog boxes for inputting names of players and for
// prompting the users to look away when it is time to place ships. -Nathan

// Tuesday, November 30th: Played around with the action listeners and got them
// into "position" in the Controller class, and configured the View class to
// have the right methods to add them to the appropriate buttons/comboboxes.
// -Nathan

// Wednesday, December 1st: IT WORKS!!! There are a few kinks to work out, and a
// lot of documentation is needed as well as JUnit test cases for the Model
// (Model/Player/Ship classes) but it works. Whn you update/chek out, just try
// running it and see how it feels (Oh and it needs to be checked with findbugs
// and checkstyle)... -Nathan
