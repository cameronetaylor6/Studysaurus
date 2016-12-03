package studysaurus;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonExporter {

	private Set _set;

	JsonExporter(Set set) {
		_set = set;
	}

	public boolean export() {
		JSONObject termValues = new JSONObject();

		for (Pair pair : _set.getPairs()) {
			termValues.put(pair.getTerm(), pair.getValue());
		}
		termValues.put(_set.getName(), termValues);
		termValues.put("name", _set.getName());

		try {
			FileWriter file = new FileWriter("/home/user/Desktop/Studysaurus/Sets/" + _set.getName() + ".json");
			file.write(termValues.toJSONString());
			file.flush();
			file.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		
	}
}
