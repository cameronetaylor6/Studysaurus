import java.util.Timer;
import java.util.TimerTask;

public class Asteroid {

	private Timer timer;
	private Pair termValue;

	//TODO: update difficulty to enumeration/class?
	Asteroid(Pair pair, int difficulty) {
		termValue = pair;
		timer = new Timer();
		timer.schedule (new Impact(), difficulty*1000);
	}

	class Impact extends TimerTask {
		public void run() {
			destroyDinosaur();
			timer.cancel(); //kill timer thread
		}
	}

	//TODO: do we want these static?
	private void destroyDinosaur() {
		//decrement dino count 
	}

	//TODO: how to access counts in GC?
	private void destroyAsteroid() {
		//decrement asteroid count
	}

	public static void main(String[] args) {
		
	}
}