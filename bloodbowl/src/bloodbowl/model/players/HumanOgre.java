/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

/**
 *
 * @author Rishinder
 */
public class HumanOgre extends Player
{
    public HumanOgre()
    {
        ma=5;
        st=5;
        ag=2;
        av=9;
        skillsList.add(Skills.LONER);
        skillsList.add(Skills.BONEHEAD);
        skillsList.add(Skills.MIGHTYBLOW);
        skillsList.add(Skills.TICKSKULL);
        skillsList.add(Skills.THROWTEAMMATE);
        type=PlayerType.CATCHER;
    }
}
