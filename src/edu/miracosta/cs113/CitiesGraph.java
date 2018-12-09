package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class CitiesGraph
{
    private final String DEFAULT_GRAPH_INPUT_FILE = "./resources/citiesInput.txt";
    private final String DEFAULT_CITY_NAMES_FILE = "./resources/cityNames.txt";
    private ListGraph citiesGraph;
    private String[] cityNames;
    private int[] predecessors;
    private double[] distances;
    private int numberOfValues;

    /**
     * Default Constructor. Will use the default input file for initializing the graph. Will then set the length of our
     * predecessors and distances arrays to match the number of vertices in our graph.
     */
    public CitiesGraph()
    {
        initializeGraph(DEFAULT_GRAPH_INPUT_FILE);
        numberOfValues = citiesGraph.getNumV();
        predecessors = new int[numberOfValues];
        distances = new double[numberOfValues];
        cityNames = new String[numberOfValues];
        loadCitiesArrayFromFile();
    }

    /**
     * With the passed in file name will create a ListGraph.
     * Pre: That the file is in the proper format for loading edges from file described in AbstractGraph class's method.
     * @param inputFileName String that will correspond to the properly formatted file to create our graph from.
     */
    public CitiesGraph(String inputFileName)
    {
        initializeGraph(inputFileName);
        numberOfValues = citiesGraph.getNumV();
        predecessors = new int[numberOfValues];
        distances = new double[numberOfValues];
        cityNames = new String[numberOfValues];
        loadCitiesArrayFromFile();
    }

    /**
     * Get predecessors array.
     * @return The predecessor array of type int.
     */
    public int[] getPredecessors()
    {
        return predecessors;
    }

    /**
     * Get distances array.
     * @return The distances array of type double.
     */
    public double[] getDistances()
    {
        return distances;
    }

    /**
     * Initialize Graph using the passed in file name.
     * @param inputFileName String for the file name holding the edges data to be input.
     */
    private void initializeGraph(String inputFileName)
    {
        Scanner inputFile = null;
        try
        {
            inputFile = new Scanner(new File(inputFileName));
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found please try again.");
        }
        citiesGraph = (ListGraph) ListGraph.createGraph(inputFile, false, "List");
    }

    /**
     * Fill our parallel array holding the cities names.
     * Pre: Assume that the city names correspond directly to their vertex indices within our Edges array.
     * Post: The cityNames array has been filled with city names from file.
     */
    private void loadCitiesArrayFromFile()
    {
        Scanner inputFile = null;
        try
        {
            inputFile = new Scanner(new File(DEFAULT_CITY_NAMES_FILE));
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found please try again.");
        }
        int count = 0;
        while(inputFile.hasNextLine())
        {
            String tempCity = inputFile.nextLine();
            cityNames[count] = tempCity;
            count++;
        }
    }

    /**
     * Public run dijkstras method. All it takes in is the starting point.
     * @param start int that corresponds to the starting vertex(city)
     */
    public void runDijkstras(int start)
    {
        this.dijkstrasAlgorithm(this.citiesGraph, start, this.predecessors, this.distances);
    }

    /**
     * A method to print out the results after dijkstras has been ran and stored within distances and predeccesors arrays.
     */
    public void printShortestPath()
    {
        for(int i = 0; i < predecessors.length; i++)
        {
            System.out.printf("V: " + i + " - d[v]: " + "%.2f", distances[i]);
            System.out.println(" - p[v]: " + predecessors[i]);
            //If I want city names to print out uncomment below for use instead of above.
//              System.out.printf("V: " + cityNames[i] + " - d[v]: " + "%.2f", distances[i]);
//              System.out.println(" - p[v]: " + cityNames[predecessors[i]]);
        }
    }

    /**
     *
     * @param destination
     */
    public void printPathToDestination(int destination)
    {
        int startVertex = -1;
        int[] backtrackFromDestination = new int[distances.length - 1];
        for(int i = 0; i < distances.length; i++) {
            if(distances[i] == 0) {
                startVertex = i;
            }
        }
        int currentVertex = destination;
        int count = 0;
        do {
            currentVertex = predecessors[currentVertex];
            backtrackFromDestination[count] = currentVertex;
            count++;
        } while(predecessors[currentVertex] != startVertex);
        System.out.println("BackTrack order is: ");
        for(int temp : backtrackFromDestination) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }

    /**
     * Dijkstra's Shortest-Path Algorithm
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred Output array to contain the predecessors in the shortest path.
     * @param dist Output array to contain the distance in the shortest path.
     */
    private void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[]dist)
    {
        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<Integer>(numV);
        //Initialize V-S
        for(int i = 0; i < numV; i++)
        {
            if(i != start)
            {
                vMinusS.add(i);
            }
        }
        //Initialize predecessor and distance.
        for(int v : vMinusS)
        {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        //Main Loop
        while(vMinusS.size() != 0)
        {
            //Find the value u in V-S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for(int v : vMinusS)
            {
                if(dist[v] < minDist)
                {
                    minDist = dist[v];
                    u = v;
                }
            }
            //Remove u from vMinusS
            vMinusS.remove(u);
            //Update the distances.
            for(int v : vMinusS)
            {
                if(graph.isEdge(u,v))
                {
                    double weight = graph.getEdge(u,v).getWeight();
                    if(dist[u] + weight < dist[v])
                    {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}
