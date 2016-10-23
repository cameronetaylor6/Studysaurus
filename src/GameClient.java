import javax.swing.*; 

public class GameClient {

    //instance variables
    private Page currentPage;
    private Set currentSet;
    private int dinosaurCount;
    private Score score;

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
    public Page getCurrentSet() {
        return currentSet;
    }
    public void setDinosaurCount(int count) {
        dinosaurCount = count;
    }
    public Page getDinosaurCount() {
        return dinosaurCount;
    }
    public void setScore(Score _score) {
        score = _score;
    }
    public Page getScore() {
        return score;
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
    //TODO: complete
    public static Boolean checkAnswer(Pair answer) {

    }
    //TODO: compelte
    private static void createAsteroid(Pair termValue) {

    }
    //TODO: complete
    private static void incrementScore() {

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