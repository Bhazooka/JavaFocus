import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

/**
 * @author B,Bukanga 221005009
 *	Main Class
 */
public class Main {
	
    /**
     * @param args
     * 	main function to run code
     */
    public static void main(String[] args) {

    	
        for (int port = 1; port <= 65535; port+=3) {
        	
            try (Socket s = new Socket("localhost", port)) {
				System.out.println("Program connected to localhost: " + port);
				System.out.println("Local Port of the connection: " + s.getLocalPort());
				System.out.println("Remote Host of the connection: " + s.getPort());
                
            } catch (IOException e) {
                System.err.println("Couldn't connect to port " + port);
            }
        }
    	
    	try {
			InetAddress IP = InetAddress.getLocalHost();
			System.out.println("The computer IP Address: " + IP.getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
    	
    }
}