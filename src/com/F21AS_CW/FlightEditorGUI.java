package com.F21AS_CW;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlightEditorGUI extends JFrame implements ActionListener
{
    public FlightEditorGUI()
    {
        // TODO
    }

    // GUI components
    JButton find, update, close;

    // Methods to set up all relevant panels

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == update)
        {
            // TODO
        }
        else if (e.getSource() == close)
        {
            System.exit(0);
        }
        else if (e.getSource() == find)
        {
            // TODO
        }
    }

    public void guiCreate()
    {
        this.setTitle("Flight Editor");
        this.setPreferredSize(new Dimension(600,300));
        this.setLocation(400,500);
        this.setLayout(new BorderLayout(5,5));
        this.setVisible(true);
        this.pack();
    }

}
