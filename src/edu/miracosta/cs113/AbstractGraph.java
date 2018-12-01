package edu.miracosta.cs113;

import java.util.Scanner;
import java.io.IOException;
/**
 * Abstract base class for graphs.Edges are ordered pairs of vertices.
 * The vertices are represented by integers that range from 0 to n-1.
 */
public class AbstractGraph implements Graph
{
    private boolean directed;
    private  int numV;

    /**
     * Constructs a graph with the specified number of vertices and
     * sets directed or not.
     * @param numV The number of vertices.
     * @param directed The directed flag.
     */
    public AbstractGraph(int numV, boolean directed)
    {
        this.numV = numV;
        this.directed = directed;
    }

    /**
     * Return the number of vertices,
     * @return The number of vertices.
     */
    @Override
    public int getNumV()
    {
        return numV;
    }

    /**
     * set the number of vertices,
     */
    public void setNumV(int numV)
    {
        this.numV = numV;
    }

    /**
     * Returns weather the graph is directed.
     * @return true if directed.
     */
    @Override
    public boolean isDirected()
    {
        return directed;
    }

    /**
     * Set wheather the graph is directed.
     * @param directed This is set to true if the graph is directed.
     */
    public void setDirected(boolean directed)
    {
        this.directed = directed;
    }

    /**
     * Load the edges of the graph from a input file.
     * The file should contain a series of lines, each line
     * with two or three data values. The first is the source,
     * the second is the destination, and the optional third
     * is the weight.
     */

    public void loadEdgesFromFile(Scanner scan) throws IOException
    {
        String line;
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            String[] tokens = line.split("\\s+");
            int source = Integer.parseInt(tokens[0]);
            int dest = Integer.parseInt(tokens[1]);
            double weight = 1.0;
            if (tokens.length == 3)
            {
                weight = Double.parseDouble(tokens[2]);
            }
            insert(new Edge(source, dest, weight));
        }
    }

    /**
     * Create a graph and load the data from and input file.
     */

    public static Graph createGraph(Scanner scan, boolean isDirected, String type)
    {
        int numV = scan.nextInt();
        AbstractGraph returnValue = null;
        if(type.equalsIgnoreCase("List"))
            returnValue = new ListGraph(numV, isDirected);
        else
            throw new IllegalArgumentException();
        returnValue.loadEdgesFromFile(scan);
        return returnValue;
    }



}
