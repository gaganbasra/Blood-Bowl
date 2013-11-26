
package bloodbowl.engine;

import bloodbowl.model.managers.TeamManager;
import bloodbowl.model.managers.ActionManager;
import bloodbowl.model.managers.EventLogger;
import bloodbowl.model.map.*;
import bloodbowl.model.objects.Ball;
import bloodbowl.model.players.Actions;
import bloodbowl.model.players.Player;
import bloodbowl.model.teams.*;
import java.io.Serializable;

/**
 * Game Engine for the game.
 * Contains all the logic behind the working of the game.
 * @author Rishinder
 * @version 1.0.0
 */
public final class Game implements Serializable {

    PitchGrid pitch;
    DugoutGrid dugoutA, dugoutB;
    transient TeamManager teamManager;
    ActionManager actionManager;
    Team teamA, teamB;
    Player selectedPlayer;
    Ball gameBall;
    int currentTeam = 1;
    public boolean isWaitingForMoveAction;
    public boolean waitingForBlockResolution;
    boolean touchdownFlag;
    boolean gameIsOver;

    transient private static Game instance = null;

    /**
     * Private Constructor for Singleton pattern
     */
    private Game() {
        pitch = new PitchGrid();
        dugoutA = new DugoutGrid();
        dugoutB = new DugoutGrid();
        teamManager = new TeamManager();
        teamManager.getTeam();
        actionManager = new ActionManager(this);
        gameBall = new Ball(pitch);
        isWaitingForMoveAction = false;
    }

    /**
     * Method to return the single instance
     * @return instance of Game
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public ActionManager getActionManager()
    {
        return actionManager;
    }

    /**
     * Method to inform if the game is over.
     * @return true in case the game is over.
     */
    public boolean isGameIsOver() {
        return gameIsOver;
    }

    /**
     * Setter to set the game over flag to true
     */
    public void setGameIsOver() {
        this.gameIsOver = true;
    }



    /**
     * Method responsible for actions taking place after the touch down has been scored.
     */
    public void touchdownScored() {
        selectedPlayer.awardPointsForAction(Actions.TOUCHDOWN);
        if(currentTeam==Team.TEAM_A)
        {
            teamA.awardPointsForTouchdown();
            teamA.turnComplete();
            teamB.turnComplete();
            setCurrentTeam(currentTeam);

        }
        else
        {
            teamB.awardPointsForTouchdown();
            teamA.turnComplete();
            teamB.turnComplete();
            setCurrentTeam(currentTeam);
        }
        teamA.restoreKnockedPlayers();
        teamB.restoreKnockedPlayers();
        placeActivePlayersInReserves();
        pitch = new PitchGrid();        
        touchdownFlag=true;
    }

    /**
     * Getter for TouchdownFlag to inform if the touchdown has been scored in the game.
     * @return True if touchdown has been scored. False otherwise.
     */
    public boolean isTouchdownFlag() {
        return touchdownFlag;
    }

    /**
     * Method to reset the touchdownFlag
     */
    public void resetTouchDownFlag() {
        touchdownFlag=false;
    }




    /**
     * Method to inform that some move action will be performed
     */
    public void initiateMoveAction() {
        isWaitingForMoveAction = true;
    }

    /**
     * Method to inform that the move action has been committed.
     */
    public void commitMoveAction() //To commit the move action
    {
        isWaitingForMoveAction = false;
        //selectedPlayer.resetMovementLeft(); //Recharge the Movement Allowance for next turn
        selectedPlayer = null;
    }

    /**
     * Method to inform inform that the game is waiting for some move action to be performed.
     * @return
     */
    public boolean isWaitingForMoveAction() {
        return this.isWaitingForMoveAction;
    }

    /**
     * Method to get the value for the waitingForBlockResolution flag
     * @return waitingForBlockResolution flag's value
     */
    public boolean isWaitingForBlockResolution() {
        return this.waitingForBlockResolution;
    }

    /**
     * Setter for setting the value for waitingForBlockResolution flag
     * @param waitingForBlockResolution true to set it to true, else false.
     */
    public void setWaitingForBlockResolution(boolean waitingForBlockResolution) {
        this.waitingForBlockResolution = waitingForBlockResolution;
    }



    /**
     * Getter for the game Ball
     * @return Ball in the game as object of Ball class
     */
    public Ball getGameBall() {
        if (gameBall==null)
            gameBall = new Ball(pitch);
        return gameBall;
    }

    /**
     * Method to get the selected player that is selected by the coach
     * @return Player that is selected
     */
    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    /**
     * Get a player from Dugout of team A
     * @param row Row number of the player's location cell
     * @param column Column number of the player's location cell
     * @return selected player
     */
    public Player getPlayerAtDugoutA(int row, int column) {

        selectedPlayer = null;
        for (Player p : teamA.getPlayers()) {
            if (p.getLocation().getRow() == row && p.getLocation().getColumn() == column) {
                selectedPlayer = p;
                break;
            }
        }

        return selectedPlayer;
    }

    /**
     * Get a player from Dugout of team B
     * @param row Row number of the player's location cell
     * @param column Column number of the player's location cell
     * @return selected player
     */
    public Player getPlayerAtDugoutB(int row, int column) {

        selectedPlayer = null;
        int i = 0;
        for (Player p : teamB.getPlayers()) {
            if (p.getLocation().getRow() == row && p.getLocation().getColumn() == column) {
                selectedPlayer = p;
                i += 1;
                break;
            }
        }
        return selectedPlayer;
    }

    /**
     * Get a player from the pitch
     * @param row Row number of the player's location cell
     * @param column Column number of the player's location cell
     * @return selected player
     */
    public Player getPlayerAtPitch(int row, int column) {

        Player pitchPlayer = null;
        for (Player p : teamA.getPlayers()) {
            if (p.getActive() && p.getLocation().getRow() == row && p.getLocation().getColumn() == column) {
                pitchPlayer = p;
                break;
            }
        }

        if (pitchPlayer == null) {
            for (Player p : teamB.getPlayers()) {
                if (p.getActive() && p.getLocation().getRow() == row && p.getLocation().getColumn() == column) {
                    pitchPlayer = p;
                    break;
                }
            }
        }

        return pitchPlayer;
    }

    /**
     * Method to Place the player on the pitch cell (row,column)
     * @param row Row number of the player's location cell
     * @param column Column number of the player's location cell
     */
    public boolean placePlayerAtPitchCell(int row, int column) {
        boolean isSuccessful;
        if (!(pitch.getCell(row, column)).hasPlayer()) {
            selectedPlayer.setLocation((pitch.getCell(row, column)));
            isSuccessful=true;
            EventLogger.log(String.format("Player placed at %s", pitch.getCell(row, column)));
        } else {
            isSuccessful=false;
            EventLogger.log("Unsuccessful player placement");
        }

        return isSuccessful;
    }

    /**
     * Setter for selected player
     * @param selectedPlayer Selected Player
     */
    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    /**
     * Getter for current team which has its turn
     * @return current team
     */
    public int getCurrentTeam() {
        return currentTeam;
    }

    /**
     * Setter for current team which has its turn
     * @param currentTeam ID for the current team
     */
    public void setCurrentTeam(int currentTeam) {
        this.currentTeam = currentTeam;

    }

    /**
     * Get the team which is not currently acting
     * @return Team A or B depending upon the current team
     */
    public Team getOpponentTeam() {
        if (currentTeam == 1) {
            return teamB;
        } else {
            return teamA;
        }
    }

    /**
     * Getter for team A
     * @return teamA
     */
    public Team getTeamA() {
        return teamA;
    }

    /**
     * Getter for team B
     * @return teamB
     */
    public Team getTeamB() {
        return teamB;
    }

    /**
     * Setter for team A
     * @param teamA Reference to the teamA's object
     */
    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    /**
     * Setter for team B
     * @param teamB Reference to the teamB's object
     */
    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    /**
     * Method for placing the players in reserves when a new game begins.
     */
    public void placeTeamsInReserves() {
        for (int i = 0; i < teamA.getTotalPlayers(); i++) {
            (teamA.getPlayer(i)).setUpOnLocation(dugoutA.getCell(i / 4, i % 4));
        }
        for (int i = 0; i < teamB.getTotalPlayers(); i++) {
            (teamB.getPlayer(i)).setUpOnLocation(dugoutB.getCell(i / 4, i % 4));
        }
    }

    /**
     * Method for placing the active players in reserves after the touchdownScored.
     */
    public void placeActivePlayersInReserves() {
        for (int i = 0; i < teamA.getTotalPlayers(); i++) {
            Player tempPlayer = (teamA.getPlayer(i));
            if (tempPlayer.getActive()) {
                tempPlayer.setUpOnLocation(dugoutA.getCell(i / 4, i % 4));
            }
        }
        for (int i = 0; i < teamB.getTotalPlayers(); i++) {
            Player tempPlayer = (teamB.getPlayer(i));
            if (tempPlayer.getActive()) {
                tempPlayer.setUpOnLocation(dugoutB.getCell(i / 4, i % 4));
            }
        }
    }


    /**
     * Method to place the player in the knocked out zone.
     * @param knockedPlayer player that is knocked out
     */
    public void placeKnockedPlayerInDugout(Player knockedPlayer)
    {
        if(knockedPlayer.getTeamID()==Team.TEAM_A)
        {            
            out:for(int i=4; i<8; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(!dugoutA.getCell(i, j).hasPlayer())
                    {
                        knockedPlayer.setLocationAfterKnock(dugoutA.getCell(i, j));
                        break out;
                    }
                }
            }
        }
        else
        {            
            out:for(int i=4; i<8; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(!dugoutB.getCell(i, j).hasPlayer())
                    {
                        knockedPlayer.setLocationAfterKnock(dugoutB.getCell(i, j));
                        break out;
                    }
                }
            }
        }
    }

    /**
     * Method to place the player in the injured zone.
     * @param knockedPlayer player that is injured
     */
    public void placeInjuredPlayerInDugout(Player knockedPlayer)
    {        
        if(knockedPlayer.getTeamID()==Team.TEAM_A)
        {            
            out:for(int i=8; i<12; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(!dugoutA.getCell(i, j).hasPlayer())
                    {
                        knockedPlayer.setLocationAfterKnock(dugoutA.getCell(i, j));
                        break out;
                    }
                }
            }
        }
        else
        {            
            out:for(int i=8; i<12; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(!dugoutB.getCell(i, j).hasPlayer())
                    {
                        knockedPlayer.setLocationAfterKnock(dugoutB.getCell(i, j));
                        break out;
                    }
                }
            }
        }
    }

    /**
     * Method for initiating a move action
     * @param pitchCell Destination for move
     * @return true for successful move, else false
     */
    public boolean movePlayer(PitchCell pitchCell) {
        //Initiate a move action        
        actionManager.initiateMoveAction();
        return actionManager.moveSelectedPlayerAt(pitchCell);
    }

    /**
     * Getter for team A's dugout
     * @return dugoutA
     */
    public DugoutGrid getDugoutA() {
        return dugoutA;
    }

    /**
     * Getter for team B's dugout
     * @return dugoutB
     */
    public DugoutGrid getDugoutB() {
        return dugoutB;
    }

    /**
     * Getter for the pitch
     * @return pitch
     */
    public PitchGrid getPitch() {
        return pitch;
    }
}
