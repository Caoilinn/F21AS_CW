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

    // Methods to set up all relevant panels

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == add)
        {
            // TODO
        }
        else if (e.getSource() == close)
        {
            System.exit(0);
        }
    }

    public void guiCreate()
    {
        this.setTitle("Add A Flight");
        this.setPreferredSize(new Dimension(600,300));
        this.setLocation(900,500);
        this.setLayout(new BorderLayout(5,5));
        this.setVisible(true);
        this.pack();
    }

}
