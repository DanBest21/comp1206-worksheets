import java.util.Random;
import java.util.Vector;

public class PrimeChecker
{
    private static Vector<Integer> vector = new Vector<>();

    public static void populateVector()
    {
        Random rand = new Random();

        for (int i = 0; i < 100; i++)
        {
            vector.add(rand.nextInt(300));
        }
    }

    public static void main(String[] args)
    {
        populateVector();

        for (int i = 0; i < 10; i++)
        {
            new Thread(new PrimeTestThread(vector, (i + 1))).start();
        }
    }
}
