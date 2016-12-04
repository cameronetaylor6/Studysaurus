package studysaurus;
import java.util.Timer;
import java.util.TimerTask;

import java.util.List;

public class Asteroid {
	static class AsteroidState {
		public boolean impacted = false;
		public boolean diffused = false;
	}
	protected GameClient gameClient = GameClient.getInstance();
	public AsteroidState state;
	private Timer timer;

	Asteroid(int difficulty) {
		state = new AsteroidState();
		timer = new Timer();
		timer.schedule (new Impact(), difficulty*1000);
	}

	class Impact extends TimerTask {
		public void run() {
			state.impacted = true;
			timer.cancel(); //kill timer thread
		}
	}
		
	public static void main(String[] args) {
		
	}
}