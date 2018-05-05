package carpooling_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class driver {
	
	public final static String ip = "localhost";

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		
		String request;
		String reply;
		Socket s = new Socket(ip, 666 );
		System.out.println("Connected to server");
		boolean keepGoing = true;
		long commas;
		//Thread.sleep(60000);		// Delay for 1 minute to test multi threading
		
		BufferedReader   userInput   = new BufferedReader  (new InputStreamReader(System.in));			//Keyboard input
		DataOutputStream out 		 = new DataOutputStream(s.getOutputStream());						//User   --> Server
		BufferedReader   serverInput = new BufferedReader  (new InputStreamReader(s.getInputStream())); //Server --> User
		
		while(keepGoing == true) {//loop used to handle wrong inputs
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
				
				

			
			System.out.println("Now please enter your cost");
			request += userInput.readLine();
			
			request += ",";
			
			System.out.println("Now please the number of passenegers currently in the car");  //PF = pet friendly
			request += userInput.readLine();
			
			request += ",";
			
			System.out.println("Now please enter the number of free seats");
			request += userInput.readLine();
			
			request += ",";
			
			System.out.println("Now please add your regulations seperated by spaces ex: S, PF");  //PF = pet friendly
			request += userInput.readLine();
			
			request += ",D";
			//Runtime.getRuntime().exec("cls"); Clear the console, does not work inside ide
			
			
			out.writeBytes(request + '\n');
			out.flush();
			//keepGoing  = false;
			
			System.out.println("Now you wait for offers");
			
			
			while(true) {		//keep checking for offers
			
				reply = serverInput.readLine();
				System.out.println("A new client has been found, take request ? [y/n]");
				request = userInput.readLine();
				out.writeBytes(request + '\n');
				out.flush();
			
			//no need to check the reply because the driver will only get a reply when pedestrians are found
			//the rest of the work will fall on the server side 		
			}
			
		}
		
		
		out.close();
		serverInput.close();
		s.close();
		
		return;
		
	}

}
