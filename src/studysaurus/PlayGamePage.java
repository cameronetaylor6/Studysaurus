package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class PlayGamePage extends Page {
	GridLayout layout = new GridLayout(1,1);
	JTextField termField, definitionField, scoreField;
	JTextField guessField;
	JButton startButton, exitGameButton, enterButton;
	GameClient gc = GameClient.getInstance();

	public PlayGamePage(String name) {
		super(name);
	}

	void drawPage(Container pane) {
		panel.setLayout(layout);
		JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
                (int)(buttonSize.getHeight() * 6.5) * 2));
		JPanel gamePanel = new JPanel(new GridLayout(8,0));
		JPanel enterGuess = new JPanel(new GridLayout(2,1));
		panel.add(gamePanel);
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		JLabel termLabel = new JLabel("Term");
		termLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		termField = new JTextField("");
		//Pair currentPair = gc.getCurrentPair();
		//termField = new JTextField(currentPair.getTerm());
	    termField.setEditable(false);
		JLabel definitionLabel = new JLabel("Definition");
		definitionLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		guessField = new JTextField();
		definitionField = new JTextField();
		definitionField.setEditable(false);
		enterButton = new JButton("Enter");
		enterButton.addActionListener(this);
		enterGuess.add(guessField);
		enterGuess.add(definitionField);
		enterGuess.add(enterButton);
		JLabel scoreLabel  = new JLabel("Score");
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		scoreField = new JTextField();
		scoreField.setEditable(false);
		exitGameButton = new JButton("Done");
		exitGameButton.addActionListener(this);
		
		
		gamePanel.add(startButton);
		gamePanel.add(scoreLabel);
		gamePanel.add(scoreField);
		gamePanel.add(termLabel);
		gamePanel.add(termField);
		gamePanel.add(definitionLabel);
		gamePanel.add(enterGuess);
		gamePanel.add(exitGameButton);
		
		pane.add(panel, BorderLayout.CENTER);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == exitGameButton){
			HomePage homePage = new HomePage("HomePage");
			createAndShowGUI(homePage);
			this.dispose();
		}
		else if(obj == startButton){
		    System.out.println(gc.getCurrentSet().toString());
		    Iterator<Pair> randomSet = gc.getCurrentSet().randomizeSet();
			while(randomSet.hasNext()){
				Pair guess = randomSet.next();
				termField.setText(guess.getTerm());
			}
		}
		else if(obj == enterButton){
			state = new Pair(termField.getText(),definitionField.getText(),null);
			//compare
			notifyObservers();
			//TODO: load in next term, clear value
		}
	}

}
