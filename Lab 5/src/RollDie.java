import javax.swing.*;
import java.util.Random;

public class RollDie implements Runnable
{
    private Die die;
    private JButton button;

    public RollDie(Die die, JButton button)
    {
        this.die = die;
        this.button = button;
    }

    public void run()
    {
        Random rand = new Random();
        button.setEnabled(false);

        for (int i = 0; i < 20; i++)
        {
            try
            {
                Thread.sleep(20 * i);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }

            int roll = rand.nextInt((6 - 1) + 1) + 1;

            System.out.println("Roll " + (i + 1) + " is: " + roll);
            die.updateVal(roll);
        }

        button.setEnabled(true);
    }
}