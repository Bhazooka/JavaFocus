import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author Awe
 * @version P04
 *
 */
public class ImgHandler implements Runnable{

	public Socket socket;
	public OutputStream os;
	public InputStream is;
	public PrintWriter pw;
	public BufferedReader br;
	public DataOutputStream dos;
	public DataInputStream dis;
	public ServerSocket serverSocket;
	/**
	 * 
	 * @param s, the constructor takes in a socket as a parameter
	 */
	public ImgHandler(Socket s) {
		socket = s;	
		
		try {
			os = socket.getOutputStream();
			is = socket.getInputStream();
			pw = new PrintWriter(os,true);
			br = new BufferedReader(new InputStreamReader(is));
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void run() {
		boolean processing = true;
		try {
			while(processing) {
				String message = br.readLine();
				StringTokenizer st = new StringTokenizer(message);
				String command = st.nextToken();
				if(command.equals("REQUEST")) {
					File file = new File("data/server/ImgList.txt");
					if(file.exists()) {
						pw.println(file.length() + " " + file.getName()+" \r\n");//writes length of file and file name on a single line
						FileInputStream fis = new FileInputStream(file);
						byte[] bytes = new byte[1024];
						int n= 0;
						while((n=fis.read(bytes))>0) {
							dos.write(bytes, 0, n);
							dos.flush();
						}
						fis.close();
						System.out.println("sent to client");
					}
				}
				
				if(command.equals("DOWNLOAD")){
					String fileID = st.nextToken();
					String fileName = "";
					File fileList = new File("data/server/ImgList.txt");
					Scanner scanner = new Scanner(fileList);
					String line = "";
					while(scanner.hasNext()) {
						line = scanner.nextLine();
						StringTokenizer tokenizer = new StringTokenizer(line);
						String id = tokenizer.nextToken();
						String fName = tokenizer.nextToken();
						if(id.equals(fileID)) {
							fileName = fName;
						}
					}
					scanner.close();
					System.out.println("Name of requeste file : " + fileName);
					File fileToReturn = new File("data/server/"+fileName);
					if(fileToReturn.exists()){
						
						pw.println(fileToReturn.length() + " " + fileToReturn.getName());
						pw.flush();	
						
						FileInputStream fis = new FileInputStream(fileToReturn);
						byte[] buffer = new byte[1024];
						int n=0;
						while((n=fis.read(buffer))>0) {
							dos.write(buffer, 0, n);
							dos.flush();
						}
						fis.close();
						System.out.println("File sent to client!");
					}
				}
				if(command.equals("UPLOAD")){
					//int fileSize = Integer.parseInt(st.nextToken());
					String fileName = st.nextToken();
					
					File file = new File("data/server/"+ fileName);
					FileOutputStream fos  = new FileOutputStream(file);		
					byte[] buffer=new byte[1024];
					int n=0;
					int totalBytes = 0;
					while(totalBytes != 1024) {
						n= dis.read(buffer,0,buffer.length);
						fos.write(buffer,0,n);
						fos.flush();
						totalBytes+=n;
					}
					System.out.print("done");
				}
			}
		}catch(IOException e) {
			
		}
	}
}
