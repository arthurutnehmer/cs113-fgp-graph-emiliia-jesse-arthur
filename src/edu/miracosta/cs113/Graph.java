package edu.miracosta.cs113;

import com.sun.javafx.geom.Edge;

import java.util.Iterator;

public interface Graph
{
    /**
     Return the number of vertices.
    @return the number of verities.
    */
    int getNumV();

    /**
     * Determine whether this is a directed graph.
     * @return true if this is a directed graph.
     */

    boolean isDirected();

    /**
     * Insert a new edge into the graph.
     * @param edge The new Edge.
     */

    void insert(Edge edge);

    /**
     * Determine whether an edge exist.
     * @param source The source vertex.
     * @param dest The destination vertex.
     * @return true if there is an edge from source to edge.
     */

    boolean isEdge(int source, int dest);

    /**
     * Get the edge between two vertices.
     * @param source The source vertex.
     * @param dest The destination vertex.
     * @return The Edge between these two vertices or an Edge with a weight
     * of Double.POSITIVE_INFINITY if there is no edge.
     */

    Edge getEdge(int source, int dest);


    /**
     * Return an iterator to the edges connected to a given vertex.
     * @param source The source vertex.
     * @return An Iterator<Edge> to the vertices connected to the source.
     */

    Iterator<Edge> edgeIterator(int source);
}
