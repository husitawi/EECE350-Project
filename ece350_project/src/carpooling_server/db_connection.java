package carpooling_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import carpooling_server.CircleLine.Point;

public class db_connection {
	
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	
	public db_connection() throws SQLException, ClassNotFoundException {
			
		// Load Driver
		Class.forName("com.mysql.jdbc.Driver");	
		
		// Load the db
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carpooling?autoReconnect=true&useSSL=false", "root", "");
		
		System.out.println("Database connected");
		
		// Initiate the statement
		st = conn.createStatement();
	}
	
	
	public ResultSet runSql(String query) throws SQLException {	//To run custom SQL from server
		
		rs = st.executeQuery(query);
		if(rs.isBeforeFirst()) {
			return rs;
		}
		else return null;
	}
	
	public ResultSet findDriver_id(int id) throws SQLException {
		
		String q = "SELECT * FROM `driver` WHERE `id` = " + id +";";
		rs = st.executeQuery(q);
		if(rs.next()) {
			return rs;
		}else
			return null;
	}
	
	public ResultSet findPedestrian_id(int id) throws SQLException {
		
		String q = "SELECT * from `pedestrian` WHERE `id`=" + id +";";
		rs = st.executeQuery(q);
		if(rs.next()) {
			return rs;
		}else
			return null;
	}
	
	
	public void savePedestrian(double posx, double posy, double destx, double desty, String pref) throws SQLException {
		String q = "INSERT INTO `pedestrian` (`posx`, `posy`, `destx`, `desty`, `preferences`) VALUES (" + posx + ", " + posy + ", "+destx+", "+desty+", '"+pref+"');";
		st.executeUpdate(q);
	}
	
	public int saveDriver(double posx, double posy, double destx, double desty, double cost, double passengers, double freeSeats, String regulations) throws SQLException {
		int id= -1;
		long currentTime = System.currentTimeMillis()/1000;
		String q = "INSERT INTO `driver` (`id`, `posx`, `posy`, `destx`, `desty`, `passengers`, `free_seats`, `cost`, `regulations`,`lastUpdate`) VALUES (NULL, '"+posx+"', '"+posy+"', '"+destx+"', '"+desty+"', '"+passengers+"', '"+freeSeats+"', '"+cost+"', '"+regulations+"', '"+currentTime+"');";
		st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
		rs = st.getGeneratedKeys();
		if (rs.next()) {
	        id = rs.getInt(1);
	    }
		return id;
		
	}
	
	public static Point newxy(double xs, double ys, double xd, double yd, double dist) {
        double slope = Math.abs((yd-ys)/(xd-xs));
        double angle = Math.atan(slope);
        double newx = Math.cos(angle)*dist;
        double newy = Math.sin(angle)*dist;
        xs = newx+xs;
        ys = newy+ys;
        
        return new Point(xs,ys);

	}
	
	
	
	public synchronized void updateDrivers() throws SQLException {
		int currentTime;
		int elapsedTime;
		double distance;
		
		 Statement statement = conn.createStatement();
		
		String q = "SELECT * FROM `driver` WHERE `available` =  1 ;";
		rs = st.executeQuery(q);
		
		while(rs.next()) {
			
			currentTime = (int) (System.currentTimeMillis()/1000);
			double px = rs.getDouble("posx");
			double py = rs.getDouble("posy");
			double dx = rs.getDouble("destx");
			double dy = rs.getDouble("desty");
			
			int t = rs.getInt("lastUpdate");
			
			elapsedTime = currentTime - t;
			
			/*
			 * 
			 ######################################################################################################################################## 
			 #																																		#
			 #	The logic is as follows:																											#
			 #	- We chose the time to pass a unit of distance to be 9 hours long for enough reading/testing time for an average speed of 50 KM/h   #
			 #	- We also considered every second to be an hour																						#
			 #	- This implies that every unit needs 9 seconds to pass																				#
			 #	- Also it implies that every unit represents a distance of 450 KM 																	#
			 #																																		#
			 ########################################################################################################################################
	
			 */
			
			
			distance = (elapsedTime * 50) / 450;
			Point position = newxy(px,py,dx,dy,distance);
			
			String query = "UPDATE `driver` SET `lastUpdate` = '" + currentTime + "', `posx` = '"+ position.x +"' , `posy` = '" + position.y + "'" ;
			
			if(position.x > dx || position.y > dy) {
				query += ", `available` = '0' "; 
			}
			
			query += " WHERE `driver`.`id` = " + rs.getInt("id");

			statement.executeUpdate(query);
		}

	}

}
