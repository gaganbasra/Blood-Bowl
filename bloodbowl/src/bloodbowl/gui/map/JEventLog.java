/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.gui.map;

import bloodbowl.model.objects.Logs;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;

/**
 *
 * Extends the JTextArea swing control.
 * Implemented the Observer feature for logging messages to its text area
 * 
 * @author Philip
 */
public class JEventLog extends JTextArea implements Observer{


    /**
     * Update the text area with messages form the observer
     * and move the cursor to the last line to view the latest
     * message
     * 
     * @param o
     * @param arg
     */
      public void update(Observable o, Object arg) {

        if (o instanceof Logs) {
            Logs logger=(Logs)o;
                this.append(String.format("%s\n",logger.getLog()));
                this.setAutoscrolls(true);
          }
    }
}
