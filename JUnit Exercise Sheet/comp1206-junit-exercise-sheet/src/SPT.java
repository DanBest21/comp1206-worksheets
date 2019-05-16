/*******************************************************************************
 * Copyright (c) 2019 University of Southampton.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Contributors: 
 *   University of Southampton - initial API and implementation
 *******************************************************************************/
import java.util.Arrays;
import Exception.*;

/**
 * An implementation of Dijsktra's Shortest Path Tree (SPT) algorithm.
 *
 * @author htson
 * @version 1.1
 */
public class SPT {

    /**
     * A static method to find a Shortest Path Tree for a non-negative-weighted
     * and directed graph represented by <code>edges</code> from a single source
     * node <code>root</code>. Nodes are assumed to be labelled from
     * <code>0</code> to </code>(number of nodes) - 1</code>. The method returns
     * an array representing a Shortest Path Tree.
     *
     * @param edges a two dimentional array representing the input graph. A cell
     *              with non-negative value indicate an weighted edge. For
     *              example <code>edges[0][1] = 2</code> means that the weight
     *              of the edge from Node 0 to Node 1 is <code>2</code>. A
     *              negative value cell indicate no connection between the
     *              corresponding nodes. For example <code>edges[0][1]=-1</code>
     *              means that there is no edge from Node 0 to Node 1.
     *
     * @param source  the input source node.
     *
     * @return an array representing the result Shortest Path Tree. The array
     *         represent the parent relationship between nodes. For example,
     *         <code>result = [2, 0, -1, 2]</code> represents the following tree
     *         (1) -> (0) -> (2) <- (3) where Node 2 is the root (value array
     *         -1), Node 0 and Node 3 connect to Node 2, and Node 1 connects to
     *         Node 0.
     *
     * @throws InvalidGraphException  in the case where the input graph
     *                                (represented by <code>edges</code>) is
     *                                invalid. In particular, <code>edges</code>
     *                                is expected to be a two-dimentional array
     *                                of the same dimention, i.e., having equal
     *                                number of rows and columns.
     *
     * @throws InvalidSourceException in the case the source node
     *                                <code>source</code> is invalid for the
     *                                input graph. This is the case when either
     *                                (1) <code>source</code> is negative, or
     *                                (2) greater than or equal to the number of
     *                                nodes.
     *
     * @throws LoopDetectedException  in the case where the input graph contains
     *                                loops, that is an edge from a node to
     *                                itself.
     *
     * @throws NoPathException        in the case where there are no paths from
     *                                the source node to another node in the
     *                                graph.
     */
    public static int[] findSPT(int[][] edges, int source)
            throws InvalidSourceException, InvalidGraphException,
            LoopDetectedException, NoPathException {

        // Check if the input edges is null.
        if (edges == null) {
            throw new NullPointerException("Edges can't be null");
        }

        // Get the number of nodes.
        int length = edges.length;

        // Check for invalid source
        if (source < 0 || source >= length)
            throw new InvalidSourceException();

        // Check for invalid graph and loops
        for (int i = 0; i != length; i++) {
            int[] row = edges[i];

            // The graph is ill-formed
            if (row.length != length)
                throw new InvalidGraphException();

            // There is a loop in the graph.
            if (row[i] >= 0)
                throw new LoopDetectedException();
        }

        // The array visited array
        boolean[] visited = new boolean[length];
        Arrays.fill(visited, false);

        // The array of distance
        int[] distance = new int[length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // The array of parent node to visit the node
        int[] parent = new int[length];
        Arrays.fill(parent, -1);

        // Continue visit nodes until no nodes can be reached.
        int current = source;
        while (current != length) {
            current = visit(edges, current, visited, distance, parent);
        }

        // Check if there are unvisted nodes
        // (i.e., nodes that are unreachable from source).
        for (int i = 0; i != length; i++) {
            if (!visited[i])
                throw new NoPathException(
                        "No path from " + source + " to " + i);
        }

        // Return the array representing the shortest path tree.
        return parent;
    }

    /**
     * Utility method to visit the @current node.
     *
     * @param edges    a two dimentional array representing the input graph. A
     *                 cell with non-negative value indicate an weighted edge.
     *                 For example <code>edges[0][1] = 2</code> means that the
     *                 weight of the edge from Node 0 to Node 1 is
     *                 <code>2</code>. A negative value cell indicate no
     *                 connection between the corresponding nodes. For example
     *                 <code>edges[0][1]=-1</code> means that there is no edge
     *                 from Node 0 to Node 1.
     *
     * @param current  The node to visit
     *
     * @param visited  the array of visited nodes.
     *
     * @param distance an array of current distances from the source node to
     *                 each node in the graph.
     *
     * @param parent   the current parent relationship between visited nodes.
     *
     * @return The next node to visit. If there are no nodes to visit then
     *         return the number of nodes in the graph.
     */
    private static int visit(int[][] edges, int current, boolean[] visited,
                             int[] distance, int[] parent) {
        int length = edges.length;
        visited[current] = true; // Set current node to be visited
        for (int i = 0; i < length; i++) {
            // If i is an adjacent node to the current node
            if (edges[current][i] >= 0) {
                // If there is a new minimal distance found then update the
                // distance and the parent of the node i
                if (distance[current] + edges[current][i] < distance[i]) {
                    distance[i] = distance[current] + edges[current][i];
                    parent[i] = current;
                }
            }
        }

        // Select new current node to be unvisited node with the smallest
        // distance to the source.
        int cost = Integer.MAX_VALUE;
        current = length;
        for (int i = 0; i < length; i++) {
            if (!visited[i] && distance[i] < cost) {
                current = i;
                cost = distance[i];
            }
        }

        return current;
    }

}