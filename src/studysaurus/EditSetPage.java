package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditSetPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(5,0);
	JButton addPairButton, deletePairButton, editPairButton, doneButton;
	JTextField editTerm, editDefinition, addTerm, addDefinition, deleteTerm, deleteDefinition;
	JComboBox<String> selectSetComboBox, selectPairEditComboBox, selectPairDeleteComboBox;
	

	public EditSetPage(String name) {
		super(name);
	}

	@Override
	void drawPage(Container pane) {
		panel.setLayout(layout);
		
		//Set up sub-panels
		JPanel selectSetPanel = new JPanel(new GridLayout(1,1));
		JPanel addPairPanel = new JPanel(new GridLayout(1,3));
		JPanel editPairPanel = new JPanel(new GridLayout(1,4));
		JPanel deletePairPanel = new JPanel(new GridLayout(1,4));
		panel.add(selectSetPanel);
		panel.add(addPairPanel);
		panel.add(editPairPanel);
		panel.add(deletePairPanel);
		
		//Stuff for selectSetPanel
	    String[] setNames = {"Set1", "Set2", "Set3"};
	    selectSetComboBox = new JComboBox<String>(setNames);
	    selectSetComboBox.addActionListener(this);
	    selectSetPanel.add(new JLabel("Select a set to edit:"));
	    selectSetPanel.add(selectSetComboBox);
	    
	    //Stuff for addPairPanel
	    addTerm = new JTextField();
	    addTerm.addActionListener(this);
	    addDefinition = new JTextField();
	    addDefinition.addActionListener(this);
	    addPairPanel.add(new JLabel("Add Pair"));
	    addPairPanel.add(addTerm);
	    addPairPanel.add(addDefinition);
	    
	    //Stuff for editPairPanel
	    String[] pairs = {"Hello : Hola", "Goodbye : Adios"};
	    selectPairEditComboBox = new JComboBox<String>(pairs);
	    selectPairEditComboBox.addActionListener(this);
	    editTerm = new JTextField();
	    editTerm.addActionListener(this);
	    editDefinition = new JTextField();
	    editDefinition.addActionListener(this);
	    editPairButton = new JButton("Save Pair");
	    editPairButton.addActionListener(this);
	    editPairPanel.add(new JLabel("Edit Pair"));
	    editPairPanel.add(selectPairEditComboBox);
	    editPairPanel.add(editTerm);
	    editPairPanel.add(editDefinition);
	    editPairPanel.add(editPairButton);
	    
	    //Stuff for deletePairPanel
	    selectPairDeleteComboBox = new JComboBox<String>(pairs);
	    selectPairDeleteComboBox.addActionListener(this);
	    deleteTerm = new JTextField();
	    deleteTerm.setEditable(false);
	    deleteDefinition = new JTextField();
	    deleteDefinition.setEditable(false);
	    deletePairButton = new JButton("Delete Pair");
	    deletePairButton.addActionListener(this);
	    deletePairPanel.add(new JLabel("Delete Pair"));
	    deletePairPanel.add(selectPairDeleteComboBox);
	    deletePairPanel.add(deleteTerm);
	    deletePairPanel.add(deleteDefinition);
	    deletePairPanel.add(deletePairButton);
	    
	    //Add Done Button
	    doneButton = new JButton("Done");
	    doneButton.addActionListener(this);
	    panel.add(doneButton);
	    
	    pane.add(panel, BorderLayout.CENTER);
		
	}

	public void actionPerformed(ActionEvent e) {
		String newTerm, newDef;
		Object obj = e.getSource();
		if(obj == selectSetComboBox){
			//Load set in from DB and populate the drop-downs
		}
		else if(obj == addPairButton){
			newTerm = addTerm.getText();
			newDef = addDefinition.getText();
			//Save Pair to DB
			addTerm.setText("");
			addDefinition.setText("");
		}
		else if(obj == editPairButton){
			newTerm = editTerm.getText();
			newDef = editDefinition.getText();
			//Save new Pair to DB
			editTerm.setText("");
			editDefinition.setText("");
			selectPairEditComboBox.removeItem(selectPairEditComboBox.getSelectedItem());
			selectPairEditComboBox.addItem(newTerm + " : " + newDef);
		}
		else if(obj == deletePairButton){
			@SuppressWarnings("unused")
			String deletedTerm = deleteTerm.getText();
			@SuppressWarnings("unused")
			String deletedDef = deleteDefinition.getText();
			//Delete Pair from DB
			//Remove from GUI
			deleteTerm.setText("");
			deleteDefinition.setText("");
			selectPairDeleteComboBox.removeItem(selectPairDeleteComboBox.getSelectedItem());
		}
		else if(obj == doneButton){
			ManageSetsPage manageSetsPage = new ManageSetsPage("ManageSetsPage");
			createAndShowGUI(manageSetsPage);
			this.dispose();
		}
		else if(obj == selectPairEditComboBox){
			editTerm.setText((String)selectPairEditComboBox.getSelectedItem());
			editDefinition.setText((String)selectPairEditComboBox.getSelectedItem());
		}
		else if(obj == selectPairDeleteComboBox){
			deleteTerm.setText((String)selectPairDeleteComboBox.getSelectedItem());
			deleteDefinition.setText((String)selectPairDeleteComboBox.getSelectedItem());
		}
		
	}

}
