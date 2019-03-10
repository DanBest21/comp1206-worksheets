import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.lang.Math;

public class RecursiveCircle extends JPanel
{
    private static final double STARTING_WIDTH = 1920.0;
    private static final double CENTRE = STARTING_WIDTH / 4.0;
    private static final int ITERATIONS = 5;

    private static double w = STARTING_WIDTH;

    private int numberOfCirclesAtLevel = 0;
    private double lineStart = 0.0;
    private double radius = w / 6.0;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize((int)(w + (w/100)), (int)w/2);
        frame.setTitle("Recursive Circles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RecursiveCircle rc = new RecursiveCircle();
        frame.setContentPane(rc);
        rc.repaint();

        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(graphics);

        drawCircle(ITERATIONS, graphics);

        numberOfCirclesAtLevel = 0;
        lineStart = 0.0;
        w = STARTING_WIDTH;
        radius = w / 6.0;
    }

    public void drawCircle(int recursiveLevel, Graphics2D graphics)
    {
        int iteration = ITERATIONS - recursiveLevel;

        graphics.setColor(new Color(10, 255 - (40 * iteration), 10));
        Ellipse2D.Double circle = new Ellipse2D.Double(lineStart + w/2 - radius, CENTRE - radius, w/3, w/3);
        graphics.fill(circle);
        numberOfCirclesAtLevel++;

        if (numberOfCirclesAtLevel < Math.pow(3, iteration))
        {
            lineStart = lineStart + w;
            drawCircle(recursiveLevel, graphics);
        }
        else if (recursiveLevel > 1)
        {
            lineStart = 0;
            w = w / 3;
            radius = w / 6;
            numberOfCirclesAtLevel = 0;
            drawCircle(recursiveLevel - 1, graphics);
        }
    }
}
