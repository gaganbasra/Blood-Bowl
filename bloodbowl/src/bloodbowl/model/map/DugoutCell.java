package bloodbowl.model.map;

/**
 * Class for Dugout cell, a subtype of Cell
 * @author Rishinder
 */
public class DugoutCell extends Cell {

    /********************Constants************************/
    static final int RESERVED_ZONE = 1, INJURED_ZONE = 2, KNOCKED_ZONE = 3;

    /*****************************************************/
    /**
     * Constructor invokes the super class constructor.
     * @param rowID Row Number
     * @param columnID Column Number
     */
    public DugoutCell(int rowID, int columnID) {
        super(rowID, columnID);
        
        if (rowID >= 0 && rowID < 4) {
            zone = RESERVED_ZONE;		//Reserves
        } else if (rowID >= 4 && rowID < 8) {
            zone = INJURED_ZONE;		//Injured
        } else {
            zone = KNOCKED_ZONE;		//Knocked out
        }
    }

    public void setZone(int zoneType) {
        zone = zoneType;
    }

    public void printStatus() {
        if (containsPlayer) {
            System.out.print("X ");
        } else {
            if (zone == 1) {
                System.out.print("O ");
            } else if (zone == 2) {
                System.out.print("@ ");
            } else {
                System.out.print("* ");
            }
        }
    }
}
