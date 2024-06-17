package za.ac.uj.acsse.csc2b.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		
		try {
			ServerSocket SS = new ServerSocket(8888);
			System.out.println("Waiting for client.");
			
			Socket connectionToClient = SS.accept();
			
			
			//This is the stuff the chatbot will reply
			BufferedReader br = new BufferedReader(new InputStreamReader(connectionToClient.getInputStream()));
			PrintWriter pw = new PrintWriter(connectionToClient.getOutputStream());
			
			pw.print("HELLO - You may ask me 4 questions.");
			
			boolean isRunning = true;
			
			String command = "";
			
			
			
			for(int count = 1; count <= 4; count++ ) 
			{
				pw.print("ASK: ");
				pw.flush();
				
				command = br.readLine();
				
			}
			
			
			
			String answer = "";
			
			
			//Answers
			for(int count = 1; count <= 4; count++)
			{
				
				if(command.toLowerCase().indexOf("are") == 0)
				{
					answer = "No";
					answer = "Maybe";
				}
				else if(command.toLowerCase().indexOf("why") == 0)
				{
					answer = "Because the boss says so";
				}
				else 
				{
					answer = "Oh okay";
				}
				
				
				pw.println(count + "#: [" + answer + ".]");
				pw.flush();
				
				
			}

			
			
			
			pw.println("HAPPY TO HAVE GELPED - 4 Questions answered");
			
			
			command = br.readLine();
			if(command == "HELLO BOT") {
				isRunning = true;
			}else
			{
				isRunning =  false;
			}
			
			
			/*			
			while(isRunning) {
				pw.println("How can I help you?");
				pw.flush();
				
				command = br.readLine();
				if(command.equals("Bye"))
				{
					pw.println();
					pw.flush();
					isRunning = false;
				}
				else
				{
					pw.println();
					pw.flush();
				}
				
			}
			*/
			
		} 
		catch (IOException e) {
			System.err.println("Connection failed.");
		}
	
	}

	
}
