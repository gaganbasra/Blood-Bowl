package bloodbowl.model.managers;

import bloodbowl.model.players.*;
import bloodbowl.model.teams.Team;
import bloodbowl.model.teams.TeamType;
import bloodbowl.model.teams.TeamsFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class responsible for managing teams of the game.
 * 
 * This class is responsible for creation and editing of teams and their players.
 * It takes care of deleting the players but teams cannot be deleted from this.
 * The Team Manager is also responsible for saving and loading the teams.
 * @author Rishinder
 * @version 1.0.0
 */
public class TeamManager {

    Team currentTeam;
    PlayersFactory plFactory;
    TeamsFactory teamsFactory;

    /**
     * Method for creating a team. Invoked by the Team Editor dialog.
     * @param teamID If the team created is A or B
     * @param teamType Type of team to be created
     * @param teamName Name of the created team
     */
    public void createTeam(int teamID, TeamType teamType, String teamName) {
        teamsFactory=new TeamsFactory();
        switch(teamType)
        {
            case HUMAN_TYPE:
                currentTeam = teamsFactory.createHuman(teamID, teamName);
                plFactory=new HumansFactory();
                break;
            case ELF_TYPE:
                currentTeam = teamsFactory.createElves(teamID, teamName);
                plFactory=new ElvesFactory();
                break;
            case ORC_TYPE:
                currentTeam = teamsFactory.createOrcs(teamID, teamName);
                plFactory=new OrcsFactory();
                break;
        } 
    }

    /**
     * Method to create players of the current team, which is being managed by the editor.
     * @param playerType Type of player to be created
     * @return True is the player is added successfully to the team, else returns false.
     */
    public boolean createPlayer(PlayerType playerType) {
        Player newPlayer = null;

        if (currentTeam.isValidPlayerAddition(playerType)) {

            switch(playerType)
            {
                case BLACKORC: newPlayer = plFactory.createBlackorc();
                break;
                case BLITZER: newPlayer = plFactory.createBlitzer();
                break;
                case CATCHER: newPlayer = plFactory.createCatcher();
                break;
                case GOBLIN: newPlayer = plFactory.createGoblin();
                break;
                case LINEMAN: newPlayer = plFactory.createLineman();
                break;
                case OGRE: newPlayer = plFactory.createOgre();
                break;
                case THROWER: newPlayer = plFactory.createThrower();
                break;
                case TROLL: newPlayer = plFactory.createTroll();
                break;
            }

            currentTeam.addPlayer(newPlayer);
            return true;
        }
        return false;
    }

    /**
     * Setter for current team managed by the Team manager
     * @param teamFromGame gets the team from the game and sets it as a team to be managed
     */
    public void setTeam(Team teamFromGame) {
        currentTeam = teamFromGame;
    }

    /**
     * Getter method to get the current team that is being managed
     * @return Current Team
     */
    public Team getTeam() {
        return currentTeam;
    }

    /**
     * Method to save the team in the SavedTeams directory.
     * The team is saved as a file with the same name as that of the team.
     * @throws FileNotFoundException To handle such cases where the directory for saved teams is not there
     * @throws IOException To handle Input Output issues related to saving and loading.
     */
    public void saveTeam() throws FileNotFoundException, IOException {

        String dirName="SavedTeams";
        String filename = "SavedTeams/" + currentTeam.getName().toLowerCase();

        File dirSavedTeam = new File(dirName);
        if (!dirSavedTeam.isDirectory()) {
            dirSavedTeam.mkdir();
        }
        if(dirSavedTeam.isDirectory())
        {
            FileOutputStream fos = null;
            ObjectOutputStream out = null;

            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(currentTeam);
            out.close();
        }
    }


    /**
     * Method to get all the saved teams in the SavedTeams folder
     * @return list of saved teams as an ArrayList of String type.
     */
    public ArrayList<String> getSavedTeams() {
        File dirSavedTeams = new File("SavedTeams");

        ArrayList<String> teamNames = null;

        if (!dirSavedTeams.exists())
        {
            dirSavedTeams.mkdir();
        }
        if (dirSavedTeams.isDirectory())
        {
            teamNames = new ArrayList(Arrays.asList(dirSavedTeams.list()));
            if (teamNames.contains("CVS")) {
                teamNames.remove("CVS");
            }

        }
        return teamNames;
    }

    /**
     * Method to restore/load a saved team as the current team for the game.
     * @param teamName Team Name, in reality the file name
     * @param teamID to assign a side i.e. A or B during the restore operation itself
     * @throws IOException To handle any input/output issues
     * @throws ClassNotFoundException To handle issues where the applicable team class is not found
     */
    public void restoreTeam(String teamName, int teamID) throws IOException, ClassNotFoundException {

        String filename = "SavedTeams/" + teamName;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        fis = new FileInputStream(filename);
        in = new ObjectInputStream(fis);
        currentTeam = (Team) in.readObject();

        currentTeam.setID(teamID);
        for(Player p:currentTeam.getPlayers())
        {
            p.setTeamID(teamID);
        }

        in.close();
    }
}
