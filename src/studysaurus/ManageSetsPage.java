package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JSeparator;


public class ManageSetsPage extends Page{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(5,0);
	 JButton createSetButton, editSetButton, importSetButton, exportSetButton, backButton;
     
	    public ManageSetsPage(String name) {
	        super(name);
	    }
	    
	    @Override
	    public void drawPage(final Container pane) {
	    	panel.setLayout(layout);
	         
	        //Set up components preferred size
	        JButton b = new JButton("Just fake button");
	        Dimension buttonSize = b.getPreferredSize();
	        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
	                (int)(buttonSize.getHeight() * 6.5) * 2));
	         
	        //Add buttons to experiment with Grid Layout
	        
	        createSetButton = new JButton("Create Set");
	        createSetButton.addActionListener(this);
	        editSetButton = new JButton("Edit Set");
	        editSetButton.addActionListener(this);
	        importSetButton = new JButton("Import Set");
	        importSetButton.addActionListener(this);
	        exportSetButton = new JButton("Export Set");
	        exportSetButton.addActionListener(this);
	        backButton = new JButton("Back");
	        backButton.addActionListener(this);
	        
	        panel.add(createSetButton);
	        panel.add(editSetButton);
	        panel.add(importSetButton);
	        panel.add(exportSetButton);
	        panel.add(backButton);
	       
	        pane.add(panel, BorderLayout.NORTH);
	        pane.add(new JSeparator(), BorderLayout.CENTER);
	}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj == createSetButton){
				CreateSetPage createSetPage = new CreateSetPage("CreateSetPage");
				createAndShowGUI(createSetPage);
			}
			else if(obj == editSetButton){
				EditSetPage editSetPage = new EditSetPage("EditSetPage");
				createAndShowGUI(editSetPage);
			}
			else if(obj == importSetButton){
				ImportSetPage importSetPage = new ImportSetPage("ImportSetPage");
				createAndShowGUI(importSetPage);
			}
			else if(obj == exportSetButton){
				ExportSetPage exportSetPage = new ExportSetPage("ExportSetPage");
				createAndShowGUI(exportSetPage);
			}
			else if(obj == backButton){
				HomePage homePage = new HomePage("HomePage");
				createAndShowGUI(homePage);
			}
			this.dispose();
			
		}


}
