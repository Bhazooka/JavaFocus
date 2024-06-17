package csc2b.server;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ZEDEMHandler implements Runnable {

	public ZEDEMHandler(Socket connection) {
		
	}
	
	@Override
	public void run() {
		
	}
	
	private boolean matchUser(String userN, String passW)
	{
		boolean found = false;
		
		//Code to search users.txt file for match with userN and passW.
		File userFile = new File(""/*OMITTED - Enter file location*/);
		try
		{
		    Scanner scan = new Scanner(userFile);
		    while(scan.hasNextLine()&&!found)
		    {
			String line = scan.nextLine();
			String lineSec[] = line.split("\\s");
	    		
			//***OMITTED - Enter code here to compare user*** 
			
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
		File lstFile = new File(""/*OMITTED - Enter file location*/);
		try
		{
			Scanner scan = new Scanner(lstFile);

			//***OMITTED - Read each line of the file and add to the arraylist***
			
			
			scan.close();
		}	    
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		return result;
	}
	
	private String idToFileName(String strID)
	{
		String result ="";
		
		//Code to find the file name that matches strID
		File lstFile = new File(""/*OMITTED - Enter file location*/);
    	try
    	{
    		Scanner scan = new Scanner(lstFile);

    		String line = "";
    		//***OMITTED - Read filename from file and search for filename based on ID***
    		
    		
    		scan.close();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
		return result;
	}
}
