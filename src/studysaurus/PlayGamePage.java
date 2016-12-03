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
	JTextField termField, feedbackField, scoreField, guessField;
	JButton startButton, exitGameButton, enterButton;
	Iterator<Pair> randomSet;
	Pair currentPair;

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
		//Pair currentPair = gameClient.getCurrentPair();
		//termField = new JTextField(currentPair.getTerm());
	    termField.setEditable(false);
		JLabel definitionLabel = new JLabel("Definition");
		definitionLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		guessField = new JTextField();
		feedbackField = new JTextField();
		feedbackField = new JTextField();
		feedbackField.setEditable(false);
		enterButton = new JButton("Enter");
		enterButton.addActionListener(this);
		enterGuess.add(guessField);
		enterGuess.add(feedbackField);
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
			gameClient.setCurrentPage(new HomePage("StudySaurus"));
			this.dispose();
		}
		if(obj == startButton){
			randomSet = gameClient.getCurrentSet().randomizeSet();
			currentPair = randomSet.next();
			termField.setText(currentPair.getTerm());
			gameClient.createAndSetAsteroid();
		}
		if(obj == enterButton){
			System.out.println(gameClient.getAsteroid().state.impacted);
			if(gameClient.getAsteroid().state.impacted == false) {
				if (gameClient.checkAnswer(currentPair, guessField.getText()) || gameClient.getAsteroid().state.diffused) {
					gameClient.getAsteroid().state.diffused = true;
					gameClient.incrementScore();
					guessField.setText("");
					feedbackField.setText("Correct");
					scoreField.setText(gameClient.getScore().toString());
					if(randomSet.hasNext()) {
						currentPair = randomSet.next();
						termField.setText("");
						termField.setText(currentPair.getTerm());
						gameClient.createAndSetAsteroid();
					}
					else {
						// TODO: game over, you win?
					}
				}
				else {
					feedbackField.setText("Incorrect, try again");
					// TODO: alert user incorrect guess, keep guessing
				}
			}
		}
			else{
				// TODO: alert user time expired
				int lives = gameClient.getDinosaurCount();
				if(lives > 1) {
					gameClient.setDinosaurCount(lives - 1);
					if(randomSet.hasNext()) {
						currentPair = randomSet.next();
						termField.setText(currentPair.getTerm());
						gameClient.createAndSetAsteroid();
					}
					else {
						// TODO: game over, you win?
					}
				}
				else {
					// TODO: you lose, wat do
				}
			}
		}
	}

