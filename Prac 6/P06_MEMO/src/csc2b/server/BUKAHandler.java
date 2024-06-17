package csc2b.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
*@author M Bowditch 2023
*/
public class BUKAHandler implements Runnable
{
	private Socket connectionToClient;
	
	//Text streams
	private PrintWriter txtout=null;
	private BufferedReader txtin=null;
	
	//Binary streams
	private DataOutputStream dos=null;
	private DataInputStream dis=null;
	
	//Byte streams
	private OutputStream os=null;
	private InputStream is=null;
	
	private boolean processing;
	private boolean authenticated;
	
    public BUKAHandler(Socket newConnectionToClient)
    {	
    	this.connectionToClient = newConnectionToClient;
    	try {
    		os = connectionToClient.getOutputStream();
    		is =connectionToClient.getInputStream();
    		
    		dos = new DataOutputStream(os);
    		dis = new DataInputStream(is);
    		
    		txtout = new PrintWriter(os);
    		txtin = new BufferedReader(new InputStreamReader(is));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void run()
    {
    	System.out.println("Start processing commands");
    	processing =true;
    	
    	try {
    		while(processing) {
    		
    		String requestln = txtin.readLine();
    		System.out.println("Got requestln: " + requestln);
    		StringTokenizer st = new StringTokenizer(requestln);
    		String command = st.nextToken();
			if(command.equals("AUTH"))
			{
				String username = st.nextToken();
				String pass = st.nextToken();
				
				if(matchUser(username, pass))
				{
					sendMessage("200 Successfully logged in");
					authenticated = true;
					System.out.println("Logged in");
				}
				else
				{
					sendMessage("500 Unsucessful login");
					authenticated = false;	
				}	
			}
			else if(command.equals("LIST"))
			{
				String fileList = "";
				System.out.println("LIST command received");
				if(authenticated == true)
				{
					ArrayList<String> fileArrList = getFileList();
					String strToSend ="";
					for(String s: fileArrList)
					{
						strToSend += s +"#";
					}
					sendMessage(strToSend);
					System.out.println("StrToSend:" + strToSend);
				}
				else
				{
					sendMessage("500 Not authenticated");
				}
			}
			else if(command.equals("PDFRET"))
			{
				if(authenticated)
				{
					String fileID = st.nextToken();
					String fileName = idToFile(fileID);
					File pdfFile = new File("data/server/"+fileName);
					if(pdfFile.exists())
					{
						System.out.println("File found");
						sendMessage("200 " + pdfFile.length()+" bytes");
						//NB: When Sending a file
						FileInputStream fis = new FileInputStream(pdfFile);
						byte[] buffer = new byte[1024];
						int n = 0;
						while((n = fis.read(buffer))>0)//read file into byte[]
						{
							dos.write(buffer,0,n); //write the buffer on dataoutputstream
							dos.flush();
						}
						fis.close();
						
						System.out.println("File sent to client");
					}
				}
				else
				{
					sendMessage("500 Not authenticated");
				}
			}
			else if(command.equals("LOGOUT"))
			{
				if(authenticated)
				{
					authenticated = false;
					sendMessage("200 Logged out");
					dos.close();
					dis.close();
					
					txtin.close();
					txtout.close();
					connectionToClient.close();
					processing = false;	
				}
				else
				{
					sendMessage("500 Not authenticated");
				}
			}
			else
			{
				//invalid command
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
    	{
    		System.out.println("Finally");
    	}
    	
    	
    }
    
    private void sendMessage(String message)
    {
    	txtout.println(message);
    	txtout.flush();
    }
    
    private boolean matchUser(String username,String password)
    {
	boolean found = false;
	File userFile = new File("data/server/users.txt");
	try
	{
	    Scanner scan = new Scanner(userFile);
	    while(scan.hasNextLine()&&!found)
	    {
		String line = scan.nextLine();
		String lineSec[] = line.split("\\s");
    		
		//Compare user 
			if(username.equals(lineSec[0])&& password.equals(lineSec[1]))
			{
				found = true;
			}
	    }
	    scan.close();
	}
	catch(IOException ex)
	{
	    ex.printStackTrace();
	}
	
	return found;
    }
    
    private ArrayList<String> getFileList()
    {
		ArrayList<String> result = new ArrayList<String>();
		File lstFile = new File("data/server/PdfList.txt");
		try
		{
			Scanner scan = new Scanner(lstFile);
			while(scan.hasNext())
			{
				result.add(scan.nextLine());
			}
			//Read in each line of file			
			scan.close();
		}	    
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return result;
    }
    
    private String idToFile(String ID)
    {
    	String result = "";
    	File lstFile = new File("data/server/PdfList.txt");
    	try
    	{
    		Scanner scan = new Scanner(lstFile);
    		String line = "";
    		while(scan.hasNext())
    		{
    			line = scan.nextLine();
    			StringTokenizer tokenizer = new StringTokenizer(line);
    			String strid = tokenizer.nextToken();
    			String fileName = tokenizer.nextToken();
    			if(strid.equals(ID))
    			{
    				result = fileName;
    			}
    		}
    		//Read filename from file
    		
    		scan.close();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
    	return result;
    }
}
