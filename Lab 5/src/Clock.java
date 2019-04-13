import javax.swing.*;
import java.awt.*;

public class Clock extends JComponent
{
    private static final int HOURS = 12;
    private static final int MINUTES = 60;
    private static final int SECONDS = 60;

    private int diameter;
    private int radius;
    private int h;
    private int m;
    private int s;

    public Clock(int diameter)
    {
        this.diameter = diameter;
        radius = diameter / 2;
        this.setPreferredSize(new Dimension(diameter + 5, diameter + 5));
    }

    public void setTime(int h, int m, int s)
    {
        this.h = h;
        this.m = m;
        this.s = s;

        repaint();
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(graphics);

        graphics.setColor(Color.WHITE);
        graphics.fillOval(0, 0, diameter, diameter);
        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(5));
        graphics.drawOval(0, 0, diameter, diameter);

        int x = radius;
        int y = 0;
        int numberMarker = 12;

        Font font = new Font("Dialog", Font.BOLD, 24);
        graphics.setFont(font);

        for (int i = 0; i < MINUTES; i++)
        {
            if (i % 5 == 0)
            {
                graphics.fillRect(x, y, 5, 20);

                if (numberMarker >= 10)
                {
                    graphics.drawString(String.valueOf(numberMarker), x - 12, y + 50);
                }
                else
                {
                    graphics.drawString(String.valueOf(numberMarker), x - 3, y + 50);
                }

                if (numberMarker == 12)
                {
                    numberMarker = 1;
                }
                else
                {
                    numberMarker++;
                }
            }
            else
            {
                graphics.fillRect(x, y, 3, 10);
            }

            graphics.rotate(Math.toRadians(360 / MINUTES), radius, radius);
        }

        graphics.setColor(Color.BLACK);
        int[] xPointsHour = {radius - 8, radius - 2, radius + 2, radius + 8};
        int[] yPointsHour = {radius + 30, y + 90, y + 90, radius + 30};
        Polygon hourHand = new Polygon(xPointsHour, yPointsHour, xPointsHour.length);
        graphics.rotate(Math.toRadians((360.0 / HOURS) * h), radius, radius);
        graphics.rotate(Math.toRadians((360.0 / HOURS / MINUTES) * m), radius, radius);
        graphics.rotate(Math.toRadians((360.0 / HOURS / MINUTES / SECONDS) * s), radius, radius);
        graphics.fillPolygon(hourHand);
        graphics.rotate(Math.toRadians(-(360.0 / HOURS) * h), radius, radius);
        graphics.rotate(Math.toRadians(-(360.0 / HOURS / MINUTES) * m), radius, radius);
        graphics.rotate(Math.toRadians(-(360.0 / HOURS / MINUTES / SECONDS) * s), radius, radius);

        int[] xPointsMinute = {radius - 8, radius - 2, radius + 2, radius + 8};
        int[] yPointsMinute = {radius + 60, y + 60, y + 60, radius + 60};
        Polygon minuteHand = new Polygon(xPointsMinute, yPointsMinute, xPointsMinute.length);
        graphics.rotate(Math.toRadians((360.0 / MINUTES) * m), radius, radius);
        graphics.rotate(Math.toRadians((360.0 / MINUTES / SECONDS) * s), radius, radius);
        graphics.fillPolygon(minuteHand);
        graphics.rotate(Math.toRadians(-(360.0 / MINUTES) * m), radius, radius);
        graphics.rotate(Math.toRadians(-(360.0 / MINUTES / SECONDS) * s), radius, radius);

        graphics.setColor(Color.RED);
        int[] xPointsSecond = {radius - 4, radius - 8, radius - 8, radius - 1, radius - 1, radius + 1, radius + 1, radius + 8, radius + 8, radius + 4};
        int[] yPointsSecond = {radius + 120, radius + 110, radius + 40, radius + 30, y + 60, y + 60, radius + 30, radius + 40, radius + 110, radius + 120};
        Polygon secondHand = new Polygon(xPointsSecond, yPointsSecond, xPointsSecond.length);
        graphics.rotate(Math.toRadians((360.0 / SECONDS) * s), radius, radius);
        graphics.fillPolygon(secondHand);
        graphics.rotate(Math.toRadians(-(360.0 / SECONDS) * s), radius, radius);

        int pivotDiameter = diameter / 20;
        int pivotRadius = pivotDiameter / 2;

        graphics.fillOval(radius - pivotRadius, radius - pivotRadius, pivotDiameter, pivotDiameter);
    }
}
