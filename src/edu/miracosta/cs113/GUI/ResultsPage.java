package edu.miracosta.cs113.GUI;

import javax.swing.*;
import java.awt.*;

public class ResultsPage extends JFrame {
    private JLabel headerLabel;
    private JTable ratingTable;
    private JTable columnLabels;

    public ResultsPage(int numCities, String[][] rating){
        setLayout(new BorderLayout());
        headerLabel=new JLabel("Rating of cities from the most dangerous to safest");
        //Change font to match other panel's
        headerLabel.setFont(new Font("Serif",Font.ITALIC, 20));
        add(headerLabel,BorderLayout.NORTH);

        columnLabels = new JTable(1,2);
        columnLabels.setFont(new Font("Serif", Font.BOLD,18));
        columnLabels.setValueAt("Cities",0,0);
        columnLabels.setValueAt("Distances", 0,1);
        add(columnLabels,BorderLayout.NORTH);
        //TODO: make headers for columns in the table

        String columnNames[] = {"Cities", "Distance From Starting City"};
        ratingTable=new JTable(numCities,2);
        //Change font to match other panel's
        ratingTable.setFont(new Font("Serif", Font.PLAIN,18));
//        ratingTable.setValueAt("Cities",0,0);
//        ratingTable.setValueAt("Distances",0,1);
        for(int i=1;i<rating[0].length;i++){
            ratingTable.setValueAt(rating[0][i],i,0);
            ratingTable.setValueAt(rating[1][i],i,1);
        }
        add(ratingTable,BorderLayout.CENTER);
//        add(ratingTable,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
    }
}
