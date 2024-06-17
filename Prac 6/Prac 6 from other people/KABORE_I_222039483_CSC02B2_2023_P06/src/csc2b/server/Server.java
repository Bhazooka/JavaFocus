package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
	//create server socket
	ServerSocket serverSocket = null;
	boolean running = false;
	
    public static void main(String[] argv)
    {
	//Setup server socket and pass on handling of request
    		Server server = new Server();
    		server.runServer();
    }
    
    public Server() {
    	//constructor initislizes the server
    	try {
			serverSocket = new ServerSocket(2018);
			System.out.println("Server is running on port "+2018);
			running = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }
    
    //start server
    public void runServer() {
    	
    	while(running) {
    		try {
    			//create a thread that creates multiple BUKAHandlers that accepts multiple clients
				Thread thread = new Thread(new BUKAHandler(serverSocket.accept()));
				thread.start(); //start thread
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
