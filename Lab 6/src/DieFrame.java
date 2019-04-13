import javax.swing.*;
import java.awt.*;

public class DieFrame extends JFrame
{
    public DieFrame()
    {
        this.setSize(550, 850);
        this.setTitle("Die Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        this.setContentPane(panel);
        panel.setLayout(new FlowLayout());

        JButton buttonRoll = new JButton("Roll Die");

        Die die1 = new Die();
        SynchRollDie rollDie1 = new SynchRollDie(die1, buttonRoll);
        panel.add(die1);

        Die die2 = new Die();
        SynchRollDie rollDie2 = new SynchRollDie(die2, buttonRoll);
        panel.add(die2);

        Die die3 = new Die();
        SynchRollDie rollDie3 = new SynchRollDie(die3, buttonRoll);
        panel.add(die3);

        Die die4 = new Die();
        SynchRollDie rollDie4 = new SynchRollDie(die4, buttonRoll);
        panel.add(die4);

        Die die5 = new Die();
        SynchRollDie rollDie5 = new SynchRollDie(die5, buttonRoll);
        panel.add(die5);

        JLabel totalValue = new JLabel();
        buttonRoll.addActionListener(e -> {

            new Thread(rollDie1).start();
            new Thread(rollDie2).start();
            new Thread(rollDie3).start();
            new Thread(rollDie4).start();
            new Thread(rollDie5).start();

            try
            {
                totalValue.setText("Total roll is: " +
                        (rollDie1.getVal() +
                                rollDie2.getVal() +
                                rollDie3.getVal() +
                                rollDie4.getVal() +
                                rollDie5.getVal()));
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }

        });
        panel.add(buttonRoll);
        panel.add(totalValue);


        this.setVisible(true);
    }

    public static void main(String[] args) { new DieFrame(); }
}
