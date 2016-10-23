import java.io.*;

/*
 *Set files are .json files formatted as follows:
 *object names will correspond to their respective Set names
 *name : value pair will correspond to their respective Pairs
 *note: there can only be one name or pair per line
 */

public class JsonExporter {

	//instance variables
	private static File file;
	private static Set set;
	private static DatabaseConnector dbConnection;
	private int success;

	//constructor
	JsonExporter(Set _set, DatabaseConnector _dbConnection) {
		file = null;
		dbConnection = _dbConnection;
		set = _set;
		success = 0;
	}

	//exports Set to .json file
	private File jsonify(Set set) {

	}

	public static void main(String[] args) {

	}
}