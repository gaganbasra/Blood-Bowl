/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class HumanBlitzer extends Player
{
    public HumanBlitzer()
    {
        ma=7;
        st=3;
        ag=3;
        av=8;
        skillsList.add(Skills.BLOCK);
        type=PlayerType.BLITZER;
    }
}
