package bloodbowl.model.map;

import java.io.Serializable;

/**
 * Base class for PitchCell and DugoutCell
 * 
 * Holds common properties of the Pitch and Dug-out cells
 * @author Rishinder
 */
public abstract class Cell implements Serializable {

    int rowID, columnID;
    int zone;
    protected boolean containsPlayer;


    /**
     * Constructor
     * @param cellRow Row Number of the cell
     * @param cellCol Column Number of the cell
     */
    public Cell(int cellRow, int cellCol) {
        zone = 0;
        rowID = cellRow;
        columnID = cellCol;
        containsPlayer = false;
    }

    /**
     * Getter for getting column number of the cell in the respective grid
     * @return column ID
     */
    public int getColumn() {
        return columnID;
    }

    /**
     * Getter for getting row number of the cell in the respective grid
     * @return row ID
     */
    public int getRow() {
        return rowID;
    }

    /**
     * Getter for getting the zone in which the cell belongs to
     * @return zone
     */
    public int getZone() {
        return zone;
    }

    /**
     * Method for setting the contains player flag to true.
     */
    public void setPlayer() {
        containsPlayer = true;
    }

    /**
     * Method for resetting the contains player flag to false
     */
    public void resetPlayer() {
        containsPlayer = false;
    }

    /**
     * Getter for getting the value for contains player flag
     * @return contains player flag
     */
    public boolean hasPlayer() {
        return containsPlayer;
    }


    /**
     * Method for printing cell information for debugging on console.
     * Prints all players as 'X' irrespective of their types and teams.
     * Prints the cell containing the ball as B.
     * Prints for both grid and dugout.
     */
    public abstract void printStatus() ;

     /**
     * Overrides toString method to display the locations
      * of cell on the pitch or dugout
      *
      * return String
     */
    @Override
    public String toString() {
        return String.format("(%s, %s)", rowID, columnID);
    }


}
