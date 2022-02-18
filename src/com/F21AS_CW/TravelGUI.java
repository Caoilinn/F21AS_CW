package com.F21AS_CW;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TravelGUI extends JFrame implements ActionListener
{
    public static boolean addFlightGUIisActive = false;
    public static boolean flightEditorGUIisActive = false;

    // Declare lists to be searched
    public TravelGUI()
    {
        // TODO
    }

    // GUI components
    JButton addFlight, editFlight, close;
    JTextField distance, time, fuel, co2;
    JLabel distanceLabel, timeLabel, fuelLabel, co2Label;
    JTextArea textArea;
    JScrollPane scrollList;



    // Methods to set up all relevant panels

    public void setupCenterPanel()
    {
        textArea = new JTextArea(15,20);
        scrollList = new JScrollPane(textArea);
        this.add(scrollList, BorderLayout.CENTER);
        textArea.setVisible(true);
    }

    public void setupWestPanel()
    {
        JPanel p = new JPanel();
        BoxLayout boxlayout = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(boxlayout);

        distanceLabel = new JLabel("Distance (km): ");
        p.add(distanceLabel);

        distance = new JTextField(3);
        distance.addActionListener(this);
        p.add(distance);

        timeLabel = new JLabel("Time: ");
        p.add(timeLabel);

        time = new JTextField(3);
        p.add(time);

        fuelLabel = new JLabel("Fuel Consumption: ");
        p.add(fuelLabel);

        fuel = new JTextField(3);
        p.add(fuel);

        co2Label = new JLabel("CO2 Emission: ");
        p.add(co2Label);

        co2 = new JTextField(3);
        p.add(co2);
        this.add(p, BorderLayout.WEST);

        p.setBorder(new EmptyBorder(new Insets(25, 25, 25, 25)));
    }

    public void setupSouthPanel()
    {
        JPanel p = new JPanel();

        addFlight = new JButton("Add");
        addFlight.addActionListener(this);
        p.add(addFlight);

        editFlight = new JButton("Edit");
        editFlight.addActionListener(this);
        p.add(editFlight);

        close = new JButton("Close");
        close.addActionListener(this);
        p.add(close);

        this.add(p, BorderLayout.SOUTH);
    }

    // When a button is clicked, execute appropriate action
   @Override
   public void actionPerformed(ActionEvent e)
   {
        if (e.getSource() == addFlight && this.addFlightGUIisActive==false)
        {
            showAddFlightGUI();
        }
        else if (e.getSource() == editFlight && this.flightEditorGUIisActive==false)
        {
            showFlightEditorGUI();
        }
        else if (e.getSource() == close)
        {
            System.exit(0);
        }
   }

    public void guiCreate()
    {
        this.setTitle("Travel Application");
        this.setPreferredSize(new Dimension(600,300));
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setLocation(700,200);
        this.setLayout(new BorderLayout(5,5));
        this.setupSouthPanel();
        this.setupCenterPanel();
        this.setupWestPanel();
        this.setVisible(true);
        this.pack();
    }

    public void showAddFlightGUI()
    {
        // Creates a new AddFlightGUI window only if there isn't one currently open
        AddFlightGUI GUI = new AddFlightGUI();
        GUI.guiCreate();
        this.addFlightGUIisActive = true;
    }

    public void showFlightEditorGUI()
    {
        FlightEditorGUI GUI = new FlightEditorGUI();
        GUI.guiCreate();
        this.flightEditorGUIisActive = true;
    }
}