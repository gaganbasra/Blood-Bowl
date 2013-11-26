package bloodbowl.engine;

import bloodbowl.gui.dialog.PlayerEditor;
import bloodbowl.model.managers.ActionManager;
import bloodbowl.model.managers.GameReferee;
import bloodbowl.model.managers.TeamManager;
import bloodbowl.model.objects.Ball;
import bloodbowl.model.objects.Coin;
import bloodbowl.model.players.PlayerType;
import bloodbowl.model.teams.TeamType;

/**
 * This class has been created to simulate the game on console
 * This is not a requirement for the project
 * The only purpose of this class is to test the game on Console base without playing it.
 * @author Rishinder
 */
public class Simulator
{
    TeamManager teamManager;
    ActionManager actionManager;
    Game gameEngine;
    GameReferee referee;
    Ball ball;
    /************************Constants***********************/
    int TEAM_A = 1, TEAM_B = 2;
    /********************************************************/

    public Simulator()
    {
        gameEngine=Game.getInstance();
        initializeSimulator();
    }

    public Simulator(Game gameEngine)
    {
        this.gameEngine=gameEngine;
        initializeSimulator();
        
    }

    /**
     * Method to initialize the console simulator.
     * Used for setting up the testing environment.
     */
    public final void initializeSimulator()
    {
        teamManager=new TeamManager();
        actionManager=new ActionManager(gameEngine);
        referee=new GameReferee();
        ball=gameEngine.getGameBall();
    }

    /**
     * Method for drawing the map on console
     */
    public void drawMap()
    {
        
        gameEngine.getDugoutA().drawGrid();
        System.out.println("\n");

        gameEngine.getDugoutB().drawGrid();
        System.out.println("\n");
        
        gameEngine.getPitch().drawGrid();
    }

    /**
     * Method to test Referee's performToss method.
     */
    public void tossTest()
    {
        referee.performToss(gameEngine.getTeamA(), gameEngine.getTeamB(), Coin.HEADS);
        System.out.println(referee.getTossMessage());
    }

    /**
     * Method to test Referee's validatePlacement method.
     */
    public void placementTest()
    {
        referee.validatePlacement(gameEngine.getTeamA());
        System.out.println(referee.getErrorMsg());
    }

    /**
     * Method for testing the game kickoff
     */
    public void kickOffTest()
    {
        actionManager.kickOff();
    }

    /**
     * Method to get the ball's location in (row, column) format
     */
    public void displayBallLocation()
    {
        int row=gameEngine.getGameBall().getLocation().getRow();
        int col=gameEngine.getGameBall().getLocation().getColumn();
        System.out.println(row+", "+col);
    }

    /**
     * Method for testing the game
     */
    public void createTeamsForTest()
    {
        teamManager.createTeam(TEAM_A, TeamType.HUMAN_TYPE, "Greek Gods");
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        gameEngine.setTeamA(teamManager.getTeam());

        teamManager.createTeam(TEAM_B, TeamType.ORC_TYPE, "Hell's Angels");
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        teamManager.createPlayer(PlayerType.LINEMAN);
        gameEngine.setTeamB(teamManager.getTeam());
    }

    /**
     * Method for testing the game
     */
    public void placePlayersOnPitchForTest()
    {
        gameEngine.getPlayerAtDugoutA(0,0);
        gameEngine.placePlayerAtPitchCell(5,10);
        gameEngine.getPlayerAtDugoutB(0,0);
        gameEngine.placePlayerAtPitchCell(5,9);
    }

    /**
     * Method for testing player movement across the pitch
     */
    public void moveTest()
    {
        gameEngine.setSelectedPlayer(gameEngine.getPlayerAtPitch(5, 10));
        actionManager.initiateMoveAction();
        actionManager.moveSelectedPlayerAt(gameEngine.getPitch().getCell(5,9));
        actionManager.pickUpBall();
        actionManager.performPass(gameEngine.getPlayerAtPitch(7, 12));

        System.out.println(actionManager.getPickUpMessage());
        System.out.println(actionManager.getPassMessage());
        actionManager.commitMove();
        
    }

    public void testPlayerEditor()
    {
//        gameEngine.setSelectedPlayer(gameEngine.getPlayerAtPitch(5, 9));
//        PlayerEditor pEdit=new PlayerEditor(gameEngine.getSelectedPlayer());
//        pEdit.refreshEditorWithPlayer();
//        pEdit.setVisible(true);
    }

    /**
     * Method for running the test
     */
    public void runSimulation()
    {
        createTeamsForTest();
        gameEngine.placeTeamsInReserves();

        placePlayersOnPitchForTest();
        actionManager.setAttacker(gameEngine.getPlayerAtPitch(5, 10));
        actionManager.setDefender(gameEngine.getPlayerAtPitch(5, 10));
        actionManager.performPush();

        drawMap();
    }

    /**
     * Method responsible for running the simulator
     * @param args console arguments that are not required in this case
     */
    public static void main(String args[])
    {
        Simulator sim=new Simulator();
        sim.runSimulation();
    }
}
