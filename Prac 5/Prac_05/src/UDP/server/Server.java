package UDP.server; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;
import javafx.scene.control.TextArea;

/**
 * @author B, Bukanga 221005009
 * Server Side of the application
 */
public class Server implements Runnable
{
	private DatagramSocket serverSocket = null;
	private boolean isRunning = false; 
	private TextArea statusArea = null; 
	
	/**
	 *Constructor creates a DatagramSocket and binds it to the specified port.
	 * @param port The port number to bind to
	 * @throws SocketException
	 */
	public Server(int port)
	{
		try {
			serverSocket = new DatagramSocket(port);
			System.out.println("Server Started at port: " +serverSocket.getLocalPort());
			isRunning = true; 
		} catch (SocketException e) {
			System.err.println(e.getMessage());
			System.err.println("Server, Constructor");
		} 
	}
	
	
	/**
	 * The run method is where the thread will execute.
	 */
	@Override
	public void run() 
	{
		//I put this code in a thread so that the ui and this code will run in different threads
		//https://stackoverflow.com.questions/43867073/ui-goes-not-responding-in-javafx
		while(isRunning == true)
		{
			//wait for commands from the client. 
			System.err.println("...waiting for a packet");
			try 
			{
				byte [] buffer = new byte[2048]; 
				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length); 
				
				serverSocket.receive(receivePacket);
				System.out.println("Received a packet");
				
				//trim the packet
				int i = buffer.length - 1; 
				while(i > 0 && buffer[i] == 0)
				{
					i--; 
				}
				byte [] newBuffer = Arrays.copyOf(buffer, i+1); 
				
				statusArea.appendText("--" + new String(newBuffer) + "\n");
				System.out.println("Packet trimmed");
				
				processCommands(new String(newBuffer), receivePacket.getAddress(), receivePacket.getPort());
			} catch (IOException e) 
			{
				System.err.println(e.getMessage());
				System.err.println("Server, startServer method");
			}
		}
	}
	/**
	 * Method to allow user to erase the DatagramSocket
	 * @param set to false if the server should stop
	 */
	public void stopServer(boolean setFalse)
	{
		isRunning = false; 
		if(this.serverSocket != null)
		{
			serverSocket.close(); 
		}
	}
	
	/**
	 * Method used to process UDP commands and return a response to the sender.
	 * @param message The message from the sender
	 * @param returnAddress The return Address of the sender
	 * @param returnPort The return port of the sender
	 */
	private void processCommands(String message, InetAddress returnAddress, int returnPort)
	{
		DatagramPacket sendPacket = null; 
		String commands [] = message.split("#");
		String response = ""; 
		System.out.println("Server, Command:" + commands[0]);
		
		switch(commands[0])
		{
			case "Helo" :
			{
				response = "Helo"; 
				sendPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, returnAddress, returnPort);
				
			}
			break; 
			case "List" :
			{
				try (Scanner fScanner = new Scanner(new File("data/seederFiles/List.txt"))) 
				{
					StringBuffer sb = new StringBuffer(); 
					while(fScanner.hasNextLine())
					{
						sb.append(fScanner.nextLine() + "#"); 
					}
					response = new String(sb); 
					sendPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, returnAddress, returnPort);
					
				}catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
				}
			
			}
			break; 
			case "File" :
			{
				String strId = commands[1]; 
				String fileName = convertIDToFile(strId);
				File fileToSend = new File("data/seederFiles/"+fileName); 
				
				try 
				{
					FileInputStream fis = new FileInputStream(fileToSend); 
					byte [] buffer = new byte[2048]; 
					fis.read(buffer); 
					String strBuffer = new String(buffer); 
					fis.close(); 
					response = new String(fileName +"#"+ String.valueOf(fileToSend.length())+ "#" + strBuffer);
				} catch (IOException e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace(); 
				}
				//send the fileName + fileSize + fileData
				sendPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, returnAddress, returnPort);
			}
			break; 
			default:
			{
				response = "Unkn"; 
				sendPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, returnAddress, returnPort);
			}
		} 
		
		try 
		{
			serverSocket.send(sendPacket);
			
		} catch (IOException e) 
		{
			System.err.println(e.getMessage());
			System.err.println("Server, processCommands method");
		}
		
	}
	
	/**
	 * Method to scan through the List.txt file to find the corresponding file name of a given
	 * file id
	 * @param strID file ID
	 * @return fileName corresponding to the file ID
	 */
	public String convertIDToFile(String strID)
	{
		String fileName = "."; 
		
		try(Scanner fScanner = new Scanner(new File("data/seederFiles/List.txt")))
		{
			while(fScanner.hasNextLine())
			{
				String [] line = fScanner.nextLine().split("\\s"); 
				if(line[0].equals(strID))
				{
					fileName = line[1]; 
					break; 
				}
			}
			
		}catch (IOException e) 
		{
			System.err.println(e.getMessage());
			System.err.println("Server side error.n/ "
							+ "Couldnt convert ID to FileName");
		}
		
		return fileName; 
	}
	
	/**
	 * Method to take whats in the text area,
	 * @param a UserInterface TextArea
	 */
	public void setStatusArea(TextArea a)
	{
		statusArea = a; 
	}
	

}
