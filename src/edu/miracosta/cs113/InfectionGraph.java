package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * InfectionGraph Class that will be what holds an instance of ListGraph and does all the necessary actions to create a Graph.
 * Then manipulates the Graph with the use of dijkstra's algorithm for use in our Infection.
 *
 * @author Jesse Wolf
 * @author Arthur Utnehmer
 * @auther Emiliia Dyrenkova
 */
public class InfectionGraph
{
    private final String DEFAULT_GRAPH_INPUT_FILE = "./resources/newCitiesInput.txt";
    private final String DEFAULT_CITY_NAMES_FILE = "./resources/newCityNames.txt";
    private NumberFormat formatter = new DecimalFormat("#0.00");
    private ListGraph infectionGraph;
    private String[] cityNames;
    private int[] predecessors;
    private double[] distances;
    private int numberOfValues;

    /**
     * Default Constructor. Will use the default input file for initializing the graph. Will then set the length of our
     * predecessors and distances arrays to match the number of vertices in our graph.
     *
     * Post: cityNames array filled with cities from file.
     *       infectionGraph has been initialized from file.
     *       predecessors and distances array have been initialized to proper size(matching the number of vertices)
     */
    public InfectionGraph()
    {
        initializeGraph(DEFAULT_GRAPH_INPUT_FILE);
        numberOfValues = infectionGraph.getNumV();
        predecessors = new int[numberOfValues];
        distances = new double[numberOfValues];
        cityNames = new String[numberOfValues];
        loadCitiesArrayFromFile();
    }

    /**
     * Constructor that will use the passed in file name to create our ListGraph.
     *
     * Pre: That the file is in the proper format for loading edges from file described in AbstractGraph class's
     *      createGraph method.
     *
     * Post: cityNames array filled with cities from file.
     *       infectionGraph has been initialized from file.
     *       predecessors and distances array have been initialized to proper size(matching the number of vertices)
     * @param inputFileName String that will correspond to the properly formatted file to create our graph from.
     */
    public InfectionGraph(String inputFileName)
    {
        initializeGraph(inputFileName);
        numberOfValues = infectionGraph.getNumV();
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
        return predecessors.clone();
    }

    /**
     * Get distances array.
     * @return The distances array of type double.
     */
    public double[] getDistances()
    {
        return distances.clone();
    }

    /**
     * private inner City used for sorting parallel arrays in getRating() method
     */
    private class City implements Comparable<City>{
        double distance;
        String name;

        public City(String name, double distance){
            this.distance=distance;
            this.name=name;
        }

        public int compareTo(City other){
            return (int)(this.distance*10-other.distance*10);
        }

    }//End of inner City class

    /**
     * Calculate and return the rating in a sorted order for the distances that were previously calculated using dijkstra's.
     *
     * Pre: Dijkstra's has been run prior to this method being called.
     * @return String[][] of formatted distances and city names in order of least to greatest distance.
     */
    public String[][] getRating(){
        City[] cities=new City[cityNames.length];
        for(int i=0;i<cityNames.length;i++){
            cities[i]=new City(cityNames[i],distances[i]);
        }
        Arrays.sort(cities);
        String[][] rating=new String[2][cities.length];
        for(int i=0;i<rating[0].length;i++){
            rating[0][i]=cities[i].name;
            rating[1][i]=String.valueOf(formatter.format(cities[i].distance));
        }
        return rating;
    }

    /**
     * Get city names array.
     * @return The city names array of type String.
     */
    public String[] getCityNames(){
        return cityNames.clone();
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
        infectionGraph = (ListGraph) ListGraph.createGraph(inputFile, false, "List");
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
        this.dijkstrasAlgorithm(this.infectionGraph, start, this.predecessors, this.distances);
    }

    /**
     * A method to print out the results after dijkstra's has been ran and stored within distances and predecessors arrays.
     *
     * Pre: Dijkstra's has been ran therefore graph has been created and both predecessor and distances arrays have been
     * populated.
     *
     * Post: The shortest path has been printed out to the screen.
     */
    public void printShortestPath()
    {
        for(int i = 0; i < predecessors.length; i++)
        {
            System.out.printf("V: " + i + " - d[v]: " + "%.2f", distances[i]);
            System.out.println(" - p[v]: " + predecessors[i]);
        }
    }

    /**
     * A method to print out the results after dijkstra's has been ran and stored within distances and predecessors arrays.
     *
     * Pre: Dijkstra's has been ran therefore graph has been created and both predecessor and distances arrays have been
     * populated. City Names must currently be stored in cityNames array as well. Assume that loadCitiesFromFile has been called.
     *
     * Post: The shortest path has been printed out to the screen with city names instead of indices.
     */
    public void printShortestPathWithCityNames()
    {
        for(int i = 0; i < predecessors.length; i++)
        {
            System.out.printf("V: " + cityNames[i] + " - d[v]: " + "%.2f", distances[i]);
            System.out.println(" - p[v]: " + cityNames[predecessors[i]]);
        }
    }

    /**
     * Print the path to a desired destination. Returns an int[] of the path from the start to the destination.
     *
     * Pre: Only works if dijkstra's has already been ran therefore predecessors and distances arrays have been filled.
     * @param destination int of the destination you want to get to.
     */
    public int[] printPathToDestination(int destination)
    {
        int startVertex = -1;
        //Create an array to store the backtrack path
        int[] backtrackFromDestination = new int[distances.length - 1];
        //Initialize each value at -1 within the array
        for(int i = 0; i < distances.length; i++) {
            if(distances[i] == 0) {
                startVertex = i;
            }
        }
        //Start currentVertex to match the destination that was passed in.
        int currentVertex = destination;
        //Initialize count to size of array - 1.
        int count = backtrackFromDestination.length-1;
        do {
            //Update current vertex to be the predecessor
            currentVertex = predecessors[currentVertex];
            //Enter this current vertex into array at count
            backtrackFromDestination[count] = currentVertex;
            //Decrement count
            count--;
            //While our currentVertex != startVertex
        } while(predecessors[currentVertex] != startVertex);
        //Print out the back order to screen.
        System.out.println("BackTrack order is: ");
        for(int temp : backtrackFromDestination) {
            System.out.print(temp + " ");
        }
        System.out.println();

        //Iterate through my array to find out number of non zeros.
        int numberOfNonZeros = 0;
        for(int temp : backtrackFromDestination) {
            if(temp != 0) {
                numberOfNonZeros++;
            }
        }
        //Add 2 to my numberOfNonZeros in order to be able to add the startVertex and destination. 1 additional for each.
        int[] myPath = new int[numberOfNonZeros+2];

        //Set mePath at 0 to be the start vertex.
        myPath[0] = startVertex;
        //Start counter at 1 to take into account startVertex being at index 0 now.
        int counter = 1;
        for(int temp : backtrackFromDestination) {
            //If temp != 0 add temp to myPath.
            if(temp != 0) {
                myPath[counter] = temp;
                counter++;
            }
        }
        //Set destination at end so that the path is now total path.
        myPath[counter] = destination;

        System.out.println("My Path is: ");
        for(int temp : myPath) {
            System.out.println(temp);
        }
        System.out.println();
        return myPath;
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
