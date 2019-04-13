import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class DynamicArray<E>
{
    private E[] array;
    private Class<E> type;
    private int max;

    public DynamicArray(Class<E> type, int max, int size)
    {
        array = (E[]) Array.newInstance(type, size);
        this.type = type;
        this.max = max;
    }

    public E get(int index) throws ArrayIndexOutOfBoundsException
    {
        if (index < 0 || index >= max)
            throw new ArrayIndexOutOfBoundsException();

        if (index > array.length - 1)
            resize();

        return array[index];
    }

    public void set(E element, int index) throws ArrayIndexOutOfBoundsException
    {
        if (index < 0 || index >= max)
            throw new ArrayIndexOutOfBoundsException();

        if (index > array.length - 1)
            resize();

        array[index] = element;
    }

    private void resize()
    {
        E[] temp = array;

        int size = array.length;
        size = size * 10;

        array = (E[])Array.newInstance(type, size);

        System.arraycopy(temp, 0, array, 0, temp.length);
    }

    public static void main(String[] args)
    {
        try
        {
            DynamicArray intArray = new DynamicArray<>(Integer.class, 1000, 10);

            for (int i = 0; i < 10; i++)
            {
                intArray.set(i, i);
            }

            intArray.set(1010, 15);

            System.out.println("The number at index 5 is " + intArray.get(5));
            System.out.println("The number at index 15 is " + intArray.get(15));
            System.out.println("There is no number at index 14 as seen by this null: " + intArray.get(14));

            intArray.set(0, 10000);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
        }

        try
        {
            DynamicArray stringArray = new DynamicArray<>(String.class, 100, 10);

            for (int i = 0; i < 50; i++)
            {
                stringArray.set("null", i);
            }

            stringArray.set("To be or not to be, that is the question.", 69);

            System.out.println(stringArray.get(69));

            stringArray.set("I am the Senate.", 99);

            System.out.println(stringArray.get(99));

            stringArray.set("It's treason then", 100);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
        }
    }
}