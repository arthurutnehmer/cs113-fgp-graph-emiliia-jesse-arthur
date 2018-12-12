package edu.miracosta.cs113.GUI;

import edu.miracosta.cs113.InfectionGraph;
import edu.miracosta.cs113.Weight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

    private static FrontPage frontPage;

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get city and infection rate from user.
        int city=frontPage.getChosenCity();
        double infectionRate = frontPage.getInfectionRate();
        //Set Weight's static infection rate to match that which was passed in by the user.
        Weight.setInfectionRate(infectionRate);
        //Create a new InfectionGraph
        InfectionGraph graph=new InfectionGraph();
        //Run dijkstra's on it with the starting city chosen by the user passed in.
        graph.runDijkstras(city);
        //New MapPage to display the current map.
        MapPage map = new MapPage();
        map.setVisible(true);
        //New ResultsPage to display the results of dijkstra's
        ResultsPage results=new ResultsPage(10,graph.getRating());
        results.setVisible(true);
    }

    public static void main(String[]args){
        frontPage=new FrontPage();
        frontPage.setVisible(true);
    }
}
