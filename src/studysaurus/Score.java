package studysaurus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Study_Score")
public class Score {
	@Id
	@GeneratedValue Integer id;
	   
	private String _userName;
	private String _setName;
	private int _score;

	public Score() {_score = 0;}
	public Score(String userName, String setName, int score) {
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
	public String getSetName() {
		return _setName;
	}
	public void setSetName(String setName) {
		_setName = setName;
	}
	public int getScore() {
		return _score;
	}
	public void setScore(int score) {
		_score = score;
	}
	
	public String toString(){
		String ret = "";
	 	ret += _userName + ", " + _setName + ": " + Integer.toString(_score);
	 	return ret;
	}

	public static void main(String[] args) {
		
	}
}