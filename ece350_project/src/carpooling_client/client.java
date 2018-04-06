package carpooling_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	
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
				
			System.out.println("Cool!\nSending Request, waiting for reply . . .");
			//Runtime.getRuntime().exec("cls"); Clear the console, does not work inside ide
			
			
			out.writeBytes(request + '\n');
			out.flush();
			//keepGoing  = false;
			
			reply = serverInput.readLine();
			if(reply.equals("1")) {
				System.out.println("Driver(s) available:");
				reply = serverInput.readLine();
				System.out.println(reply);
				
			}else {
				System.out.println("No drivers were found, you were put on the waiting list:");
				
			}
			
		}
		
		
		out.close();
		serverInput.close();
		s.close();
		
		return;
		
	}

}
