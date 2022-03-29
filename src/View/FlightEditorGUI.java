//package View;
//import Controller.AddFlightController;
//import Controller.FlightEditorController;
//import View.TravelGUI;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class FlightEditorGUI extends JFrame implements ActionListener
//{
//    public FlightEditorGUI()
//    {
//        // TODO
//    }
//
//    // GUI components
//    JButton find, update, close;
//    JTextField flightCode, currentAirline, currentFlightNumber, currentPlane, currentDeparture, currentDestination,
//               newAirline, newFlightNumber, newPlane, newDeparture, newDestination;
//    JLabel findByCode, airlineLabel, flightNumberLabel, planeLabel, departureLabel, destinationLabel, currentLabel, newLabel;
//
//    // Methods to set up all relevant panels
//
//    public void setupNorthPanel()
//    {
//        JPanel p = new JPanel();
//
//        findByCode = new JLabel("Flight Code: ");
//        p.add(findByCode);
//
//        flightCode = new JTextField(7);
//        flightCode.addActionListener(this);
//        p.add(flightCode);
//
//        find = new JButton("Find");
//        find.addActionListener(this);
//        p.add(find);
//
//        this.add(p, BorderLayout.NORTH);
//    }
//
//    public void setupCenterPanel()
//    {
//        JPanel p = new JPanel( new GridBagLayout());
//
//        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(10,0,0,0);
//        c.anchor=GridBagConstraints.LINE_START;
//
//        // Titles
//        currentLabel = new JLabel("Current");
//        newLabel = new JLabel("New");
//
//        c.gridx=1;c.gridy=1;p.add(currentLabel,c);
//        c.gridx=2;c.gridy=1;p.add(newLabel,c);
//
//        // Airline
//        airlineLabel = new JLabel("Airline: ");
//        currentAirline = new JTextField(12);
//        currentAirline.setEditable(false);
//        currentAirline.addActionListener(this);
//        newAirline = new JTextField(12);
//        newAirline.addActionListener(this);
//
//        c.gridx=0;c.gridy=2;p.add(airlineLabel,c);
//        c.gridx=1;c.gridy=2;p.add(currentAirline,c);
//        c.gridx=2;c.gridy=2;p.add(newAirline,c);
//
//        // Flight Number
//        flightNumberLabel = new JLabel("Flight Number: ");
//        currentFlightNumber = new JTextField(12);
//        currentFlightNumber.setEditable(false);
//        currentFlightNumber.addActionListener(this);
//        newFlightNumber = new JTextField(12);
//        newFlightNumber.addActionListener(this);
//
//        c.gridx=0;c.gridy=3;p.add(flightNumberLabel,c);
//        c.gridx=1;c.gridy=3;p.add(currentFlightNumber,c);
//        c.gridx=2;c.gridy=3;p.add(newFlightNumber,c);
//
//        // Plane
//        planeLabel = new JLabel("Plane: ");
//        currentPlane = new JTextField(12);
//        currentPlane.setEditable(false);
//        currentPlane.addActionListener(this);
//        newPlane = new JTextField(12);
//        newPlane.addActionListener(this);
//
//        c.gridx=0;c.gridy=4;p.add(planeLabel,c);
//        c.gridx=1;c.gridy=4;p.add(currentPlane,c);
//        c.gridx=2;c.gridy=4;p.add(newPlane,c);
//
//        // Departure
//        departureLabel = new JLabel("Departure: ");
//        currentDeparture = new JTextField(12);
//        currentDeparture.setEditable(false);
//        currentDeparture.addActionListener(this);
//        newDeparture = new JTextField(12);
//        newDeparture.addActionListener(this);
//
//        c.gridx=0;c.gridy=5;p.add(departureLabel,c);
//        c.gridx=1;c.gridy=5;p.add(currentDeparture,c);
//        c.gridx=2;c.gridy=5;p.add(newDeparture,c);
//
//        // Destination
//        destinationLabel = new JLabel("Departure: ");
//        currentDestination = new JTextField(12);
//        currentDestination.setEditable(false);
//        currentDestination.addActionListener(this);
//        newDestination = new JTextField(12);
//        newDestination.addActionListener(this);
//
//        c.gridx=0;c.gridy=6;p.add(destinationLabel,c);
//        c.gridx=1;c.gridy=6;p.add(currentDestination,c);
//        c.gridx=2;c.gridy=6;p.add(newDestination,c);
//
//        this.add(p);
//    }
//
//    public void setupSouthPanel()
//    {
//        JPanel p = new JPanel();
//
//        update = new JButton("Update");
//        update.addActionListener(this);
//        p.add(update);
//
//        close = new JButton("Close");
//        close.addActionListener(this);
//        p.add(close);
//
//        this.add(p, BorderLayout.SOUTH);
//    }
//
//    public void addSetListener(FlightEditorController.SetListener setListener) {
//
//    }
//    public void guiCreate()
//    {
//        this.setTitle("Flight Editor");
//        this.setPreferredSize(new Dimension(600,300));
//        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
//        this.setLocation(400,500);
//        this.setLayout(new BorderLayout(5,5));
//        this.setupNorthPanel();
//        this.setupSouthPanel();
//        this.setupCenterPanel();
//        this.setVisible(true);
//        this.pack();
//    }
//
//}
