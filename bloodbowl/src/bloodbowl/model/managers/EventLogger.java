/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.model.managers;

import bloodbowl.model.objects.Logs;
import java.util.Observer;

/**
 *
 * @author Philip
 * Class to log messages to output window.
 * 
 */

public class EventLogger {

    private static Logs logs;
    private static Observer logObserver;

    /**
     * Method to set the observer
     * @param observer Add set the observer for the log event
     * 
     */
    public static void setObserver(Observer observer) {
        if (logs == null) {
            logs = new Logs();
            
            logObserver = observer;
            logs.addObserver(logObserver);            
        }
    }

    /**
     * This method log messages
     * @param message takes the log message and log it to the output window
     *
     */
    public static void log(String message) {
        if (logs != null) {
            logs.log(message);
        }
    }
}
