package studysaurus;
public interface Subject {
	public void register(Observer obj);
	public void unregister(Observer obj);

	public void notifyObservers();

	//used by observer to check subject's state
	public Object getUpdate(Observer obj);
}