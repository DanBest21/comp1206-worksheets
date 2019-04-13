import javax.swing.*;

public class SynchRollDie extends RollDie
{
    private boolean valLocked = true;

    public SynchRollDie(Die die, JButton button)
    {
        super(die, button);
    }

    public synchronized void run()
    {
        while (!valLocked)
        {
            try
            {
                this.wait();
            }
            catch (InterruptedException ex)
            {
               ex.printStackTrace();
            }
        }

        super.run();
        valLocked = false;
        this.notify();
    }

    public synchronized int getVal() throws InterruptedException
    {
        while (valLocked)
        {
            this.wait();
        }

        valLocked = true;
        this.notify();
        return super.getDieValue();
    }

    public static void main(String[] args)
    {
        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();
        Die die4 = new Die();
        Die die5 = new Die();

        JButton dummyButton = new JButton();

        SynchRollDie rollDie1 = new SynchRollDie(die1, dummyButton);
        SynchRollDie rollDie2 = new SynchRollDie(die2, dummyButton);
        SynchRollDie rollDie3 = new SynchRollDie(die3, dummyButton);
        SynchRollDie rollDie4 = new SynchRollDie(die4, dummyButton);
        SynchRollDie rollDie5 = new SynchRollDie(die5, dummyButton);

        new Thread(rollDie1).start();
        new Thread(rollDie2).start();
        new Thread(rollDie3).start();
        new Thread(rollDie4).start();
        new Thread(rollDie5).start();

        try
        {
            System.out.println("Total roll is: " + (rollDie1.getVal() + rollDie2.getVal() + rollDie3.getVal() + rollDie4.getVal() + rollDie5.getVal()));
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }
}
