package csc2b.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BUKAHandler implements Runnable
{
	
	Socket clientConnection;
	
	BufferedReader br;
	PrintWriter pw;
	DataOutputStream dos;
	DataInputStream dis;
	
	
    public BUKAHandler(Socket newConnectionToClient)
    {	
	//Bind streams
    	this.clientConnection = newConnectionToClient;
    	
    	//create streams
    	try {
    		pw = new PrintWriter(clientConnection.getOutputStream());
    		br = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
    		dos = new DataOutputStream(clientConnection.getOutputStream());
    		dis = new DataInputStream(clientConnection.getInputStream());
  
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    	
    }
    
    public void run()
    {
	//Process commands from client
    	boolean running = true;
    	System.out.println("Handling client request");
    	
    	try {
    		while(running) {
	    		String request = "";
	    		//read first request
	    		request = br.readLine();	
	    		
	    		if(request.startsWith("AUTH")) {
	    			StringTokenizer token = new StringTokenizer(request);
	    			String command = token.nextToken();
	    			String userName = token.nextToken();
	    			String password = token.nextToken();
	    			//check if details match
	    			if(matchUser(userName, password)==true) {
		    			sendResponse("200 <"+command+" Command Successful>");
	    			}
	    			else {
	    				sendResponse("500 <"+command+" Command Unsuccessful>");
	    			}
	    			
	    		}
	    		else if(request.contains("LIST")) {
	    			
	    			ArrayList<String> List = getFileList();
	    			sendResponse("200 <"+request+" Command Successful>");
	    			
	    			for(String item : List) {
		    			dos.writeUTF(item);
	    			}
	    			
	    		}
	    		else if(request.startsWith("PDFRET")) {
	    			String[] command = request.split(" ");
	    			String ID = command[1];
	    			sendFile(ID);
	    			
	    		}
	    		else if(request.equals("LOGOUT")) {
	    			clientConnection.close();
	    		}
	    		else {
	    			System.out.println("Invalid");
	    		}
    		}
    	} catch (IOException e) {
		
			e.printStackTrace();
		}
   
    	
    	
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
			//***OMITTED - Enter code here to compare user***
			
			if(username.contains(lineSec[0]) && password.contains(lineSec[1])) {
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
		File lstFile = new File("data/server/PdfList.txt");
		try
		{
			Scanner scan = new Scanner(lstFile);

			//***OMITTED - Read each line of the file and add to the arraylist***
			
			while(scan.hasNext()) {
				String line = scan.nextLine();
				StringTokenizer token = new StringTokenizer(line);
				String ID = token.nextToken();
				String fileName = token.nextToken();
				result.add(ID+" "+fileName);
			}
			
			scan.close();
		}	    
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		return result;
    }
    
    private void sendFile(String ID)
    {
    	//Code to find the file name that matches strID
    	File lstFile = new File("data/server/PdfList.txt");
    	try
    	{
    		Scanner scan = new Scanner(lstFile);
    		String line = "";
    		//***OMITTED - Read filename from file and search for filename based on ID***
    		while(scan.hasNext()) {
    			line = scan.nextLine();
    			String[] arrLine = line.split(" ");
    			
    			if(arrLine[0].contains(ID)) {
    				
    				String fileName = arrLine[1];
    				File file = new File("data/server/"+fileName);
    				System.out.println("this file name #"+fileName);
    				if(file.exists()) {
    					pw.println("<200 PDF retreive Successful> #"+fileName+"#"+String.valueOf(file.length()));
    					pw.flush();
    					System.out.println("this file size #"+file.length());
    					FileInputStream fis = new FileInputStream(file);
            			byte[] buffer = new byte[1024];
            			
            			int n = 0;
            			
            			while((n=fis.read(buffer))>0) {
            				dos.write(buffer, 0, n);
            				dos.flush();
            			}
            			fis.close();
    				}
    				else {
    					sendResponse("<500 File does not exist>");
    					System.out.println("FILE DNE");
    				}
    				break;
    			}
    			
    			
    			
    		}
    		
    		scan.close();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
   
    }
    
    private void sendResponse(String response) {
    	pw.println(response);
    	pw.flush();
    }
}
