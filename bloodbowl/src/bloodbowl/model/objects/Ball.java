/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.objects;

import bloodbowl.model.managers.DiceManager;
import bloodbowl.model.map.PitchGrid;
import bloodbowl.model.map.Cell;
import bloodbowl.model.map.PitchCell;
import bloodbowl.model.players.Player;
import java.io.Serializable;
import java.util.Observable;

/**
 * This class represents the ball used in the Game
 * @author Rishinder
 */
public class Ball extends Observable implements Serializable{
    Player possessor;
    PitchCell location;
    PitchGrid pitch;

    /**
     * Constructor to set the ball on the pitch
     * @param pitch The pitch reference in the Game
     */
    public Ball(PitchGrid pitch)
    {
        this.pitch=pitch;
    }

    public void refreshBallPosition()
    {
        setPossessor(possessor);
    }

    /**
     * Method to Place the Ball on the pitch cell (row,column)
     * @param row Row number of the destination cell
     * @param column Column number of the destination cell
     */
    public void setLocation(int row, int column)
    {
        if(location!=null)
        {
            location.resetBall();
        }
        location = pitch.getPitchCell(row, column);
        
        location.setBall();

        update();
    }

    /**
     * Getter for getting the location of the ball in the pitch
     * @return Location cell of the ball
     */
    public Cell getLocation()
    {
        return location;
    }

    /**
     * Method to set the possessor player of the ball.
     * Used whenever a player tries to catch a throw.
     * Used whenever a player tried to pick up a lying ball.
     * @param catcher
     */
    public void setPossessor(Player player)
    {
        possessor=player;
        setLocation(possessor.getLocation().getRow(), possessor.getLocation().getColumn());

        update();
    }

    /**
     * Method to inform the ball that it lies on the ground now
     */
    public void resetPossessor()
    {
        possessor=null;

        update();
    }

    public Player getPossessor()
    {
        return possessor;
    }

    /**
     * Method to move the ball up by some gradient
     * @param gradient Move by how many cells
     */
    public void moveUpBy(int gradient)
    {
        if(location.getRow()-gradient<0)
        {
            setLocation(0, location.getColumn());
        }
        else
        {
            setLocation((location.getRow()-gradient), location.getColumn());
        }

        update();
    }

    /**
     * Method to move the ball down by by some gradient
     * @param gradient Move by how many cells
     */
    public void moveDownBy(int gradient)
    {
        if(location.getRow()+gradient>15)
        {
           setLocation(15, location.getColumn());
        }
        else
        {
            setLocation((location.getRow()+gradient), location.getColumn());
        }

        update();
    }

    /**
     * Method to move the ball left by some gradient
     * @param gradient Move by how many cells
     */
    public void moveLeftBy(int gradient)
    {
        if(location.getColumn()-gradient<0)
        {
           setLocation(location.getRow(), 0);
        }
        else
        {
            setLocation(location.getRow(), (location.getColumn()-gradient));
        }

        update();
    }

    /**
     * Method to move the ball right by some gradient
     * @param gradient Move by how many cells
     */
    public void moveRightBy(int gradient)
    {
        if(location.getColumn()+gradient>25)
        {
           setLocation(location.getRow(), 25);
        }
        else
        {
            setLocation(location.getRow(), (location.getColumn()+gradient));
        }

        update();
    }

    /**
     * Method to move the ball top right by some gradient
     * @param gradient Move by how many cells
     */
    public void moveTopRightBy(int gradient)
    {
        if(location.getColumn()+gradient>25)
        {
            if(location.getRow()-gradient<0)
            {
                if(location.getRow()>(25-location.getColumn()))
                {
                    gradient=25-location.getColumn();
                }
                else
                {
                    gradient=location.getRow();
                }
            }
            else
            {
                gradient=25-location.getColumn();
            }
        }
        else if(location.getRow()-gradient<0)
        {
            gradient=location.getRow();
        }
        setLocation((location.getRow()-gradient), (location.getColumn()+gradient));

        update();
    }

    /**
     * Method to move the ball top left by some gradient
     * @param gradient Move by how many cells
     */
    public void moveTopLeftBy(int gradient)
    {
        if(location.getColumn()-gradient<0)
        {
            if(location.getRow()-gradient<0)
            {
                if(location.getRow()>location.getColumn())
                {
                    gradient=location.getColumn();
                }
                else
                {
                    gradient=location.getRow();
                }
            }
            else
            {
                gradient=location.getColumn();
            }
        }
        else if(location.getRow()-gradient<0)
        {
            gradient=location.getRow();
        }
        setLocation((location.getRow()-gradient), (location.getColumn()-gradient));

        update();
    }

     /**
     * Method to move the ball bottom left by some gradient
     * @param gradient Move by how many cells
     */
    public void moveBottomLeftBy(int gradient)
    {
        if(location.getColumn()-gradient<0)
        {
            if(location.getRow()+gradient>14)
            {
                if(location.getColumn()>(14-location.getRow()))
                {
                    gradient=14-location.getRow();
                }
                else
                {
                    gradient=location.getColumn();
                }
            }
            else
            {
                gradient=location.getColumn();
            }
        }
        else if(location.getRow()+gradient>14)
        {
            gradient=14-location.getRow();
        }
        setLocation((location.getRow()+gradient), (location.getColumn()-gradient));

        update();
    }

     /**
     * Method to move the ball bottom right by some gradient
     * @param gradient Move by how many cells
     */
    public void moveBottomRightBy(int gradient)
    {
        if(location.getColumn()+gradient>25)
        {
            if(location.getRow()+gradient>14)
            {
                if((25-location.getColumn())>(14-location.getRow()))
                {
                    gradient=14-location.getRow();
                }
                else
                {
                    gradient=25-location.getColumn();
                }
            }
            else
            {
                gradient=25-location.getColumn();
            }
        }
        else if(location.getRow()+gradient>14)
        {
            gradient=14-location.getRow();
        }
        setLocation((location.getRow()+gradient), (location.getColumn()+gradient));

        update();

    }

    public void scatterToAdjacentCell()
    {
        int directionForBounce = DiceManager.rollD8();
        switch (directionForBounce) {
            case 0:
                moveBottomLeftBy(1);
                break;
            case 1:
                moveBottomRightBy(1);
                break;
            case 2:
                moveDownBy(1);
                break;
            case 3:
                moveUpBy(1);
                break;
            case 4:
                moveRightBy(1);
                break;
            case 5:
                moveLeftBy(1);
                break;
            case 6:
                moveTopLeftBy(1);
                break;
            case 7:
                moveTopRightBy(1);
                break;
        }
    }

    private void update() {
        setChanged();
        notifyObservers();
    }
}
