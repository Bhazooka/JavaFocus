import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		Socket socket = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		ServerSocket serverSocket = null;	//this just waits for request to come on a certain port
		
		serverSocket = new ServerSocket(1234); //the port numbers should match between server and client to establish a connection
		
		//This while loop is used to create new socket everytime client accepts connection
		//constantly send messages, client disconnects by sending bye
		while(true) 
		{
			try {
				
				socket = serverSocket.accept();		//accept a new client connection
				
				inputStreamReader = new InputStreamReader(socket.getInputStream());
				outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
				
				bufferedReader = new BufferedReader(inputStreamReader);
				bufferedWriter = new BufferedWriter(outputStreamWriter);
				
				
				//This while loop is used to send data back and forward
				while(true) 
				{
				
					String msgFromClient = bufferedReader.readLine();
					
					System.out.println("Client: " + msgFromClient);
					
					bufferedWriter.write("Message Recieved.");
					bufferedWriter.newLine();
					bufferedWriter.flush();
					
					if(msgFromClient.equalsIgnoreCase("BYE"))
						break;
				}
				socket.close();
				inputStreamReader.close();
				outputStreamWriter.close();
				bufferedReader.close();
				bufferedWriter.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
		
		
	}
}

