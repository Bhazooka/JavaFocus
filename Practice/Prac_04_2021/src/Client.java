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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * 
 * @author Awe
 * @version P04
 *
 */
public class Client extends GridPane{
	public Socket socket;
	public InputStream is;
	public OutputStream os;
	public BufferedReader br;
	public PrintWriter pw;
	public DataOutputStream dos;
	public DataInputStream dis;
	
	public File fileUpload;
	public String outcome ="";
	public int IDToRetrieve;
	
	public Client() {
		setUp();	
	}
	
	//request list of images
	public void request() {
		pw.println("REQUEST");
		pw.flush();
		
		String response = "";
		FileOutputStream fos = null;
		int fileSize = 0;
		String fileName = null;
		try {
			response = br.readLine();
			StringTokenizer st = new StringTokenizer(response);
			fileSize = Integer.parseInt(st.nextToken());
			fileName = st.nextToken();
			
			File file = new File("data/server/" +fileName);
			fos=new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			int n=0;
			int totalBytes =0;
			while((totalBytes!=fileSize)) {
				n=dis.read(bytes, 0, bytes.length);
				fos.write(bytes,0,n);
				fos.flush();
				totalBytes+=n;
			}
			outcome="complete!";
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//method for downloading from a server
	public void download() {
		pw.println("DOWNLOAD " + getIDToRetrieve());
		pw.flush();

		String response = "";
		FileOutputStream fos = null;
		try {
			response = br.readLine();
			StringTokenizer st = new StringTokenizer(response);
			int fileSize = Integer.parseInt(st.nextToken());
			String fileName = st.nextToken();
			System.out.println("File size received from server: "+ fileSize);
			System.out.println("Name of file received from server: "+ fileName);
			String fileToReiceveName = fileName;//2 change
			File fileDownloaded = new File("data/client/"+ fileToReiceveName);
			fos  = new FileOutputStream(fileDownloaded);		
			byte[] buffer=new byte[1024];
			int n=0;
			int totalBytes = 0;
			while(totalBytes != fileSize) {
				n= dis.read(buffer,0,buffer.length);
				fos.write(buffer,0,n);
				fos.flush();
				totalBytes+=n;
			}
			outcome="Downloaded";
			System.out.print("File saved to client!");
		}catch(IOException e) {
			e.printStackTrace();
			outcome="Could not download";
		}finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		}
	}
	
	public void upload() {
		pw.println("UPLOAD");
		pw.flush();

		try {
			File file = new File("data/client/testing.jpg");
			if(file.exists()) {
				pw.println( file.length() + " " + file.getName());
				//pw.flush();
				FileInputStream fis = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int n= 0;
				while((n=fis.read(bytes))>0) {
					dos.write(bytes, 0, n);
					dos.flush();
				}
				fis.close();
			}
			outcome = "Wonderful";
		}catch(IOException e) {
			outcome = "Unsuccessful";
		}
	}
	
	public void connect() {
		try {
			socket = new Socket("localhost", 7007);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(os);
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			System.out.println("Connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void setUp() {
		
		Button btnConnect = new Button("Connect");
		Button btnUpload = new Button("Upload");
		Button btnRequest = new Button("Record");
		TextArea txtRequest = new TextArea();
		Label lblID = new Label("Enter ID:");
		TextField txtID = new TextField();
		Button btnDownload = new Button("Download");
		TextArea txtOutcome = new TextArea();
		
		btnConnect.setOnAction(e->{
			connect();
		});
		
		btnRequest.setOnAction(e->{
			request();
			txtOutcome.setText(outcome);
		});
		
		btnDownload.setOnAction(e->{
			int x = Integer.parseInt(txtID.getText());
			setIDToRetrieve(x);
			download();
			txtOutcome.setText(outcome);
		});
		
		btnUpload.setOnAction(e->{
			/*FileChooser filechooser = new FileChooser();
			setFilePath(filechooser.showOpenDialog(null));*/
			//System.out.println("Path finder : " + getFilePath());
			upload();
			txtOutcome.setText(outcome);
		});
		setVgap(10);
		add(btnConnect, 1, 1);
		add(btnUpload, 1, 2);
		add(btnRequest, 1, 3);
		add(txtRequest, 1, 4);
		add(lblID, 1, 5);
		add(txtID, 1, 6);
		add(btnDownload, 1, 7);
		add(txtOutcome, 1,8);
	}
	
	//accessors and mutators
	public void setIDToRetrieve(int IDToRetrieve) {
		this.IDToRetrieve =IDToRetrieve;	
	}
	
	public int getIDToRetrieve() {
		return IDToRetrieve;
	}
	
	public void setFilePath(File file) {
		//this.IDToRetrieve =IDToRetrieve;	
		this.fileUpload = file;
	}
	
	public File getFilePath() {
		return fileUpload;
	}
}