package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
	
	ServerSocket serverSocket = null;
	
	
    public static void main(String[] argv)
    {
    	Server server = new Server();
    	server.startServer();
    }
    
    public void startServer() {
    	try {
    		
    		serverSocket = new ServerSocket(2018);
    		System.out.println("Server Running on Port 2018");
    		
    		Thread thread = new Thread(new BUKAHandler(serverSocket.accept()));
    		thread.start();
    		
    	}
    	catch(IOException e) {
    		e.getMessage();
    	}
    }
}
