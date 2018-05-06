package carpooling_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import carpooling_server.server;

public class client {
	
	public final static String ip = "localhost";

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		
		String request;
		String reply;
		Socket s = new Socket(ip, 666 );
		System.out.println("Connected to server");
		boolean keepGoing = true;
		long commas;
		String savedRequest;
		boolean flag = true;
		//Thread.sleep(60000);		// Delay for 1 minute to test multi threading
		
		BufferedReader   userInput   = new BufferedReader  (new InputStreamReader(System.in));			//Keyboard input
		DataOutputStream out 		 = new DataOutputStream(s.getOutputStream());						//User   --> Server
		BufferedReader   serverInput = new BufferedReader  (new InputStreamReader(s.getInputStream())); //Server --> User
		
		while(keepGoing == true) {
			//Runtime.getRuntime().exec("cls"); Clear the console, does not work inside ide
			
			
				//Do all the logic and stuff here
			System.out.println("Welcome!Please Start by inputting your current location ex: x,y");
			request = userInput.readLine();
		
				//check for chars instead of integers
				commas = request.chars().filter(num -> num == ',').count();
				if(commas != 1) {
					//Runtime.getRuntime().exec("cls"); Clear the console, does not work inside ide
					System.out.println("Your input doesn't look right, please start again:");	
					break;
				}
				
				request += ",";
				
			System.out.println("Awesome!Now please add your destination ex: x,y");
			request += userInput.readLine();
				
				//check for chars instead of integers
				commas = request.chars().filter(num -> num == ',').count();
				if(commas != 3) {
					//Runtime.getRuntime().exec("cls"); Clear the console, does not work inside ide
					System.out.println("Your input doesn't look right, please start again:");
					break;
				}
				
				request += ",";
				
				
			System.out.println("Now please add your preferences seperated by spaces ex: S, PF");  //PF = pet friendly
			request += userInput.readLine();
			
			request += ",P";
			//Runtime.getRuntime().exec("cls"); Clear the console, does not work inside eclipse
			
			System.out.println("Waiting for some drivers to come up");
			
			savedRequest = request;
			out.writeBytes(request + '\n');
			out.flush();
			
			while(flag) 
			{	
			
				reply = serverInput.readLine();
				if(reply.equals("1")) {
						System.out.println("Driver(s) available:");
						reply = serverInput.readLine();
						reply = reply.replace("##", "\n");
						System.out.println(reply);
						System.out.println("\nPlease pick a driver form the list above by entering his ID#:");
						request = userInput.readLine();
						out.writeBytes(request + '\n');
						out.flush();
						
					System.out.println("\nWaiting for driver response");
					reply = serverInput.readLine();
					
					if(reply.equals("R")) {
						System.out.println("\nThe driver rejected the request, please pick another ");
						//just wait for another check
					}else{
						System.out.println("\nThe driver accepted the request!");
						//start doing some other shit
						
						//wait for a server signal to display bill and end the pedestrian
						//the server will calculate and wait the needed time for them to meet 
						reply = serverInput.readLine();
						
						String[] parts = reply.split(",");
						System.out.print("Your driver will arrive in " + parts[0] + " seconds, and your total bill will be " + parts[1]);
						
						return;
					}
					
				}else {
					//System.out.println("No drivers were found, Please wait some more");
						//just wait for another check
					}
			
				
			}

			
			
			
		}
		
		
		out.close();
		serverInput.close();
		s.close();
		
		return;
		
	}

}
