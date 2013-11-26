/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.model.managers;

import bloodbowl.engine.BloodBowlException;
import bloodbowl.engine.Game;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible for Managing the state of the Game.
 * It has methods for Saving and loading the game.
 * @author Rishinder
 */
public class GameManager {

    Game gameEngine;
    private static GameManager instance = null;

    private GameManager() {
        gameEngine = Game.getInstance();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Getter for Game Engine
     * @return Game Engine
     */
    public Game getGameEngine() {
        return gameEngine;
    }

    /**
     * Setter for gameEngine
     * @param gameEngine New state of GameEngine
     */
    public void setGameEngine(Game gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Getter method for getting the list of saved games in the system.
     * @return list of saved games
     */
    public ArrayList<String> getSavedGames() {
        File dirSavedGames = new File("SavedGames");

        ArrayList<String> gameNames = null;

        if (!dirSavedGames.exists()) {
            dirSavedGames.mkdir();
        }
        if (dirSavedGames.isDirectory()) {
            gameNames = new ArrayList(Arrays.asList(dirSavedGames.list()));
            if (gameNames.contains("CVS")) {
                gameNames.remove("CVS");
            }
        }
        return gameNames;
    }

    /**
     * Method to save the Game in the SavedGames directory.
     * The Game is saved as a file with the same name as that of the game.
     * @param gameName Any name given to the game
     * @throws BloodBowlException To handle such cases where the directory for saved teams is not there    
     */
    public void saveGame(String gameName) throws BloodBowlException {
        try {
            String dirName = "SavedGames";
            String filename = "SavedGames/" + gameName.toLowerCase();

            File dirSavedGame = new File(dirName);
            if (!dirSavedGame.isDirectory()) {
                dirSavedGame.mkdir();
            }
            if (dirSavedGame.isDirectory()) {
                FileOutputStream fos = null;
                ObjectOutputStream out = null;

                fos = new FileOutputStream(filename);
                out = new ObjectOutputStream(fos);
                out.writeObject(gameEngine);
                out.close();
            }

            EventLogger.log("Game saved");
        } catch (FileNotFoundException fileEx) {
            throw new BloodBowlException("Error saving game, Check game name");
        } catch (IOException ex) {
            throw new BloodBowlException("An error occurs while saving game, please try again");
        }
    }

    /**
     * Method to restore/load a saved game as the current game.
     * @param gameName Game Name, in reality the file name
     * @throws BloodBowlException To handle issues where the applicable team class is not found
     */
    public Game restoreGame(String gameName) throws BloodBowlException {

        Game engine =null;
        try {
            String filename = "SavedGames/" + gameName;
            FileInputStream fis = null;
            ObjectInputStream in = null;

            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            engine = (Game) in.readObject();
            in.close();

            EventLogger.log("Game restored");


        } catch (ClassNotFoundException ex) {
            throw new BloodBowlException("An error occurs while loading saved game, please try again");
        } catch (FileNotFoundException fileEx) {
            throw new BloodBowlException("Error loaing game, Check game name");
        } catch (IOException ex) {
            throw new BloodBowlException("An error occurs while loading saved game, please try again");
        }
        return engine;
    }
}
