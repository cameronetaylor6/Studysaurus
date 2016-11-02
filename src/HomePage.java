import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HomePage extends Page{
    GridLayout layout = new GridLayout(4,0);
     
    public HomePage(String name) {
        super(name);
    }
     
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(layout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(4,0));
         
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5),
                (int)(buttonSize.getHeight() * 3.5) * 2));
         
        //Add buttons to experiment with Grid Layout
        compsToExperiment.add(new JButton("Play Game"));
        compsToExperiment.add(new JButton("Manage Sets"));
        compsToExperiment.add(new JButton("View Score"));
        compsToExperiment.add(new JButton("Exit"));
       
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
}
