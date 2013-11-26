/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class OrcTroll extends Player
{
    public OrcTroll()
    {
        ma=4;
        st=5;
        ag=1;
        av=9;
        skillsList.add(Skills.MIGHTYBLOW);
        skillsList.add(Skills.REGENERATION);
        skillsList.add(Skills.REALLYSTUPID);
        skillsList.add(Skills.THROWTEAMMATE);
        type=PlayerType.TROLL;
    }

}
