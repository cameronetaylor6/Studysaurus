package studysaurus;
import java.io.File;
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
		JSONObject jSet = new JSONObject();
		jSet.put("name", _set.getName());

		JSONArray termValues = new JSONArray();
		for (Pair pair : _set.getPairs()) {
			JSONObject jPair = new JSONObject();
			jPair.put(pair.getTerm(), pair.getValue());
			termValues.add(jPair);
		}
		jSet.put("termValues", termValues);

		try {
			FileWriter file = new FileWriter(new File("/home/user/Desktop/Studysaurus/Sets/" + _set.getName() + ".json"));
			file.write(jSet.toJSONString());
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
