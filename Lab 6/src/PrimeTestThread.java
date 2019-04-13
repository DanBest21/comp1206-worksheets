public class PrimeTestThread extends Thread
{
    private int threadNumber;
    private IntList intList;

    public PrimeTestThread(IntList intList, int threadNumber)
    {
        this.intList = intList;
        this.threadNumber = threadNumber;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                int number = intList.get();

                if (number < 0)
                    this.interrupt();
                else if (AePrime.checkPrime(number))
                    System.out.println("[Thread " + threadNumber + "] Int " + number + " is prime.");
                else
                    System.out.println("[Thread " + threadNumber + "] Int " + number + " is not prime.");

                if (this.isInterrupted())
                {
                    System.out.println ("[Thread " + threadNumber + "] IntList is empty, stopping thread.");
                    return;
                }
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
