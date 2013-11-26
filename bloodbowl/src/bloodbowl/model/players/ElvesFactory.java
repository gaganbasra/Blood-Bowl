/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 * Class for implementing Factory for Elves Team
 * @author Rishinder
 */
public class ElvesFactory implements PlayersFactory
{
    public Player createCatcher()
    {
        return new ElvesCatcher();
    }
    public Player createBlitzer()
    {
        return new ElvesBlitzer();
    }
    public Player createLineman()
    {
        return new ElvesLineman();
    }
    public Player createOgre()
    {
        return null;
    }
    public Player createThrower()
    {
        return new ElvesThrower();
    }
    public Player createBlackorc()
    {
        return null;
    }
    public Player createTroll()
    {
        return null;
    }
    public Player createGoblin()
    {
        return null;
    }

}
