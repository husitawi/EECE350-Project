package carpooling_server;
import java.sql.SQLException;
import carpooling_server.CircleLine.Point;

public class logic {

	public static server mainServer = null;
	
	public static void main(String[] args) throws SQLException {

		
			try {
				mainServer = new server();
				
			}catch (ClassNotFoundException e) {
				
			    System.out.println("unable to load MYSQL");
			    e.printStackTrace();
			    System.exit(1);
			}
			
			// All loaded good, now start the server 
			System.out.println(CircleLine.getCircleLineIntersectionPoint(new Point(0, 0),
	                new Point(0, 10), new Point(0, 2), 1));
			mainServer.acceptConnections();
			}
	
	

}
