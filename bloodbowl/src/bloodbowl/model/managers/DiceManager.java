/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.managers;

import java.util.Random;

/**
 * This class manages the various types of dice rolls.
 * For example, if you need a D6 roll, you can call the method rollD6().
 * This method will return a random value from 1-6.
 * Similarly there are other methods for other dice types.
 * @author Rishinder
 */
public final class DiceManager {

    private static Random randomiser=new Random();
    private static int outcome;
    /**
     * Method for a D6 roll
     * @return Random number between 1 and 6
     */
    public static int rollD6()
    {
        outcome=randomiser.nextInt(6)+1;
        EventLogger.log("Dice roll is "+ outcome);
        return outcome;
    }

    /**
     * Method for two D6 rolls
     * @return Random number between 1 and 12
     */
    public static int roll2D6()
    {
        outcome=randomiser.nextInt(11)+2;
        EventLogger.log("Dice roll is "+ outcome);
        return outcome;
    }

    /**
     * Method for a D3 roll
     * @return Random number between 1 and 3
     */
    public static int rollD3()
    {
        outcome = randomiser.nextInt(3)+1;
        EventLogger.log("Dice roll is "+ outcome);
        return outcome;
    }

    /**
     * Method for a D8 roll
     * @return Random number between 0 and 7
     */
    public static int rollD8()
    {
        outcome = randomiser.nextInt(8);
        EventLogger.log("Dice roll is "+ outcome);
        return outcome;
    }

    /**
     * Method for a block dice roll
     * @return Random number between 1 and 5
     */
    public static BlockDiceFaces rollBlock()
    {
        outcome = randomiser.nextInt(5);
        BlockDiceFaces blockDiceOutcome=null;
        switch(outcome)
        {
            case 0:
                blockDiceOutcome=BlockDiceFaces.ATTACKER_DOWN;
                EventLogger.log("Block dice is: Attacker down");
                break;
            case 1:
                blockDiceOutcome=BlockDiceFaces.DEFENDER_DOWN;
                EventLogger.log("Block dice is: Defender down");
                break;
            case 2:
                blockDiceOutcome=BlockDiceFaces.BOTH_DOWN;
                EventLogger.log("Block dice is: Both down");
                break;
            case 3:
                blockDiceOutcome=BlockDiceFaces.PUSHED;
                EventLogger.log("Block dice is: Pushed");
                break;
            case 4:
                blockDiceOutcome=BlockDiceFaces.DEFENDER_STUMBLES;                
                break;
        }
        return blockDiceOutcome;
    }
}
