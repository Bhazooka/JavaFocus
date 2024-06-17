package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BUKAServer {
    ServerSocket server;
    
    public BUKAServer(int port) {
 
    	try {
			server = new ServerSocket(port);
			while(true) {
				System.out.println("========================== listening for new connections ==================");
				Socket client = server.accept(); // accepting connections
				BUKAHandler clientHandler = new BUKAHandler(client);
				(new Thread(clientHandler)).start(); // starting a new thread for each connection 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
