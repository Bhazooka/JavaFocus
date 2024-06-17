/**
 * 
 */
package acsse.csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author strin
 *
 */
public class ImgServer {
	
	private ServerSocket server;
	private boolean running;
	
	public ImgServer(int port) {
		try {
			server = new ServerSocket(port);
			running = true;
			startServer();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void startServer() {
		System.out.println("Starting the server...");
		while(running) {
			try {
				Socket incomingConnection = server.accept();
				System.out.println("New Client Connected");
				ImgHandler imgHandler = new ImgHandler(incomingConnection);
				Thread t = new Thread(imgHandler);
				t.start();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ImgServer s = new ImgServer(9876);
	}

}
