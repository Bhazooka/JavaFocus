package za.ac.uj.acsse.csc2b.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		try {
			ServerSocket serverS = new ServerSocket(8080);
			System.out.println("I am ready for my guests!");
			Socket connectionToClient = serverS.accept();	//when the client tries to connect to 8080 it catches it and we carry on with requests
			System.out.println("Got a connection.");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connectionToClient.getInputStream()));
			PrintWriter pw = new PrintWriter(connectionToClient.getOutputStream());
			
			boolean running = true;
			
			String command = "";
			
			while(running) 
			{
				pw.println("How can i help you?");
				pw.flush();
				
				//it outputs how can i help you once and waits for reply command
				command = br.readLine();
				if(command.equals("Bye"))
				{
					pw.println();
					pw.flush();
					running = false;
				}
				else
				{
					pw.println("Yay!");
					pw.flush();
				}
				
			}
			
		} 
		catch (IOException e) {
			System.err.println("Unable to bind to port.");
		}
	}

}
