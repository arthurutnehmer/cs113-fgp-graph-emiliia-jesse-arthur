package edu.miracosta.cs113;

import java.util.Scanner;
import java.io.IOException;
/**
 * Abstract base class for graphs.Edges are ordered pairs of vertices.
 * The vertices are represented by integers that range from 0 to n-1.
 */
public abstract class AbstractGraph implements Graph
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
     * Returns whether the graph is directed.
     * @return true if directed.
     */
    public boolean isDirected()
    {
        return directed;
    }

    /**
     * Set whether the graph is directed.
     * @param directed This is set to true if the graph is directed.
     */
    public void setDirected(boolean directed)
    {
        this.directed = directed;
    }

    public static Graph createGraph(Scanner scan, boolean isDirected, String type) throws IOException
    {
        int numV = scan.nextInt();
        AbstractGraph returnValue = null;
        if(type.equalsIgnoreCase("List"))
            returnValue = new ListGraph(numV, isDirected);
        else
            throw new IllegalArgumentException();
        ((ListGraph) returnValue).loadEdgesFromFile(scan);
        return returnValue;
    }



}
