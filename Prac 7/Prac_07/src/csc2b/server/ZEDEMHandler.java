package csc2b.server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;

public class ZEDEMHandler implements Runnable {
	
	Socket clientConnection;
	BufferedReader br;
	PrintWriter pw;
	DataOutputStream dos;
	DataInputStream dis;

	public ZEDEMHandler(Socket connection) {
		this.clientConnection = connection;
		
		try {
			br = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
			pw = new PrintWriter(clientConnection.getOutputStream());
			dos = new DataOutputStream(clientConnection.getOutputStream());
			dis = new DataInputStream(clientConnection.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		boolean running = true;
		System.out.println("Handling client request");
		
		try {
			while(running) {
				String request = "";
				
				request = br.readLine();
				
				if(request.contains("BONJOUR"))
				{
					StringTokenizer token = new StringTokenizer(request);
					String command = token.nextToken();
					String userName = token.nextToken();
					String Password = token.nextToken();
					
					if(matchUser(userName, Password) == true) {
						sendResponse("JA <"+ command +"> login successful");
					}
					else
					{
						sendResponse("NEE <" + command +"> failed. Incorrect Username or Password");
					}

				}
				else if(request.contains("PLAYLIST")) {
					ArrayList<String> List = getFileList();
					sendResponse("JA <"+request+" Sent");
					
					for(String item : List)
					{
						dos.writeUTF(item);
					}
					
				}
				else if(request.contains("ZEDEMGET")) {
					String[] command = request.split(" ");
					String ID = command[1];
					idToFileName(ID);
				}
				else if(request.contains("ZEDEMBYE"))
				{
					clientConnection.close();
					System.out.println("Closing Streams \nClient Logged Off");
				}
				else {
					System.out.println("Invalid");
				}
				
				
			}
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			}
	}
	
	
	private boolean matchUser(String userN, String passW)
	{
		boolean found = false;
		
		//Code to search users.txt file for match with userN and passW.
		File userFile = new File("data/server/users.txt");
		try
		{
		    Scanner scan = new Scanner(userFile);
		    while(scan.hasNextLine()&&!found)
		    {
				String line = scan.nextLine();
				String lineSec[] = line.split("\\s");
		    		
				//***OMITTED - Enter code here to compare user*** 
				if(userN.contains(lineSec[0]) && passW.contains(lineSec[1])) 
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
		
		//Code to add list text file contents to the arraylist.
		File lstFile = new File("data/server/List.txt");
		try
		{
			Scanner scan = new Scanner(lstFile);

			//***OMITTED - Read each line of the file and add to the arraylist***
			while(scan.hasNext())
			{
				String line = scan.nextLine();
				StringTokenizer token = new StringTokenizer(line);
				String ID = token.nextToken();
				String fileName = token.nextToken();
				
				result.add(ID + " " + fileName);	
			}
			
			scan.close();
		}	    
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		return result;
	}
	
	
	private void idToFileName(String strID)
	{
		//Code to find the file name that matches strID
		File lstFile = new File("data/server/List.txt");
    	try
    	{
    		
    		if(Integer.parseInt(strID) >= 4)
    		{
    			sendResponse("<NEE Invalid file ID>");
    			System.out.println("NEE Invalid file ID");
    		}
    		else
    		{
    			Scanner scan = new Scanner(lstFile);

        		String line = "";
        		//***OMITTED - Read filename from file and search for filename based on ID***
        		while(scan.hasNext()) {
        			
        			line = scan.nextLine();
        			String[] lineSong = line.split(" ");
        			
        			if(lineSong[0].contains(strID)) {
        				
        				String fileName = lineSong[1];
        				
        				File file = new File("data/server/" + fileName);
        				
        				System.out.println("the file Name #" + fileName);
        				if(file.exists()) {
        					pw.println("<JA song retrieved #"+fileName+"#"+String.valueOf(file.length()));
        					pw.flush();
        					
        					System.out.println("this file size #"+file.length());
        					
        					FileInputStream fis = new FileInputStream(file);
        					
        					byte[] buffer = new byte[2048];
        					
        					int n=0;
        					
        					while((n=fis.read(buffer))>0)
        					{
        						dos.write(buffer, 0, n);
        						dos.flush();
        					}
        					fis.close();
        				}
        				else
        				{
        					sendResponse("<NEE File doesn't exist>");
        					System.out.println("FILE DNE");
        				}
        				break;
        			}
        			
        		}

        		scan.close();
        	}
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
		
	}
	
	
	private void sendResponse(String response)
	{
		pw.println(response);
		pw.flush();
	}
	
	
	
}
