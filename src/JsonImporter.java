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

	private int import() {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(_filePath));
			JSONObject jObj = (JSONObject) obj;
			String setName = (String) jObj.get("name");
			JSONObject termValues = jObj.get(setName);

			_set.setName(setName);

			for (Object term : jObj.keySet()) {
				String termStr = (String) term;
				Object value = jObj.get(termStr);
				String valueStr = (String) value;
				//TODO: create pair
				_set.addPair(pair);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return -1;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	//TODO: insert set into DB
	private int save() {

	}

	public int importAndSave() {
		int success = import();
		if (success) {
			success = save();
		}
		return success;
	}

	public static void main(String[] args) {

	}
}