package za.ac.uj.acsse.csc2b.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		try {
			
			/**
			 *ServerSocket Object Class
			 * The ServerSocket class is used on the server side to listen for incoming client connection requests on a specific port 
			 *  It is initialized with a port number, which represents the endpoint on the server where clients can connect. 
			 *  When a client tries to establish a connection to that port, the ServerSocket listens for the incoming connection request
			 *  Once a connection request arrives, the accept() method of the ServerSocket is used to accept the connection and create a Socket object that represents the established connection to the client
			 *  The server can then use this Socket object to communicate with the connected client.
			 *  
			 *Socket Object Class 
			 * 	The Socket class, on the other hand, represents a connection between the server and a specific client.
			 * 	It allows bidirectional communication between the server and the client.
			 * 	ServerSocket has accepted a client's connection request, it creates a Socket object on the server-side to interact with the connected client. Similarly, on the client-side, a Socket object is created to communicate with the server.
			 * 	In summary, the ServerSocket is used to listen for incoming client connections, and when a client connects, it creates a Socket object to handle communication with that specific client. The ServerSocket is used on the server-side to manage incoming connection requests, while the Socket is used on both the server-side and client-side to handle the actual communication once the connection is established.
			 */
			ServerSocket serverS = new ServerSocket(8080);
			System.out.println("I am ready for my guests!");
			Socket connectionToClient = serverS.accept();	//when the client tries to connect to 8080 it catches it and we carry on with requests
			System.out.println("Got a connection.");
			
			
			/**
			 *Buffered Stream
			 *	The BufferedReader is used to read data from the client by getting the input stream from the `connectionToClient` socket and wrapping it with an `InputStreamReader. 
			 *	The input stream converts the bytes recieved from the client into characters
			 *	the `BufferedReader` provides a convenient way to read these characters line by line or in any desired format.
			 *
			 *InputStreamReader
			 *	The InputStreamReader class in Java is a bridge between byte streams and character streams. 
			 *	It reads bytes from an InputStream and converts them into characters using a specified character encoding. 
			 *	This is useful when you want to read textual data from an input stream, where the data is in the form of bytes (such as when reading from a network connection or a file).
			 *
			 *PrintWriter
			 *	PrintWriter is used to send data to the client. 
			 *	It does so by getting the output stream from the `connectionToClient` socket.
			 *	The `PrintWriter` provides convenient methods to write text data 
			 *	and automatically converts text into bytes to send it over the network.
			 *
			 *In Conclusion `BufferedReader`, is used to read data and Printwriter is used to send data to
			 *	The server can read data from client using `BufferedReader`, and can send data to client using the `PrintWriter`. 
			 *	
			*/
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connectionToClient.getInputStream()));	
			PrintWriter pw = new PrintWriter(connectionToClient.getOutputStream());

			boolean running = true;
			
			String command = "";
			
			while(running) 
			{
				pw.println("How can i help you?");
				pw.flush();
				
				//it outputs how can i help you once and waits for reply command
				/**
				 * The readLine() function is a method of the BufferedReader class, used to read a line of text from the input stream. 
				 * It reads characters from the input stream until it encounters a newline character ('\n') or the end of the stream.
				 */
				command = br.readLine();
				if(command.equals("Bye"))
				{
					pw.println();
					pw.flush();
					running = false;
				}
				else
				{
					/** 
					 * The flush() method is used with the PrintWriter object (pw) to ensure that the data written to the output stream is immediately sent to the client.
					 * When you write data using a PrintWriter, it doesn't automatically send the data to the output stream immediately. 
					 * 	Instead, it stores the data in an internal buffer until either the buffer is full or the flush() method is explicitly called
					 */
					
					pw.println("Yay!");
					pw.flush();
				}
				
			}
			
		} 
		catch (IOException e) {
			System.err.println("Unable to bind to port.");
		}
	}

}
