package studysaurus;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonImporter {

	private String _filePath;
	private Set _set;
	//private DatabaseConnector dbConnection;

	JsonImporter(String filePath) {
		_filePath = filePath;
		//dbConnection = new DatabaseConnector();
		_set = new Set();
	}

	private boolean ImportJson() {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(_filePath));
			JSONObject jObj = (JSONObject) obj;
			String setName = (String) jObj.get("name");
			JSONObject termValues = (JSONObject) jObj.get(setName);

			_set.setName(setName);

			for (Object term : jObj.keySet()) {
				String termStr = (String) term;
				Object value = jObj.get(termStr);
				String valueStr = (String) value;
				Pair pair = new Pair(termStr, valueStr, _set.getName());
				_set.addPair(pair);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//TODO: insert set into DB
	private boolean saveToDB() {
		return true;

	}

	public boolean importAndSave() {
		boolean success = ImportJson();
		if (success) {
			success = saveToDB();
		}
		return success;
	}

	public static void main(String[] args) {

	}
}