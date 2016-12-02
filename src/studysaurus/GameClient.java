package studysaurus;

import javax.swing.*;

import studysaurus.Asteroid.AsteroidState;

import java.util.ArrayList;
import java.util.Iterator;

public final class GameClient implements Observer{
    private static final GameClient instance = new GameClient();

    //instance variables
    private static Page currentPage;
    private static Set currentSet;
    private static int difficulty;
    private static int dinosaurCount;
    private static Pair currentPair;
    private static String guess;
    private static Score score;
    private static Asteroid asteroid;
    private static ArrayList<Subject> subjects;
    
    public Pair getCurrentPair() {
		return currentPair;
	}

	public void setCurrentPair(Pair currentPair) {
		this.currentPair = currentPair;
	}

	private GameClient() {
        currentPage = new HomePage("Studysaurus");
        Page.createAndShowGUI(currentPage);
        currentSet = null;
        difficulty = 0;
        dinosaurCount = 0;
        currentPair = null;
        guess = null;
        score = null;
        asteroid = null;
        subjects = null;
        randomizedSet = null;
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
    public void setSubjects(ArrayList<Subject> _subjects) {
    	subjects = _subjects;
    }
    public ArrayList<Subject> getSubjects() {
    	return subjects;
    }

    public void update(Subject sub) {
        if (sub instanceof Asteroid) {
            AsteroidState state = (AsteroidState) sub.getUpdate(this);
            if(state.impacted == true && state.diffused == false) {
                //  destroy asteroid?
            }
        }
        else if (sub instanceof PlayGamePage) {
        	if (checkAnswer(guess)) {
        		incrementScore();
        		//TODO: update new pair (use currentpage?)
        	}
        	else {
        		if (dinosaurCount > 0) {
        			dinosaurCount--;
        		}
        		else {
        			dinosaurCount = 0;
        		}
        	}
        }
    }

    public void setSubject(Subject topic) {
        subjects.add(topic);
    }

    public Boolean checkAnswer(Pair guess) {
		if (currentPair.getValue().toLowerCase() == guess.getValue().toLowerCase()) {
			return true;
		}
		else {
			return false;
		}
    }
    
    private static void createAsteroid(Pair termValue) {
    	asteroid = new Asteroid(termValue, difficulty);
    }

    private static void incrementScore() {
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