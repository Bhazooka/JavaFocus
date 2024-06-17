package server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author B,Bukanga
 * 	221005009
 *	Class to handle the client side
 */
public class ClientSide implements Runnable {
	
	private Socket clientConnection = null;
	BufferedReader readIn = null;
	DataOutputStream writeOut = null;
	StringTokenizer token = null;
	String requestCommand;
	String fileType;
	String requestedFile;
	HTMLPage createHTML = null;
	

	/**
	 * @param socket
	 * 	Constructor accepting a Client connection to a socket
	 */
	public ClientSide(Socket clientConnection) {
		this.clientConnection = clientConnection;
	}
	
	public void run() {
		
		try {
			readIn = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
			writeOut = new DataOutputStream(new BufferedOutputStream(clientConnection.getOutputStream()));
			
			String requestInput = readIn.readLine();
			System.out.println(requestInput);
			
			UserRequest(requestInput);
			
			if(requestCommand.equals("GET")) {
				if(fileDelivered().equals(new File("data","NoSuchFile.html"))) {
					System.err.println("Cannot locate File");
					createHTML = new HTMLPage(404, fileType, fileDelivered(), writeOut);
					createHTML.response();
				}
				else {
					System.out.println("File Found:\t" + fileDelivered().exists());
					createHTML = new HTMLPage(200, fileType, fileDelivered(), writeOut);
					createHTML.response();
				}
			}
			else {
				System.err.println("Invalid Request");
				createHTML = new HTMLPage(500, fileType, fileDelivered(), writeOut);
				createHTML.response();
			}
			System.out.println("\n");
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * The uses a switch method categorizes the type of file
	 * 	depending on the user input
	 * @return file object with details on file path requested (parent folder and actual file)
	 */
	private File fileDelivered() {
		fileType = "text/html"; // Default content type
		
		switch (requestedFile) {
			case "Afrikaans":
				return new File("data", "Afrikaans.html");
			case "Zulu":
				return new File("data", "Zulu.html");
			case "ZuluWithImage":
				return new File("data", "ZuluWithImage.html");
			case "Africa":
				fileType = "image/jpg";
				return new File("data", "Africa.jpg");
			default:
				return new File("data", "NoSuchFile.html");
		}
	}
	
	//Change
	/** The function is used to return response 
	 * 	depending on user request, removes the forward slash from the request 
	 * 	and displays the response on the terminal
	 * 	it also accounts for the case of .jpg files, which do include the filetype in the User request
	 * @param clientRequest
	 */
	private void UserRequest(String clientRequest) {
		token = new StringTokenizer(clientRequest);
		requestCommand = token.nextToken();
		
		/**to remove the '/' at the start of the request for file
		 * The substring() function removes the characters ahead of the indicated index 
		 * and returns the rest of the string from the indicated index
		 */
		requestedFile = token.nextToken().substring(1);	
		
		if(requestedFile.endsWith(".jpg")) {
			requestedFile = new String("Africa");
		}
		
	}
	
	
}
