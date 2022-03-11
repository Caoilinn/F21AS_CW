package View;

import Controller.AddFlightController;
import Controller.TravelController;
import Model.Flight;
import Model.Flights;
import Model.TravelModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TravelGUI extends JFrame implements IObserver {
    public static boolean addFlightGUIisActive = false;
    public static boolean flightEditorGUIisActive = false;

    private TravelModel model;

    // Declare lists to be searched
    public TravelGUI(TravelModel model) {
        this.model = model;
    }

    // GUI components
    public JButton addFlight, editFlight, close;
    public JTextField distance, time, fuel, co2, flightPlan;
    public JLabel distanceLabel, timeLabel, fuelLabel, co2Label, flightPlanLabel;
    public JList<String> flightList;
    public JScrollPane scrollList;


    // GUI panels setup
    public void setupCenterPanel() {

        JPanel p = new JPanel();
        BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(box);

        // Creates a selectable, scrollable list of currently stored flights
        DefaultListModel<String> list = new DefaultListModel<>();
        ArrayList<Flight> flights = new ArrayList<Flight>(model.getFlights().values());
        for (Flight flight : flights) {
            list.addElement(flight.getFlightCode() + "  " + flight.getPlane().getModel() + "  " + flight.getDeparture().getName() + "  " + flight.getDestination().getName() + " "
                    + flight.getDate() + " " + flight.getDepartureTime());
        }
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
        p.add(distance);

        // Time
        timeLabel = new JLabel("Time: ");
        p.add(timeLabel);
        time = new JTextField(3);
        time.setEditable(false);
        p.add(time);

        // Fuel consumption
        fuelLabel = new JLabel("Fuel Consumption: ");
        p.add(fuelLabel);
        fuel = new JTextField(3);
        fuel.setEditable(false);
        p.add(fuel);

        // CO2 emission
        co2Label = new JLabel("CO2 Emission: ");
        p.add(co2Label);
        co2 = new JTextField(3);
        co2.setEditable(false);
        p.add(co2);

        // Adds the panel to the west with an empty boarder
        this.add(p, BorderLayout.WEST);
        p.setBorder(new EmptyBorder(new Insets(25, 25, 25, 25)));
    }

    public void setupSouthPanel() {
        JPanel p = new JPanel();

        // Add
        addFlight = new JButton("Add");
        p.add(addFlight);

        // Edit
        editFlight = new JButton("Edit");
        p.add(editFlight);

        // Close
        close = new JButton("Close");
        p.add(close);

        // Adds the panel to the south with an empty boarder
        this.add(p, BorderLayout.SOUTH);
    }


    // Sets parameters for creating the GUI
    public void guiCreate() {
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
    public void showAddFlightGUI() {
        System.out.println("Add Flight GUI");
        AddFlightGUI GUI = new AddFlightGUI(this, this.model);
        GUI.guiCreate();
        AddFlightController addFlightController = new AddFlightController(this.model, GUI);
        this.addFlightGUIisActive = true;
    }

    // Creates a new FlightEditorGUI window only if there isn't one currently open
    public void showFlightEditorGUI() {
        FlightEditorGUI GUI = new FlightEditorGUI();
        GUI.guiCreate();
        this.flightEditorGUIisActive = true;
    }

    //This will get called whenever the flights hashmap is added to
    @Override
    public void update() {
        resetList();
    }

    public void addSetListener(TravelController.SetListener setListener) {
        time.addActionListener(setListener);
        fuel.addActionListener(setListener);
        co2.addActionListener(setListener);
        addFlight.addActionListener(setListener);
        editFlight.addActionListener(setListener);
        close.addActionListener(setListener);
        flightList.addListSelectionListener(setListener);
    }

    // Refreshes the list with any new additions or amendments
    public void resetList() {
        DefaultListModel<String> list = new DefaultListModel<>();
        ArrayList<Flight> flights = new ArrayList<Flight>(model.getFlights().values());
        for (Flight flight : flights) {
            list.addElement(flight.getFlightCode() + "  " + flight.getPlane().getModel() + "  " + flight.getDeparture().getName() + "  " + flight.getDestination().getName() + " "
                    + flight.getDate() + " " + flight.getDepartureTime());
        }
        flightList.setModel(list);
    }
}