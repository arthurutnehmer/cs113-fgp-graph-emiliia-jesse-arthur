package edu.miracosta.cs113.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Map Page used to display the modified map of LA and San Diego County.
 */
public class MapPage extends JFrame {
    private JLabel backgroundImage = new JLabel(new ImageIcon("./resources/guiResources/OneMap.png"));
    private final int MAP_X = 200, MAP_Y = -50, MAP_WIDTH = 1250, MAP_HEIGHT = 1000;

    public MapPage() {
        setLayout(new BorderLayout());
        setSize(1300,900);

        backgroundImage.setBounds(MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
        add(backgroundImage);

        //Set location of JFrame relative to screen size.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/25;
        int y = (dim.height-h)/3;
        setLocation(x,y);

        setResizable(false);
    }
}
