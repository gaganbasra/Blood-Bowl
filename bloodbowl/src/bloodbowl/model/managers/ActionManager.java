package bloodbowl.model.managers;

import bloodbowl.engine.Game;
import bloodbowl.model.map.Cell;
import bloodbowl.model.objects.Ball;
import bloodbowl.model.players.Actions;
import bloodbowl.model.players.Player;
import bloodbowl.model.players.PlayerStates;
import bloodbowl.model.players.Skills;
import bloodbowl.model.teams.Team;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to take care of all the actions that the player can do.
 * This class will be responsible for the validity of the actions.
 * It takes care of initiating and committing the action as well.
 * For all such actions it communicates with the Game Engine i.e. the Game Class.
 * @author Rishinder
 * @version 1.0.0
 */
public class ActionManager implements Serializable {

    private Game game;
    private Player selectedPlayer, attacker, defender;
    private boolean isKickedOff = false;
    private boolean tackleIssues = false;
    private String pickUpMessage;
    private String moveMessage;
    private String passMessage;
    private BlockDiceFaces dice1, dice2, dice3;

    public ActionManager(Game game) {
        this.game = game;
    }

    public ActionManager() {
    }

    /**
     * Getter for the message, which displays the status of the Pick-up action made.
     * @return String containing the result of pick up action
     */
    public String getPickUpMessage() {
        return pickUpMessage;
    }

    /**
     * Getter for the message, which displays the status of the move action made.
     * @return String containing the result of move action
     */
    public String getMoveMessage() {
        return moveMessage;
    }

    /**
     * Getter for the message, which displays the status of the pass action made.
     * @return String containing the result of pass action
     */
    public String getPassMessage() {
        return passMessage;
    }

    /**
     * Method that informs the Game about the action initiation
     * @param newGameState Updated state of the game i.e. the current game state.
     */
    public void initiateMoveAction() {
        //game = newGameState;        
        selectedPlayer = game.getSelectedPlayer();
        game.initiateMoveAction();
    }

    /**
     * Method to inform the Game about the action completion
     */
    public void commitMove() {
        if (selectedPlayer != null) {
            selectedPlayer.doneActionInTurn();
        }
        selectedPlayer = null;
        game.commitMoveAction();
    }

    /**
     * Method to check if the player is tackled by an opponent
     */
    public boolean isTackleZone() {
        int totalOpponents = (game.getOpponentTeam()).getTotalPlayers();
        for (int i = 0; i < totalOpponents; i++) {
            if ((((game.getOpponentTeam()).getPlayer(i))).getActive()) {
                if (((game.getOpponentTeam()).getPlayer(i)).isNear(selectedPlayer.getLocation())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to check if the destination is tackled by an opponent
     */
    public int getOpponentsInfluenceOn(Cell destination) {
        int opponentsInfluenceOnDestination = 0;
        int opponentStrength = 0;
        int totalOpponents = (game.getOpponentTeam()).getTotalPlayers();
        for (int i = 0; i < totalOpponents; i++) {
            if ((((game.getOpponentTeam()).getPlayer(i))).getActive()) {
                if (((game.getOpponentTeam()).getPlayer(i)).isNear(destination)
                        && ((game.getOpponentTeam()).getPlayer(i)).getState() != PlayerStates.STUNNED) {
                    opponentsInfluenceOnDestination++;

                    //To get the blocking player with max strength
                    if ((game.getOpponentTeam()).getPlayer(i).getSt() > opponentStrength) {

                        attacker = (game.getOpponentTeam()).getPlayer(i);
                        defender = selectedPlayer;
                        opponentStrength = (game.getOpponentTeam()).getPlayer(i).getSt();
                    }
                }
            }
        }
        return opponentsInfluenceOnDestination;
    }

    /**
     * Method to set the attacker depending upon the strengths of the players involved in the block
     * @param attacker
     */
    public void setAttacker(Player attacker) {
        this.attacker = attacker;
    }

    /**
     * Method to set the defender depending upon the strengths of the players involved in the block
     * @param defender
     */
    public void setDefender(Player defender) {
        this.defender = defender;
    }

    /**
     * Block action, which depends upon the players attacker and defender set by action manager
     */
    public void performBlock(BlockDiceFaces choosenBlockDice) {

        if (attacker.getSt() < defender.getSt()) {
            Player swapPlayer;
            swapPlayer = attacker;
            attacker = defender;
            defender = swapPlayer;
        }
        String AttackerTeamName, DefenderTeamName;
        if(attacker.getTeamID()==1)
        {
            AttackerTeamName=game.getTeamA().getName();
            DefenderTeamName=game.getTeamB().getName();
        }
        else
        {
            AttackerTeamName=game.getTeamB().getName();
            DefenderTeamName=game.getTeamA().getName();
        }
        EventLogger.log("Attacker is from team"+AttackerTeamName);
        EventLogger.log("Defender is from team"+DefenderTeamName);

        EventLogger.log("Chosen Block dice is: " + choosenBlockDice.toString());
        int diceVal = 2;

        switch (choosenBlockDice) {
            case ATTACKER_DOWN:
                diceVal = DiceManager.roll2D6();
                if (diceVal < 7) {
                    attacker.setState(PlayerStates.STUNNED);
                    EventLogger.log("Attacker is stunned");
                } else if (diceVal > 7 && diceVal < 10) {
                    game.placeKnockedPlayerInDugout(attacker);
                    attacker.setState(PlayerStates.KNOCKED);
                    EventLogger.log("Attacker is knocked down");
                } else {
                    game.placeInjuredPlayerInDugout(attacker);
                    attacker.setState(PlayerStates.INJURED);
                    defender.awardPointsForAction(Actions.KNOCKDOWN);
                    EventLogger.log("Attacker is injured");
                }
                break;
            case DEFENDER_DOWN:
                performPush();
                diceVal = DiceManager.roll2D6();
                if (diceVal < 7) {
                    defender.setState(PlayerStates.STUNNED);
                    EventLogger.log("Defender is stunned");
                } else if (diceVal > 7 && diceVal < 10) {
                    game.placeKnockedPlayerInDugout(defender);
                    defender.setState(PlayerStates.KNOCKED);
                    EventLogger.log("Defender is knocked out");
                } else {
                    game.placeInjuredPlayerInDugout(defender);
                    defender.setState(PlayerStates.INJURED);
                    attacker.awardPointsForAction(Actions.KNOCKDOWN);
                    EventLogger.log("Defender is injured");
                }
                break;
            case BOTH_DOWN:
                diceVal = DiceManager.roll2D6();

                if (diceVal < 7) {
                    defender.setState(PlayerStates.STUNNED);
                    EventLogger.log("Defender is stunned");
                } else if (diceVal > 7 && diceVal < 10) {
                    game.placeKnockedPlayerInDugout(defender);
                    defender.setState(PlayerStates.KNOCKED);
                    EventLogger.log("Defender is knocked down");
                } else {
                    game.placeInjuredPlayerInDugout(defender);
                    defender.setState(PlayerStates.INJURED);
                    EventLogger.log("Defender is injured");
                }

                diceVal = DiceManager.roll2D6();
                if (diceVal < 7) {
                    attacker.setState(PlayerStates.STUNNED);
                    EventLogger.log("Attacker is stunned");
                } else if (diceVal > 7 && diceVal < 10) {
                    game.placeKnockedPlayerInDugout(attacker);
                    attacker.setState(PlayerStates.KNOCKED);
                    EventLogger.log("Attacker is knocked out");
                } else {
                    game.placeInjuredPlayerInDugout(attacker);
                    attacker.setState(PlayerStates.INJURED);
                    EventLogger.log("Attacker is injured");
                }
                break;
            case PUSHED:
                performPush();
                EventLogger.log("Defender is pushed");
                break;
            case DEFENDER_STUMBLES:
                performPush();
                break;
        }
    }

    public List<BlockDiceFaces> getDiceOptions() {

        List<BlockDiceFaces> diceOptions = new ArrayList<BlockDiceFaces>();

        if (attacker.getSt() == defender.getSt()) {
            dice1 = DiceManager.rollBlock();
            diceOptions.add(dice1);
            EventLogger.log("Players have equal strengths, so only one block dice roll is performed");            
        } else if (attacker.getSt() > defender.getSt() && attacker.getSt() < defender.getSt() * 2) {
            dice1 = DiceManager.rollBlock();
            dice2 = DiceManager.rollBlock();
            diceOptions.add(dice1);
            diceOptions.add(dice2);
            EventLogger.log("Attacer has more strength, so 2 block dice rolls are performed");                        
        } else {
            dice1 = DiceManager.rollBlock();
            dice2 = DiceManager.rollBlock();
            dice3 = DiceManager.rollBlock();

            diceOptions.add(dice1);
            diceOptions.add(dice2);
            diceOptions.add(dice3);
            EventLogger.log("Attacer has more than double strength, so 3 block dice rolls are performed");            
        }
        return diceOptions;
    }

    /**
     * Method to perform the push to push the defender away from the attacker
     */
    public void performPush() {
        if (attacker.getLocation().getColumn() < defender.getLocation().getColumn()) {
            performPushRight();
        } else if (attacker.getLocation().getColumn() > defender.getLocation().getColumn()) {
            performPushLeft();
        } else if (attacker.getLocation().getRow() < defender.getLocation().getRow()) {
            performPushDown();
        } else {
            performPushUp();
        }
    }

    /**
     * Method to get if player is tackle
     */
    public boolean isTackle() {
        return tackleIssues;
    }

    /**
     * Helper methods for the push action
     */
    public void performPushDown() {
        int directionRandomizer = DiceManager.rollD3();
        if (directionRandomizer == 1 && !game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn() - 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn() - 1));
        } else {
            directionRandomizer = 2;
        }

        if (directionRandomizer == 2 && !game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn() + 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn() + 1));
        } else {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn()));
        }
        EventLogger.log("Player Pushed down");
    }

    /**
     * Helper methods for the push action
     */
    public void performPushUp() {
        int directionRandomizer = DiceManager.rollD3();
        if (directionRandomizer == 1 && !game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() - 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() - 1));
        } else {
            directionRandomizer = 2;
        }

        if (directionRandomizer == 2 && !game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() + 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() + 1));
        } else {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn()));
        }

        EventLogger.log("Player Pushed Upwards");
    }

    /**
     * Helper methods for the push action
     */
    public void performPushRight() {
        int directionRandomizer = DiceManager.rollD3();
        if (directionRandomizer == 1 && !game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() + 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() + 1));
        } else {
            directionRandomizer = 2;
        }

        if (directionRandomizer == 2 && !game.getPitch().getCell(defender.getLocation().getRow(), defender.getLocation().getColumn() + 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow(), defender.getLocation().getColumn() + 1));
        } else {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn() + 1));
        }
        EventLogger.log("Player Pushed towards right");
    }

    /**
     * Helper methods for the push action
     */
    public void performPushLeft() {
        int directionRandomizer = DiceManager.rollD3();
        if (directionRandomizer == 1 && !game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() - 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() - 1, defender.getLocation().getColumn() - 1));
        } else {
            directionRandomizer = 2;
        }

        if (directionRandomizer == 2 && !game.getPitch().getCell(defender.getLocation().getRow(), defender.getLocation().getColumn() - 1).hasPlayer()) {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow(), defender.getLocation().getColumn() - 1));
        } else {
            defender.setLocation(game.getPitch().getCell(defender.getLocation().getRow() + 1, defender.getLocation().getColumn() - 1));
        }
        EventLogger.log("Player Pushed towards left");
    }

    public boolean IsKickedOff() {
        return isKickedOff;
    }

    /**
     * Main method for moving the players by validating the move request.
     * This method also implements the knock-out action and the dodge skill.
     * @param destinationCell Destination in the pitch, where the player has to move
     * @return true if the move is successful, else returns false
     */
    public boolean moveSelectedPlayerAt(Cell destinationCell) {
        
        boolean playerMoved = false;
        tackleIssues = false;
        boolean isTouchDown = false;
        if (selectedPlayer != null && selectedPlayer.isNear(destinationCell)
                && selectedPlayer.getActive() && game.isWaitingForMoveAction()
                && selectedPlayer.canMove() && !destinationCell.hasPlayer()
                && selectedPlayer.getTeamID() == game.getCurrentTeam()) {
            if (isTackleZone()) {
                EventLogger.log("Player moving in Tackle Zone");
                if (breakTackle(getOpponentsInfluenceOn(destinationCell))) {
                    tackleIssues = false;
                    EventLogger.log("Tackle Sucessfull");
                } else if ((selectedPlayer.getSkillsList()).contains(Skills.DODGE)) {
                    if (breakTackle(getOpponentsInfluenceOn(destinationCell))) {
                        tackleIssues = false;
                        EventLogger.log("Dodge player's tackle Sucessfull");
                    }
                } else {
                    tackleIssues = true;
                    EventLogger.log("Tackle Unsucessfull");
                }
            }

            if (tackleIssues) {
                playerMoved = false;
                getOpponentsInfluenceOn(destinationCell);
                EventLogger.log("Player cannot break the tackle because of low dice value.");
                EventLogger.log("Block Dice roll has to be performed.");
                game.setWaitingForBlockResolution(true);
                //performBlock();                
            } else {
                //Logic to check touchdownScored condition
                if (selectedPlayer.getTeamID() == Team.TEAM_A && destinationCell.getColumn() == 25 && game.getGameBall().getPossessor() == selectedPlayer) {
                    isTouchDown = true;
                } else if (selectedPlayer.getTeamID() == Team.TEAM_B && destinationCell.getColumn() == 0 && game.getGameBall().getPossessor() == selectedPlayer) {
                    isTouchDown = true;
                }

                EventLogger.log(String.format("Player at %s moved to %s", selectedPlayer.getLocation(), destinationCell));
                selectedPlayer.setLocation(destinationCell);
                selectedPlayer.decreaseMovementLeft();
                playerMoved = true;
            }
        } else {
            playerMoved = false;
        }

        if (isTouchDown) {
            game.touchdownScored();
            Ball ball = game.getGameBall();
            ball = null;
        }        
        return playerMoved;
    }

    /**
     * Method to check if the tackle has been broken.
     * @return true if the tackle has been broken
     */
    public boolean breakTackle(int dodgeModifier) {
        boolean succcessfullBreak = false;
        int diceFaceValue = DiceManager.rollD6();

        if (diceFaceValue == 6) {
            succcessfullBreak = true;
        } else if (diceFaceValue == 1) {
            succcessfullBreak = false;
        } else if (diceFaceValue + selectedPlayer.getAg() + 1 - dodgeModifier >= 7) {
            succcessfullBreak = true;
        } else {
            succcessfullBreak = false;
        }
        return succcessfullBreak;
    }

    /**
     * Method to perform the pick of the ball during the game play automatically.
     * This means that the player will attempt to pick up the ball as soon as he enters
     * the square containing the ball. Sure hands skill has been implemented in this
     * method as well. If you take a closer look at the else-if block you can see the
     * implementation of sure hands skill.
     *
     * @return
     */
    public boolean pickUpBall() {
        boolean pickUpBallResult = false;
        int diceFaceVal = DiceManager.rollD6();
        int modifier = 1;
        int tackleZoneModifier = 0;

        selectedPlayer = game.getSelectedPlayer();

        if (selectedPlayer != null) {
            tackleZoneModifier = getOpponentsInfluenceOn(selectedPlayer.getLocation());
            modifier -= tackleZoneModifier;
            if (game.getGameBall().getLocation() == selectedPlayer.getLocation()) {
                if (selectedPlayer.getAg() + diceFaceVal + modifier >= 7) {
                    game.getGameBall().setPossessor(selectedPlayer);
                    pickUpBallResult = true;
                    pickUpMessage = "Player picked up the ball successfully";
                } else if ((selectedPlayer.getSkillsList()).contains(Skills.SUREHANDS)) {
                    diceFaceVal = DiceManager.rollD6();
                    if (selectedPlayer.getAg() + diceFaceVal + modifier >= 7) {
                        game.getGameBall().setPossessor(selectedPlayer);
                        pickUpBallResult = true;
                        pickUpMessage = "Sure hands player picked up the ball successfully";
                    }
                } else {
                    game.getGameBall().scatterToAdjacentCell();
                    pickUpMessage = "Player could not pick up the ball successfully";

                }
            } else {
                pickUpMessage = "There is no ball at this location.";
            }
        }

        EventLogger.log(pickUpMessage);
        return pickUpBallResult;
    }

    /**
     * Method to implement the pass action in the game.
     * @param recepientPlayer The receiver of the ball
     */
    public boolean performPass(Player recepientPlayer) {
        boolean passSuccessful = false;
        Pass passType = Pass.QUICK;
        String passTypeString = "";
        int diceFaceVal = DiceManager.rollD6();
        int modifier = 0;
        int tackleZoneModifier = 0;
        passMessage = "";

        selectedPlayer = game.getSelectedPlayer();

        if (game.getGameBall().getPossessor() != null) {

            if (selectedPlayer.getActive()
                    && recepientPlayer.getActive()
                    && selectedPlayer.getTeamID() == recepientPlayer.getTeamID()
                    && selectedPlayer.getTeamID() == game.getCurrentTeam()
                    && teamCanPerformPass()
                    && game.getGameBall().getPossessor() == selectedPlayer) {

                passType = computePassType(selectedPlayer, recepientPlayer);

                switch (passType) {
                    case QUICK:
                        modifier = 1;
                        passTypeString = "Quick";
                        break;
                    case SHORT:
                        modifier = 0;
                        passTypeString = "Short";
                        break;
                    case LONG:
                        modifier = -1;
                        passTypeString = "Long";
                        break;
                    case LONGBOMB:
                        modifier = -2;
                        passTypeString = "Long Bomb";
                        break;
                }

                tackleZoneModifier = getOpponentsInfluenceOn(selectedPlayer.getLocation());
                modifier -= tackleZoneModifier;

                if (selectedPlayer.getAg() + diceFaceVal + modifier >= 7) {
                    selectedPlayer.awardPointsForAction(Actions.PASS);
                    passSuccessful = true;
                    performCatch(recepientPlayer, selectedPlayer, passSuccessful);
                    passMessage = "Pass of type: " + passTypeString + " is successful";
                } else {
                    passSuccessful = false;
                    performCatch(recepientPlayer,selectedPlayer, passSuccessful);
                    passMessage = "Pass of type: " + passTypeString + " is not successful";
                }
            }
        }
        EventLogger.log(passMessage);
        return passSuccessful;
    }

    /**
     * Method to implement the catch action
     * @param recepientPlayer Player who is catching the ball
     * @param passingPlayer Player who is passing the ball
     * @param passIsSuccess Result of the pass action
     */
    public void performCatch(Player recepientPlayer, Player passingPlayer, boolean passIsSuccess) {
        int tackleZoneModifier = 0, modifier = 0;
        if (DiceManager.rollD6() == 6) {
            game.getGameBall().setPossessor(recepientPlayer);
            EventLogger.log("Ball catched successfully because of a 6 on dice roll!");
        } else if (DiceManager.rollD6() == 1) {
            game.getGameBall().setLocation(recepientPlayer.getLocation().getRow(), recepientPlayer.getLocation().getColumn());
            game.getGameBall().scatterToAdjacentCell();
            EventLogger.log("Ball not catched successfully");
        } else {
            if (passIsSuccess) {
                modifier = 1;
            } else {
                modifier = 0;
            }
            tackleZoneModifier = getOpponentsInfluenceOn(recepientPlayer.getLocation());
            modifier -= tackleZoneModifier;
            if (DiceManager.rollD6() + recepientPlayer.getAg() + modifier >= 7) {
                passingPlayer.awardPointsForAction(Actions.PASS);
                game.getGameBall().setPossessor(recepientPlayer);
                EventLogger.log("Ball catched successfully");

            } else {
                game.getGameBall().setLocation(recepientPlayer.getLocation().getRow(), recepientPlayer.getLocation().getColumn());
                game.getGameBall().scatterToAdjacentCell();
                game.getGameBall().setPossessor(null);
                EventLogger.log("Ball not catched successfully");
            }
        }
    }

    /**
     * Method to compute the pass type based upon the concept of the pass ruler.
     * Pass ruler has been considered as the distance between the two players.
     * @param thrower The player making the pass
     * @param catcher The player who is supposed to receive the ball
     * @return Type of pass depending upon the rules.
     */
    public Pass computePassType(Player thrower, Player catcher) {
        Pass passType;
        int throwerRow, throwerColumn, catcherRow, catcherColumn, rowDiff, colDiff, displacement;

        throwerRow = thrower.getLocation().getRow();
        catcherRow = catcher.getLocation().getRow();
        throwerColumn = thrower.getLocation().getColumn();
        catcherColumn = catcher.getLocation().getColumn();

        rowDiff = throwerRow - catcherRow;
        colDiff = throwerColumn - catcherColumn;

        displacement = (int) Math.sqrt(rowDiff * rowDiff + colDiff * colDiff);
        if (displacement < 4) {
            passType = Pass.QUICK;
        } else if (displacement >= 4 && displacement < 7) {
            passType = Pass.SHORT;
        } else if (displacement >= 7 && displacement < 11) {
            passType = Pass.LONG;
        } else {
            passType = Pass.LONGBOMB;
        }
        return passType;
    }

    /**
     * Method to check if a pass has been done by some player of this team.
     * Made private so that only methods (i.e. performPass) in ActionManager can access it.
     * @return true if no player in the team has done a pass action.
     */
    private boolean teamCanPerformPass() {
        boolean passAllowed = false;

        if (game.getCurrentTeam() == Team.TEAM_A) {
            if (!game.getTeamA().getPlayerHasMadePass()) {
                passAllowed = true;
            }
        } else {
            if (!game.getTeamB().getPlayerHasMadePass()) {
                passAllowed = true;
            }
        }
        return passAllowed;
    }

    /**
     * Method to implement Game Kick-Off
     */
    public void kickOff() {
        if (game == null) {
            game = Game.getInstance();
        }
        Ball ball = game.getGameBall();
        int directionForKickOff = DiceManager.rollD8();
        int distanceForKickOff = DiceManager.rollD6();

        isKickedOff = true;

        switch (directionForKickOff) {
            case 0:
                ball.moveBottomLeftBy(distanceForKickOff);
                break;
            case 1:
                ball.moveBottomRightBy(distanceForKickOff);
                break;
            case 2:
                ball.moveDownBy(distanceForKickOff);
                break;
            case 3:
                ball.moveUpBy(distanceForKickOff);
                break;
            case 4:
                ball.moveRightBy(distanceForKickOff);
                break;
            case 5:
                ball.moveLeftBy(distanceForKickOff);
                break;
            case 6:
                ball.moveTopLeftBy(distanceForKickOff);
                break;
            case 7:
                ball.moveTopRightBy(distanceForKickOff);
                break;
        }

        ball.scatterToAdjacentCell();

        EventLogger.log("Ball kicked off");
    }
}
