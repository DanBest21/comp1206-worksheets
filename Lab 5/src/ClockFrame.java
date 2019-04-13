import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ClockFrame extends JFrame
{
    public ClockFrame()
    {
        this.setSize(550, 650);
        this.setTitle("Analogue Clock");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        this.setContentPane(panel);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(layout);

        Clock clock = new Clock(500);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 6;
        layout.setConstraints(clock, constraints);
        panel.add(clock);

        JLabel labelHour = new JLabel("Hour:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 30, 5, 30);
        layout.setConstraints(labelHour, constraints);
        panel.add(labelHour);

        JSpinner spinnerHour = new JSpinner(new SpinnerNumberModel(12, 1, 12, 1));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 30);
        layout.setConstraints(spinnerHour, constraints);
        panel.add(spinnerHour);

        JLabel labelMinute = new JLabel("Minute:");
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 30);
        layout.setConstraints(labelMinute, constraints);
        panel.add(labelMinute);

        JSpinner spinnerMinute = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 30);
        layout.setConstraints(spinnerMinute, constraints);
        panel.add(spinnerMinute);

        JLabel labelSecond = new JLabel("Second:");
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 5, 30);
        layout.setConstraints(labelSecond, constraints);
        panel.add(labelSecond);

        JSpinner spinnerSecond = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        constraints.gridx = 5;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 30);
        layout.setConstraints(spinnerSecond, constraints);
        panel.add(spinnerSecond);

        JButton buttonApply = new JButton("Apply Changes");
        buttonApply.addActionListener(e -> clock.setTime((int)spinnerHour.getValue(), (int)spinnerMinute.getValue(), (int)spinnerSecond.getValue()));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(15, 25, 5, 30);
        layout.setConstraints(buttonApply, constraints);
        panel.add(buttonApply);

        JToggleButton toggleCurrentTime = new JToggleButton("Show Current Time");
        Timer timer = new Timer(0, null);
        toggleCurrentTime.addActionListener(e -> {
            ActionListener updateTime = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    clock.setTime(Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND));
                    spinnerHour.setValue(Calendar.getInstance().get(Calendar.HOUR));
                    spinnerMinute.setValue(Calendar.getInstance().get(Calendar.MINUTE));
                    spinnerSecond.setValue(Calendar.getInstance().get(Calendar.SECOND));
                }
            };

            if (toggleCurrentTime.isSelected())
            {
                timer.addActionListener(updateTime);
                timer.start();
            }
            else
            {
                timer.stop();
            }
        });
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(15, 5, 5, 30);
        layout.setConstraints(toggleCurrentTime, constraints);
        panel.add(toggleCurrentTime);

        this.setVisible(true);
    }

    public static void main(String[] args) { new ClockFrame(); }
}
