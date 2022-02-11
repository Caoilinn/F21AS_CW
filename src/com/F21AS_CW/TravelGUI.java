package com.F21AS_CW;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TravelGUI extends JFrame implements ActionListener
{
    // Declare lists to be searched
    public TravelGUI()
    {
        // TODO
    }

    // GUI components
    JButton add, cancel;
    JTextArea textArea;
    JScrollPane scrollList;


    // Methods to set up all relevant panels

    public void setupCenterPanel()
    {
        textArea = new JTextArea(15,20);
        scrollList = new JScrollPane(textArea);
        this.add(scrollList, BorderLayout.CENTER);
        textArea.setVisible(true);
    }

    public void setupSouthPanel()
    {
        JPanel p = new JPanel();

        add = new JButton("Add");
        add.addActionListener(this);
        p.add(add);

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        p.add(cancel);

        this.add(p, BorderLayout.SOUTH);
    }

    // When a button is clicked, execute appropriate action
   @Override
   public void actionPerformed(ActionEvent e)
   {
        if (e.getSource() == add)
        {
             // TODO
        }
        else if (e.getSource() == cancel)
        {
            // TODO
        }
   }

    public void guiCreate()
    {
        JFrame Frame = new JFrame();
        Frame.setPreferredSize(new Dimension(600,500));
        Frame.setLocation(700, 200);
        Frame.setTitle("Travel Application");
        Frame.setVisible(true);
        Frame.pack();
    }
}