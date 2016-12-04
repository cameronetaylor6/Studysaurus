package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class PlayGamePage extends Page {
	private GridLayout layout = new GridLayout(1,1);
	private JTextField termField, feedbackField, scoreField, guessField;
	private JButton startButton, exitGameButton, enterButton;
	private Iterator<Pair> randomSet;
	private Pair currentPair;

	public PlayGamePage(String name) {
		super(name);
	}

	void drawPage(Container pane){
		panel.setLayout(layout);
		JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
                (int)(buttonSize.getHeight() * 6.5) * 2));
		JPanel gamePanel = new JPanel(new GridLayout(8,0));
		JPanel enterGuess = new JPanel(new GridLayout(2,1));
		
		BufferedImage dinosaur = null;
		try {
			dinosaur = ImageIO.read(new File("/home/user/Desktop/dino.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(dinosaur));
		panel.add(picLabel);
		
		panel.add(enterGuess);
		panel.add(gamePanel);
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		JLabel termLabel = new JLabel("Term");
		termLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		termField = new JTextField("");
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
			HomePage homePage = new HomePage("HomePage");
			createAndShowGUI(homePage);
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
			System.out.println(gameClient.getAsteroid().state.diffused);
			System.out.println();
			if(gameClient.getAsteroid().state.impacted == false) {
				if (gameClient.checkAnswer(currentPair, guessField.getText()) || gameClient.getAsteroid().state.diffused) {
					gameClient.getAsteroid().state.diffused = true;
					gameClient.incrementScore();
					guessField.setText("");
					feedbackField.setText("Correct - here's the next one!");
					scoreField.setText(gameClient.getScore().toString());
					if(randomSet.hasNext()) {
						currentPair = randomSet.next();
						termField.setText("");
						termField.setText(currentPair.getTerm());
						gameClient.createAndSetAsteroid();
					}
					else {
						feedbackField.setText("You win, good job! Your score was: " + gameClient.getScore());
						dc.saveScore(gameClient.getScore());
					}
				}
				else {
					feedbackField.setText("Incorrect, try again.");
				}
			}
			else{
				int lives = gameClient.getDinosaurCount();
				if(lives > 1) {
					gameClient.setDinosaurCount(lives - 1);
					feedbackField.setText("Time expired, you have " + gameClient.getDinosaurCount() + " left! Keep going!");
					if(randomSet.hasNext()) {
						currentPair = randomSet.next();
						termField.setText(currentPair.getTerm());
						gameClient.createAndSetAsteroid();
					}
					else {
						feedbackField.setText("You win, good job! Your score was: " + gameClient.getScore());
						dc.saveScore(gameClient.getScore());
					}
				}
				else {
					feedbackField.setText("Game over, you're out of lives. Better luck next time!");
				}
			}
		}
	}
}

