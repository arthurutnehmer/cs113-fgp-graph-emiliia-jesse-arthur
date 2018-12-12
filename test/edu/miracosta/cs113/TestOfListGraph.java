package edu.miracosta.cs113;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

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
    private static final String PATH_TO_FILE ="resources/citiesInput.txt";
    private static final String OUTPUT_STRING= "[[(0, 1): 0.6], [(0, 6): 0.2], [(0, 5): 0.1]]\n" +
            "[[(1, 0): 0.6], [(1, 2): 0.4]]\n" +
            "[[(2, 1): 0.4], [(2, 6): 0.7], [(2, 8): 0.3], [(2, 3): 0.2]]\n" +
            "[[(3, 2): 0.2], [(3, 9): 0.9], [(3, 8): 0.2]]\n" +
            "[[(4, 5): 0.1]]\n" +
            "[[(5, 0): 0.1], [(5, 4): 0.1]]\n" +
            "[[(6, 0): 0.2], [(6, 2): 0.7], [(6, 7): 0.2]]\n" +
            "[[(7, 6): 0.2], [(7, 8): 0.2]]\n" +
            "[[(8, 2): 0.3], [(8, 3): 0.2], [(8, 7): 0.2]]\n" +
            "[[(9, 3): 0.9]]\n" +
            "[]\n" +
            "[]\n";


    public void insert()
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
    }

    public Scanner readFromFile()
    {
        Scanner inputFile = null;
        try
        {
            inputFile = new Scanner(new File(PATH_TO_FILE));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found please try again.");
        }
        int count = 0;
        return inputFile;
    }

    @Test
    public void TestInsert()
    {
        insert();
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

    @Test
    public void isEdgeTrue()
    {
        //insert the edges.
        insert();

        for(int i = 0; i< EDGES.length; i++)
        {
            assertEquals(true, testGraph.isEdge(EDGES[i].getSource(), EDGES[i].getDest()));
        }
    }

    @Test
    public void isEdgeFalse()
    {
        //insert the edges.
        insert();
        assertEquals(false, testGraph.isEdge(1,22));
        assertEquals(false, testGraph.isEdge(1,4));
        for(int i = 0; i< EDGES.length; i++)
        {
            assertEquals(false, testGraph.isEdge(i+NUMBER_OF_EDGES, i+NUMBER_OF_EDGES));
        }
    }


    @Test
    public void testReadFromFile()
    {
        //create graph from file.
        Graph testGraph = ListGraph.createGraph(readFromFile(),true,"List");
        //typecast.
        testGraph = (ListGraph)testGraph;

        //check for equality.
        for(int i = 0; i< EDGES.length; i++)
        {
            assertEquals(EDGES[i], testGraph.getEdge(EDGES[i].getSource(), EDGES[i].getDest()));
        }
    }

    @Test
    public void testToString()
    {
        insert();
        assertEquals(testGraph.toString(), OUTPUT_STRING);
    }


}
