package server;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author B,Bukanga
 * 	221005009
 * HTML Class to generate web Pages 
 * depending on request
 *
 */
public class HTMLPage {
	
	private int responseCode = 0;		//c
	private String contentType = null;		//c
	private File fileDelivered = null;
	private DataOutputStream dos = null;
	private BufferedInputStream bis = null;
	
	/**
	 * @param responseCode
	 * @param infoType
	 * @param fileDelivered
	 * @param dos
	 */
	public HTMLPage(int responsCode, String infoType, File fileDelivered, DataOutputStream dos) {
		this.responseCode = responsCode;
		this.contentType = infoType;
		this.fileDelivered = fileDelivered;
		this.dos = dos;
	}


	/**
	 * Method to format HTTP response
	 */
	public void response() {
		try {
			System.out.println("HTTP/1.1 " + this.responseCode + " OK \r\n");
			System.out.println("Content-Length: " + fileDelivered.length() + " \r\n");
			System.out.println("Content-Type: " + contentType + "\r\n");
			System.out.println("Connection: close \r\n");
			System.out.println("\r\n");
			
			dos.writeBytes("HTTP/1.1 " + this.responseCode + " OK \r\n");
			dos.writeBytes("Content-Length: " + fileDelivered.length() + " \r\n");
			dos.writeBytes("Content-Type: " + contentType + "\r\n" );
			dos.writeBytes("Connection: close \r\n");
			dos.writeBytes("\r\n");		
			
			
			//Buffer to store bytes read from the file
			bis = new BufferedInputStream(new FileInputStream(fileDelivered));
			byte [] byteArr = new byte [1024]; //we're taking the bytes in in chunks of 1024bytes and storing it in the byteArr array
			//we then use dos.write to write to the client, 
			int n = 0;
			
			while((n = bis.read(byteArr)) > 0) {
				System.err.println(n + ", ");
				/**
				 * Writes bytes from the specified byte array
			     * starting at offset {@code off} to the underlying output stream.
			     * If no exception is thrown, the counter {@code written} is
			     * 				write(byte b[], int off, int len)
				 */	
				dos.write(byteArr, 0, n);	//writing to the client, by taking our byteArr --> 		
			}
			bis.close();
			
			dos.writeBytes("\r\n");
			dos.flush();

			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
