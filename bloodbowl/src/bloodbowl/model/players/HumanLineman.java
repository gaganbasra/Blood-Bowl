/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.players;

import java.util.ArrayList;

/**
 *
 * @author Rishinder
 */
public class HumanLineman extends Player
{
    public HumanLineman()
    {
        ma=6;
        st=3;
        ag=3;
        av=9;
        type=PlayerType.LINEMAN;
    }
}
