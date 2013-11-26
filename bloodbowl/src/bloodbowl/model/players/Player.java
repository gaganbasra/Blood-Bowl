package bloodbowl.model.players;

import bloodbowl.model.map.Cell;
import bloodbowl.model.map.DugoutCell;
import bloodbowl.model.map.PitchCell;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The player class that deals with all types of players.
 * It's an abstract class, which should be inherited by each Player type to function as a player.
 * The list of objects of this class go into the team object to form a team of players.
 * @author Rishinder
 * @version 1.0.0
 */
public abstract class Player extends Observable implements Serializable {

    protected int ma, st, ag, av;                 //ma=Movement Allowance, st=Strength, ag=Agility, av=Armor Value
    protected int points;
    protected int maLeft;                         //Movement allowance left after a move
    protected int teamID;                         //For Team A, teamID=1. For Team B, teamID=2;
    protected Cell location;                      //Location in the Map (pitch or the dugout)
    protected boolean isActive;                   //Player is on pitch
    protected boolean hasMoved;                   //Player has made its move in the turn
    protected ArrayList<Skills> skillsList;
    protected PlayerType type;                    //Stores player type
    protected PlayerStates state;
    protected PlayerTitle pTitle;
    protected boolean upgradable;

    /**
     * Method to get the state of the player.
     * @return State of the player.
     */
    public PlayerStates getState() {    
        return state;
    }

    /**
     * Method to check if the player's skills or characteristics can upgrade depending upon the increase in points of the player.
     */
    public void upgradeDone() {
        upgradable = false;
    }

    /**
     * Getter for checking if the player can be upgraded
     * @return True if upgrade is possible, false otherwise.
     */
    public boolean isUpgradable() {
        return upgradable;
    }

    /**
     * Method to set the state of the player
     * @param state Any state out of the available states
     */
    public void setState(PlayerStates state) {
        if (state == PlayerStates.KNOCKED || state == PlayerStates.INJURED) {
            this.isActive = false;
        } else if (state == PlayerStates.STUNNED) {
            this.isActive = true;
            this.ma=this.ma-3;
        }
        this.state = state;
    }

    public void restoreStunned()
    {
        this.ma=this.ma+3;
        this.state=PlayerStates.NORMAL;
    }

    /**
     * Method to get the title of the player depending upon the player's points.
     * @return Title or level of the player
     */
    public PlayerTitle getPlayerTitle() {
        PlayerTitle pTitle = PlayerTitle.ROOKIE;
        if (points <= 5) {
            pTitle = PlayerTitle.ROOKIE;
        } else if (points > 5 && points < 16) {
            pTitle = PlayerTitle.EXPERIENCED;
        } else if (points > 15 && points < 31) {
            pTitle = PlayerTitle.VETERAN;
        } else if (points > 30 && points < 51) {
            pTitle = PlayerTitle.EMERGING_STAR;
        } else if (points > 50 && points < 76) {
            pTitle = PlayerTitle.STAR;
        } else if (points > 75 && points < 176) {
            pTitle = PlayerTitle.SUPER_STAR;
        } else {
            pTitle = PlayerTitle.LEGEND;
        }
        return pTitle;
    }

    /**
     * Default constructor for player.
     * Not used at all
     */
    public Player() {
        skillsList = new ArrayList();
        //state = PlayerStates.NORMAL;
    }

    /**
     * Player constructor
     * @param ma Movement Allowance
     * @param st Strength
     * @param ag Agility
     * @param av Armor Value
     * @param teamID Team ID in current game play.
     * @param skills List of skills that the player possesses
     * @param type Type of player i.e. Black Orc, Lineman, etc.
     */
    public Player(int ma, int st, int ag, int av, int teamID, ArrayList<Skills> skills, PlayerType type) {
        this.ma = ma;
        this.st = st;
        this.ag = ag;
        this.av = av;
        this.teamID = teamID;
        this.type = type;
        skillsList = skills;
        maLeft = ma;
        //state = PlayerStates.NORMAL;
    }

    /**
     * Method to reset the movement left (maLeft) back to the initial movement allowance on a move complete.
     */
    public void resetPlayer() {
        if(this.state==PlayerStates.STUNNED)
            restoreStunned();
        maLeft = ma;
        hasMoved = false;
    }

    public void doneActionInTurn() {
        hasMoved = true;
    }

    /**
     * Method to update the movement left (maLeft) on each move.
     */
    public void decreaseMovementLeft() {
        maLeft--;
    }

    /**
     * Method to check if the player can move depending upon the movement allowance left.
     * @return true if the player can still move in regard to its movement allowance, else returns false
     */
    public boolean canMove() {
        if (maLeft > 0 && !hasMoved) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if the player has moved in its turn
     * @return True if it has moved. False otherwise.
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Method to set the new location of the player on a move.
     * Resets the previous location so that it could be used by another player.
     * Used during the game play.
     * @param newLocation New cell, a pitch cell more specifically.
     */
    public void setLocation(Cell newLocation) {
        location.resetPlayer();
        if (newLocation instanceof PitchCell) {
            location = (PitchCell) newLocation;
            location.setPlayer();
            setActive();
        } else {
            location = (DugoutCell) newLocation;
            location.setPlayer();
        }

        setChanged();
        notifyObservers();
    }

    public void setLocationAfterKnock(Cell newLocation) {
        ((DugoutCell)newLocation).resetPlayer();
        location = (DugoutCell) newLocation;
        location.setPlayer();
        isActive=false;

        setChanged();
        notifyObservers();
    }

    /**
     * Method to set the player's location back to the reserves.
     * @param setUpLocation New cell, a pitch cell more specifically.
     */
    public void setUpOnLocation(Cell setUpLocation) //Method for setting location at game start
    {
        ((DugoutCell)setUpLocation).setPlayer();      //Inform cell that it has something
        location = (DugoutCell) setUpLocation;         //Assign player to the respective cell
        isActive = false;                  //Make the player inactive

        setChanged();
        notifyObservers();

    }

    /**
     * Method to check is the Clicked cell is adjacent to the player's cell to make the player move cell by cell.
     * @param clickedCell destination location in the pitch
     * @return true is the player is adjacent to the clicked cell, else returns false.
     */
    public boolean isNear(Cell clickedCell) {
        boolean isNearVar = false;
        if (clickedCell.getRow() == location.getRow() - 1 && clickedCell.getColumn() == location.getColumn() - 1) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() - 1 && clickedCell.getColumn() == location.getColumn()) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() - 1 && clickedCell.getColumn() == location.getColumn() + 1) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() + 1 && clickedCell.getColumn() == location.getColumn() - 1) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() + 1 && clickedCell.getColumn() == location.getColumn()) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() + 1 && clickedCell.getColumn() == location.getColumn() + 1) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() && clickedCell.getColumn() == location.getColumn() - 1) {
            isNearVar = true;
        } else if (clickedCell.getRow() == location.getRow() && clickedCell.getColumn() == location.getColumn() + 1) {
            isNearVar = true;
        }

        return isNearVar;
    }

    /**
     * Method to make the player's status from inactive to active and notify the to the map as well.
     * Called when the player is placed from the dugout to the pitch.
     */
    public void setActive() {
        this.isActive = true;
        if(state!=PlayerStates.STUNNED)
            state = PlayerStates.NORMAL;
        setChanged();
        notifyObservers();
    }

    /**
     * Method to get the status of the player
     * @return Player Status
     */
    public boolean getActive() {
        return isActive;
    }

    /***************************Other Getters and Setters************************/
    /**
     * Method to get current location of the player.
     * @return Cell where the player is present.
     */
    public Cell getLocation() {
        return location;
    }

    /**
     * Method to set the team (A or B) for the player
     * @param id Team ID i.e. TeamA or TeamB
     */
    public void setTeamID(int id) {
        teamID = id;
    }

    /**
     * Method for getting the teamID of the player
     * @return teamID in integer form
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * Method to get the type of the player
     * @return Player type from the choices present in the player type enumerator
     */
    public PlayerType getType() {
        return type;
    }

    /**
     * Method to get the Movement allowance of the player
     * @return Movement allowance
     */
    public int getMa() {
        return ma;
    }

    /**
     * Method to get the Movement allowance left of the player
     * @return Movement allowance
     */
    public int getMaLeft() {
        return maLeft;
    }

    /**
     * Method to get Strength
     * @return Strength
     */
    public int getSt() {
        return st;
    }

    /**
     * Method to get Armor Value
     * @return Armor Value
     */
    public int getAv() {
        return av;
    }

    /**
     * Method to set the Agility of the player
     * @param ag Agility in integer form
     */
    public void setAg(int ag) {
        this.ag = ag;
    }

    /**
     * Method to set Armor Value
     * @param av Armor Value
     */
    public void setAv(int av) {
        this.av = av;
    }

    /**
     * Method to set Movement allowance
     * @param ma Movement Allowance
     */
    public void setMa(int ma) {
        this.ma = ma;
        this.maLeft = ma;
    }

    /**
     * Method to set the Skills for the player
     * @param skillsList List of Skills
     */
    public void setSkillsList(ArrayList<Skills> skillsList) {
        this.skillsList = skillsList;
    }

    /**
     * Method to get the Skill set of the player
     * @return list of Skills
     */
    public ArrayList<Skills> getSkillsList() {
        return skillsList;
    }

    /**
     * Method to set the player's strength
     * @param st Strength
     */
    public void setSt(int st) {
        this.st = st;
    }

    /**
     * Method to get player's Agility
     * @return Agility
     */
    public int getAg() {
        return ag;
    }

    /**
     * Method to increase points after the player becomes the most valuable player.
     * Invoked by the Coach after completion of the Match.
     */
    public void selectedMostValued() {
        incrementPointsBy(5);
    }

    /**
     * Method to increment points by a certain number
     * @param addMorePoints Increase in the player points
     */
    public void incrementPointsBy(int addMorePoints) {
        if (points <= 5 && points + addMorePoints > 5) {
            upgradable = true;
        } else if (points <= 15 && points + addMorePoints > 15) {
            upgradable = true;
        } else if (points <= 30 && points + addMorePoints > 30) {
            upgradable = true;
        } else if (points <= 50 && points + addMorePoints > 50) {
            upgradable = true;
        } else if (points <= 75 && points + addMorePoints > 75) {
            upgradable = true;
        } else if (points <= 175 && points + addMorePoints > 175) {
            upgradable = true;
        }

        points += addMorePoints;
    }

    public void awardPointsForAction(Actions action) {
        switch (action) {
            case PASS:
                incrementPointsBy(1);
                break;
            case KNOCKDOWN:
                incrementPointsBy(2);
                break;
            case TACKLE:
                incrementPointsBy(2);
                break;
            case TOUCHDOWN:
                incrementPointsBy(3);
                break;
        }
    }

    /**
     * Getter for player points
     * @return Points
     */
    public int getPoints() {
        return points;
    }   
}
