import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class Asteroid implements Subject {
	static class AsteroidState {
		public boolean impacted = false;
		public boolean diffused = false;
	}

	private AsteroidState _state;
	private Timer _timer;
	private Pair _termValue;
	private List<Observer> _observers;	

	//TODO: update difficulty to enumeration/class?
	Asteroid(Pair pair, int difficulty) {
		_state = new AsteroidState();
		_termValue = pair;
		_timer = new Timer();
		_timer.schedule (new Impact(), difficulty*1000);
	}

	class Impact extends TimerTask {
		public void run() {
			_state.impacted = true;
			notifyObservers();
			_timer.cancel(); //kill timer thread
		}
	}

	private void destroyAsteroid() {
		_state.diffused = true;
		notifyObservers();
	}

	@Override
	public void register(Observer obj) {
		if(obj == null) throw new NullPointerException("null observer - asteroid");
	
		if(!_observers.contains(obj)) _observers.add(obj);
	}

	@Override
	public void unregister(Observer obj) {
		_observers.remove(obj);
	}

	@Override
	public void notifyObservers() {
		List<Observer> obsTemp = null;
		
		if (!_state.impacted && !_state.diffused) return;
	
		for (Observer obj : _observers) {
			obj.update();
		}
	}

	@Override
	public Object getUpdate(Observer obj) {
		return _state;
	}
		

	public static void main(String[] args) {
		
	}
}