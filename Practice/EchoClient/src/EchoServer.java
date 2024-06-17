import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		
		try {
			System.out.println("Waiting for clients");
			ServerSocket ss = new ServerSocket(9806);	//9806 is the port number of our server. Port number is used to idetify your application on the network
			Socket soc = ss.accept();	//the accept method is a blocking call, when the flow of control gets to accept, it will wait for client connections
			System.out.println("Connection established");
			
			//Reading the string the client sent
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));	//This buffered reader is used for reading data from he sockets input stream
			String str = in.readLine();
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			out.println("Server says" + str);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
