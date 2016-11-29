package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;


public class ViewScorePage extends Page {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(4,0);
	JList<String> scoreList;
    JButton doneButton;
    
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
	        String[] scores = {"100", "99", "98", "100"};
	        scoreList = new JList<String>(scores);
	        doneButton = new JButton("Done");
	        doneButton.addActionListener(this);
	        panel.add(scoreList);
	        panel.add(doneButton);
	       
	        pane.add(panel, BorderLayout.NORTH);
	        pane.add(new JSeparator(), BorderLayout.CENTER);
	}

		@Override
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			if(obj == doneButton){
				HomePage homePage = new HomePage("HomePage");
				createAndShowGUI(homePage);
				this.dispose();
			}
		}



}
