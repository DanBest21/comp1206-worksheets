public class MinInt
{
    int[] arr = {24,52,74,9,34,23,64,34};

    public static void main(String[] args)
    {
        MinInt m = new MinInt();
        System.out.println("Minimum is: " + m.findMin(0));
    }

    public int findMin(int index)
    {
        if (index == arr.length - 1)
        {
            return arr[index];
        }

        int currentMinValue = findMin(index + 1);

        if (arr[index] < currentMinValue)
        {
            return arr[index];
        }
        else
        {
            return currentMinValue;
        }
    }
}