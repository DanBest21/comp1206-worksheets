public class IntListQueue extends IntList
{
    private Queue queue = new Queue();
    private boolean ready = true;
    private int currentSize = 0;

    public synchronized void add(Integer o) throws InterruptedException
    {
        queue.add(o);
        currentSize++;
    }

    public synchronized Integer get() throws InterruptedException
    {
        while (!ready || isEmpty())
        {
            if (isEmpty())
                return -1;

            this.wait();
        }

        ready = false;

        int value = (Integer)queue.remove();

        ready = true;
        this.notify();

        currentSize--;

        return value;
    }

    public boolean isEmpty()
    {
        return (currentSize <= 0);
    }
}
