import java.util.Random;
import java.util.Vector;

public class PrimeTestThread implements Runnable
{
    private Random rand = new Random();
    private Vector<Integer> vector;
    private int threadNumber;

    public PrimeTestThread(Vector<Integer> vector, int threadNumber)
    {
        this.vector = vector;
        this.threadNumber = threadNumber;
    }

    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            int index = rand.nextInt(100);

            int number = vector.get(index);

            if (AePrime.checkPrime(number))
                System.out.println("[Thread " + threadNumber + "] Int " + index + " is prime: " + number);
            else
                System.out.println("[Thread " + threadNumber + "] Int " + index + " is not prime: " + number);
        }
    }
}
