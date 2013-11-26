/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
* Class for implementing Factory for Orcs Team
 * @author Rishinder
 */
public class OrcsFactory implements PlayersFactory
{
    public Player createCatcher()
    {
        return null;
    }
    public Player createBlitzer()
    {
        return new OrcBlitzer();
    }
    public Player createLineman()
    {
        return new OrcLineman();
    }
    public Player createOgre()
    {
        return null;
    }
    public Player createThrower()
    {
        return new OrcThrower();
    }
    public Player createBlackorc()
    {
        return new OrcBlackorc();
    }
    public Player createTroll()
    {
        return new OrcTroll();
    }
    public Player createGoblin()
    {
        return new OrcGoblin();
    }

}
