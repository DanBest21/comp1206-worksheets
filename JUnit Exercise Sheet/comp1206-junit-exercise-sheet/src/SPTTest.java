import java.util.Arrays;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SPTTest
{
    private int[][] edges = null;
    private int source;

    private void initGraph(int nodes, boolean setupBasicGraph)
    {
        edges = new int[nodes][nodes];

        for (int[] edge : edges)
        {
            Arrays.fill(edge, -1);
        }

        if (setupBasicGraph && nodes >= 4)
            setupBasicGraph();
    }

    private void setupBasicGraph()
    {
        edges[0][1] = 4;
        edges[2][0] = 1;
        edges[2][1] = 12;
        edges[3][1] = 10;
        edges[2][3] = 6;
    }

    @Test
    void testNullGraph()
    {
        assertThrows(NullPointerException.class, () -> SPT.findSPT(edges, source));
    }

    @Test
    void testNegativeInvalidSource()
    {
        initGraph(4, true);
        source = -1;
        assertThrows(InvalidSourceException.class, () -> SPT.findSPT(edges, source));
    }

    @Test
    void testAboveLengthInvalidSource()
    {
        initGraph(4, true);
        source = 4;
        assertThrows(InvalidSourceException.class, () -> SPT.findSPT(edges, source));
    }

    @Test
    void testInvalidGraph()
    {
        initGraph(4, true);
        source = 0;
        edges[1] = new int[]{-1, -1, -1, -1, -1};
        assertThrows(InvalidGraphException.class, () -> SPT.findSPT(edges, source));
    }

    @Test
    void testLoopDetected()
    {
        initGraph(4, true);
        source = 0;
        edges[1][1] = 50;
        assertThrows(LoopDetectedException.class, () -> SPT.findSPT(edges, source));
    }

    @Test
    void testNoPathAvailable()
    {
        initGraph(4, true);
        source = 0;
        edges[0][1] = -1;
        assertThrows(NoPathException.class, () -> SPT.findSPT(edges, source));
    }

    @Test
    void testSingletonGraph() throws InvalidSourceException, InvalidGraphException,
            LoopDetectedException, NoPathException
    {
        initGraph(1, false);
        source = 0;
        int[] expectedResult = {-1};
        assertArrayEquals(expectedResult, SPT.findSPT(edges, source));
    }

    @Test
    void testSimpleGraph() throws InvalidSourceException, InvalidGraphException,
            LoopDetectedException, NoPathException
    {
        initGraph(4, true);
        source = 2;
        int[] expectedResult = {2, 0, -1, 2};
        assertArrayEquals(expectedResult, SPT.findSPT(edges, source));
    }

    @Test
    void testComplexGraph() throws InvalidSourceException, InvalidGraphException,
            LoopDetectedException, NoPathException
    {
        initGraph(10, false);
        source = 0;

        edges[0][7] = 24;
        edges[0][5] = 45;
        edges[7][5] = 37;
        edges[7][3] = 47;
        edges[7][4] = 70;
        edges[7][9] = 95;
        edges[3][4] = 49;
        edges[3][6] = 96;
        edges[5][4] = 65;
        edges[5][8] = 37;
        edges[8][4] = 60;
        edges[8][9] = 41;
        edges[4][6] = 69;
        edges[4][9] = 45;
        edges[4][1] = 102;
        edges[9][6] = 88;
        edges[9][1] = 101;
        edges[6][2] = 21;
        edges[2][1] = 45;

        int[] expectedResult = {-1, 4, 6, 7, 7, 0, 4, 0, 5, 7};
        assertArrayEquals(expectedResult, SPT.findSPT(edges, source));
    }

    @Test
    void testMultipleValidResults() throws InvalidSourceException, InvalidGraphException,
            LoopDetectedException, NoPathException
    {
        initGraph(4, true);
        source = 2;

        edges[2][3] = 2;
        edges[3][1] = 3;

        int[] expectedResult1 = {2, 0, -1, 2};
        int[] expectedResult2 = {2, 3, -1, 2};
        int[] actualResult = SPT.findSPT(edges, source);
        assertArrayEquals((Arrays.equals(expectedResult1, actualResult)) ? expectedResult1 : expectedResult2, actualResult);
    }

    @Test
    void testLargeGraph() throws InvalidSourceException, InvalidGraphException,
    LoopDetectedException, NoPathException
    {
        initGraph(1000, true);
        source = 0;

        int[] expectedResult = new int[1000];

        for (int i = 0; i < 1000; i++)
        {
            if ((i + 1) != 1000)
                edges[i][i + 1] = 1;

            expectedResult[i] = i - 1;
        }

        assertArrayEquals(expectedResult, SPT.findSPT(edges, source));
    }
}