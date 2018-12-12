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
        columnLabels.setFont(new Font("Serif", Font.BOLD,20));
        columnLabels.setValueAt("Cities",0,0);
        columnLabels.setValueAt("Distances", 0,1);
        add(columnLabels,BorderLayout.NORTH);

        String columnNames[] = {"Cities", "Distance From Starting City"};
        ratingTable=new JTable(numCities,2);
        //Change font to match other panel's
        ratingTable.setFont(new Font("Serif", Font.BOLD,18));
        for(int i=1;i<rating[0].length;i++){
            ratingTable.setValueAt(rating[0][i],i,0);
            ratingTable.setValueAt(rating[1][i],i,1);
        }
        add(ratingTable,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,240);
        //Position the window relative the total screen size.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)*2/3;
        int y = (dim.height-h)/30;

        setLocation(x,y);
    }
}
