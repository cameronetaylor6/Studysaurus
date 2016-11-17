import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class PlayGamePage extends Page {
	GridLayout layout = new GridLayout(1,1);
	JTextField termField, definitionField, scoreField;
	JButton exitGameButton;

	public PlayGamePage(String name) {
		super(name);
	}

	@Override
	void drawPage(Container pane) {
		panel.setLayout(layout);
		JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
                (int)(buttonSize.getHeight() * 6.5) * 2));
		JPanel gamePanel = new JPanel(new GridLayout(7,0));
		panel.add(gamePanel);
		
		JLabel termLabel = new JLabel("Term");
		termLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		termField = new JTextField();
		termField.setEditable(false);
		JLabel definitionLabel = new JLabel("Definition");
		definitionLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		definitionField = new JTextField();
		definitionField.addActionListener(this);
		JLabel scoreLabel  = new JLabel("Score");
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		scoreField = new JTextField();
		scoreField.setEditable(false);
		exitGameButton = new JButton("Done");
		exitGameButton.addActionListener(this);
		
		
		gamePanel.add(scoreLabel);
		gamePanel.add(scoreField);
		gamePanel.add(termLabel);
		gamePanel.add(termField);
		gamePanel.add(definitionLabel);
		gamePanel.add(definitionField);
		gamePanel.add(exitGameButton);
		
		pane.add(panel, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == exitGameButton){
			HomePage homePage = new HomePage("HomePage");
			createAndShowGUI(homePage);
			this.dispose();
		}
		else if(obj == definitionField){
			@SuppressWarnings("unused")
			String inputText = definitionField.getText();
			//compare
		}
	}

}
