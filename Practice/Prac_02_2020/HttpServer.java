import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer {
	
	private ServerSocket server;
	private boolean ready = false;
	
	public HttpServer(int port) {
		try {
			server = new ServerSocket(port);
			ready = true;
			
		}catch(IOException e) {
			System.err.println("Error starting server...");
			
		}
		
	}
	

	//helper method to start the server
	public void start() {
		System.out.println("Server started on port "+ server.getLocalPort());
		while(ready) {
			try {
				
				//multithreading to allow multiple client connections
				Thread thread = new Thread(new ClientHandler(server.accept()));
				thread.start();

			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
