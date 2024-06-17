package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author B,Bukanga
 * 	221005009
 *	Class to handle the server side
 */
public class ServerSide{
	private ServerSocket ss = null;	//to listen for incoming connections requests
	//private Socket socket = null; //to connect the server and a specific client
	boolean liveServer = false;
	
	public ServerSide(int port) { 
		
		try {
			ss = new ServerSocket(port);
			liveServer = true;
			System.out.println("Successfully connected to port " + ss.getLocalPort());
		}catch(IOException e) {
			System.err.println("Faild to start server");
		}
	}
	
	
	/**
	 * Start Method 
	 * which delegates client handling to separate threads
	 */
	public void start() {
		while(liveServer) {
			try {
				Thread thread = new Thread((Runnable) new ClientSide(ss.accept()));
				thread.start();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
