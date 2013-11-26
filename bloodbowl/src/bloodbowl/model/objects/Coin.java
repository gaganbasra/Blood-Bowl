/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bloodbowl.model.objects;

import java.util.Random;

/**
 *
 * @author Rishinder
 */
public final class Coin {
    public static final int HEADS=0,TAILS=1;
    static int toss;
    private int choice;
    private int result;
    private Random random;
    public void chooseHeadsOrTails(int choice)
    {
        this.choice=choice;
    }
    public void toss()
    {
        random=new Random();
        result=random.nextInt(2);
    }
    public boolean ifChoiceIsUp()
    {
        if(result==choice)
        {
            return true;
        }
        return false;
    }
}
