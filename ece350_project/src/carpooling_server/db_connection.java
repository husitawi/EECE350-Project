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
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carpooling?autoReconnect=true&useSSL=false", "pewpew", "godaddy");
		
		System.out.println("Database connected");
		
		// Initiate the statement
		st = conn.createStatement();
	}
	
	
	public ResultSet runSql(String query) throws SQLException {	//To run custom SQL from server
		
		rs = st.executeQuery(query);
		if(rs.next()) {
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

}
