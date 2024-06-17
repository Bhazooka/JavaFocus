import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Awe
 * @version P04
 *
 */
public class Server{
	public ServerSocket serverSocket;
	public boolean running;
	public Server() {
		try {
			serverSocket = new ServerSocket(135);
			running = true;
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void startServer() {
		System.out.println("Starting server");
		while(running) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("New Client connected");
				ImgHandler imgHandler = new ImgHandler(socket);
				Thread t = new Thread(imgHandler);
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
	}
}
