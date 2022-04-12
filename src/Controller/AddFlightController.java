package Controller;

import Model.*;
import View.AddFlightGUI;
import View.TravelGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AddFlightController {

    private TravelModel model;
    private AddFlightGUI view;
    public static boolean suspended = false;

    public AddFlightController(TravelModel model, AddFlightGUI view) {
        this.model = model;
        this.view = view;
        view.addSetListener(new SetListener());
    }


    public class SetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.addFlight) {
                try {
                    addFlight();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(view.getRootPane(), "Please press start");
            } else if (e.getSource() == view.close) {
                view.dispose();
                JOptionPane.showMessageDialog(view.getRootPane(), "Please press start");
                TravelGUI.addFlightGUIisActive = false;
            } else if (e.getSource() == view.addToList) {
                addToList();
            } else if (e.getSource() == view.removeFromList) {
                removeFromList();

            } else if (e.getSource() == view.destinationCombo || e.getSource() == view.departureCombo) {
                destinationDepartureChange();

            }
        }
    }

    //Adds a new flight to the flights HashMap
    private void addFlight() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
        Date earliest = dateFormat.parse("01:01:2000");
        Date latest = dateFormat.parse("30:12:2099");

        //Retrieve values from the text boxes
        String date = view.dateTxt.getText();
        String time = view.timeTxt.getText();
        String departure = view.departureCombo.getSelectedItem().toString();
        String destination = view.destinationCombo.getSelectedItem().toString();
        String planeCode = view.planeCombo.getSelectedItem().toString();

        //Generate flight code - airline code + random 3 digit number
        String[] airlineStuff = view.airlineCombo.getSelectedItem().toString().split(" ");
        String airlineCode = airlineStuff[0];
        Random rand = new Random();
        int randNo = rand.nextInt(999 - 100);
        if (randNo < 100) {
            randNo += 100;
        }
        String flightCode = airlineCode + randNo;

        //Check if the departure and destination airports are the same, don't allow users to continue if so
        if (departure.equals(destination)) {
            JOptionPane.showMessageDialog(view.getRootPane(), "destination and departure can not be the same");
            return;
        }
        //Check if data and time are empty, if so don't allow users to continue
        if (date.equals(null) || time.equals(null)) {
            JOptionPane.showMessageDialog(view.getRootPane(), "empty field is not allowed");
            return;
        }
        //Make sure data matches a specific format (DD:MM:YYYY)
        if (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Date does not match dd:mm:yyyy format");
            return;
        }

        Date enteredDate = dateFormat.parse(date);

        if (enteredDate.after(latest)) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Date cannot be after 30-12-2099");
            return;
        }

        if (enteredDate.before(earliest)) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Date cannot be before 01-01-2000");
            return;
        }

        //Make sure time matches a specific format (HH:MM)
        if (!time.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Time does not match hh:mm format");
            return;
        }
        String[] timeSplit = time.split(":");

        String hour = timeSplit[0];
        String minute = timeSplit[1];

        if (Integer.parseInt(hour) > 23 || Integer.parseInt(hour) < 0) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Hours cannot be greater than 23 or less than 0");
            return;
        }
        if (Integer.parseInt(minute) > 59 || Integer.parseInt(hour) < 0) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Minutes cannot be greater than 59 or less than 0");
            return;
        }


        //Create new FlightPlan object
        FlightPlan plan = new FlightPlan();

        //Find the departure airport from the airports HashMap and add it to the beginning of flight plan
        Airport departureA = this.model.getAirports().get(departure);
        plan.addToPlan(departureA);

        //Loop through all the airport codes in the user generated flight plan list
        for (int x = 0; x < view.flightPlan.size(); x++) {
            //retrieve the airports from the HashMap and add them to the flight plan
            plan.addToPlan(this.model.getAirports().get(view.flightPlan.get(x).toString()));
        }

        //Find the destination airport from the airports HashMap and add it to the end of flight plan
        Airport destinationA = this.model.getAirports().get(destination);
        plan.addToPlan(destinationA);

        //Create a new flight object with all the retrieved information
        Flight flight = new Flight(flightCode, this.model.getAeroplanes().get(planeCode), departureA, destinationA, date, time, plan, this.model.getAirlines().get(airlineCode));

        //Add the flight to the flight HashMap
        this.model.addFlight(flight);

        Log.getInstance().addToLog(flightCode + " has been added");
        //Reset the main GUI to allow for the updated flights HashMap
        TravelGUI.addFlightGUIisActive = false;
        //travelGUI.resetList(); //FIGURE OUT

        //Close this window
        view.dispose();
    }

    //Adds a specific airport code to the flight plan list
    private void addToList() {
        //Retrieve the selected code from the control tower list
        String controlTowerCode = view.controlTList.getSelectedValue();

        //Ensure no more than 6 control towers can be added
        if (view.flightPlan.getSize() >= 6) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Cannot add more than 6 control towers!");
            return;
        }

        //Ensure that the user has actually selected a code
        if (view.controlTList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Nothing Selected!");
        } else {
            //Retrieve the index of the selected code
            int value = view.controlTList.getSelectedIndex();

            //Don't allow the user to add the departure airport to the plan
            if (view.departureCombo.getSelectedItem().toString().equals(controlTowerCode)) {
                JOptionPane.showMessageDialog(view.getRootPane(), "Can't add departure to flight plan");
            }
            //Don't allow the user to add the destination airport to the plan
            else if (view.destinationCombo.getSelectedItem().toString().equals(controlTowerCode)) {
                JOptionPane.showMessageDialog(view.getRootPane(), "Can't add destination to flight plan");
            } else {
                //Add the selected code to the flight plan list
                view.flightPlan.addElement(controlTowerCode);

                //Reset the UI element
                view.flightPlanList.setModel(view.flightPlan);

                //Remove the selected code from the control tower list to ensure that the user doesn't add the same one twice
                if (view.flightPlan.getSize() != 0) {
                    view.controlTowers.removeElementAt(value);
                }
            }
        }
    }

    //Removes an element from the flight plan list
    private void removeFromList() {
        //Retrieve the selected code from the flight plan list
        String flightPlanCode = view.flightPlanList.getSelectedValue();

        //Ensure the user has made a selction
        if (view.flightPlanList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(view.getRootPane(), "Nothing Selected");
        } else {
            //Retrieve the index of the element
            int value = view.flightPlanList.getSelectedIndex();

            //Add the element back to the control tower list
            view.controlTowers.addElement(flightPlanCode);

            //Reset the UI element
            view.controlTList.setModel(view.controlTowers);

            //Remove the element from the flight plan list
            if (view.controlTowers.getSize() != 0) {
                view.flightPlan.removeElementAt(value);
            }
        }
    }

    //Resets the control tower list once the destination or departure airport has been changed
    private void destinationDepartureChange() {
        //Loop through all of the elements in flightPlan add them all back into controlTower
        for (int x = 0; x < view.flightPlan.size(); x++) {
            view.controlTowers.addElement(view.flightPlan.get(x));
        }

        //Reset the UI element
        view.controlTList.setModel(view.controlTowers);

        //Reset the flight plan list
        view.flightPlan.clear();

        //Reset the UI element
        view.flightPlanList.setModel(view.flightPlan);
    }
}