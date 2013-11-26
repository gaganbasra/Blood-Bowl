/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class HumanCatcher extends Player
{
    public HumanCatcher()
    {
        ma=8;
        st=2;
        ag=3;
        av=7;
        skillsList.add(Skills.CATCH);
        skillsList.add(Skills.DODGE);
        type=PlayerType.CATCHER;
    }
}
