package edu.miracosta.cs113.GUI;

import edu.miracosta.cs113.GUI.Controller;

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

    public FrontPage(){
        //Must catch ClassNotFoundException, IllegalAccessException and UnsupportedLookAndFeelException.
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
        setLayout(new BorderLayout());
        welcomeLabel=new JLabel("Welcome to...");
        add(welcomeLabel,BorderLayout.NORTH);
        setSize(525,550);
        panel1=new JPanel();

        try {
            panel1 = new JPanelWithBackground(backgroundImageFile);
        } catch(IOException e) {
            System.out.println("Do you have all the necessary files to run this file? Please try again.");
        }
        panel1.setLayout(new GridLayout(3,2));

        cityLabel=new JLabel(" Enter starting city:");
        cityLabel.setFont(new Font("Serif", Font.BOLD, 20));
        panel1.add(cityLabel);
        cityCombo=new JComboBox<>();
        fillCitiesCombo();
        panel1.add(cityCombo);

        rateLabel=new JLabel("Enter infection rate:");
        rateLabel.setFont(new Font("Serif", Font.BOLD, 20));
        panel1.add(rateLabel);
//        rateField=new JTextField("in people per day");
        rateField = new CustomTextField();
        rateField.setPlaceholder("in people per day.");
        panel1.add(rateField);

        rateButton = new JButton("Get Ratings");
        Controller controller = new Controller();
        rateButton.addActionListener(controller);
        add(rateButton,BorderLayout.SOUTH);

        add(panel1,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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

    private class JPanelWithBackground extends JPanel {
        private Image backgroundImage;

        // Some code to initialize the background image.
        // Here, we use the constructor to load the image.
        public JPanelWithBackground(String fileName) throws IOException {
            backgroundImage = ImageIO.read(new File(fileName));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the background image.
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    private class CustomTextField extends JTextField {

        private Font originalFont;
        private Color originalForeground;
        /**
         * Grey by default*
         */
        private Color placeholderForeground = new Color(160, 160, 160);
        private boolean textWrittenIn;

        public CustomTextField() {
            super();
        }

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
