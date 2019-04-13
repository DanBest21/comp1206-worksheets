public class NumberThread extends Thread
{
    private int number;

    public NumberThread(int number)
    {
        super();
        this.number = number;
    }

    public void start()
    {
        System.out.println("Thread " + number + " starting...");
        super.start();
    }

    public void run()
    {
        int sleepDuration = 10000 / number;

        try
        {
            super.sleep(sleepDuration);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }

        interrupt();
    }

    public void interrupt()
    {
        System.out.println("Thread " + number + " stopped.");
        super.interrupt();
    }

    public static void main(String[] args)
    {
        NumberThread thread1 = new NumberThread(1);
        NumberThread thread2 = new NumberThread(2);
        NumberThread thread3 = new NumberThread(3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}