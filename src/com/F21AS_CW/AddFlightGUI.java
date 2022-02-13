package com.F21AS_CW;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddFlightGUI extends JFrame implements ActionListener
{
    public AddFlightGUI()
    {
        // TODO
    }

    // GUI components
    JButton add, close;
    JLabel flightNoLbl, airlineLbl,planeLbl, departureLbl, destinationLbl;
    JTextField flightNoTxt;
    JComboBox<String> airline = new JComboBox<String>();
    JComboBox<String> plane = new JComboBox<String>();
    JComboBox<String> departure = new JComboBox<String>();
    JComboBox<String> destination = new JComboBox<String>();

    // Methods to set up all relevant panels
    public void mainPanel() {
        JPanel p = new JPanel();

        airlineLbl = new JLabel("Airline:");
        p.add( airlineLbl);
        // add items to the combo box
        airline.addItem("Air France");
        airline.addItem("Emirates");
        p.add(airline);

        flightNoLbl = new JLabel("Flight Number:");
        p.add( flightNoLbl);
        flightNoTxt = new JTextField(12);
        flightNoTxt.addActionListener(this);
        p.add(flightNoTxt);

        planeLbl = new JLabel("Plane:");
        p.add(planeLbl);
        // add items to the combo box
        plane.addItem("A350");
        plane.addItem("B777");
        p.add(plane);

        departureLbl = new JLabel("Departure:");
        p.add(departureLbl);
        // add items to the combo box
        departure.addItem("CDG");
        departure.addItem("LHR");
        p.add(departure);

        destinationLbl = new JLabel("Destination:");
        p.add(destinationLbl);
        // add items to the combo box
        destination.addItem("DXB");
        destination.addItem("EDI");
        p.add(destination);

        this.add(p);

        // South Panel for the add & close buttons
        JPanel p2 = new JPanel();
        add = new JButton("Add");
        add.addActionListener(this);
        p2.add(add);

        close = new JButton("Close");
        close.addActionListener(this);
        p2.add(close);
        this.add(p2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == add)
        {
            // TODO
        }
        else if (e.getSource() == close)
        {
            this.dispose();
        }
    }

    public void guiCreate()
    {
        this.setTitle("Add A Flight");
        this.setPreferredSize(new Dimension(600,300));
        this.setLocation(900,500);
        this.setLayout(new BorderLayout(5,5));
        this.mainPanel();
        this.pack();
    }

}
