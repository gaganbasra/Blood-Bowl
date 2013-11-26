/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.model.managers;

import bloodbowl.engine.Game;
import bloodbowl.model.map.PitchCell;
import bloodbowl.model.objects.Coin;
import bloodbowl.model.players.Player;
import bloodbowl.model.teams.Team;

/**
 * The GameReferee is the class which is responsible for ensuring fair game play
 * It contains methods to check if the player placements is correct
 * It keeps track of turns and counts the turns
 * It does the toss
 * It keeps scores for each team
 * @author Rishinder
 */
public class GameReferee {

    private StringBuilder errorMsg;
    private String tossMessage;
    private int teamATurnCount = 0;
    private int teamBTurnCount = 0;
    private Game gameEngine;

    public GameReferee(Game gameEngine)
    {
        this.gameEngine=gameEngine;
    }

    public GameReferee()
    {
        gameEngine=Game.getInstance();
    }

    /**
     * Method to validate team placements in the game
     * @param t Reference to team for which validation is performed
     * @return true if the placement is valid
     */
    public boolean validatePlacement(Team t) {
        errorMsg = new StringBuilder("\n");

        boolean validated = false;
        boolean validatePitch = true;
        boolean validateWideZoneUpper = true;
        boolean validateWideZoneLower = true;
        boolean validateScrimmageZone = true;

        int playersInWideZoneUpper = 0;
        int playersInWideZoneLower = 0;
        int playersInScrimmageZone = 0;
        int playersOnPitch = 0;
        int playersOnLeft = 0;
        int playersOnRight = 0;

        /**
         * First loop to check if players are placed on the side of the team
         */
        if (t.getID() == 1) {
            for (Player p1 : t.getPlayers()) {
                if ((p1.getLocation()).getColumn() < 12 && p1.getActive()) {
                    playersOnLeft += 1;
                }
            }
        } else {
            for (Player p2 : t.getPlayers()) {
                if ((p2.getLocation()).getColumn() > 13 && p2.getActive()) {
                    playersOnRight += 1;
                }
            }
        }


        if (t.getID() == 1 && playersOnLeft == 0) {
            errorMsg.append("Players should be placed on the left\n");
            validatePitch = false;
        }

        if (t.getID() == 2 && playersOnRight == 0) {
            errorMsg.append("Players should be placed on the Right\n");
            validatePitch = false;
        }


        /**
         * Second loop to check if the total number of players on pitch are within the limit
         */
        for (Player p : t.getPlayers()) {
            if (p.getActive()) {
                playersOnPitch += 1;
            }
        }
        if (playersOnPitch > 11) {
            errorMsg.append("More than 11 players on pitch\n");
            validatePitch = false;
        }
        /**
         * Last loop to check if the players have been placed as per the zone rules.
         */
        for (Player p : t.getPlayers()) {
            int pitchZone;
            if (p.getActive()) {                
                pitchZone = (p.getLocation()).getZone();

                switch (pitchZone) {
                    case PitchCell.END_ZONE:
                        errorMsg.append("Player placed on End Zone\n");
                        validatePitch = false;
                        break;
                    case PitchCell.SCRIMMAGE_ZONE:
                        playersInScrimmageZone += 1;
                        break;
                    case PitchCell.WIDE_ZONE_UPPER:
                        playersInWideZoneUpper += 1;
                        break;
                    case PitchCell.WIDE_ZONE_LOWER:
                        playersInWideZoneLower += 1;
                        break;
                }
            }

        }
        if (playersInScrimmageZone < 3) {
            validateScrimmageZone = false;
            System.out.println("Number of players in scrimmage zone= " + playersInScrimmageZone);
            errorMsg.append("Minimum 3 players on Scrimmage Line are required\n");
        } else if (playersInWideZoneUpper > 2) {
            validateWideZoneUpper = false;
            errorMsg.append("More than 2 players in Upper Wide Zone\n");
        } else if (playersInWideZoneLower > 2) {
            validateWideZoneLower = false;
            errorMsg.append("More than 2 players in Lower Wide Zone\n");
        }

        if (validatePitch && validateScrimmageZone && validateWideZoneUpper && validateWideZoneLower) {
            errorMsg.append("\nValidated placement of players\n");
            validated = true;
        }
        return validated;
    }

    /**
     * Method to get the error message depending upon the validate placement method
     * @return String containing validation result
     */
    public String getErrorMsg() {
        return errorMsg.toString();
    }

    /**
     * Method to perform the game toss at the beginning of the game.
     * This method will also assign the current active team on game start.
     * @param tossingTeam The team which calls.
     * @param waitingTeam The team which watches.
     * @param chosenSide Chosen side of the coin by the tossing Team.
     * @return true if the coin falls with the chosen side up.
     */
    public boolean performToss(Team tossingTeam, Team waitingTeam, int chosenSide) {
        Coin coin = new Coin();
        coin.chooseHeadsOrTails(chosenSide);
        coin.toss();

        boolean outcome;

        if (coin.ifChoiceIsUp()) {
            Game.getInstance().setCurrentTeam(tossingTeam.getID());
            tossMessage = tossingTeam.getName() + " won the toss";
            outcome = true;

        } else {
            Game.getInstance().setCurrentTeam(waitingTeam.getID());
            tossMessage = waitingTeam.getName() + " won the toss";
            outcome = false;
        }

        //To be deleted [begin]
        Game.getInstance().setCurrentTeam(tossingTeam.getID());
        tossMessage = tossingTeam.getName() + " won the toss";
        outcome = true;
        //To be deleted [end]
        
        waitingTeam.initializePlayers();
        tossingTeam.initializePlayers();

        return outcome;
    }

    /**
     * Method to get the toss result in the string message form.
     * @return message for the toss result.
     */
    public String getTossMessage() {
        return tossMessage;
    }

    public int getTeamATurnCount() {
        return teamATurnCount;
    }

    public int getTeamBTurnCount() {
        return teamBTurnCount;
    }

    /**
     * Method to swap turns during game play and update the game counter.
     * Will be initiated by the coaches.
     */
    public void swapTurn() {
        int currentTeam = gameEngine.getCurrentTeam();
         
        if(!gameEngine.isGameIsOver())
        {
            if(teamBTurnCount>7 || teamATurnCount>7 )
            {
                gameEngine.setGameIsOver();
                gameEngine.setCurrentTeam(0);
                EventLogger.log("Game is over");
            }
            if (currentTeam == Team.TEAM_A) {
                gameEngine.getTeamA().turnComplete();
                gameEngine.setCurrentTeam(Team.TEAM_B);
                teamBTurnCount += 1;

            } else {
                gameEngine.getTeamB().turnComplete();
                gameEngine.setCurrentTeam(Team.TEAM_A);

                teamATurnCount += 1;
            }
        }
    }
}
