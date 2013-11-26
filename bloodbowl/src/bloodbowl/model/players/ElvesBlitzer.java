/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class ElvesBlitzer extends Player
{
    public ElvesBlitzer()
    {
        ma=7;
        st=3;
        ag=4;
        av=7;
        skillsList.add(Skills.BLOCK);
        skillsList.add(Skills.SIDESTEP);
        type=PlayerType.BLITZER; 
    }
}
