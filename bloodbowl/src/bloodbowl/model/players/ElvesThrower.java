/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class ElvesThrower extends Player
{
    public ElvesThrower()
    {
        ma=6;
        st=3;
        ag=4;
        av=7;
        skillsList.add(Skills.PASS);
        type=PlayerType.THROWER;
    }
}
