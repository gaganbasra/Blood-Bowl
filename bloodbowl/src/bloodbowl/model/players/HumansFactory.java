/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 * Class for implementing Factory for Humans Team
 * @author Rishinder
 */
public class HumansFactory implements PlayersFactory
{
    public Player createCatcher()
    {
        return new HumanCatcher();
    }
    public Player createBlitzer()
    {
        return new HumanBlitzer();
    }
    public Player createLineman()
    {
        return new HumanLineman();
    }
    public Player createOgre()
    {
        return new HumanOgre();
    }
    public Player createThrower()
    {
        return new HumanThrower();
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
