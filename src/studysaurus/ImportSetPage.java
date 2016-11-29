package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ImportSetPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(4,0);
	JButton browseButton, importButton, doneButton, cancelButton;
	JTextField setNameTextField, filenameTextfield;
	File selectedFile;

	public ImportSetPage(String name) {
		super(name);
	}

	@Override
	void drawPage(Container pane) {
		panel.setLayout(layout);
		
		JPanel setNamePanel = new JPanel(new GridLayout(2,0));
		JPanel selectFilePanel = new JPanel(new GridLayout(2,0));
		JPanel importFilePanel = new JPanel(new GridLayout(2,0));
		JPanel finishedPanel = new JPanel(new GridLayout(2,0));
		panel.add(setNamePanel);
		panel.add(selectFilePanel);
		panel.add(importFilePanel);
		panel.add(finishedPanel);
		
		JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
                (int)(buttonSize.getHeight() * 6.5) * 2));

		setNameTextField = new JTextField();
		setNameTextField.addActionListener(this);
		setNamePanel.add(new JLabel("What would you like to name this set?"));
		setNamePanel.add(setNameTextField);
		
		browseButton = new JButton("Browse");
		browseButton.addActionListener(this);
		selectFilePanel.add(new JLabel("Please select the file containing your set:"));
		selectFilePanel.add(browseButton);
		
		filenameTextfield = new JTextField();
		filenameTextfield.setEditable(false);
		importButton = new JButton("Import");
		importButton.addActionListener(this);
		importFilePanel.add(filenameTextfield);
		importFilePanel.add(importButton);
		
		doneButton = new JButton("Done");
		doneButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		finishedPanel.add(doneButton);
		finishedPanel.add(cancelButton);
		
		pane.add(panel, BorderLayout.CENTER);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == browseButton){
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(ImportSetPage.this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				selectedFile = fc.getSelectedFile();
				filenameTextfield.setText(selectedFile.getName());
			}
		}
		else if(obj == importButton){
			//Import file (File object called selectedFile)
		}
		else if(obj == doneButton || obj == cancelButton){
			ManageSetsPage manageSetsPage = new ManageSetsPage("Manage Sets");
			createAndShowGUI(manageSetsPage);
			this.dispose();
		}
		
	}

}
