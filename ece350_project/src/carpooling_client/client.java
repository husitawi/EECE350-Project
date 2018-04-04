package carpooling_client;

import java.io.IOException;
import java.net.Socket;

public class client {
	
	public final static String ip = "localhost";
	
	public static boolean hostAvailabilityCheck() { 
	    try (Socket s = new Socket(ip, 7777 )) {
	        return true;
	    } catch (IOException ex) {
	        /* ignore */
	    }
	    return false;
	}

	public static void main(String[] args) throws InterruptedException {

		boolean x = hostAvailabilityCheck();
		System.out.println(x);
		Thread.sleep(60000);		// Delay for 1 minute to test multithreading
		return;
		
	}

}
