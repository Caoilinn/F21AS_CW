package View;

import Controller.AddFlightController;
import Controller.TravelController;
import Model.ControlTower;
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
    public JButton addFlight, editFlight, close, start, stop, showControlTowerFlights;
    public JTextField distance, time, fuel, co2, flightPlan, flightStatus, controlledFlights;
    public JLabel distanceLabel, timeLabel, fuelLabel, co2Label, flightPlanLabel, flightStatusLabel, controlledFlightsLabel;
    public JList<String> flightList, controlTowerList;
    public JScrollPane scrollList, controlTowerScrollList;
    public String selectedFlight;

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

        JPanel p3 = new JPanel();
        flightStatusLabel = new JLabel("Flight Status: ");
        p3.add(flightStatusLabel);
        flightStatus = new JTextField(20);
        flightStatus.setEditable(false);
        p3.add(flightStatus);
        p.add(p3);

        DefaultListModel<String> displayList = new DefaultListModel<>();
        ArrayList<ControlTower> controlTowers = new ArrayList<ControlTower>(model.controlTowers);
        for (ControlTower controlTower : controlTowers) {
            displayList.addElement(controlTower.getName());
        }

        JPanel p4 = new JPanel();

        controlledFlightsLabel = new JLabel("Controlled Flights: ");
        p4.add(controlledFlightsLabel);
        controlledFlights = new JTextField(20);
        controlledFlights.setEditable(false);
        p4.add(controlledFlights);

        controlTowerList = new JList(displayList);
        controlTowerScrollList = new JScrollPane(controlTowerList);
        controlTowerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        p4.add(controlTowerList);
        p.add(p4);


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

        // Close
        close = new JButton("Close");
        p.add(close);

        // Start
        start = new JButton("Start");
        p.add(start);

        // Stop
        stop = new JButton("Stop");
        p.add(stop);


        // Adds the panel to the south with an empty boarder
        this.add(p, BorderLayout.SOUTH);
    }


    // Sets parameters for creating the GUI
    public void guiCreate() {
        this.setTitle("Travel Application");
        this.setPreferredSize(new Dimension(900, 600));
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
    /*public void showFlightEditorGUI() {
        FlightEditorGUI GUI = new FlightEditorGUI();
        GUI.guiCreate();
        this.flightEditorGUIisActive = true;
    }*/

    public synchronized void updateFlightStatus(String selection) {
        if (selection != null) {
            String[] temp = selection.split(" ");
            String flightCode = temp[0];
            Flight flight = model.getFlights().get(flightCode);

            //Retrieve Distance, Emissions, Time and Fuel Consumption from the flight object
            double distance = flight.getCurrentDistance();
            double emissions = flight.getCo2Emissions();
            String time = flight.getDurationOfFlight();
            double fuelConsumption = flight.getFuelConsumption();
            String flightPlan = flight.getFlightPlan().toString();
            String flightStatus = "";

          //  String flightsControlled = flight

            for (int i = 0; i < flight.getFlightPlan().getFlightPlan().size(); i++) {
                if (i < flight.listCounter ) {
                    flightStatus += "Done | ";
                } else if (i == flight.listCounter) {
                    flightStatus += "En route | ";
                }
            }
            //Set the text fields to the appropriate values
            this.distance.setText(String.valueOf(distance));
            this.co2.setText(String.valueOf(emissions));
            this.time.setText(time);
            this.fuel.setText(String.valueOf(fuelConsumption));
            this.flightPlan.setText(flightPlan);
            this.flightStatus.setText(flightStatus);

        }
    }


    //    this.controlledFlights.setText(controlledFlights);

    //This will get called whenever the flights hashmap is added to
    @Override
    public void update() {

        resetList();
        updateFlightStatus(selectedFlight);
    }

    public void setSelectedFlight(String selection) {
        selectedFlight = selection;
    }

    public void addSetListener(TravelController.SetListener setListener) {
        time.addActionListener(setListener);
        fuel.addActionListener(setListener);
        co2.addActionListener(setListener);
        addFlight.addActionListener(setListener);
        close.addActionListener(setListener);
        start.addActionListener(setListener);
        stop.addActionListener(setListener);
        flightList.addListSelectionListener(setListener);
        controlTowerList.addListSelectionListener(setListener);

    }

    // Refreshes the list with any new additions or amendments
    public void resetList() {
        DefaultListModel<String> list = new DefaultListModel<>();
        ArrayList<Flight> flights = new ArrayList<Flight>(model.getFlights().values());
        for (Flight flight : flights) {
            list.addElement(flight.getFlightCode() + "  " + flight.getPlane().getModel() + "  " + flight.getDeparture().getName() + "  " + flight.getDestination().getName() + " "
                    + flight.getDate() + " " + flight.getDepartureTime() + " " + flight.getGPSCoordinates());
        }
        flightList.setModel(list);
    }
}
