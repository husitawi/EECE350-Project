package carpooling_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import carpooling_server.CircleLine.Point;

public class server {
	
	private int port = 666;
	public static db_connection db;
	private ServerSocket serverSocket;
	public static Map<Integer, String> driver_id = new HashMap<Integer, String>(); //requested drivers

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
	
	public void acceptConnections() throws IOException {

		serverSocket = new ServerSocket(port);
		System.out.println("Port connected");
		while(true) {
				Socket newConnection = serverSocket.accept();
		        ServerThread st = new ServerThread(newConnection, db);
		        new Thread(st).start();
		      }
			}
	
	public static ArrayList<String> checkDrivers(double posx, double posy, double destx, double desty, ArrayList<Integer> skip) throws SQLException {
		
		
		String sql = "SELECT * FROM `driver` where free_seats > 0 ";
		
		if(!skip.isEmpty()) {
	
				sql += " AND id NOT IN (";
				
				for(int k=0;k<skip.size();k++) {
					sql += skip.get(k) + ",";
				}
				
				if(sql.substring(sql.length() - 1).equals(",")) {
					sql = sql.substring(0,sql.length() - 1);
				}
				
				sql += ")";			
		}
		
		
		ResultSet result = db.runSql(sql);
		ArrayList<String> drivers = new ArrayList<String>() ;

		while(result.next()) {
			//check the intersection of the user's current position and the driver
			double driverx = result.getDouble("posx");
			double drivery = result.getDouble("posy");
			double driverdx= result.getDouble("destx");
			double driverdy= result.getDouble("desty");
			
			
			boolean intersection = CircleSegment.isIntersecting(posx, posy, 1, driverx, drivery, driverdx, driverdy);
			if(intersection == false) {
				continue;	//check if there is an intersection between User's position and driver's
			}
			
			intersection = CircleSegment.isIntersecting(destx, desty, 1, driverx, drivery, driverdx, driverdy);
			if(intersection == false) {
				continue;	//check if there is an intersection between User's destination and driver
			}

			
			//if both are found check, and since the CircleLine returns all intersections, find the closest intersection to the user's destination, since both intersections are equally distant from the user --but that doesnt matter cz it is not asked for, we only need to calculate the time needed to arrive to the driver so only any pos of intersection is needed to do that
			List<Point> intersectionPoints = CircleLine.getCircleLineIntersectionPoint(new Point(driverx, drivery), new Point(driverdx, driverdy), new Point(posx, posy), 1);
			double distance;
			if(intersectionPoints.get(1) == null) {
				distance = CircleLine.getDistance(intersectionPoints.get(0), new Point(posx,posy));
			}else {
				double halfx = (intersectionPoints.get(1).x - intersectionPoints.get(0).x)/2;
				double halfy = (intersectionPoints.get(1).y - intersectionPoints.get(0).y)/2;
				Point midway = new Point( halfx , halfy);
				
				distance = CircleLine.getDistance(midway, new Point(posx,posy));
			}
			
			drivers.add(String.valueOf(result.getInt("id")));
			drivers.add(String.valueOf(distance));
			drivers.add(String.valueOf(result.getDouble("cost")));

		}
		
		return drivers;
	}
	
	
	}
	
	
	class ServerThread implements Runnable{
		
		private Socket socket;
		private BufferedReader din;
		private DataOutputStream dout;
		private db_connection db;
		public  String input;
		public  String output;
		public  long connectionStart = System.currentTimeMillis()/1000;	//get current time in seconds
		public  Double[] positions;
		public  ArrayList<Integer> skip = new ArrayList<Integer>();
		public  ArrayList<String> drivers;
		
		
		public ServerThread(Socket socket, db_connection database) { 
		  this.socket = socket;
		  this.db = database;
		  }
		
		
		public int completeOffer(int Did, int Pid, DataOutputStream dout) throws SQLException, IOException {
			ResultSet x;
			Point intersection;
			double distanceDriver;
			double distancePedestrian;
			int driverDelay;
			int pedestrianDelay;
			int totalDelay;
			
			//Get the Pedestrian and Driver current locations
			
			x = server.db.findPedestrian_id(Pid);
			Point Pedestrian = new Point(x.getDouble("posx"),x.getDouble("posy"));
			
			
			
			x = server.db.findDriver_id(Did);
			Point Driver 	 = new Point(x.getDouble("posx"),x.getDouble("posy"));
			
			//Find the Intersection point
			List<Point> intersectionPoints = CircleLine.getCircleLineIntersectionPoint(Driver, new Point(x.getDouble("destx"), x.getDouble("desty")), Pedestrian, 1);
			if(intersectionPoints.get(1) == null) {
				intersection = intersectionPoints.get(0);
			}else {
				double halfx = (intersectionPoints.get(1).x - intersectionPoints.get(0).x)/2;
				double halfy = (intersectionPoints.get(1).y - intersectionPoints.get(0).y)/2;
				Point midway = new Point( halfx , halfy);
				
				intersection = midway;
			}
			
			//Calculate the Distance and Time needed by each one
			
			distanceDriver = CircleLine.getDistance(intersection, Driver);
			distancePedestrian = CircleLine.getDistance(intersection, Pedestrian);
			System.out.println(distanceDriver + "and the pedestrian distance is " + distancePedestrian);
			
			driverDelay = (int) (distanceDriver / 50);
			pedestrianDelay = (int) (distancePedestrian / 7);		//should be passed back or something
			
			
			totalDelay = driverDelay + pedestrianDelay;
			
			//Call non blocking delayed-seats updater function
			ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

			exec.schedule(new Runnable() {
			          public void run() {
			              //run();
			        	  System.out.println("Yo");
			          }
			     }, totalDelay, TimeUnit.SECONDS);
		
			
			return totalDelay;
		}
		
		
		
		public void  run() {
			//	Do calculations to find driver and other wonders
			try {
				
				int i;
				din  = new BufferedReader(new InputStreamReader (socket.getInputStream()));
				dout = new DataOutputStream(socket.getOutputStream());
				
							input = din.readLine();	
				//Do all the separations and functionalities here
							
							String[] parts = input.split(",");
							if(parts[parts.length-1].equals("P")) {
								
						        System.out.println("Pedestrian connection accepted");
								positions = new Double[4]; 
								//Pedestrian part
								for(i=0;i<parts.length-2;i++) {
									positions[i] = Double.valueOf(parts[i]);		//convert into doubles
								}
								
								String pref = parts[i];	//save preferences
								int Pid = server.db.savePedestrian(positions[0], positions[1], positions[2], positions[3], pref);
									
								
									//Search for available drives DO THE LOGIC HERE, THEN REPLY
								drivers = server.checkDrivers(positions[0], positions[1], positions[2], positions[3], skip);
								boolean flag = true;
								
								while(flag) {
									
								
										//get intersections and pick the correct output 1 or 0
										if(drivers.isEmpty()) {
											output = "0";
										}else {
											output = "1";
										}
		
										if(output.equals("1")) {
											dout.writeBytes(output + '\n');
											output ="";
											for(int j=0;j<drivers.size()/3;j++) {
												output += "ID# "+ drivers.get(j*3).toString() + ", closest meeting point: " + drivers.get(3*j+1).toString() + ", charge: " + drivers.get(3*j+2).toString() + "##";
											}
											dout.writeBytes(output + '\n');
											dout.flush();
											//send the cars
											//wait for reply, picking any of the drivers
											
											input = din.readLine();
											int id = Integer.valueOf(input);
											server.driver_id.put(id,"W");
											
											int Did = id;
											
											while(server.driver_id.get(id) == "W" ) {	//keep checking for offer response
												Thread.sleep(1000); //wait 1 second before checking the requested drivers again
											}
											
											output = server.driver_id.get(id);
											
											dout.writeBytes(output + '\n');
											dout.flush();
											
						
											//rejected case
											if(server.driver_id.get(id) == "R" ) {
												skip.add(id);
												drivers = server.checkDrivers(positions[0], positions[1], positions[2], positions[3], skip);
											}else {
												//fix the free seats right now
												
												//call blocking function to continue accepting offers

												int totalDelay = completeOffer(Did, Pid, dout);
												
												dout.writeBytes(String.valueOf(totalDelay)  + '\n');
												dout.flush();			
												
												flag = false;
												

											}
											
											
											
											
										}else {
											//put on waiting list
											//dout.writeBytes(output + '\n');
											//dout.flush();
											drivers = server.checkDrivers(positions[0], positions[1], positions[2], positions[3], skip);
											//wait
											//check for drivers again 
											//read result again
											
											//break;
										}
										
								}
								
								
								
							}else
							{
								
								//Driver case
								positions = new Double[8]; 
								//Driver part
								for(i=0;i<parts.length-2;i++) {
									positions[i] = Double.valueOf(parts[i]);		//convert into doubles
								}
								
								String regulations = parts[i];	//save preferences
								
								int myid = db.saveDriver(positions[0], positions[1], positions[2], positions[3],positions[4],positions[5],positions[6], regulations);
								//this is where the driver connected
								
								
								
								
								
								while(true) {
									
										while(server.driver_id.containsKey(myid) == false || server.driver_id.get(myid) != "W") {	//keep checking for offers
											Thread.sleep(2000); //wait 2 seconds before checking the requested drivers again
										}
										
										output = "offer";
										dout.writeBytes(output + '\n');
										dout.flush();
										
										input = din.readLine();	//read answer
										if(input.equals("y")) {
											server.driver_id.replace(myid, "W", "A");			//A --> accepted
										}else {
											server.driver_id.replace(myid, "W", "R");			//R --> rejected
										}
								}
								
							}
							
							
							
							

							

				//close data streams
				din.close();
				dout.close();
				socket.close();
			
				}
				catch (IOException | SQLException | InterruptedException e) {
				e.printStackTrace();
			}
			
			return;
		}
		
		
		
	}
	
	
	
	

