package edu.miracosta.cs113;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultsPage extends JFrame {
    private JLabel headerLabel;
    private JTable ratingTable;

    public ResultsPage(int numCities, String[][] rating){
        setLayout(new BorderLayout());
        headerLabel=new JLabel("Rating of cities from the most dangerous to safest");
        add(headerLabel,BorderLayout.NORTH);
        //TODO: make headers for columns in the table
        ratingTable=new JTable(numCities,2);
        for(int i=0;i<rating[0].length;i++){
            ratingTable.setValueAt(rating[0][i],i,0);
            ratingTable.setValueAt(rating[1][i],i,1);
        }
        add(ratingTable,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
    }
}
