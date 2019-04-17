import java.io.*;

public class JavaCat
{
    private String dirName;
    private String fileName;

    public JavaCat(String dirName, String fileName)
    {
        this.dirName = dirName;
        this.fileName = fileName;
    }

    public boolean isValidDirectory()
    {
        return new File(dirName).isDirectory();
    }

    public String concatenateJavaFiles(String dirName)
    {
        String output = "";

        for (File file : new File(dirName).listFiles())
        {
            try
            {
                String name = file.getName();

                if (file.isDirectory())
                {
                    output = output + concatenateJavaFiles(file.getAbsolutePath());
                }
                else if (name.contains(".") && name.substring(name.lastIndexOf(".")).equals(".java") && !file.getAbsolutePath().equals(this.dirName + "\\" + fileName))
                {
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));

                    output = output + "****************************************************************" + "\r\n";
                    output = output + "** " + name + "\r\n";
                    output = output + "****************************************************************" + "\r\n";

                    while (fileReader.ready())
                    {
                        output = output + fileReader.readLine() + "\r\n";
                    }

                    fileReader.close();

                    output = output + "\r\n";
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return output;
    }

    public void createFile()
    {
        if (isValidDirectory())
        {
            String file = concatenateJavaFiles(dirName);

            file = file.substring(0, file.length() - 4);

            try
            {
                FileWriter fileWriter = new FileWriter(dirName + "\\" + fileName, false);

                fileWriter.write(file);

                fileWriter.close();
            }
            catch (IOException ex)
            {
                ex.getStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        JavaCat javaCat = new JavaCat(args[0], args[1]);

        javaCat.createFile();
    }
}
