package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;


public class EditSetPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(5,0);
	JButton addPairButton, deletePairButton, editPairButton, doneButton;
	JTextField editTerm, editDefinition, addTerm, addDefinition, deleteTerm, deleteDefinition;
	JComboBox<String> selectSetComboBox, selectPairEditComboBox, selectPairDeleteComboBox;
	
	String setName;
	Set setToEdit= new Set();
	ArrayList<Pair> pairs = new ArrayList<Pair>();
	MutableComboBoxModel<String> editModel;
	MutableComboBoxModel<String> deleteModel;
	

	public EditSetPage(String name) {
		super(name);
	}

	void drawPage(Container pane) {
		panel.setLayout(layout);
		
		//Set up sub-panels
		JPanel selectSetPanel = new JPanel(new GridLayout(1,1));
		JPanel addPairPanel = new JPanel(new GridLayout(1,4));
		JPanel editPairPanel = new JPanel(new GridLayout(1,4));
		JPanel deletePairPanel = new JPanel(new GridLayout(1,4));
		panel.add(selectSetPanel);
		panel.add(addPairPanel);
		panel.add(editPairPanel);
		panel.add(deletePairPanel);
		
		//Stuff for selectSetPanel
		ArrayList<String> setNames = dc.getSets(true);
	    selectSetComboBox = new JComboBox(setNames.toArray());
	    selectSetComboBox.addActionListener(this);
	    selectSetPanel.add(new JLabel("Select a set to edit:"));
	    selectSetPanel.add(selectSetComboBox);
	    
	    //Stuff for addPairPanel
	    addTerm = new JTextField();
	    addTerm.addActionListener(this);
	    addDefinition = new JTextField();
	    addDefinition.addActionListener(this);
	    addPairButton = new JButton("SavePair");
	    addPairButton.addActionListener(this);
	    addPairPanel.add(new JLabel("Add Pair"));
	    addPairPanel.add(addTerm);
	    addPairPanel.add(addDefinition);
	    addPairPanel.add(addPairButton);
	    
	    //Stuff for editPairPanel
	    
	    selectPairEditComboBox = new JComboBox();
	    editModel = (MutableComboBoxModel<String>) selectPairEditComboBox.getModel();
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
	    selectPairDeleteComboBox = new JComboBox();
	    deleteModel = (MutableComboBoxModel<String>) selectPairDeleteComboBox.getModel();
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
			setName = selectSetComboBox.getSelectedItem().toString();
			setToEdit = dc.selectSet(setName);
			pairs = setToEdit.getPairs();
			for(int i=0; i<pairs.size();i++){
				String pair = pairs.get(i).toString();
				editModel.addElement(pair.substring(pair.indexOf(':')+2, pair.length()));
				deleteModel.addElement(pair.substring(pair.indexOf(':')+2, pair.length()));
			}
		}
		else if(obj == addPairButton){
			Pair newPair = new Pair(addTerm.getText(), addDefinition.getText(), setName);
			setToEdit.addPair(newPair);
			editModel.addElement(newPair.getTerm() + " , " + newPair.getValue());
			deleteModel.addElement(newPair.getTerm() + " , " + newPair.getValue());
			addTerm.setText("");
			addDefinition.setText("");
		}
		else if(obj == editPairButton){
			newTerm = editTerm.getText();
			newDef = editDefinition.getText();
			setToEdit.editPair(pairs.get(selectPairDeleteComboBox.getSelectedIndex()), new Pair(newTerm, newDef, setName));
			editTerm.setText("");
			editDefinition.setText("");
			selectPairDeleteComboBox.removeItem(selectPairEditComboBox.getSelectedItem());
			selectPairDeleteComboBox.addItem(newTerm + " , " + newDef);
			selectPairEditComboBox.removeItem(selectPairEditComboBox.getSelectedItem());
			selectPairEditComboBox.addItem(newTerm + " , " + newDef);
			
		}
		else if(obj == deletePairButton){
			String deletedTerm = deleteTerm.getText();
			String deletedDef = deleteDefinition.getText();
			setToEdit.deletePair(pairs.get(selectPairDeleteComboBox.getSelectedIndex()));
			deleteTerm.setText("");
			deleteDefinition.setText("");
			selectPairEditComboBox.removeItem(selectPairDeleteComboBox.getSelectedItem());
			selectPairDeleteComboBox.removeItem(selectPairDeleteComboBox.getSelectedItem());
			
		}
		else if(obj == doneButton){
			System.out.print(setToEdit.toString());
			dc.saveSet(setToEdit);
			ManageSetsPage manageSetsPage = new ManageSetsPage("StudySaurus");
			createAndShowGUI(manageSetsPage);
			this.dispose();
		}
		else if(obj == selectPairEditComboBox){
			String pairStr = (String)selectPairEditComboBox.getSelectedItem();
			editTerm.setText(pairStr.substring(0, pairStr.indexOf(',')));
			editDefinition.setText(pairStr.substring(pairStr.indexOf(',')+2, pairStr.length()));
		}
		else if(obj == selectPairDeleteComboBox){
			String pairStr = (String)selectPairDeleteComboBox.getSelectedItem();
			deleteTerm.setText(pairStr.substring(0, pairStr.indexOf(',')));
			deleteDefinition.setText(pairStr.substring(pairStr.indexOf(',')+2, pairStr.length()));
		}
		
	}

}
