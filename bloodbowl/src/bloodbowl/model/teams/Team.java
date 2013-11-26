package bloodbowl.model.teams;

import bloodbowl.model.managers.DiceManager;
import bloodbowl.model.map.Cell;
import java.util.ArrayList;

import bloodbowl.model.players.*;
import java.io.Serializable;

/**
 * Team class is an abstract class, which represents the teams in the game.
 * This class can be extended by any class for being eligible as a team
 * @author Rishinder
 */
public abstract class Team implements Serializable {

    protected String name;
    protected int teamID;
    protected TeamType teamType;
    protected int turns;
    protected int scores;
    protected int totalPlayers;
    protected int wins;
    protected int ties;
    protected int looses;
    protected int numLineman, numCatcher, numThrower, numBlitzer, numOgre, numGoblin, numBlackorc, numTroll;
    protected int maxLineman, maxCatcher, maxThrower, maxBlitzer, maxOgre, maxGoblin, maxBlackorc, maxTroll;
    protected PlayerType[] typeOfAvailablePlayers;    
    protected ArrayList<Player> pList;
    protected boolean playerHasMadePass;

    public static final int TEAM_A=1, TEAM_B=2;

    /**
     * Default Constructor
     */
    public Team() {
        pList = new ArrayList();
        linemanSkills = new ArrayList();
        blackorcSkills = new ArrayList();
        blitzerSkills = new ArrayList();
        catcherSkills = new ArrayList();
        goblinSkills = new ArrayList();
        ogreSkills = new ArrayList();
        throwerSkills = new ArrayList();
        trollSkills = new ArrayList();
    }

    
    /**
     * Method to get team's Type
     * @return Type of the team
     */
    public TeamType getType() {
        return teamType;
    }

    /**
     * Method to get the List of Players in a team
     * @return List of Players
     */
    public ArrayList<Player> getPlayers() {            
        return pList;
    }

    /**
     * Method to give the available types of players that can be the part of this team.
     * The return type is a String array to make it compatible with combo-boxes of dialogs.
     * @return
     */
    public String[] getAvailablePlayerOptions() {
        String[] typeOfAvailablePlayersString = new String[typeOfAvailablePlayers.length];
        for (int i = 0; i < typeOfAvailablePlayers.length; i++) {
            typeOfAvailablePlayersString[i] = typeOfAvailablePlayers[i].toString();
        }
        return typeOfAvailablePlayersString;
    }

    /**
     * Method to get the number of Ties of a team
     * @return Number of Ties
     */
    public int getTies() {
        return ties;
    }

    /**
     * Method to get the turns available for the team
     * @return Available turns
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Method to get the number of Looses for a team
     * @return Number of Looses
     */
    public int getLooses() {
        return looses;
    }

    /**
     * Method to get the scores for a team
     * @return Scores
     */
    public int getScores() {
        return scores;
    }

    /**
     * Method to get the number of wins for a team
     * @return Number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Method to get the Team ID i.e. 1 for Team A and 2 for Team B
     * @return Team's ID
     */
    public int getID() {
        return teamID;
    }

    /**
     * Method to set the team ID for the team
     * @param teamID ID for the team
     */
    public void setID(int teamID) {
        this.teamID = teamID;
    }

    /**
     * Method to get the Player from the Players' list for a leam
     * @param i index of player in the list
     * @return Player
     * @version 1.0.0
     */
    public Player getPlayer(int i) {
        return pList.get(i);
    }


    /**
     * Used by the Map Renderer to get the Player at the dugout cell.
     * @param cell Reference to the Cell from where we want to get the player
     * @return Player
     */
    public Player getDugoutPlayerAt(Cell cell) {
        Player playerAt = null;
        for (Player player : pList) {
            if (!player.getActive()) {                
                if (player.getLocation().getRow()==cell.getRow() && player.getLocation().getColumn()==cell.getColumn()) {
                    playerAt = player;
                }
            }
        }

        return playerAt;
    }

    /**
     * Method to get the Total Players present in the Team
     * @return Size of the Players' List
     */
    public int getTotalPlayers() {
        return pList.size();
    }

    /**
     * Method to get the Name of the Team
     * @return Name
     */
    public String getName() {
        return name;
    }

    /*****************************Player count getters***********************/
    /**
     * Method to get the Max Number of Blackorcs that can be a part of this team
     * @return Max number for Blackorcs
     */
    public int getMaxBlackorc() {
        return maxBlackorc;
    }

    /**
     * Method to get the Max Number of Blitzers that can be a part of this team
     * @return Max number for Blitzers
     */
    public int getMaxBlitzer() {
        return maxBlitzer;
    }

    /**
     * Method to get the Max Number of Catchers that can be a part of this team
     * @return Max number for Catchers
     */
    public int getMaxCatcher() {
        return maxCatcher;
    }

    /**
     * Method to get the Max Number of Goblins that can be a part of this team
     * @return Max number for Goblins
     */
    public int getMaxGoblin() {
        return maxGoblin;
    }

    /**
     * Method to get the Max Number of Linemen that can be a part of this team
     * @return Max number for Linemen
     */
    public int getMaxLineman() {
        return maxLineman;
    }

    /**
     * Method to get the Max Number of Ogres that can be a part of this team
     * @return Max number for Ogres
     */
    public int getMaxOgre() {
        return maxOgre;
    }

    /**
     * Method to get the Max Number of Thrower that can be a part of this team
     * @return Max number for Thrower
     */
    public int getMaxThrower() {
        return maxThrower;
    }

    /**
     * Method to get the Max Number of Trolls that can be a part of this team
     * @return Max number for Trolls
     */
    public int getMaxTroll() {
        return maxTroll;
    }

    /**
     * Method to get the Total Number of Blackorcs that are a part of this team
     * @return Total number for Blackorcs
     */
    public int getNumBlackorc() {
        return numBlackorc;
    }

    /**
     * Method to get the Total Number of Blitzers that are a part of this team
     * @return Total number for Blitzers
     */
    public int getNumBlitzer() {
        return numBlitzer;
    }

    /**
     * Method to get the Total Number of Catchers that are a part of this team
     * @return Total number for Catchers
     */
    public int getNumCatcher() {
        return numCatcher;
    }

    /**
     * Method to get the Total Number of Goblin that are a part of this team
     * @return Total number for Goblin
     */
    public int getNumGoblin() {
        return numGoblin;
    }

    /**
     * Method to get the Total Number of Linemen that are a part of this team
     * @return Total number for Linemen
     */
    public int getNumLineman() {
        return numLineman;
    }

    /**
     * Method to get the Total Number of Ogres that are a part of this team
     * @return Total number for Ogres
     */
    public int getNumOgre() {
        return numOgre;
    }

    /**
     * Method to get the Total Number of Throwers that are a part of this team
     * @return Total number for Throwers
     */
    public int getNumThrower() {
        return numThrower;
    }

    /**
     * Method to get the Total Number of Trolls that are a part of this team
     * @return Total number for Trolls
     */
    public int getNumTroll() {
        return numTroll;
    }

    /**
     * Method to initialize all the players on game start, after the game toss.
     */
    public void initializePlayers()
    {
        for(Player p:pList)
        {
            p.resetPlayer();
        }
    }
    /**
     * Method to change team statistics on completion of a turn.
     * Refreshes all the players to their initial state.
     * Resets the hasMadePass flag too, as now the team can make another pass.
     */
    public void turnComplete()
    {
        for(Player p:pList)
        {
            p.resetPlayer();
        }
        if(turns>0)
        {
            turns--;
        }
        playerHasMadePass=false;
    }

    /**
     * Method to inform the team that a pass has been made in the current turn.
     */
    public void passMade()
    {
        playerHasMadePass=true;
    }

    /**
     * Getter to get the value of the playerHasMadePass flag.
     * @return true if a pass has been made in the current turn
     */
    public boolean getPlayerHasMadePass()
    {
        return playerHasMadePass;
    }
    /**
     * Method to check if the player addition in this team is valid or not.
     * @param playerType Type of Player to be added
     * @return True if addition is possible, else false.
     */
    public boolean isValidPlayerAddition(PlayerType playerType) {
        boolean validAddition = false;
        if (pList.size() < 16) {
            if (playerType == PlayerType.BLACKORC && numBlackorc < maxBlackorc) {
                validAddition = true;
                numBlackorc++;
            } else if (playerType == PlayerType.BLITZER && numBlitzer < maxBlitzer) {
                validAddition = true;
                numBlitzer++;
            } else if (playerType == PlayerType.CATCHER && numCatcher < maxCatcher) {
                validAddition = true;
                numCatcher++;
            } else if (playerType == PlayerType.GOBLIN && numGoblin < maxGoblin) {
                validAddition = true;
                numGoblin++;
            } else if (playerType == PlayerType.LINEMAN && numLineman < maxLineman) {
                validAddition = true;
                numLineman++;
            } else if (playerType == PlayerType.OGRE && numOgre < maxOgre) {
                validAddition = true;
                numOgre++;
            } else if (playerType == PlayerType.THROWER && numThrower < maxThrower) {
                validAddition = true;
                numThrower++;
            } else if (playerType == PlayerType.TROLL && numTroll < maxTroll) {
                validAddition = true;
                numTroll++;
            }
        }
        return validAddition;
    }

    /**
     * Add the player in the Players List
     * @param p Player to be added
     */
    public void addPlayer(Player p) {
        p.setTeamID(teamID);
        pList.add(p);
    }

    /**
     * Method to remove the player from the team
     * @param pIndex Index of the player in the Players List.
     */
    public void removePlayer(int pIndex) {
        PlayerType playerType = pList.get(pIndex).getType();
        if (playerType == PlayerType.BLACKORC) {
            numBlackorc--;
        }
        if (playerType == PlayerType.BLITZER) {
            numBlitzer--;
        }
        if (playerType == PlayerType.CATCHER) {
            numCatcher--;
        }
        if (playerType == PlayerType.GOBLIN) {
            numGoblin--;
        }
        if (playerType == PlayerType.LINEMAN) {
            numLineman--;
        }
        if (playerType == PlayerType.OGRE) {
            numOgre--;
        }
        if (playerType == PlayerType.THROWER) {
            numThrower--;
        }
        if (playerType == PlayerType.TROLL) {
            numTroll--;
        }
        pList.remove(pIndex);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Method to increment the scores of the team by 1 on each touchdown scored.
     */
    public void awardPointsForTouchdown()
    {
        scores++;
    }

    /**
     * Method to place knocked players in the reserves depending upon a D6 roll
     */
    public void restoreKnockedPlayers()
    {
        for(Player p:pList)
        {
            if(p.getState()==PlayerStates.KNOCKED)
            {
                if(DiceManager.rollD6()>3)
                {
                    p.setActive();
                }
            }
        }
    }

    /**
     * Method used only for debugging in console to print team details.
     */
    public void displayTeamDetails() {
        System.out.println("Team:" + name);
        System.out.println("Values for max count of each type of player.");
        System.out.println("--------------------------------------------");
        System.out.println(maxLineman);
        System.out.println(maxCatcher);
        System.out.println(maxThrower);
        System.out.println(maxBlitzer);
        System.out.println(maxOgre);
        System.out.println(maxGoblin);
        System.out.println(maxBlackorc);
        System.out.println(maxTroll);
    }
    /**************************Player Creation Rules**********************/
    //Player Chanracterisitcs and Skills for different players.
    //If a new type of player is to be added, its characteristics and skills should go here.
    int linemanMA, linemanST, linemanAG, linemanAV;
    int blackorcMA, blackorcST, blackorcAG, blackorcAV;
    int blitzerMA, blitzerST, blitzerAG, blitzerAV;
    int catcherMA, catcherST, catcherAG, catcherAV;
    int goblinMA, goblinST, goblinAG, goblinAV;
    int ogreMA, ogreST, ogreAG, ogreAV;
    int throwerMA, throwerST, throwerAG, throwerAV;
    int trollMA, trollST, trollAG, trollAV;
    ArrayList<Skills> linemanSkills, blackorcSkills, blitzerSkills, catcherSkills,
            goblinSkills, ogreSkills, throwerSkills, trollSkills;

    //Getter Methods for getting these characteristics and skills.
    //These have to be updated as well in order to accomodate the new player.
    public int getBlackorcAG() {
        return blackorcAG;
    }

    public int getBlackorcAV() {
        return blackorcAV;
    }

    public int getBlackorcMA() {
        return blackorcMA;
    }

    public int getBlackorcST() {
        return blackorcST;
    }

    public ArrayList<Skills> getBlackorcSkills() {
        return blackorcSkills;
    }

    public int getBlitzerAG() {
        return blitzerAG;
    }

    public int getBlitzerAV() {
        return blitzerAV;
    }

    public int getBlitzerMA() {
        return blitzerMA;
    }

    public int getBlitzerST() {
        return blitzerST;
    }

    public ArrayList<Skills> getBlitzerSkills() {
        return blitzerSkills;
    }

    public int getCatcherAG() {
        return catcherAG;
    }

    public int getCatcherAV() {
        return catcherAV;
    }

    public int getCatcherMA() {
        return catcherMA;
    }

    public int getCatcherST() {
        return catcherST;
    }

    public ArrayList<Skills> getCatcherSkills() {
        return catcherSkills;
    }

    public int getGoblinAG() {
        return goblinAG;
    }

    public int getGoblinAV() {
        return goblinAV;
    }

    public int getGoblinMA() {
        return goblinMA;
    }

    public int getGoblinST() {
        return goblinST;
    }

    public ArrayList<Skills> getGoblinSkills() {
        return goblinSkills;
    }

    public int getLinemanAG() {
        return linemanAG;
    }

    public int getLinemanAV() {
        return linemanAV;
    }

    public int getLinemanMA() {
        return linemanMA;
    }

    public int getLinemanST() {
        return linemanST;
    }

    public ArrayList<Skills> getLinemanSkills() {
        return linemanSkills;
    }

    public int getOgreAG() {
        return ogreAG;
    }

    public int getOgreAV() {
        return ogreAV;
    }

    public int getOgreMA() {
        return ogreMA;
    }

    public int getOgreST() {
        return ogreST;
    }

    public ArrayList<Skills> getOgreSkills() {
        return ogreSkills;
    }

    public int getThrowerAG() {
        return throwerAG;
    }

    public int getThrowerAV() {
        return throwerAV;
    }

    public int getThrowerMA() {
        return throwerMA;
    }

    public int getThrowerST() {
        return throwerST;
    }

    public ArrayList<Skills> getThrowerSkills() {
        return throwerSkills;
    }

    public int getTrollAG() {
        return trollAG;
    }

    public int getTrollAV() {
        return trollAV;
    }

    public int getTrollMA() {
        return trollMA;
    }

    public int getTrollST() {
        return trollST;
    }

    public ArrayList<Skills> getTrollSkills() {
        return trollSkills;
    }
    /*********************************************************************/
}
