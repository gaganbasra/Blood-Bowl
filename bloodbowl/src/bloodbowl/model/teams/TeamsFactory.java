/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.teams;

/**
 * Class for implementing Factory for Game Teams
 * @author Rishinder
 */
public class TeamsFactory {
    /**
     * Method for creating a new Humans team
     * @param ID Team ID
     * @param name Team Name
     * @return Created Team
     */
    public Team createHuman(int ID, String name)
    {
        return new Humans(ID,name);
    }

    /**
     * Method for creating a new Elves team
     * @param ID Team ID
     * @param name Team Name
     * @return Created Team
     */
    public Team createElves(int ID, String name)
    {
        return new Elves(ID,name);
    }

    /**
     * Method for creating a new Orcs team
     * @param ID Team ID
     * @param name Team Name
     * @return Created Team
     */
    public Team createOrcs(int ID, String name)
    {
        return new Orcs(ID,name);
    }
}
