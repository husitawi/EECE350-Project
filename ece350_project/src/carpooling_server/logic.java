package carpooling_server;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import carpooling_server.CircleLine.Point;

public class logic {

	public static server mainServer = null;
	
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

			mainServer = new server();
			mainServer.acceptConnections();
	
	}
}
