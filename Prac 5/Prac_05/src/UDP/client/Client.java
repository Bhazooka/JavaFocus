package UDP.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author B, Bukanga 221005009
 *	Client side of the application
 */
public class Client 
{
	private DatagramSocket clientSocket = null;
	private String stringAddress = ""; 
	private int destinationPort = 10;
	private InetAddress destinationAddress = null; 
	

	/**Method used to create a datagram to be send over the UDP server
	 * Contains the destination and the address
	 * @param address
	 * @param destPort
	 */
	public Client(String address, int destPort)
	{
		stringAddress = address; 
		destinationPort = destPort; 
		try {
			clientSocket = new DatagramSocket();
			destinationAddress = InetAddress.getByName(stringAddress);
		} 
		catch (SocketException e) 
		{
			System.err.println(e.getMessage());
			System.err.println("Client Constructor issue");
		}
		catch (UnknownHostException e) 
		{
			System.err.println(e.getMessage());
			System.err.println("Sending Command Failed");
		} 
	}
	
	/**
	 * Method used to send commands to the remote host.
	 * @param command to send to the remote host
	 */
	public void sendCommands(String command)
	{
		try {
			byte [] buffer = command.getBytes();  
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, destinationAddress, destinationPort);
			clientSocket.send(sendPacket);
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
			System.err.println("Client sendCommand Failed");
		}
		
	}
	
	/**
	 * Method returns the string response of the current client server
	 * @return Response from server
	 */
	public String getResponse()
	{
		String response = ""; 
		try 
		{
			byte[] buffer = new byte[2048]; //prepare to receive a file of 2048 bytes
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length); 
			clientSocket.receive(receivePacket);
			
			//trim the buffer 
			int i = buffer.length - 1; 
			while(i > 0 && buffer[i] ==0)
			{
				i--; 
			}
			byte [] newBuffer = Arrays.copyOf(buffer, i + 1); 
			
			response = new String(newBuffer); 
			
		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
			System.err.println("Client, getResponse");
		}
		
		return response; 
	}
	

}
