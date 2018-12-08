package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CitiesGraph {
    private ListGraph citiesGraph;
    private Scanner inputFile;
    private String[] cityNames;

    public CitiesGraph() {
        initializeGraph();
    }

    private void initializeGraph() {
        try {
            inputFile = new Scanner(new File("./resources/citiesInput.txt"));
        } catch(FileNotFoundException e) {
            System.out.println("File not found please try again.");
        }
        citiesGraph = (ListGraph) ListGraph.createGraph(inputFile, false, "List");
    }
    /**
    /**
    /**
     * Dijkstra's Shortest-Path Algorithm
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred Output array to contain the predecessors in the shortest path.
     * @param dist Output array to contain the distance in the shortest path.
     */
    public void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[]dist) {
        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<Integer>(numV);
        //Initialize V-S
        for(int i = 0; i < numV; i++) {
            if(i != start) {
                vMinusS.add(i);
            }
        }
        //Initialize predecessor and distance.
        for(int v : vMinusS) {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        //Main Loop
        while(vMinusS.size() != 0) {
            //Find the value u in V-S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for(int v : vMinusS) {
                if(dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            //Remove u from vMinusS
            vMinusS.remove(u);
            //Update the distances.
            for(int v : vMinusS) {
                if(graph.isEdge(u,v)) {
                    double weight = graph.getEdge(u,v).getWeight();
                    if(dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}
