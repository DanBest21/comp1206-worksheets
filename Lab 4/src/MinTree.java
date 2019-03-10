public class MinTree
{
    Tree tree = new Tree( 24,
            new Tree( 45,
                    null ,
                    new Tree(8, null , new Tree (31, null , new Tree (21, null , null ) )) ) ,
            new Tree ( 17,
                    new Tree (74, new Tree (7, new Tree (11, null , null ) , null ), null ) ,
                    null ) );

    public static void main(String[] args)
    {
        MinTree mt = new MinTree();
        System.out.println("Minimum is: " + mt.findMin(mt.tree));
    }

    public int findMin(Tree tree)
    {
        int currentMinValue = tree.getVal();
        int subtreeMinValue;

        if (tree.left() != null)
        {
            subtreeMinValue = findMin(tree.left());
            currentMinValue = (currentMinValue < subtreeMinValue) ? currentMinValue : subtreeMinValue;
        }

        if (tree.right() != null)
        {
            subtreeMinValue = findMin(tree.right());
            currentMinValue = (currentMinValue < subtreeMinValue) ? currentMinValue : subtreeMinValue;
        }

        return currentMinValue;
    }
}
class Tree
{
    private int val;
    private Tree left, right;

    public Tree(int val, Tree left, Tree right)
    {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal()
    {
        return val;
    }

    public Tree left()
    {
        return left;
    }

    public Tree right()
    {
        return right;
    }
}