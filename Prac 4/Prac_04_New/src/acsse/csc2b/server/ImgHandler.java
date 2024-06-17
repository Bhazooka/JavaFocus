package acsse.csc2b.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ImgHandler implements Runnable {

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	/**
	 * Constructor to initialize all the streams 
	 * 	needed to process file data
	 * @param client
	 */
	public ImgHandler(Socket client) {
		this.socket = client;
		try {
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			printWriter = new PrintWriter(outputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			dataOutputStream = new DataOutputStream(outputStream);
			dataInputStream = new DataInputStream(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Function to return the Image file
	 * @return
	 * @throws FileNotFoundException
	 */
	private String ImageList() throws FileNotFoundException {
		String request = "";
		Scanner scan = new Scanner(new File("./data/server/ImgList.txt"));
		while(scan.hasNextLine()) 
		{
			String images = scan.nextLine();
			request += images + "#";
		}
		System.out.println("Image list loaded");
		scan.close();
		
		return request;
	}

	/**
	 *	Overriden run function 
	 *		handles the Button click Events commands
	 *		handles retrieving of files 
	 */
	@Override
	public void run() {
	    System.out.println("Running");
	    boolean running = true;

	    try {
	        while (running) {
	            String message = bufferedReader.readLine();
	            System.out.println("Message " + message);
	            StringTokenizer stringTokenizer = new StringTokenizer(message);
	            String command = stringTokenizer.nextToken().toUpperCase();

	            if ("PULL".equals(command)) {
	                printWriter.println(ImageList());
	                printWriter.flush();
	                
	            } else if ("DOWNLOAD".equals(command)) {
	                String fileID = stringTokenizer.nextToken();
	                System.out.println("ID requested: " + fileID);
	                String fileName = "";
	                File fileList = new File("data/server/ImgList.txt");
	                Scanner sc = new Scanner(fileList);
	                String line = "";

	                while (sc.hasNext()) {
	                    line = sc.nextLine();
	                    StringTokenizer tokenizer = new StringTokenizer(line);
	                    String id = tokenizer.nextToken();
	                    String fName = tokenizer.nextToken();
	                    if (id.equals(fileID)) {
	                        fileName = fName;
	                    }
	                }
	                sc.close();

	                System.out.println("Name of the requested file: " + fileName);
	                File fileFound = new File("data/server/" + fileName);
	                if (fileFound.exists()) {
	                    printWriter.println(fileFound.length());
	                    printWriter.flush();
	                    FileInputStream fis = new FileInputStream(fileFound);
	                    byte[] buffer = new byte[1024];
	                    int n = 0;
	                    while ((n = fis.read(buffer)) > 0) {
	                        dataOutputStream.write(buffer, 0, n);
	                        dataOutputStream.flush();
	                    }
	                    fis.close();
	                    System.out.println("File sent to client.");
	                }
	            } else if ("UPLOAD".equals(command)) {
	                String fileRecID = stringTokenizer.nextToken();
	                String fileRecName = stringTokenizer.nextToken();
	                int size = Integer.parseInt(stringTokenizer.nextToken());

	                PrintWriter writeOut = new PrintWriter(new BufferedWriter(new FileWriter("./data/server/ImgList.txt", true)));
	                writeOut.println(fileRecID + " " + fileRecName);
	                writeOut.flush();
	                writeOut.close();
	                System.out.println("File appended to list");

	                File fileToRec = new File("data/server/" + fileRecName);
	                FileOutputStream fos = null;
	                System.out.println("Still receiving bytes from client...");

	                try {
	                    fos = new FileOutputStream(fileToRec);
	                    byte[] buffer = new byte[1024];
	                    int n = 0;
	                    int totalBytes = 0;
	                    while (totalBytes != size) {
	                        n = dataInputStream.read(buffer, 0, buffer.length);
	                        fos.write(buffer, 0, n);
	                        fos.flush();
	                        totalBytes += n;
	                    }

	                    printWriter.println("HAPPY");
	                    printWriter.flush();
	                    System.out.println("FILE UPLOADED");

	                } catch (IOException e) {
	                	printWriter.println("SAD");
	                	printWriter.flush();
	                    e.printStackTrace();
	                } finally {
	                    if (fos != null) {
	                        try {
	                            fos.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	

}



