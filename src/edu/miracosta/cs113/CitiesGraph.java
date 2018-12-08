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
}
