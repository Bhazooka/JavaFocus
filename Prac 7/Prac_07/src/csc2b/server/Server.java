package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author 221005009 BUKANGA, B
 *	Start Server
 */
public class Server {
	
	ServerSocket serverSocket = null;
	Boolean running = false;
	
	public static void main(String[] args) {
		Server server = new Server();
		server.runServer();
	}
	
	
	public Server(){
		try {
			serverSocket = new ServerSocket(2021);
			System.out.println("running on 2021");
			running = true;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void runServer() {
		try {
			Thread thread = new Thread(new ZEDEMHandler(serverSocket.accept()));
			thread.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}