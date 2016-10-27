public class Score {
	private int id;
	private String userName;
	private String setName;
	private int score;

	Score(String _userName, String _setName, int _score) {
		userName = _userName;
		setName = _setName;
		score = _score;
	}

	public int getId() {
		return id;
	}
	public void setId(int _id) {
		id = _id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String _userName) {
		userName = _userName;
	}
	public String getsetName() {
		return setName;
	}
	public void setsetName(String _setName) {
		setName = _setName;
	}
	public int getscore() {
		return score;
	}
	public void setscore(int _score) {
		score = _score;
	}

	public static void main(String[] args) {
		
	}
}