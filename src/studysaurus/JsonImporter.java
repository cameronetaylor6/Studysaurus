package studysaurus;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonImporter {

	private String _filePath;
	private Set _set;
	private DatabaseConnector dc = DatabaseConnector.getInstance();

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
			_set.setName(setName);

			JSONArray termValues = (JSONArray) jObj.get("termValues");

			for(int i = 0; i < termValues.size(); i++) {
				JSONObject jPair = (JSONObject) termValues.get(i);
				java.util.Set<String> keySet = jPair.keySet();
				for(String key : keySet) {
					String termStr = (String) key;
					Object value = jPair.get(termStr);
					String valueStr = (String) value;
					Pair pair = new Pair(termStr, valueStr, setName);
					_set.addPair(pair);
				}
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