package kz.kbtu.battleship;

/**
 * Enumeration for direction in order to use switch statements rather then if
 * statements in the implementation since switch statements are better for
 * performance.
 * 
 * @author Nathan Newman
 * 
 */
public enum Direction {

	/**
	 * Corresponds to the vertical axis.
	 */
	VERTICAL,

	/**
	 * Corresponds to the horizontal.
	 */
	HORIZONTAL;
}
