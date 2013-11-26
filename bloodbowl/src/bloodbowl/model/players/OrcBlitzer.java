/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class OrcBlitzer extends Player
{
    public OrcBlitzer()
    {
        ma=6;
        st=3;
        ag=3;
        av=9;
        skillsList.add(Skills.BLOCK);
        type=PlayerType.BLITZER;
    }

}
