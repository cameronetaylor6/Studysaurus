import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonExporter {

	private Set set;

	JsonExporter(Set _set) {
		set = _set;
	}

	public int export() {
		int success = 1;

		JSONObject termValues = new JSONObject();

		ArrayList<Pair<String, String>> pairs = set.getPairs();
		for (Pair pair : pairs) {
			//TODO: udpate with new pair
			//termValues.put(pair.termString, pair.valueString);
		}

		set.put("name", set.getName());
		set.put(set.getName(), termValues);

		try {
			FileWriter file = new FileWriter("/home/user/Desktop/Studysaurus/Sets/" + set.getName() + ".json");
			file.write(set.toJSONString());
			file.flush();
			file.close();
		} 
		catch (IOException e) {
			success = 0;
			e.printStackTrace();
			return success;
		}
		return success;
	}

	public static void main(String[] args) {
		
	}
}
