import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonImporter {

	private String filePath;
	private Set set;
	//private DatabaseConnector dbConnection;

	JsonImporter(String _filePath) {
		filePath = _filePath;
		//dbConnection = new DatabaseConnector();
		set = null;
	}

	private int import() {
		int success = 1;

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jObj = (JSONObject) obj;
			String setName = (String) jObj.get("name");
			JSONObject termValues = jObj.get(setName);

			set = new Set(setName);

			for (Object term : jObj.keySet()) {
				String termStr = (String) term;
				Object value = jObj.get(termStr);
				String valueStr = (String) value;
				//TODO: create pair
				set.addPair(pair);
			}
		}
		catch (FileNotFoundException e) {
			success = 0;
			e.printStackTrace();
			return success;
		} 
		catch (IOException e) {
			success = 0;
			e.printStackTrace();
			return success;
		} 
		catch (ParseException e) {
			success = 0;
			e.printStackTrace();
			return success;
		}
		return success;
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