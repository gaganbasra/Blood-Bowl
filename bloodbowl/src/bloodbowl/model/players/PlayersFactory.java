/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 * Players Factory Interface for Player Factories for different team types.
 * @author Rishinder
 */
public interface PlayersFactory {

    /**
     * Factory Method for creating a Lineman for a specific team
     * @return Player of Type Lineman
     */
    public Player createLineman();

    /**
     * Factory Method for creating a Blackorc for a specific team
     * @return Player of Type Blackorc
     */
    public Player createBlackorc();

    /**
     * Factory Method for creating a Blitzer for a specific team
     * @return Player of Type Blitzer
     */
    public Player createBlitzer();

    /**
     * Factory Method for creating a Catcher for a specific team
     * @return Player of Type Catcher
     */
    public Player createCatcher();

    /**
     * Factory Method for creating a Goblin for a specific team
     * @return Player of Type Goblin
     */
    public Player createGoblin();

    /**
     * Factory Method for creating a Ogre for a specific team
     * @return Player of Type Ogre
     */
    public Player createOgre();

    /**
     * Factory Method for creating a Thrower for a specific team
     * @return Player of Type Thrower
     */
    public Player createThrower();

    /**
     * Factory Method for creating a Troll for a specific team
     * @return Player of Type Troll
     */
    public Player createTroll();
}
