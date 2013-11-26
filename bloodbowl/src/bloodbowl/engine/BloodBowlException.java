/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.engine;

/**
 *
 * @author Philip
 *
 * The class BloodBowlException extends Exception that indicates
 * conditions that the game engine application might want to catch.
 */
public class BloodBowlException extends Exception {

    /**
     * Constructs a new BloodBowlException with the specified detail message.
     * 
     * @param message
     */
    public BloodBowlException(String message) {
        super(message);
    }
}
