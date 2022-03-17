package Controller;

import Model.Flight;
import Model.TravelModel;
import View.TravelGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelController {

    private TravelModel model;
    private TravelGUI view;

    public TravelController(TravelModel model, TravelGUI view) {
        this.model = model;
        this.view = view;
        view.addSetListener(new SetListener());
    }

    public class SetListener implements ActionListener, ListSelectionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Add
            if (e.getSource() == view.addFlight && view.addFlightGUIisActive == false) {
                view.showAddFlightGUI();
            }

            // Edit
            else if (e.getSource() == view.editFlight && view.flightEditorGUIisActive == false) {
                view.showFlightEditorGUI();
            }

            // Close
            else if (e.getSource() == view.close) {
                JOptionPane.showMessageDialog(view.getRootPane(), "Report File Generated");
                model.writeToFile();
                System.exit(0);
            }
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String x = view.flightList.getSelectedValue();
            String[] temp = x.split(" ");
            String flightCode = temp[0];
            Flight flight = model.getFlights().get(flightCode);

            //Retrieve Distance, Emissions, Time and Fuel Consumption from the flight object
            double distance = flight.getCurrentDistance();
            double emissions = flight.getCo2Emissions();
            String time = flight.getDurationOfFlight();
            double fuelConsumption = flight.getFuelConsumption();
            String flightPlan = flight.getFlightPlan().toString();

            //Set the text fields to the appropriate values
            view.distance.setText(String.valueOf(distance));
            view.co2.setText(String.valueOf(emissions));
            view.time.setText(time);
            view.fuel.setText(String.valueOf(fuelConsumption));
            view.flightPlan.setText(flightPlan);
        }
    }
}
