package studysaurus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Study_Score")
public class Score {
	@Id
	@GeneratedValue Integer idScore;
	   
	private String _userName;
	private String _setName;
	private int _score;

	Score(String userName, String setName, int score) {
		_userName = userName;
		_setName = setName;
		_score = score;
	}

	public String getUserName() {
		return _userName;
	}
	public void setUserName(String userName) {
		_userName = userName;
	}
	public String getsetName() {
		return _setName;
	}
	public void setsetName(String setName) {
		_setName = setName;
	}
	public int getscore() {
		return _score;
	}
	public void setscore(int score) {
		_score = score;
	}

	public static void main(String[] args) {
		
	}
}