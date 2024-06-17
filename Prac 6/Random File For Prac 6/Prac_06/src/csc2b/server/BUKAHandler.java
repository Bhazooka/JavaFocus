package csc2b.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

public class BUKAHandler implements Runnable
{
	
	private PrintWriter pw = null;
	private BufferedReader br = null; 
	private DataOutputStream dos = null; 
	private DataInputStream dis = null; 
	private boolean loggedIn = false;
	private Socket connection = null; 
	
    public BUKAHandler(Socket connection)
    {	
	//Bind streams
    	this.connection = connection;
    	
    	try {
    		pw = new PrintWriter(connection.getOutputStream());
    		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    		dos = new DataOutputStream(connection.getOutputStream());
    		dis = new DataInputStream(connection.getInputStream());
    	}
    	catch(IOException e)
    	{
    		e.getMessage();
    	}
    	
    }
    
	@Override
	public void run() 
	{
		String commandLine = requestLine(); // read the line
		String line[] = commandLine.split("#"); //spilt the line into tokens
		
		StringTokenizer tokenizer = new StringTokenizer("AUTH", "#");
		String command = tokenizer.nextToken().toUpperCase();
		
		if((loggedIn == false) || command.equals("AUTH"))
		{
			String userName = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			boolean user = matchUser(userName, password); 
			if(user == true)
			{
				responseCode("200", "Successfully Logged In");
				loggedIn = true;
			}
			else
			{
				loggedIn = false; 
				responseCode("500", "User not found");
			}
		}
		
		if(loggedIn == true)
		{
			String otherRequests [] = requestLine().split("#");
			
			if(otherRequests[0].equals("LIST"))
			{
				System.out.println("***Command:" + line[0] + "***"); 
				ArrayList<String> list = getFileList();
				StringBuffer sb = new StringBuffer(); 
				for(String l: list)
				{
					sb.append(l +"\n"); 
				}
				responseCode("200", new String(sb));
				
				System.out.println("--File List Sent"); 
				
			}
			else if(otherRequests[0].equals("PDFRET"))
			{
				System.out.println("***Command:" + line[0] + "***"); 
				
				String fileName = idToFile(line[1]); 
				
				if(fileName.equals("null"))
				{
					responseCode("NEE", "Could not find the file ID: " + line[1]);
					System.out.println("--Unknown file ID");
				}
				else
				{
					File fileToSend = new File ("data/server/"+fileName); 
					
					try 
					{
						FileInputStream fis = new FileInputStream(fileToSend);
						
						byte[] buffer = new byte[2048]; 
						int n = 0; 
						
						pw.println(fileToSend.length()); //sending the file length 
						
						while( (n = fis.read(buffer)) >0)
						{
							dos.write(buffer, 0, n); 
							dos.flush(); 
						}
						fis.close(); 
						
						responseCode("200", "The file requested has been sent"); 
						System.out.println("--File sent");
						
					} 
					catch (FileNotFoundException e) 
					{
						e.getMessage(); 
					}  
					catch(IOException e)
					{
						e.getMessage();
					}
					
				}
				
			}
			else if(otherRequests[0].equals("LOGOUT"))
			{
				System.out.println("***Command:" + line[0] + "***"); 
				
				loggedIn = false; 
				try 
				{ 
					responseCode("200", "LOGOUT");
					connection.close();
					dis.close();
					dos.close();
					pw.close();
					br.close();
				} catch (IOException e) 
				{
					e.getMessage(); 
				}
				System.out.println("--Logged off"); 
				
			}
			else
			{
				System.out.println("***Command:" + line[0] + "***"); 
				loggedIn = false; 
				
				try 
				{ 
					connection.close();
					dis.close();
					dos.close();
					pw.close();
					br.close();
				} catch (IOException e) 
				{ 
					e.getMessage(); 
				}
				System.out.println("--Logged off"); 
			}
			
		}
		
	}
	private String requestLine()
	{
		String line = ""; 
		try
		{
		    line = br.readLine();
		} catch (IOException e)
		{
			e.getMessage(); 
		} 
		return line; 
	}
    
    private boolean matchUser(String username,String password)
    {
		boolean found = false;
		File userFile = new File("data/server/users.txt");
		try
		{
			//Code to search users.txt file for match with username and password
		    Scanner scan = new Scanner(userFile);
		    while(scan.hasNextLine()&&!found)
		    {
				String line = scan.nextLine();
				String lineSec[] = line.split("\\s");
		    		
		    	if(lineSec.length == 2 && lineSec[0].equals(username) && lineSec[1].equals(password))
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
		
		File lstFile = new File("data/server/List.txt");
		try
		{
			Scanner scan = new Scanner(lstFile);

			while(scan.hasNext()) {
				result.add(scan.nextLine());
			}
			
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
		String result ="";
		
		//Code to find the file name that matches strID
		File lstFile = new File("data/server/List.txt");
    	try
    	{
    		Scanner scan = new Scanner(lstFile);
    		
    		while(scan.hasNextLine())
    		{
    			String line[] = scan.nextLine().split("\\s");
    			if(line[0].equals(ID))
    			{
    				result = line[1]; 
    			}
    			else
    			{
    				result = "null"; 
    			}
    		}
    		
    		scan.close();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
		return result;
	}
    
    
    private String readCommand() {
    	String line = "";
    	
    	try {
    		line = br.readLine();
    		
    	}catch(IOException e) {
    		e.getMessage();
    	}
    	return line;
    }
    
    
    private void response(String command, String message)
    {

    	
    }
    
	private void responseCode(String command, String message)
	{
		pw.println(command + "#" + message); 
	}
	
    
}
