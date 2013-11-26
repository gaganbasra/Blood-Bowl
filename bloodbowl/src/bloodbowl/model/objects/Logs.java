/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.model.objects;

import java.util.Observable;

/**
 * This class is responsible for logging all actions within
 * the game engine
 *
 * @author Philip
 * 
 * 
 */
public class Logs extends Observable {

    private String message;

    public String getLog() {
        return message;
    }

    /**
     * get the recent log and update the logs
     *
     * @param message
     */
    public void log(String message) {
        this.message = message;
        
        updateLog();
    }

    /**
     * Method to notify observers about the log changes
     */
    private void updateLog()
    {
         setChanged();
         notifyObservers();
    }
}
