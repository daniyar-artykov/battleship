package kz.kbtu.battleship;

/**
 * Enumerated values used to quickly determine ships in tandem with switch
 * statements. Used instead of possibly using a String/if statment method, since
 * switch statements are typically faster than if stamenets
 * 
 * @author Nathan Newman
 * 
 */
public enum ShipEnum {

	/**
	 * Destroyer.
	 */
	DESTROYER,

	/**
	 * Submarine.
	 */
	SUBMARINE,

	/**
	 * Cruiser.
	 */
	CRUISER,

	/**
	 * Battleship.
	 */
	BATTLESHIP,

	/**
	 * Carrier.
	 */
	CARRIER;
}
