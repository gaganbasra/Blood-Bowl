/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.engine;

import bloodbowl.model.players.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mavencode
 */
public class Test {

    public void serialize() throws FileNotFoundException, IOException {
        String dirName = "SavedGames";
        String filename = "SavedGames/boolean.test";

        File dirSavedGame = new File(dirName);

        if (!dirSavedGame.isDirectory()) {
            dirSavedGame.mkdir();
        }
        if (dirSavedGame.isDirectory()) {
            FileOutputStream fos = null;
            ObjectOutputStream out = null;

            boolean test=true;
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(test);
            out.close();
        }
    }

    public void deserialize() throws FileNotFoundException, IOException, ClassNotFoundException
    {
          String dirName = "SavedGames";
        String filename = "SavedGames/testgame";

        File dirSavedGame = new File(dirName);

        FileInputStream fis = null;
        ObjectInputStream in = null;

        fis = new FileInputStream(filename);
        in = new ObjectInputStream(fis);
        Game game = (Game) in.readObject();
        in.close();

        for (Player p: game.getTeamB().getPlayers())
        {
            System.out.println("Player status: "+ p.getActive());
        }

    }

    public static void main(String[]args)
    {
        try {
            Test t = new Test();
            t.serialize();
            t.deserialize();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
