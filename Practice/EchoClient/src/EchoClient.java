import java.net.Socket;
import java.io.*;

public class EchoClient {

	public static void main(String[] args) {

		try 
		{
			System.out.println("Client started");
			Socket soc = new Socket("localhost", 9806);	//the first parameter is the ip of our server, which we can just type local (because both client and server are on the same machine) host or 127.0.0.1, second parameter is the port number
			
			//This buffered reader is used for reading data from keyboard
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));	//The systemin is an input stream object capable of reading one byte at a time. BufferedReader reads entire strings at a time, without It we would read one character at a time. Input stream reader takes bytestream and gives use a character stream
			System.out.println("Enter a string");
			String str = userInput.readLine(); //readline waits for the user to enter a string, which will be stored in str
			
			//for sending 
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);	//true is essential, its auto flush functionality, printwriter has a tendency to keep the data that it should pass, without the true statement youd have to flush it manually
			out.println(str);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));//read data from the sockets input stream. It'll read the data the server has sent
			System.out.println(in.readLine());	//print what the server has sent
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		

	}

}
