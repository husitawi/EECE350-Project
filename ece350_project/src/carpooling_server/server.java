package carpooling_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class server {
	
	private int port = 7777;
	private db_connection db;
	private ServerSocket serverSocket;
	
	public server() throws ClassNotFoundException, SQLException {
		 db = new db_connection();
	}
	
	public ResultSet getDriver_id(int id) throws SQLException {
		ResultSet result  = db.findDriver_id(id);
		return result;
	}
	
	public ResultSet getPedestrian_id(int id) throws SQLException {
		ResultSet result  = db.findPedestrian_id(id);
		return result;
	}
	
	public void acceptConnections() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("ServerSocket instantiation failure");
		    e.printStackTrace();
		    System.exit(0);
		}
		
		while(true) {
			try {
				Socket newConnection = serverSocket.accept();
		        System.out.println("accepted connection");
		        ServerThread st = new ServerThread(newConnection);
		        new Thread(st).start();
		        
			}catch (IOException ioe) {
		        System.err.println("server accept failed");
		      }
			}
	}
	
	
	class ServerThread implements Runnable{
		
		private Socket socket;
		
		public ServerThread(Socket socket) { 
		  this.socket = socket;
		  }
		
		public void run() {
			//	Do calculations to find driver and other wonders
			
			
			return;
		}
	}
	
	
	
	
}
