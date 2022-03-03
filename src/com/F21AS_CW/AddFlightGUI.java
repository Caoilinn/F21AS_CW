package com.F21AS_CW;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

public class AddFlightGUI extends JFrame implements ActionListener {
    TravelGUI travelGUI;

    public AddFlightGUI(TravelGUI travelGUI) {
        this.travelGUI = travelGUI;
    }

    // GUI components
    JButton addFlight, close, addToList, removeFromList;
    JLabel flightNoLbl, airlineLbl, planeLbl, departureLbl, destinationLbl, dateLbl, timeLbl;
    JTextField flightNoTxt, dateTxt, timeTxt;
    JComboBox<String> airlineCombo = new JComboBox<String>();
    JComboBox<String> planeCombo = new JComboBox<String>();
    JComboBox<String> departureCombo = new JComboBox<String>();
    JComboBox<String> destinationCombo = new JComboBox<String>();
    JList<String> controlTList, flightPlanList;
    JScrollPane scrollList1, scrollList2;
    DefaultListModel controlTowers, flightPlan;

    // Methods to set up all relevant panels
    public void mainPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        airlineLbl = new JLabel("Airline:");

        // add items to the combo box
        ArrayList<Airline> airlines = new ArrayList<Airline>(Airlines.getAirlines().values());
        Collections.sort(airlines);
        for (Airline airline : airlines) {
            airlineCombo.addItem(airline.getCode() + " " + airline.getName());
        }

        planeLbl = new JLabel("Plane:");

        ArrayList<Aeroplane> aeroplanes = new ArrayList<Aeroplane>(Aeroplanes.getAeroplanes().values());
        for (Aeroplane aeroplane : aeroplanes) {
            planeCombo.addItem(aeroplane.getModel());
        }

        departureLbl = new JLabel("Departure:");
        destinationLbl = new JLabel("Destination:");
        Airports ap = new Airports();
        for (String airportCode : ap.getAirports().keySet()) {
            departureCombo.addItem(airportCode);
            destinationCombo.addItem(airportCode);
        }

        destinationCombo.addActionListener(this);
        departureCombo.addActionListener(this);

        dateLbl = new JLabel("Date:");
        dateTxt = new JTextField(8);
        dateTxt.addActionListener(this);

        timeLbl = new JLabel("Time:");
        timeTxt = new JTextField(8);
        timeTxt.addActionListener(this);

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
        addFlight.addActionListener(this);
        p2.add(addFlight);
        close = new JButton("Close");
        close.addActionListener(this);
        p2.add(close);
        this.add(p2, BorderLayout.NORTH);


        JPanel p3 = new JPanel();
        controlTowers = new DefaultListModel<>();
        flightPlan = new DefaultListModel<>();

        // Populating the list of control towers from the hashmap
        for (String airportCode : ap.getAirports().keySet()) {
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
        addToList.addActionListener(this);
        removeFromList = new JButton("Remove");
        removeFromList.addActionListener(this);
        p3.add(addToList);
        p3.add(removeFromList);
        this.add(p3, BorderLayout.SOUTH);

        p3.setBorder(BorderFactory.createTitledBorder("Flight Plan"));
        p3.add(scrollList1);
        p3.add(scrollList2);

        this.add(p3, BorderLayout.EAST);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFlight) {
            String date = dateTxt.getText();
            String time = timeTxt.getText();
            String departure = departureCombo.getSelectedItem().toString();
            String destination = destinationCombo.getSelectedItem().toString();
            String planeCode = planeCombo.getSelectedItem().toString();
            String[] airlineStuff = airlineCombo.getSelectedItem().toString().split(" ");
            String airlineCode = airlineStuff[0];
            Random rand = new Random();
            int randNo = rand.nextInt(999 - 100);
            if (randNo < 100) {
                randNo += 100;
            }
            String flightCode = airlineCode + randNo;

            if (departure.equals(destination)) {
                JOptionPane.showMessageDialog(rootPane, "destination and departure can not be the same");
                return;
            }
            if (date.equals(null) || time.equals(null)) {
                JOptionPane.showMessageDialog(rootPane, "empty field is not allowed");
                return;
            }
            if (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
                JOptionPane.showMessageDialog(rootPane, "Date does not match dd:mm:yyyy format");
                return;
            }
            if (!time.matches("\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(rootPane, "Time does not match hh:mm format");
                return;
            }

            FlightPlan plan = new FlightPlan();

            Airport departureA = Airports.getAirports().get(departure);
            plan.addToPlan(departureA);
            for (int x = 0; x < flightPlan.size(); x++) {
                plan.addToPlan(Airports.getAirports().get(flightPlan.get(x).toString()));
            }
            Airport destinationA = Airports.getAirports().get(destination);
            plan.addToPlan(destinationA);
            Flight flight = new Flight(flightCode, Aeroplanes.getAeroplanes().get(planeCode), departureA, destinationA, date, time, plan, Airlines.getAirlines().get(airlineCode));
            Flights.getFlights().put(flightCode, flight);
            System.out.println(flight);
            TravelGUI.addFlightGUIisActive = false;
            travelGUI.resetList();
            this.dispose();


        } else if (e.getSource() == close) {
            this.dispose();
            TravelGUI.addFlightGUIisActive = false;
        } else if (e.getSource() == addToList) {
            String controlTowerCode = controlTList.getSelectedValue();

            if (flightPlan.getSize() >= 6) {
                JOptionPane.showMessageDialog(rootPane, "Cannot add more than 6 control towers!");
                return;
            }

            if (controlTList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(rootPane, "Nothing Selected!");
            } else {
                int value = controlTList.getSelectedIndex();

                if (departureCombo.getSelectedItem().toString().equals(controlTowerCode)) {
                    JOptionPane.showMessageDialog(rootPane, "Can't add departure to flight plan");

                } else if (destinationCombo.getSelectedItem().toString().equals(controlTowerCode)) {
                    JOptionPane.showMessageDialog(rootPane, "Can't add destination to flight plan");
                } else {
                    flightPlan.addElement(controlTowerCode);
                    flightPlanList.setModel(flightPlan);
                    if (flightPlan.getSize() != 0) {
                        controlTowers.removeElementAt(value);
                    }
                }
            }
        } else if (e.getSource() == removeFromList) {
            String controlTowerCode = flightPlanList.getSelectedValue();

            if (flightPlanList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(rootPane, "Nothing Selected");
            } else {
                int value = flightPlanList.getSelectedIndex();
                controlTowers.addElement(controlTowerCode);
                controlTList.setModel(controlTowers);

                if (controlTowers.getSize() != 0) {
                    flightPlan.removeElementAt(value);
                }
            }
        } else if (e.getSource() == destinationCombo || e.getSource() == departureCombo) {
            for (int x = 0; x < flightPlan.size(); x++) {
                controlTowers.addElement(flightPlan.get(x));
            }
            controlTList.setModel(controlTowers);
            flightPlan.clear();
            flightPlanList.setModel(flightPlan);

        }
    }

    public void guiCreate() {
        this.setTitle("Add A Flight");
        this.setPreferredSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setLocation(600, 400);
        this.setLayout(new BorderLayout(5, 5));
        this.mainPanel();
        this.setVisible(true);
        this.pack();
    }
}