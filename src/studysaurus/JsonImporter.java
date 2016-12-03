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
	DatabaseConnector dc = DatabaseConnector.getInstance();

	JsonImporter(String filePath) {
		_filePath = filePath;
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

			for (Object term : termValues.keySet()) {
				String termStr = (String) term;
				Object value = termValues.get(termStr);
				String valueStr = (String) value;
				Pair pair = new Pair(termStr, valueStr, setName);
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

	public boolean importAndSave() {
		if (ImportJson()) {
			dc.saveSet(_set);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

	}
}