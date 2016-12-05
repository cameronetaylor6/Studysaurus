package studysaurus;

import javax.swing.*;

import studysaurus.Asteroid.AsteroidState;

import java.util.ArrayList;
import java.util.Iterator;

public final class GameClient {
    //instance variables
    private static final GameClient instance = new GameClient();
    private static Page currentPage;
    private static Set currentSet;
    private static int difficulty;
    private static int dinosaurCount;
    private static String guess;
    private static Score score;
    private static Asteroid asteroid;

	private GameClient() {
        currentPage = new HomePage("Studysaurus");
        Page.createAndShowGUI(currentPage);
        currentSet = null;
        difficulty = 8; //  set default to easy
        dinosaurCount = 3;
        guess = null;
        score = new Score();
        asteroid = null;
    }

    public static GameClient getInstance() {
        return instance;
    }

    //getters and setters
    public void setCurrentPage(Page page) {
        currentPage = page;
    }
    public Page getCurrentPage() {
        return currentPage;
    }
    public void setCurrentSet(Set set) {
        currentSet = set;
    }
    public Set getCurrentSet() {
        return currentSet;
    }
    public void setDifficulty(int dif) {
    	difficulty = dif;
    }
    public int getDifficulty() {
    	return difficulty;
    }
    public void setDinosaurCount(int count) {
        dinosaurCount = count;
    }
    public int getDinosaurCount() {
        return dinosaurCount;
    }
    public void setGuess(String _guess) {
    	guess = _guess;
    }
    public String getGuess() {
    	return guess;
    }
    public void setScore(Score _score) {
        score = _score;
    }
    public Score getScore() {
        return score;
    }
    public void setAsteroid(Asteroid ast) {
    	asteroid = ast;
    }
    public Asteroid getAsteroid() {
    	return asteroid;
    }

    public Boolean checkAnswer(Pair termValue, String guess) {
		if (termValue.getValue().toLowerCase().equals(guess.toLowerCase())) {
			return true;
		}
		else {
			return false;
		}
    }
    
    public void createAndSetAsteroid() {
    	asteroid = new Asteroid(difficulty);
    }

    public void incrementScore() {
        int cur = score.getScore();
        score.setScore(cur + 100);
    }

    public static void main(String[] args) {
        //schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
  
            }
        });
    }

}