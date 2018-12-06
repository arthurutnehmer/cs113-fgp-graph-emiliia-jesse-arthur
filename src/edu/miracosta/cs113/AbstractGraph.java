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

    //Other Methods
    /**
     * Load the edges of a graph from the data in an input file.
     * The file should contain a series of lines, each line
     * with two or three data values. The first is the source
     * the second is the destination, and the optional third
     * is the weight
     * @param scan The scanner that contains info about the file.
     * @throws IOException - If an I/O error occurs
     */
    public void loadEdgesFromFile(Scanner scan)
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
     * Factory method to create a graph and load the data from an input file. The first line of the input file should
     * contain the number of vertices. The remaining lines should contain the edge data as described under loadEdgesFromFile.
     * @param scan The Scanner connected to the data file.
     * @param isDirected true if this is a directed graph, false otherwise.
     * @param type The string "List" if an adjacency list is to be created. Will throw IllegalArgumentException
     *             if any other String is passed in. Currently only support the use of "List" as that is the graph
     *             we have decided to implement.
     * @return A Graph that has been created matching what it took in from the file.
     * @throws IllegalArgumentException if type is not "List"
     */
    public static Graph createGraph(Scanner scan, boolean isDirected, String type)
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
