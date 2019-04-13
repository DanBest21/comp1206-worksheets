import javax.swing.*;
import java.awt.*;

public class Die extends JPanel
{
    private int i;
    private static final int DICE_SIZE = 250;

    public Die()
    {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(DICE_SIZE, DICE_SIZE));
        this.setSize(DICE_SIZE, DICE_SIZE);
        i = 1;
        repaint();
    }

    public void updateVal(int i)
    {
        this.i = i;
        repaint();
    }

    public int getVal()
    {
        return i;
    }

    public void paintComponent(Graphics g)
    {
        final int DOT_DIAMETER = 35;
        final int DOT_RADIUS = DOT_DIAMETER / 2;

        Graphics2D graphics = (Graphics2D) g;

        super.paintComponent(graphics);

        graphics.setColor(Color.BLACK);

        if (i == 1 || i == 3 || i == 5)
        {
            graphics.fillOval(DICE_SIZE / 2 - DOT_RADIUS, DICE_SIZE / 2 - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
        }

        if (i != 1)
        {
            graphics.fillOval(2 * DOT_DIAMETER - DOT_RADIUS, DICE_SIZE - (2 * DOT_DIAMETER) - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
            graphics.fillOval(DICE_SIZE - (2 * DOT_DIAMETER) - DOT_RADIUS, 2 * DOT_DIAMETER - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
        }

        if (i == 4 || i == 5 || i == 6)
        {
            graphics.fillOval(2 * DOT_DIAMETER - DOT_RADIUS, 2 * DOT_DIAMETER - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
            graphics.fillOval(DICE_SIZE - (2 * DOT_DIAMETER) - DOT_RADIUS, DICE_SIZE - (2 * DOT_DIAMETER) - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
        }

        if (i == 6)
        {
            graphics.fillOval(2 * DOT_DIAMETER - DOT_RADIUS, DICE_SIZE / 2 - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
            graphics.fillOval(DICE_SIZE - (2 * DOT_DIAMETER) - DOT_RADIUS, DICE_SIZE / 2 - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
        }
    }
}
