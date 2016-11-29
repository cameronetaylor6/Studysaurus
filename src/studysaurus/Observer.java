package studysaurus;
public interface Observer {

	//used by subject to update the observer
	public void update(Subject sub);

	public void setSubject(Subject sub);
}