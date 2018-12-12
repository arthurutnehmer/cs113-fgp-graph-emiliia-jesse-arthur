package edu.miracosta.cs113.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
    private CustomTextField rateField;
    private JComboBox<String> cityCombo;
    private final String backgroundImageFile = "./resources/bioHazard.png";
    private final String DEFAULT_CITY_NAMES_FILE = "./resources/cityNames.txt";

    /**
     * Constructor for the Front Page JFrame.
     */
    public FrontPage(){
        //Must catch ClassNotFoundException, IllegalAccessException and UnsupportedLookAndFeelException.
        //Changes the look and feel to match Nimbus.
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch(Exception e) {
            e.getStackTrace();
        }

        //Set Layout to BorderLayout
        setLayout(new BorderLayout());
        setSize(525,550);

        //Label for top of JFrame, set text and font then add to frame.
        welcomeLabel=new JLabel("Welcome to Infection", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        add(welcomeLabel,BorderLayout.NORTH);

        //Create new panel within frame
        panel1=new JPanel();
        //Add background image from predefined file location.
        try {
            panel1 = new JPanelWithBackground(backgroundImageFile);
        } catch(IOException e) {
            System.out.println("Do you have all the necessary files to run this file? Please try again.");
        }
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20,10));

        //Label for the drop down
        cityLabel=new JLabel(" Enter starting city:");
        cityLabel.setFont(new Font("Serif", Font.BOLD, 30));
        panel1.add(cityLabel);

        //Create drop down box and fill it with list of cities through use of fillCitiesCombo method.
        cityCombo=new JComboBox<>();
        cityCombo.setPreferredSize(new Dimension(150,50));
        fillCitiesCombo();
        panel1.add(cityCombo);

        //Label for the text box for infection rate.
        rateLabel=new JLabel("Enter infection rate:");
        rateLabel.setFont(new Font("Serif", Font.BOLD, 30));
        //Add Vertical Space between the drop down box and this.
        panel1.add(Box.createVerticalStrut(150));
        panel1.add(rateLabel);

        //Custom text field that allows for hint text to be visible until focus is put on it.
        rateField = new CustomTextField();
        rateField.setPlaceholder("in people per day.");
        rateField.setPreferredSize(new Dimension(155,50));
        panel1.add(rateField);

        //Button to move onto next screen
        rateButton = new JButton("Get Ratings");
        rateButton.setBackground(Color.GREEN);
        Controller controller = new Controller();
        rateButton.addActionListener(controller);
        add(rateButton,BorderLayout.SOUTH);

        add(panel1,BorderLayout.CENTER);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set location that the window appears.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)*2/3;
        int y = (dim.height-h)/2;
        setLocation(x,y);
    }

    /**
     * Takes city names from file and add to city combo drop down box.
     */
    private void fillCitiesCombo(){
        try {
            File citiesFile = new File(DEFAULT_CITY_NAMES_FILE);
            Scanner fileReader = new Scanner(new FileInputStream(citiesFile));
            while(fileReader.hasNextLine()){
                cityCombo.addItem(fileReader.nextLine());
            }
        }
        catch(IOException e){
            cityCombo.addItem("Failed to read list of the cities");
        }
    }

    /**
     * Get selected city from the drop down box.
     * @return int corresponding to selected city.
     */
    protected int getChosenCity(){
        return cityCombo.getSelectedIndex();
    }

    /**
     * Get infection rate that was entered in by the user.
     * @return A double that corresponds to the infection rate the user passed in.
     */
    protected double getInfectionRate() {
        double temp = 100;
        try {
            temp = Double.parseDouble(rateField.getText());
        } catch(NumberFormatException e) {
            System.out.println("Unexpected token within string. Please try again.");
        }
        return temp;
    }

    /**
     * Private inner class used in order to have a panel with a background image.
     */
    private class JPanelWithBackground extends JPanel {
        private Image backgroundImage;

        // Some code to initialize the background image.
        // Use the constructor to load the image.
        public JPanelWithBackground(String fileName) throws IOException {
            backgroundImage = ImageIO.read(new File(fileName));
        }

        //Paints the background image.
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the background image.
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    /**
     * Private inner class used in order to have hidden text until focus is placed on text field.
     */
    private class CustomTextField extends JTextField {

        private Font originalFont;
        private Color originalForeground;
        //Grey by default
        private Color placeholderForeground = new Color(160, 160, 160);
        private boolean textWrittenIn;

        /**
         * Default Constructor. Calls super constructor for JTextField.
         */
        public CustomTextField() {
            super();
        }

        /**
         * Constructor that takes in number of columns and passes this into super constructor for JTextField.
         * @param columns int number of columns
         */
        public CustomTextField(int columns) {
            super(columns);
        }

        @Override
        public void setFont(Font f) {
            super.setFont(f);
            if (!isTextWrittenIn()) {
                originalFont = f;
            }
        }

        @Override
        public void setForeground(Color fg)
        {
            super.setForeground(fg);
            if (!isTextWrittenIn()) {
                originalForeground = fg;
            }
        }

        public Color getPlaceholderForeground()
        {
            return placeholderForeground;
        }

        public void setPlaceholderForeground(Color placeholderForeground)
        {
            this.placeholderForeground = placeholderForeground;
        }

        public boolean isTextWrittenIn()
        {
            return textWrittenIn;
        }

        public void setTextWrittenIn(boolean textWrittenIn)
        {
            this.textWrittenIn = textWrittenIn;
        }

        public void setPlaceholder(final String text)
        {
            this.customizeText(text);
            this.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    warn();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    warn();
                }

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    warn();
                }

                public void warn()
                {
                    if (getText().trim().length() != 0)
                    {
                        setFont(originalFont);
                        setForeground(originalForeground);
                        setTextWrittenIn(true);
                    }
                }
            });
            this.addFocusListener(new FocusListener()
            {
                @Override
                public void focusGained(FocusEvent e)
                {
                    if (!isTextWrittenIn())
                    {
                        setText("");
                    }

                }
                @Override
                public void focusLost(FocusEvent e)
                {
                    if (getText().trim().length() == 0)
                    {
                        customizeText(text);
                    }
                }
            });
        }

        /**
         * Customize text method that will set the text from passed in String
         * @param text String text to write.
         */
        private void customizeText(String text)
        {
            setText(text);
            /**If you change font, family and size will follow
             * changes, while style will always be italic**/
            setFont(new Font(getFont().getFamily(), Font.ITALIC, getFont().getSize()));
            setForeground(getPlaceholderForeground());
            setTextWrittenIn(false);
        }
    }//End of Class CustomTextField
}
