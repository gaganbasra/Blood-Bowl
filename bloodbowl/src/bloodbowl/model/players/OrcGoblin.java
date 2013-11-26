/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class OrcGoblin extends Player
{
    public OrcGoblin()
    {
        ma=6;
        st=2;
        ag=3;
        av=7;
        skillsList.add(Skills.RIGHTSTUFF);
        skillsList.add(Skills.DODGE);
        skillsList.add(Skills.STUNTY);
        type=PlayerType.GOBLIN;
    }
}
