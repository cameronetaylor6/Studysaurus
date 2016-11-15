import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;


public class ViewScorePage extends Page {
	 GridLayout layout = new GridLayout(4,0);
     
	    public ViewScorePage(String name) {
	        super(name);
	    }
	     
	    public void drawPage(final Container pane) {
	    	panel.setLayout(layout);
	        panel.setLayout(layout);
	        JPanel controls = new JPanel();
	        controls.setLayout(new GridLayout(4,0));
	         
	        //Set up components preferred size
	        JButton b = new JButton("Just fake button");
	        Dimension buttonSize = b.getPreferredSize();
	        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
	                (int)(buttonSize.getHeight() * 6.5) * 2));
	         
	        //Add buttons to experiment with Grid Layout
	        
	        panel.add(new JButton("Play Game!"));
	       
	        pane.add(panel, BorderLayout.NORTH);
	        pane.add(new JSeparator(), BorderLayout.CENTER);
	}



}
