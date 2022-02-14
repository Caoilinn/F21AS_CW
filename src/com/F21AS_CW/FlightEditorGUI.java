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
    JTextField flightCode;
    JLabel findByCode;

    // Methods to set up all relevant panels

    public void setupNorthPanel()
    {
        JPanel p = new JPanel();

        findByCode = new JLabel("Flight Code: ");
        p.add(findByCode);

        flightCode = new JTextField(7);
        flightCode.addActionListener(this);
        p.add(flightCode);

        find = new JButton("Find");
        find.addActionListener(this);
        p.add(find);

        this.add(p, BorderLayout.NORTH);
    }

    public void setupSouthPanel()
    {
        JPanel p = new JPanel();

        update = new JButton("Update");
        update.addActionListener(this);
        p.add(update);

        close = new JButton("Close");
        close.addActionListener(this);
        p.add(close);

        this.add(p, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == update)
        {
            // TODO
        }
        else if (e.getSource() == close)
        {
            this.dispose();
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
        this.setupNorthPanel();
        this.setupSouthPanel();
        this.setVisible(true);
        this.pack();
    }

}
