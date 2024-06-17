import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable
{
	
	private Socket client = null;	//reciving connecion from client

	public ClientHandler(Socket connection) 	//constructor
	{
		client = connection;
	}
	
	@Override
	public void run() 
	{
		
		BufferedReader in = null;	//using this to read user inpit, could also use the scanner
		DataOutputStream out = null;	//using dataoutputstream because it allows to handle binary data (meaning we could also input files and videos and all), whereas printwriter would only work for textfiles, files with text data
		
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));	
			out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			
			
			String line = in.readLine();
			System.out.println("Client request: \\t" + line);	//the request that we get from the client
			
			if(line.contains("GET"))	//wrong command 
			{
				sendErrorHeader(out, 501, "Command not supported " + line);
			}
			else
			{
				StringTokenizer httpGETTokenizer = new StringTokenizer(line);
				
				if(httpGETTokenizer.countTokens() != 3)//Malformed request 
				{
					sendErrorHeader(out, 500, "Incorrect GET request " + line);
				}
				else
				{
					String filename = httpGETTokenizer.nextToken();
					filename = httpGETTokenizer.nextToken();
					
					if(filename.equals("/")) //if the filename is just a '/', by default, get the index.html file
					{
						filename = "index.html";
					}
					else if(filename.indexOf('/') == 0)	//otherwise get the substring of the file thats being asked for
					{
						filename = filename.substring(1);
					}
					
					File file = new File(filename);
					
					if(!file.exists()) {
						sendErrorHeader(out, 404, "File Not Found");
					}
					else
					{ //this is the format of how you have to set it out so the site can be rendered
						out.writeBytes("HTTP/1.1 200 OK \r\n");
						out.writeBytes("Connection: close \r\n");
						
						if(filename.endsWith(".html")) 
						{
							out.writeBytes("Content-Type: text/html\r\n");
						}
						else if(filename.endsWith("png")) 
						{
							out.writeBytes("Content-Type: image/png\r\n");
						}
						else if(filename.endsWith("jpg")) 
						{
							out.writeBytes("Content-Type: image/jpg\r\n");
						}
						else if(filename.endsWith("mp4")) {
							out.writeBytes("Content-Type: video/mp4\r\n");
						}
						out.writeBytes("Content-length: " + file.length() + "\r\n");
						out.writeBytes("\r\n");
						
						try {
							BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
							byte[] buffer = new byte[1024];
							int n = 0;
							
							while((n = bin.read(buffer))>0) 
							{
								out.write(buffer,0,n);
							}
							
							bin.close();	
							
						}
						catch(IOException e) {
							System.err.println("Error reading file to be set to client");
						}
						out.writeBytes("\r\n");
						out.flush();
					}
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void sendErrorHeader(DataOutputStream out, int errorCode, String message) 
	{
		//building an HTML document to generate when an error page
		String content = "<html><head><title>An ERROR OCCURED</title></head>";
		content += "<body>Message: " + message + "</body></html>";
		String errorString = "";
		
		switch(errorCode)
		{
		case 404:
			errorString = "404 Not Found";
			break;
		case 500: 
			errorString = "500 Server Not Found";
			break;
		}
		
		try {
			out.writeBytes("HTTP/1.1 "+ errorCode + " " + errorString + "\r\n");
			out.writeBytes("Connection: close\r\n");
			out.writeBytes("Current-length " + content.getBytes().length + "\r\n");
			out.writeBytes("\r\n");
			out.writeBytes(content);
			out.writeBytes("\r\n");
			out.flush();
			
		}catch(IOException e) { 
			e.printStackTrace();
		}
		
	}
	
	

}
