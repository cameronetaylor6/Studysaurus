import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;


public class CreateSetPage extends Page  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(4,0);
	JButton savePairButton, doneButton, cancelButton;
	JTextField setNameBox, termBox, definitionBox;
	

	public CreateSetPage(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void drawPage(Container pane) {
		panel.setLayout(layout);
    	
    	//Set up sub-panels
		JPanel titlePanel = new JPanel(new GridLayout(2,0));
        JPanel termDefinitionPanel = new JPanel(new GridLayout(2,0));
        JPanel saveTermPanel = new JPanel(new GridLayout(1,0));
        JPanel doneAndCancelPanel = new JPanel(new GridLayout(1,1));
        panel.add(titlePanel);
        panel.add(termDefinitionPanel);
        panel.add(saveTermPanel);
        panel.add(doneAndCancelPanel);

         
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
                (int)(buttonSize.getHeight() * 6.5) * 2));
         
        //Add Set Name Prompt
        titlePanel.add(new JLabel("What would you like to name your set?"));
        setNameBox = new JTextField();
        setNameBox.addActionListener(this);
        titlePanel.add(setNameBox);
        
        //Add Term Prompt
        termDefinitionPanel.add(new JLabel("Enter a term:"));
        termBox = new JTextField();
        termDefinitionPanel.add(termBox);
        
        //Add Definition Prompt
        termDefinitionPanel.add(new JLabel("Enter the term's definition:"));
        definitionBox = new JTextField();
        termDefinitionPanel.add(definitionBox);
        
        //Add Save Term Button
        savePairButton = new JButton("Save Pair");
        savePairButton.addActionListener(this);
        saveTermPanel.add(savePairButton);
        
        //Add Done Button
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        doneAndCancelPanel.add(doneButton);
        
        //Add Cancel Button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        doneAndCancelPanel.add(cancelButton);

        //Display to the pane
        pane.add(panel, BorderLayout.CENTER);
        pane.add(new JSeparator(), BorderLayout.NORTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == cancelButton){
			ManageSetsPage manageSetsPage = new ManageSetsPage("ManageSetsPage");
			createAndShowGUI(manageSetsPage);
			this.dispose();
		}
		else if(obj == doneButton){
			HomePage homePage = new HomePage("HomePage");
			createAndShowGUI(homePage);
			this.dispose();
		}
		else if(obj == savePairButton){
			//Save term and definition to DB
			termBox.setText("");
			definitionBox.setText("");
		}
		else if(obj == setNameBox){
			//Save name to DB
		}
			
		
		
	}

}
