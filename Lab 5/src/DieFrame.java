import javax.swing.*;
import java.awt.*;

public class DieFrame extends JFrame
{
    public DieFrame()
    {
        this.setSize(500, 600);
        this.setTitle("Dice Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        this.setContentPane(panel);
        panel.setLayout(new FlowLayout());

        Die die = new Die();
        panel.add(die);

        JButton buttonRoll = new JButton("Roll Dice");
        RollDie rollDie = new RollDie(die, buttonRoll);
        buttonRoll.addActionListener(e -> {
            new Thread(rollDie).start();
        });
        panel.add(buttonRoll);

        this.setVisible(true);
    }

    public static void main(String[] args) { new DieFrame(); }
}
