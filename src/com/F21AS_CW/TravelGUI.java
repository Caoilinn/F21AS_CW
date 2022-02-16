package com.F21AS_CW;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TravelGUI extends JFrame implements ActionListener
{
    public static boolean addFlightGUIisActive = false;
    public boolean flightEditorGUIisActive = false;

    // Declare lists to be searched
    public TravelGUI()
    {
        // TODO
    }

    // GUI components
    JButton addFlight, editFlight, close;
    JTextField distance, time, fuel, co2;
    JLabel distanceLabel;
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

        distanceLabel = new JLabel("Distance (km): ");
        p.add(distanceLabel);

        distance = new JTextField(7);
        distance.addActionListener(this);
        p.add(distance);

        this.add(p, BorderLayout.WEST);
    }

    public void setupSouthPanel()
    {
        JPanel p = new JPanel();

        addFlight = new JButton("Add Flight");
        addFlight.addActionListener(this);
        p.add(addFlight);

        editFlight = new JButton("Edit Flight");
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