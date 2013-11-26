/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class ElvesCatcher extends Player
{
    public ElvesCatcher()
    {
        ma=8;
        st=3;
        ag=4;
        av=7;
        skillsList.add(Skills.CATCH);
        skillsList.add(Skills.NERVESOFSTEEL);
        type=PlayerType.CATCHER;
    }
}
