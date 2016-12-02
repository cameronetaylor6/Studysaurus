package studysaurus;

public class TestDriver {
	 public static void main(String[] args)
	    {
		 	DatabaseConnector dc = new DatabaseConnector();
		 	dc.checkForDatabase();
		 	dc.checkForDefaults();
	        GameClient gc = GameClient.getInstance();
	    }
}
