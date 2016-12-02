package studysaurus;

public class TestDriver {
	public static void main(String[] args)
		{
			DatabaseConnector dc = DatabaseConnector.getInstance();
			dc.checkForDatabase();
			dc.checkForDefaults();
			GameClient gc = GameClient.getInstance();
		}
}
