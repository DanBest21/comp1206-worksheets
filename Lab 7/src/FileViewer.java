import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileViewer extends JFrame
{
    private JPanel panel = new JPanel(new FlowLayout());

    public FileViewer()
    {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("File Viewer");
        this.setSize(880, 1000);
        this.setResizable(false);

        createUI();

        this.setVisible(true);
    }

    private void createUI()
    {
        JTextArea textArea = new JTextArea();
        textArea.setColumns(80);
        textArea.setRows(57);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        JButton button = new JButton("Open File");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                String output = "";

                try
                {
                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    while (reader.ready())
                    {
                        output = output + reader.readLine() + "\r\n";
                    }

                    textArea.setText(output);
                }
                catch (IOException ex)
                {
                    ex.getStackTrace();
                }
            }
        });
        panel.add(button);
    }

    public static void main(String[] args) { FileViewer fileViewer = new FileViewer(); }
}
