import java.util.Random;

public class PrimeAppQueue
{
    public static void main(String[] args)
    {
        int size = Integer.parseInt(args[0]);
        int threads = Integer.parseInt(args[1]);

        Random rand = new Random();

        IntListQueue intList = new IntListQueue();

        for (int i = 0; i < size; i++)
        {
            try
            {
                intList.add(rand.nextInt(10000));
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }

        for (int i = 1; i <= threads; i++)
        {
            new PrimeTestThread(intList, i).start();
        }
    }
}
