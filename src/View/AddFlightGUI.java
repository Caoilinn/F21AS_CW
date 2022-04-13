package View;

import Controller.AddFlightController;
import Controller.TravelController;
import Model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

public class AddFlightGUI extends JFrame implements IObserver {
    public TravelGUI travelGUI;
    TravelModel model;

    public AddFlightGUI(TravelGUI travelGUI, TravelModel model) {
        this.travelGUI = travelGUI;
        this.model = model;
    }

    // GUI components
    public JButton addFlight, close, addToList, removeFromList;
    public JLabel flightNoLbl, airlineLbl, planeLbl, departureLbl, destinationLbl, dateLbl, timeLbl, labelTitle, labelInfo;
    public JTextField flightNoTxt, dateTxt, timeTxt;
    public JComboBox<String> airlineCombo = new JComboBox<String>();
    public JComboBox<String> planeCombo = new JComboBox<String>();
    public JComboBox<String> departureCombo = new JComboBox<String>();
    public JComboBox<String> destinationCombo = new JComboBox<String>();
    public JList<String> controlTList, flightPlanList;
    public JScrollPane scrollList1, scrollList2;
    public DefaultListModel controlTowers, flightPlan;

    public void title() {

        JPanel p = new JPanel();
        JPanel title = new JPanel();
        BoxLayout boxlayout = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(boxlayout);

        labelTitle = new JLabel("<html><span style='color: navy;'>Add Flight</span></html>");
        labelTitle.setFont(new Font("Dialog", Font.BOLD, 35));
        title.add(labelTitle);
        p.add(title);

        JPanel textInfo = new JPanel();
        labelInfo = new JLabel("<html><span style='color:  navy;'> Please fill out all the fields below and add a flight plan then select the 'Add flight' button. </span></html>");
        textInfo.add(labelInfo);
        p.add(textInfo);

        this.add(p, BorderLayout.NORTH);
        p.setBorder(new EmptyBorder(new Insets(25, 25, 2, 25)));

    }


    // Methods to set up all relevant panels
    public void mainPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        airlineLbl = new JLabel("Airline:");
        airlineLbl.setFont( new Font("Dialog", Font.BOLD , 15));

        // add items to the combo box
        ArrayList<Airline> airlines = new ArrayList<Airline>(this.model.getAirlines().values());
        Collections.sort(airlines);
        for (Airline airline : airlines) {
            airlineCombo.addItem(airline.getCode() + " " + airline.getName());
        }

        planeLbl = new JLabel("Plane:");
        planeLbl.setFont( new Font("Dialog", Font.BOLD , 15));

        ArrayList<Aeroplane> aeroplanes = new ArrayList<Aeroplane>(this.model.getAeroplanes().values());
        for (Aeroplane aeroplane : aeroplanes) {
            planeCombo.addItem(aeroplane.getModel());
        }

        departureLbl = new JLabel("Departure:");
        departureLbl.setFont( new Font("Dialog", Font.BOLD , 15));
        destinationLbl = new JLabel("Destination:");
        destinationLbl.setFont( new Font("Dialog", Font.BOLD , 15));
        for (String airportCode : model.getAirports().keySet()) {
            departureCombo.addItem(airportCode);
            destinationCombo.addItem(airportCode);
        }

        dateLbl = new JLabel("Date:");
        dateLbl.setFont( new Font("Dialog", Font.BOLD , 15));
        dateTxt = new JTextField(8);

        timeLbl = new JLabel("Time:");
        timeLbl.setFont( new Font("Dialog", Font.BOLD , 15));
        timeTxt = new JTextField(8);

        // Adding constraints to GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;

        c.gridx = 0;
        c.gridy = 1;
        p.add(airlineLbl, c);
        c.gridx = 1;
        c.gridy = 1;
        p.add(airlineCombo, c);
        c.gridx = 0;
        c.gridy = 3;
        p.add(planeLbl, c);
        c.gridx = 1;
        c.gridy = 3;
        p.add(planeCombo, c);
        c.gridx = 0;
        c.gridy = 4;
        p.add(departureLbl, c);
        c.gridx = 1;
        c.gridy = 4;
        p.add(departureCombo, c);
        c.gridx = 0;
        c.gridy = 5;
        p.add(destinationLbl, c);
        c.gridx = 1;
        c.gridy = 5;
        p.add(destinationCombo, c);
        c.gridx = 0;
        c.gridy = 6;
        p.add(dateLbl, c);
        c.gridx = 1;
        c.gridy = 6;
        p.add(dateTxt, c);
        c.gridx = 0;
        c.gridy = 7;
        p.add(timeLbl, c);
        c.gridx = 1;
        c.gridy = 7;
        p.add(timeTxt, c);

        this.add(p);

        // South Panel for the add and close buttons
        JPanel p2 = new JPanel();
        addFlight = new JButton("Add Flight");
        p2.add(addFlight);
        close = new JButton("Close");
        p2.add(close);
        this.add(p2, BorderLayout.PAGE_END);


        JPanel p3 = new JPanel();
        controlTowers = new DefaultListModel<>();
        flightPlan = new DefaultListModel<>();

        // Populating the list of control towers from the hashmap
        for (String airportCode : model.getAirports().keySet()) {
            controlTowers.addElement(airportCode);
        }
        controlTList = new JList(controlTowers);
        controlTList.setVisibleRowCount(16);
        controlTList.setFixedCellWidth(60);
        controlTList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollList1 = new JScrollPane(controlTList);


        flightPlanList = new JList(flightPlan);
        flightPlanList.setVisibleRowCount(18);
        //flightPlanList.setFixedCellHeight(100);
        flightPlanList.setFixedCellWidth(60);
        flightPlanList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollList2 = new JScrollPane(flightPlanList);

        // add and remove list buttons
        addToList = new JButton("Add");
        removeFromList = new JButton("Remove");
        p3.add(addToList);
        p3.add(removeFromList);
        this.add(p3, BorderLayout.SOUTH);

        p3.setBorder(BorderFactory.createTitledBorder("Flight Plan"));
        p3.add(scrollList1);
        p3.add(scrollList2);

        this.add(p3, BorderLayout.EAST);
    }

    public void addSetListener(AddFlightController.SetListener setListener) {
        destinationCombo.addActionListener(setListener);
        departureCombo.addActionListener(setListener);
        close.addActionListener(setListener);
        planeCombo.addActionListener(setListener);
        airlineCombo.addActionListener(setListener);
        dateTxt.addActionListener(setListener);
        timeTxt.addActionListener(setListener);
        addToList.addActionListener(setListener);
        addFlight.addActionListener(setListener);
        removeFromList.addActionListener(setListener);
    }

    //Adds a new flight to the flights HashMap
    private void addFlight() {
        //Retrieve values from the text boxes
        String date = dateTxt.getText();
        String time = timeTxt.getText();
        String departure = departureCombo.getSelectedItem().toString();
        String destination = destinationCombo.getSelectedItem().toString();
        String planeCode = planeCombo.getSelectedItem().toString();

        //Generate flight code - airline code + random 3 digit number
        String[] airlineStuff = airlineCombo.getSelectedItem().toString().split(" ");
        String airlineCode = airlineStuff[0];
        Random rand = new Random();
        int randNo = rand.nextInt(999 - 100);
        if (randNo < 100) {
            randNo += 100;
        }
        String flightCode = airlineCode + randNo;

        //Check if the departure and destination airports are the same, don't allow users to continue if so
        if (departure.equals(destination)) {
            JOptionPane.showMessageDialog(rootPane, "destination and departure can not be the same");
            return;
        }
        //Check if data and time are empty, if so don't allow users to continue
        if (date.equals(null) || time.equals(null)) {
            JOptionPane.showMessageDialog(rootPane, "empty field is not allowed");
            return;
        }
        //Make sure data matches a specific format (DD:MM:YYYY)
        if (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
            JOptionPane.showMessageDialog(rootPane, "Date does not match dd:mm:yyyy format");
            return;
        }
        //Make sure time matches a specific format (HH:MM)
        if (!time.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(rootPane, "Time does not match hh:mm format");
            return;
        }

        //Create new FlightPlan object
        FlightPlan plan = new FlightPlan();

        //Find the departure airport from the airports HashMap and add it to the beginning of flight plan
        Airport departureA = this.model.getAirports().get(departure);
        plan.addToPlan(departureA);

        //Loop through all the airport codes in the user generated flight plan list
        for (int x = 0; x < flightPlan.size(); x++) {
            //retrieve the airports from the HashMap and add them to the flight plan
            plan.addToPlan(this.model.getAirports().get(flightPlan.get(x).toString()));
        }

        //Find the destination airport from the airports HashMap and add it to the end of flight plan
        Airport destinationA = this.model.getAirports().get(destination);
        plan.addToPlan(destinationA);

        //Create a new flight object with all the retrieved information
        Flight flight = new Flight(flightCode, this.model.getAeroplanes().get(planeCode), departureA, destinationA, date, time, plan, this.model.getAirlines().get(airlineCode));

        //Add the flight to the flight HashMap
        this.model.getFlights().put(flightCode, flight);
        //Reset the main GUI to allow for the updated flights HashMap
        TravelGUI.addFlightGUIisActive = false;
        travelGUI.resetList();

        //Close this window
        this.dispose();
    }

    //Adds a specific airport code to the flight plan list
    private void addToList() {
        //Retrieve the selected code from the control tower list
        String controlTowerCode = controlTList.getSelectedValue();

        //Ensure no more than 6 control towers can be added
        if (flightPlan.getSize() >= 6) {
            JOptionPane.showMessageDialog(rootPane, "Cannot add more than 6 control towers!");
            return;
        }

        //Ensure that the user has actually selected a code
        if (controlTList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Nothing Selected!");
        } else {
            //Retrieve the index of the selected code
            int value = controlTList.getSelectedIndex();

            //Don't allow the user to add the departure airport to the plan
            if (departureCombo.getSelectedItem().toString().equals(controlTowerCode)) {
                JOptionPane.showMessageDialog(rootPane, "Can't add departure to flight plan");
            }
            //Don't allow the user to add the destination airport to the plan
            else if (destinationCombo.getSelectedItem().toString().equals(controlTowerCode)) {
                JOptionPane.showMessageDialog(rootPane, "Can't add destination to flight plan");
            } else {
                //Add the selected code to the flight plan list
                flightPlan.addElement(controlTowerCode);

                //Reset the UI element
                flightPlanList.setModel(flightPlan);

                //Remove the selected code from the control tower list to ensure that the user doesn't add the same one twice
                if (flightPlan.getSize() != 0) {
                    controlTowers.removeElementAt(value);
                }
            }
        }
    }

    //Removes an element from the flight plan list
    private void removeFromList() {
        //Retrieve the selected code from the flight plan list
        String flightPlanCode = flightPlanList.getSelectedValue();

        //Ensure the user has made a selction
        if (flightPlanList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Nothing Selected");
        } else {
            //Retrieve the index of the element
            int value = flightPlanList.getSelectedIndex();

            //Add the element back to the control tower list
            controlTowers.addElement(flightPlanCode);

            //Reset the UI element
            controlTList.setModel(controlTowers);

            //Remove the element from the flight plan list
            if (controlTowers.getSize() != 0) {
                flightPlan.removeElementAt(value);
            }
        }
    }

    //Resets the control tower list once the destination or departure airport has been changed
    private void destinationDepartureChange() {
        //Loop through all of the elements in flightPlan add them all back into controlTower
        for (int x = 0; x < flightPlan.size(); x++) {
            controlTowers.addElement(flightPlan.get(x));
        }

        //Reset the UI element
        controlTList.setModel(controlTowers);

        //Reset the flight plan list
        flightPlan.clear();

        //Reset the UI element
        flightPlanList.setModel(flightPlan);
    }

    public void guiCreate() {
        this.setTitle("Add A Flight");
        this.setPreferredSize(new Dimension(800, 520));
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setLocation(400, 160);
        this.setLayout(new BorderLayout(5, 5));
        this.title();
        this.mainPanel();
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void update() {

    }
}