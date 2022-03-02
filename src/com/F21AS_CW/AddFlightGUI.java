package com.F21AS_CW;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class AddFlightGUI extends JFrame implements ActionListener
{
    public AddFlightGUI()
    {
        // TODO
    }


    // GUI components
    JButton add, close;
    JLabel flightNoLbl, airlineLbl,planeLbl, departureLbl, destinationLbl,dateLbl,timeLbl;
    JTextField flightNoTxt,dateTxt,timeTxt;
    JComboBox<String> airlineCombo = new JComboBox<String>();
    JComboBox<String> planeCombo = new JComboBox<String>();
    JComboBox<String> departureCombo = new JComboBox<String>();
    JComboBox<String> destinationCombo = new JComboBox<String>();

    // Methods to set up all relevant panels
    public void mainPanel() {
        JPanel p = new JPanel( new GridBagLayout());

        airlineLbl = new JLabel("Airline:");
        // add items to the combo box

        ArrayList<Airline> airlines = new ArrayList<Airline>(Airlines.getAirlines().values());
        Collections.sort(airlines);
        for (Airline airline: airlines) {
            airlineCombo.addItem(airline.getCode()+" "+airline.getName());
        }

        flightNoLbl = new JLabel("Flight Number:");
        flightNoTxt = new JTextField(12);
        flightNoTxt.addActionListener(this);

        planeLbl = new JLabel("Plane:");

        ArrayList<Aeroplane> aeroplanes = new ArrayList<Aeroplane>(Aeroplanes.getAeroplanes().values());
        for (Aeroplane aeroplane: aeroplanes) {
            planeCombo.addItem(aeroplane.getModel());
        }
        departureLbl = new JLabel("Departure:");
        destinationLbl = new JLabel("Destination:");

        Airports ap = new Airports();
        for(String airportCode : ap.getAirports().keySet())
        {
            departureCombo.addItem(airportCode);
            destinationCombo.addItem(airportCode);
        }

        dateLbl = new JLabel("Date:");
        dateTxt = new JTextField(8);
        dateTxt.addActionListener(this);

        timeLbl = new JLabel("Time:");
        timeTxt = new JTextField(8);
        timeTxt.addActionListener(this);

        // Adding constraints to GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,0,0);
        c.anchor=GridBagConstraints.LINE_START;

        c.gridx=0;c.gridy=1;p.add(airlineLbl,c);
        c.gridx=1;c.gridy=1;p.add(airlineCombo,c);
        c.gridx=0;c.gridy=2;p.add(flightNoLbl,c);
        c.gridx=1;c.gridy=2;p.add(flightNoTxt,c);
        c.gridx=0;c.gridy=3;p.add(planeLbl,c);
        c.gridx=1;c.gridy=3;p.add(planeCombo,c);
        c.gridx=0;c.gridy=4;p.add(departureLbl,c);
        c.gridx=1;c.gridy=4;p.add(departureCombo,c);
        c.gridx=0;c.gridy=5;p.add(destinationLbl,c);
        c.gridx=1;c.gridy=5;p.add(destinationCombo,c);
        c.gridx=0;c.gridy=6;p.add(dateLbl,c);
        c.gridx=1;c.gridy=6;p.add(dateTxt,c);
        c.gridx=0;c.gridy=7;p.add(timeLbl,c);
        c.gridx=1;c.gridy=7;p.add(timeTxt,c);

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
            // ToDo

        }
        else if (e.getSource() == close)
        {
            this.dispose();
            TravelGUI.addFlightGUIisActive = false;
        }
    }

    public void guiCreate()
    {
        this.setTitle("Add A Flight");
        this.setPreferredSize(new Dimension(600,400));
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setLocation(600,400);
        this.setLayout(new BorderLayout(5,5));
        this.mainPanel();
        this.setVisible(true);
        this.pack();
    }
}