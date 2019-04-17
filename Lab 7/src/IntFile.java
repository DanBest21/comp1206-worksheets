import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class IntFile
{
    private File byteFile = new File("C:/Users/Dan/Desktop/byteFile.txt");
    private File charFile = new File("C:/Users/Dan/Desktop/charFile.txt");

    public void createByteFile() throws IOException
    {
        FileOutputStream fos = new FileOutputStream(byteFile);
        String contents = generateFileContents();

        fos.write(contents.getBytes());

        fos.close();
    }

    public void createCharFile() throws IOException
    {
        FileWriter fw = new FileWriter(charFile);
        String contents = generateFileContents();

        fw.write(contents);

        fw.close();
    }

    public String generateFileContents()
    {
        Random rand = new Random();

        String contents = "";

        for (int i = 0; i < 10000; i++)
        {
            contents = (i == 10000 - 1) ? contents + rand.nextInt(100001) : contents + rand.nextInt(100001) + "\r\n";
        }

        return contents;
    }

    public static void main(String[] args)
    {
        IntFile intFile = new IntFile();

        try
        {
            intFile.createByteFile();
            intFile.createCharFile();
        }
        catch (IOException ex)
        {
            ex.getStackTrace();
        }
    }
}
