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
        JPanel p = new JPanel( new GridBagLayout());

        Airlines al = new Airlines();
        for (Airline Airline : al.getAirlines())
        {
            System.out.println(Airline);
        }

        airlineLbl = new JLabel("Airline:");
        // add items to the combo box
        airline.addItem("Air France");
        airline.addItem("Emirates");

        flightNoLbl = new JLabel("Flight Number:");
        flightNoTxt = new JTextField(12);
        flightNoTxt.addActionListener(this);

        planeLbl = new JLabel("Plane:");
        // add items to the combo box
        plane.addItem("A350");
        plane.addItem("B777");

        departureLbl = new JLabel("Departure:");
        // add items to the combo box
        departure.addItem("CDG");
        departure.addItem("LHR");

        destinationLbl = new JLabel("Destination:");
        // add items to the combo box
        destination.addItem("DXB");
        destination.addItem("EDI");

        // Adding constraints to GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,0,0);
        c.anchor=GridBagConstraints.LINE_START;

        c.gridx=0;c.gridy=1;p.add(airlineLbl,c);
        c.gridx=1;c.gridy=1;p.add(airline,c);
        c.gridx=0;c.gridy=2;p.add(flightNoLbl,c);
        c.gridx=1;c.gridy=2;p.add(flightNoTxt,c);
        c.gridx=0;c.gridy=3;p.add(planeLbl,c);
        c.gridx=1;c.gridy=3;p.add(plane,c);
        c.gridx=0;c.gridy=4;p.add(departureLbl,c);
        c.gridx=1;c.gridy=4;p.add(departure,c);
        c.gridx=0;c.gridy=5;p.add(destinationLbl,c);
        c.gridx=1;c.gridy=5;p.add(destination,c);

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
        this.setPreferredSize(new Dimension(600,300));
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setLocation(700,500);
        this.setLayout(new BorderLayout(5,5));
        this.mainPanel();
        this.setVisible(true);
        this.pack();
    }
}
