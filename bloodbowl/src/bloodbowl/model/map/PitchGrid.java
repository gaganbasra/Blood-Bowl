package bloodbowl.model.map;

import java.io.Serializable;

/**
 * Container Class for holding Pitch Cells
 * @author singh
 */
public class PitchGrid implements Serializable {

    private static final int ROW = 15;
    private static final int COLUMN = 26;
    public PitchCell[][] pitch;		//PitchGrid contains 2D grid pitch-cells

    /**
     * Constructor which created all the pitch cells in its call
     */
    public PitchGrid() {
        pitch = new PitchCell[ROW][COLUMN];	//Grid of 15 rows and 26 columns of pitch cells.
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                pitch[i][j] = new PitchCell(i, j);
            }
        }
    }

    /**
     * Method to draw the whole pitch with players and zone distinction.
     * Only for debugging in console.
     */
    public void drawGrid() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                pitch[i][j].printStatus();
            }
            System.out.println();
        }
    }

    public Cell getCell(int i, int j) {
        return pitch[i][j];
    }

    public PitchCell getPitchCell(int i, int j) {
        return pitch[i][j];
    }

    public boolean hasBall() {
        PitchCell cellBall = null;
        boolean ballPlaced = false;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                cellBall = pitch[i][j];
                if (cellBall.hasBall()) {
                    ballPlaced = cellBall.hasBall();
                    break;
                }

            }
        }

        return ballPlaced;
    }

    public PitchCell getBallAtPitch() {

        PitchCell cellBall = null;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                cellBall = pitch[i][j];
                if (cellBall.hasBall()) {                    
                    break;
                }

            }
        }

        return cellBall;
    }
}
