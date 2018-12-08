package edu.miracosta.cs113;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TestOfListGraph : Will test the ListGraph.
 *
 * @author Arthur Utnehmer
 * @version 1.0
 */
public class TestOfListGraph
{
    ListGraph testGraph = new ListGraph(12, false);

    //Will create Edges to test.
    private static final Edge[] EDGES =
            {
            new Edge(0, 1, 0.6),
            new Edge(0, 6, 0.2),
            new Edge(0, 5, 0.1),
            new Edge(1, 2, 0.4),
            new Edge(2, 6, 0.7),
            new Edge(2, 8, 0.3),
            new Edge(2, 3, 0.2),
            new Edge(3, 9, 0.9),
            new Edge(3, 8, 0.2),
            new Edge(4, 5, 0.1),
            new Edge(6, 7, 0.2),
            new Edge(7, 8, 0.2),
            };

    private static final int NUMBER_OF_EDGES = 12;

    @Test
    public void TestInsert()
    {
        testGraph.insert(new Edge(0, 1, 0.6));
        testGraph.insert(new Edge(0, 6, 0.2));
        testGraph.insert(new Edge(0, 5, 0.1));
        testGraph.insert(new Edge(1, 2, 0.4));
        testGraph.insert(new Edge(2, 6, 0.7));
        testGraph.insert(new Edge(2, 8, 0.3));
        testGraph.insert(new Edge(2, 3, 0.2));
        testGraph.insert(new Edge(3, 9, 0.9));
        testGraph.insert(new Edge(3, 8, 0.2));
        testGraph.insert(new Edge(4, 5, 0.1));
        testGraph.insert(new Edge(6, 7, 0.2));
        testGraph.insert(new Edge(7, 8, 0.2));

        for(int i = 0; i< EDGES.length; i++)
        {
            assertEquals(EDGES[i], testGraph.getEdge(EDGES[i].getSource(), EDGES[i].getDest()));
        }
    }

    @Test
    public void TestNumberOfEdges()
    {
        assertEquals(testGraph.getNumV(), NUMBER_OF_EDGES);
    }

    @Test
    public void TestDirected()
    {
        assertEquals(testGraph.isDirected(), false);
    }

}
