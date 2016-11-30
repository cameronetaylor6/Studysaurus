package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ExportSetPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(1,1);
	JComboBox<String> selectSetToExport;
	JButton exportButton, doneButton, cancelButton;
	JTextField nameSetToExport;

	public ExportSetPage(String name) {
		super(name);
	}

	void drawPage(Container pane) {
		panel.setLayout(layout);
		JPanel selectSetPanel = new JPanel(new GridLayout(2,0));
		JPanel actionPanel = new JPanel(new GridLayout(4,0));
		panel.add(selectSetPanel);
		panel.add(actionPanel);
		
		JButton b = new JButton("Just fake button");
	    Dimension buttonSize = b.getPreferredSize();
	    panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
	                (int)(buttonSize.getHeight() * 6.5) * 2));
		
		String[] setNames = {"Set1", "Set2", "Set3"};
		selectSetToExport = new JComboBox<String>(setNames);
		selectSetToExport.addActionListener(this);
		JLabel label = new JLabel("Select a set to export:");
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		selectSetPanel.add(label);
		selectSetPanel.add(selectSetToExport);
		
		nameSetToExport = new JTextField();
		exportButton = new JButton("Export");
		exportButton.addActionListener(this);
		doneButton = new JButton("Done");
		doneButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		actionPanel.add(nameSetToExport);
		actionPanel.add(exportButton);
		actionPanel.add(doneButton);
		actionPanel.add(cancelButton);
		
		pane.add(panel, BorderLayout.CENTER);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == selectSetToExport){
			//Grab set
			@SuppressWarnings("unused")
			String setName = (String)selectSetToExport.getSelectedItem();
		}
		else if(obj == exportButton){
			@SuppressWarnings("unused")
			String filePath = nameSetToExport.getText();
			//Export
		}
		else if(obj == doneButton || obj == cancelButton){
			ManageSetsPage manageSetsPage = new ManageSetsPage("Manage Sets Page");
			createAndShowGUI(manageSetsPage);
			this.dispose();
		}
	}

}
