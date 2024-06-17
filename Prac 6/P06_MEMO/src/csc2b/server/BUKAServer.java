package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
*@author M Bowditch 2023
*/
public class BUKAServer
{
	private ServerSocket server;
	private boolean isReady;
	
	public BUKAServer(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Server created on port: " + port);
			isReady = true;
			
			while(isReady)
			{
				System.out.println("Waiting to accept clients");
				Socket clientConn = server.accept();
				Thread clientThread = new Thread(new BUKAHandler(clientConn));
				clientThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void main(String[] argv)
    {
    	//Setup server socket and pass on handling of request
    	BUKAServer server = new BUKAServer(2018);    	
    }
}
