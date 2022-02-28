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
    JTextField distance, time, fuel, co2, flightPlan;
    JLabel distanceLabel, timeLabel, fuelLabel, co2Label, flightPlanLabel;
    JList<String> flightList;
    //JTextArea textArea;
    JScrollPane scrollList;



    // Methods to set up all relevant panels

    public void setupCenterPanel()
    {
        // Create new panel
        JPanel p = new JPanel();
        BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(box);

        // Creates placeholder list and assigns it to the panel, sets single selection
        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        list.addElement("AF670   A350   CDG   DBX   10/01/2022   23:00");
        flightList = new JList(list);
        scrollList = new JScrollPane(flightList);
        flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        p.add(scrollList);

        // Creates another panel within the box layout and
        // assigns a label and a text field to itself before
        // being assigned back to the boxlayout panel
        JPanel p2 = new JPanel();
        flightPlanLabel = new JLabel("Flight Plan: ");
        p2.add(flightPlanLabel);
        flightPlan = new JTextField(20);
        flightPlan.setEditable(false);
        p2.add(flightPlan);
        p.add(p2);

        // Adds panel to the center and sets an empty boarder around it
        this.add(p, BorderLayout.CENTER);
        p.setBorder(new EmptyBorder(new Insets(25, 25, 25, 25)));
    }

    public void setupWestPanel()
    {

        JPanel p = new JPanel();
        BoxLayout boxlayout = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(boxlayout);

        // Distance
        distanceLabel = new JLabel("Distance (km): ");
        p.add(distanceLabel);
        distance = new JTextField(3);
        distance.setEditable(false);
        distance.addActionListener(this);
        p.add(distance);

        // Time
        timeLabel = new JLabel("Time: ");
        p.add(timeLabel);
        time = new JTextField(3);
        time.addActionListener(this);
        time.setEditable(false);
        p.add(time);

        // Fuel consumption
        fuelLabel = new JLabel("Fuel Consumption: ");
        p.add(fuelLabel);
        fuel = new JTextField(3);
        fuel.addActionListener(this);
        fuel.setEditable(false);
        p.add(fuel);

        // CO2 emission
        co2Label = new JLabel("CO2 Emission: ");
        p.add(co2Label);
        co2 = new JTextField(3);
        co2.addActionListener(this);
        co2.setEditable(false);
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