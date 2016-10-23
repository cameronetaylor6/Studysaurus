import java.io.*;

/*
 *Set files are .json files formatted as follows:
 *object names will correspond to their respective Set names
 *name : value pair will correspond to their respective Pairs
 *note: there can only be one name or pair per line
 */

public class JsonImporter {

	//instance variables
	private static File file;
	private static Set set;
	private static DatabaseConnector dbConnection;
	private int success;

	//Constructor
	JsonImporter(File _file) {
		file = _file;
	}

	private static String parseFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(file.getCanonicalFile()))) {
			String currentLine;

			while ((currentLine = br.readLine()) != null) {

			}
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}

	private Pair createPair(String jsonLine) {

	}

	//inserts set into db
	//return: 0 -> success, !=0 -> failure
	private int saveSet(String setName) {

	}

	//exports Set to .json file
	private File jsonify(Set set) {

	}

	public static void main(String[] args) {

	}
}