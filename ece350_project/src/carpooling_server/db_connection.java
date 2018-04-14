package carpooling_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String q = "INSERT INTO `driver` (`id`, `posx`, `posy`, `destx`, `desty`, `passengers`, `free_seats`, `cost`, `regulations`) VALUES (NULL, '"+posx+"', '"+posy+"', '"+destx+"', '"+desty+"', '"+passengers+"', '"+freeSeats+"', '"+cost+"', '"+regulations+"');";
		st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
		rs = st.getGeneratedKeys();
		if (rs.next()) {
	        id = rs.getInt(1);
	    }
		return id;
		
	}

}
