import java.util.Vector;

public class IntList
{
    private Vector<Integer> vector = new Vector<>();
    private boolean ready = true;

    public synchronized void add(Integer o) throws InterruptedException
    {
        vector.add(o);
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

        int value = vector.get(0);
        vector.remove(0);

        ready = true;
        this.notify();

        return value;
    }

    public boolean isEmpty()
    {
        return vector.isEmpty();
    }
}
