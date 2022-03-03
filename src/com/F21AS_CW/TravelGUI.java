package com.F21AS_CW;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TravelGUI extends JFrame implements ActionListener, ListSelectionListener {
    public static boolean addFlightGUIisActive = false;
    public static boolean flightEditorGUIisActive = false;

    // Declare lists to be searched
    public TravelGUI() {}

    // GUI components
    JButton addFlight, editFlight, close;
    JTextField distance, time, fuel, co2, flightPlan;
    JLabel distanceLabel, timeLabel, fuelLabel, co2Label, flightPlanLabel;
    JList<String> flightList;
    JScrollPane scrollList;


    // GUI panels setup
    public void setupCenterPanel() {

        JPanel p = new JPanel();
        BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(box);

        // Creates a selectable, scrollable list of currently stored flights
        DefaultListModel<String> list = new DefaultListModel<>();
        ArrayList<Flight> flights = new ArrayList<Flight>(Flights.getFlights().values());
        for (Flight flight : flights) {
            list.addElement(flight.getFlightCode() + "  " + flight.getPlane().getModel() + "  " + flight.getDeparture().getName() + "  " + flight.getDestination().getName() + " "
                    + flight.getDate() + " " + flight.getDepartureTime());
        }
        flightList = new JList(list);
        flightList.addListSelectionListener(this);
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

        // Adds the panel to the center with an empty boarder
        this.add(p, BorderLayout.CENTER);
        p.setBorder(new EmptyBorder(new Insets(25, 25, 25, 25)));
    }

    public void setupWestPanel() {

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

        // Adds the panel to the west with an empty boarder
        this.add(p, BorderLayout.WEST);
        p.setBorder(new EmptyBorder(new Insets(25, 25, 25, 25)));
    }

    public void setupSouthPanel()
    {
        JPanel p = new JPanel();

        // Add
        addFlight = new JButton("Add");
        addFlight.addActionListener(this);
        p.add(addFlight);

        // Edit
        editFlight = new JButton("Edit");
        editFlight.addActionListener(this);
        p.add(editFlight);

        // Close
        close = new JButton("Close");
        close.addActionListener(this);
        p.add(close);

        // Adds the panel to the south with an empty boarder
        this.add(p, BorderLayout.SOUTH);
    }

    // Action listener for the GUI components
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Add
        if (e.getSource() == addFlight && this.addFlightGUIisActive == false)
        {showAddFlightGUI();}

        // Edit
        else if (e.getSource() == editFlight && this.flightEditorGUIisActive == false)
        {showFlightEditorGUI();}

        // Close
        else if (e.getSource() == close)
        {
            TravelManager.writeToFiles();
            System.exit(0);
        }
    }

    // Sets parameters for creating the GUI
    public void guiCreate()
    {
        this.setTitle("Travel Application");
        this.setPreferredSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setLocation(700, 200);
        this.setLayout(new BorderLayout(5, 5));
        this.setupSouthPanel();
        this.setupCenterPanel();
        this.setupWestPanel();
        this.setVisible(true);
        this.pack();
    }

    // Creates a new AddFlightGUI window only if there isn't one currently open
    public void showAddFlightGUI()
    {
        AddFlightGUI GUI = new AddFlightGUI(this);
        GUI.guiCreate();
        this.addFlightGUIisActive = true;
    }

    // Creates a new FlightEditorGUI window only if there isn't one currently open
    public void showFlightEditorGUI()
    {
        FlightEditorGUI GUI = new FlightEditorGUI();
        GUI.guiCreate();
        this.flightEditorGUIisActive = true;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String x = flightList.getSelectedValue();
        String[] temp = x.split(" ");
        String flightCode = temp[0];
        Flight flight = Flights.getFlights().get(flightCode);

        //Retrieve Distance, Emissions, Time and Fuel Consumption from the flight object
        double distance = flight.getDistance();
        double emissions = flight.getCo2Emissions();
        String time = flight.getDurationOfFlight();
        double fuelConsumption = flight.getFuelConsumption();
        String flightPlan = flight.getFlightPlan().toString();

        //Set the text fields to the appropriate values
        this.distance.setText(String.valueOf(distance));
        this.co2.setText(String.valueOf(emissions));
        this.time.setText(time);
        this.fuel.setText(String.valueOf(fuelConsumption));
        this.flightPlan.setText(flightPlan);
    }

    // Refreshes the list with any new additions or amendments
    public void resetList()
    {
        DefaultListModel<String> list = new DefaultListModel<>();
        ArrayList<Flight> flights = new ArrayList<Flight>(Flights.getFlights().values());
        for (Flight flight : flights) {
            list.addElement(flight.getFlightCode() + "  " + flight.getPlane().getModel() + "  " + flight.getDeparture().getName() + "  " + flight.getDestination().getName() + " "
                    + flight.getDate() + " " + flight.getDepartureTime());
        }
        flightList.setModel(list);
    }
}