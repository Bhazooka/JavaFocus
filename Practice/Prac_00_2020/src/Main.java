import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		// 
		 Socket s = null;
		 
		 try {
			 System.out.println(InetAddress.getLocalHost());	//shows the IP address and name of Host PC
			 s = new Socket("localHost", 135 );		//instead of "localHost" you can also enter "127.0.0.1"		Some ports might not connect so you need to check which port(number) is opened using cmd, then type "netstat -aon" or search on google
			 System.out.println("Connected");
			 
			 
			 //loop back address, this will give you the local host IP, 127.0.0.1
			 System.out.println("Loopback address: " + s.getInetAddress());		
			 System.out.println("Connected to port on remote host: " + s.getPort());
			 System.out.println("What local port is my computer using: " + s.getLocalPort());
			 
			 
			 System.out.println("Open Ports Include");
			 for (int port = 1; port <= 65535; port++) {
				 try {
					 s = new Socket(InetAddress.getLocalHost(), port);
					 
				 }
				 catch (IOException e) {
					System.err.println("Couldn't proceed to find opened ports.");
				}
			 }
			 
			 
		 }
		 catch(UnknownHostException e) {
		
			 System.err.println("Unable to connect to Host.");
		 } 
		 catch (IOException e) {
			System.err.println("Unable to connect to port.");
		 }
		 
		 finally
		 {
			 try {
				 if(s != null)
				 {
					 s.close();
				 }
				 
			 } catch(IOException e) {
				 System.err.println("Unable to close the connection.");
			 }

		 }

	}

}
