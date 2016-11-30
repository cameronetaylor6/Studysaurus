package studysaurus;

import javax.swing.*;

import studysaurus.Asteroid.AsteroidState;

import java.util.ArrayList;

public final class GameClient implements Observer{
    private static final GameClient instance = new GameClient();

    //instance variables
    private Page currentPage;
    private Set currentSet;
    private int difficulty;
    private int dinosaurCount;
    private Pair currentPair;
    private Pair guess;
    private static Score score;
    private ArrayList<Subject> subjects;
    private ArrayList<Asteroid> asteroids;

    private GameClient() {
        currentPage = new HomePage("Studysaurus");
        currentSet = null;
        difficulty = 0;
        dinosaurCount = 0;
        currentPair = null;
        guess = null;
        score = null;
        subjects = null;
        asteroids = null;
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
    public void setGuess(Pair _guess) {
    	guess = _guess;
    }
    public Pair getGuess() {
    	return guess;
    }
    public void setScore(Score _score) {
        score = _score;
    }
    public Score getScore() {
        return score;
    }

    public void update(Subject sub) {
        if (sub instanceof Asteroid) {
            AsteroidState state = (AsteroidState) sub.getUpdate(this);
            if(state.impacted == true && state.diffused == false) {
                //  destroy asteroid?
            }
        }
        else if (sub instanceof GameOptionsPage) {
            difficulty = (int) sub.getUpdate(this);
        }
        else if (sub instanceof PlayGamePage) {
        	guess = (Pair) sub.getUpdate(this);
        	if (checkAnswer(guess)) {
        		incrementScore();
        		//TODO: update new pair
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

    //methods
    //TODO: compelte
    private static void displayHomePage() {

    }
    //TODO: complete
    private static void displaySuccessPage() {

    }
    //TODO: complete
    private static void displayFailurePage() {

    }
    public static Boolean checkAnswer(Pair guess) {
		if (currentPair.value.toLowerCase() == guess.value.toLowerCase()) {
			return true;
		}
		else {
			return false;
		}
    }
    //TODO: compelte
    private static void createAsteroid(Pair termValue) {

    }
    

    private static void incrementScore() {
        int cur = score.getScore();
        score.setScore(cur + 100);
    }

    private static void createFrame() {
    	//create window
    	JFrame frame = new JFrame("Studysaurus");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	//add title label
    	JLabel label = new JLabel("Studysaurus");
        frame.getContentPane().add(label);

        //show frame
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createFrame();
                displayHomePage();
            }
        });
    }

}