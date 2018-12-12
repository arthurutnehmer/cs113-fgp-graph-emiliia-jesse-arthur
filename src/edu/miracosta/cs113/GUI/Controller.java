package edu.miracosta.cs113.GUI;

import edu.miracosta.cs113.CitiesGraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

    private static FrontPage frontPage;

    @Override
    public void actionPerformed(ActionEvent e) {
        int city=frontPage.getChosenCity();
        CitiesGraph graph=new CitiesGraph();
        graph.runDijkstras(city);
        ResultsPage results=new ResultsPage(10,graph.getRating());
        results.setVisible(true);
    }

    public static void main(String[]args){
        frontPage=new FrontPage();
        frontPage.setVisible(true);
    }
}
