package edu.miracosta.cs113;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FrontPage extends JFrame {
    private JPanel panel1;
    private JLabel welcomeLabel;
    private JLabel cityLabel;
    private JLabel rateLabel;
    private JButton rateButton;
    private JTextField rateField;
    private JComboBox<String> cityCombo;

    public FrontPage(){
        setLayout(new BorderLayout());
        welcomeLabel=new JLabel("Welcome to...");
        add(welcomeLabel,BorderLayout.NORTH);
        rateButton=new JButton("Get ratings");
        Controller controller=new Controller();
        rateButton.addActionListener(controller);
        add(rateButton,BorderLayout.SOUTH);
        setSize(400,400);
        panel1=new JPanel();
        panel1.setLayout(new GridLayout(3,2));
        rateLabel=new JLabel("Enter infection rate:");
        panel1.add(rateLabel);
        rateField=new JTextField("in people/day");
        panel1.add(rateField);
        cityLabel=new JLabel("Enter starting city:");
        panel1.add(cityLabel);
        cityCombo=new JComboBox<>();
        fillCitiesCombo();
        panel1.add(cityCombo);
        add(panel1,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void fillCitiesCombo(){
        try {
            File citiesFile = new File("resources/cityNames.txt");
            Scanner fileReader = new Scanner(new FileInputStream(citiesFile));
            while(fileReader.hasNextLine()){
                cityCombo.addItem(fileReader.nextLine());
            }
        }
        catch(IOException e){
            cityCombo.addItem("Failed to read list of the cities");
        }
    }

    protected int getChosenCity(){
        return cityCombo.getSelectedIndex();
    }
}
