/**
 * 
 */
package acsse.csc2b.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.io.File;
"
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * @author strin
 *
 */
public class ImgHandler implements Runnable {
	
	private Socket incomingConnection;
	private OutputStream os;
	private InputStream is;
	private PrintWriter pw;
	private BufferedReader br;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public ImgHandler(Socket s) {
		this.incomingConnection = s;
		try {
			os = incomingConnection.getOutputStream();
			is = incomingConnection.getInputStream();
			pw = new PrintWriter(os);
			br = new BufferedReader(new InputStreamReader(is));
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Handling Client Requests");
		boolean processing = true;
		
		try {
			while(processing) {
				String message = br.readLine();
				System.out.println("Message: " + message);
				StringTokenizer st = new StringTokenizer(message);
				String command = st.nextToken().toUpperCase();
				
				switch(command) {
				case "PULL" : 
					pw.println(loadImgList());
					pw.flush();
					break;
				case "DOWNLOAD": 
					String fileID = st.nextToken();
					System.out.println("ID requested: " + fileID);
					String filename = "";
					
					File fileList = new File("data/server/ImgList.txt");
					Scanner sc = new Scanner(fileList);
					String line = "";
					
					while(sc.hasNext()) {
						line = sc.nextLine();
						StringTokenizer tokenizer = new StringTokenizer(line);
						String id = tokenizer.nextToken();
						String fName = tokenizer.nextToken();
						if(id.equals(fileID)) {
							filename = fName;
						}
					}
					sc.close();
					
					System.out.println("Name of the requested file: " + filename);
					File fileToReturn = new File("data/server" + filename);
					
					if(fileToReturn.exists()) {
						
						pw.println(fileToReturn.length());
						pw.flush();
						
						FileInputStream fis = new FileInputStream(fileToReturn);
						byte[] buffer = new byte[1024];
						int n = 0;
						
						while((n=fis.read(buffer)) > 0) {
							dos.write(buffer, 0, n);
							dos.flush();
						}
						
						fis.close();
						System.out.println("File sent to client.");
					}
					break;
				case "UPLOAD":	
					String fileRecID = st.nextToken();
					String fileRecName = st.nextToken();
					
					int size = Integer.parseInt(st.nextToken());
					
					PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("./data/server/ImgList.txt", true)));
					pout.println(fileRecID + " " + fileRecName);
					pout.flush();
					pout.close();
					System.out.println("File appended to list");
					
					File fileToRec = new File("data/server/" + fileRecName);
					FileOutputStream fos = null;
					System.out.println("Still Receiving Bytes from client...");
					
					try {
						fos = new FileOutputStream(fileToRec);
						byte[] buffer = new byte[1024];
						int n = 0;
						int totalbytes = 0;
						
						while(totalbytes != size) {
							n = dis.read(buffer, 0, n);
							fos.flush();
							totalbytes+=n;
						}
						pw.println("HAPPY");
						pw.flush();
						System.out.println("DONE File uploaded to server");
					}
					catch(IOException ex) {
						pw.println("SAD");
						pw.flush();
						ex.printStackTrace();
					}
					finally {
						if(fos != null) {
							try {
								fos.close();
							}
							catch(IOException ex) {
								ex.printStackTrace();
							}
						}
					}
					break;
				}
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private String getFileNameFromID(String searchID) {
		String ret = "";
		
		File imglist = new File("data/server/ImgList.txt");
		try {
			Scanner sc = new Scanner(imglist);
			String line = "";
			while(sc.hasNext()) {
				line = sc.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				String id = st.nextToken();
				String fname = st.nextToken();
				
				if(id.equals(searchID)) {
					ret =  fname;
				}
			}
			sc.close();
		}
		catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}
		
		return ret;
	}
	
	private String loadImgList() {
		String ret = "";
		
		try {
			Scanner sc = new Scanner(new File("./data/server/ImgList.txt"));
			while(sc.hasNextLine()) {
				String img = sc.nextLine();
				ret += img + "#";
			}
			System.out.println("Image list loaded");
			sc.close();
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return ret;
	}

}
