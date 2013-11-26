package bloodbowl.model.map;

/**
 * Class for Pitch cell, a subtype of Cell
 * PitchCells can hold the player and the ball both
 * @author Rishinder
 */
public class PitchCell extends Cell {

    private boolean containsBall;
    /************************Constants***********************/
    public static final int END_ZONE = 1, WIDE_ZONE_UPPER = 4,
            WIDE_ZONE_LOWER = 5, NORMAL_ZONE = 2, SCRIMMAGE_ZONE = 3;

    /********************************************************/
    /**
     * Constructor for pitch cell
     * @param rowID Row Number
     * @param columnID Column Number
     */
    public PitchCell(int rowID, int columnID) {
        super(rowID, columnID);

        //Set zones for the left half of pitch
        if (columnID >= 0 && columnID <= 13) {
            if (columnID == 0) {
                zone = END_ZONE;
            } else if (rowID >= 0 && rowID <= 3) {
                zone = WIDE_ZONE_UPPER;
            } else if (rowID >= 11 && rowID <= 14) {
                zone = WIDE_ZONE_LOWER;
            } else if (rowID >= 4 && rowID <= 10 && columnID < 12) {
                zone = NORMAL_ZONE;
            } else {
                zone = SCRIMMAGE_ZONE;
            }
        }

        //Set zones for the right half of pitch
        if (columnID >= 14 && columnID <= 25) {
            if (columnID == 25) {
                zone = END_ZONE;
            } else if (rowID >= 0 && rowID <= 3) {
                zone = WIDE_ZONE_UPPER;
            } else if (rowID >= 11 && rowID <= 14) {
                zone = WIDE_ZONE_LOWER;
            } else if (rowID >= 4 && rowID <= 10 && columnID > 13) {
                zone = NORMAL_ZONE;
            } else {
                zone = SCRIMMAGE_ZONE;
            }
        }
    }

    /**
     * Method for setting the contains player flag to true.
     */
    public void setBall() {
        containsBall = true;
    }

    /**
     * Method for resetting the contains ball flag to false
     */
    public void resetBall() {
        containsBall = false;
    }

    /**
     * Getter for getting the value for contains ball flag
     * @return contains ball flag
     */
    public boolean hasBall() {
        return containsBall;
    }

    public void printStatus() {
        if (containsBall) {
            System.out.print("B ");
        } else if (containsPlayer) {
            System.out.print("X ");
        } else {
            if (zone == 1) {
                System.out.print("O ");
            } else if (zone == 2) {
                System.out.print("@ ");
            } else if (zone == 3) {
                System.out.print("* ");
            } else {
                System.out.print("Q ");
            }
        }
    }
}
