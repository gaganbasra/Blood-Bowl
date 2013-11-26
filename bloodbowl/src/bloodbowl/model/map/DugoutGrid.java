package bloodbowl.model.map;

import java.io.Serializable;

/**
 * Container Class for holding Dugout Cells
 * @author Risshinder
 */
public class DugoutGrid implements Serializable
{
    private static final int ROW=12;
    private static final int COLUMN=4;

    public DugoutCell[][] dugout;               //Dugout Grid contains 2D grid pitch-cells

    /**
     * Constructor which creates all the dugout cells for each dugout.
     */
    public DugoutGrid()
    {
        dugout=new DugoutCell[ROW][COLUMN];           //Grid of 4 rows and 12 columns of pitch cells.
        for(int i=0;i<ROW;i++)
        {
            for(int j=0;j<COLUMN;j++)
            {
                dugout[i][j]=new DugoutCell(i,j);
            }
        }
    }

    /**
     * Method to draw the whole dugout with players and zone distinction.
     * Only for debugging in console.
     */
    public void drawGrid()
    {
        for(int i=0;i<ROW;i++)			//Loop for creating new objects of all those pitch-cells
        {
            for(int j=0;j<COLUMN;j++)
            {
                dugout[i][j].printStatus();
            }
            System.out.println();
        }
    }
    
    public Cell getCell(int i, int j)
    {
            return dugout[i][j];
    }
}
