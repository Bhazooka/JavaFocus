/**
 * 
 */
package acsse.csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

/**
 * @author strin
 *
 */
public class ImgClientPane extends GridPane{
	
	private Socket socket;
	private OutputStream os;
	private InputStream is;
	private PrintWriter pw;
	private BufferedReader br;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String[] listData;
	
	private Button btnConnect;
	private Button btnPULL;
	private TextField txtIDToRetrieve;
	private Label lblID;
	private Button btnDownload;
	private Button btnUpload;
	TextArea listArea;
	private TextArea responseArea;
	private Label lblList;
	private Label lblResponse;
	private ImageView imgView;
	private Button btnDisplay;
	
	private String fileToGetName = "";
	
	public ImgClientPane(Stage stage) {
		
		setupGUI();
		
		btnConnect.setOnAction((e)->{
			try {
				socket = new Socket("localhost", 9876);
				os = socket.getOutputStream();
				is = socket.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				pw = new PrintWriter(os);
				dis = new DataInputStream(is);
				dos = new DataOutputStream(os);
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		});
		
		btnPULL.setOnAction((e)->{
			sendCommand(pw, "PULL");
			String response = "";
			
			response = readResponse(br);
			System.out.println(response);
			listData = response.split("#");
			
			for(int i = 0; i<listData.length; i++) {
				listArea.appendText(listData[i]+"\n");
			}
		});
		
		btnDownload.setOnAction((e)->{
			
			int idToRetrieve = Integer.parseInt(txtIDToRetrieve.getText());
			pw.println("DOWNLOAD" + idToRetrieve);
			pw.flush();
			String response = "";
			
			try {
				response = br.readLine();
				int fileSize = Integer.parseInt(response);
				responseArea.appendText("File Received Size: " +response);
				
				
				File fileDownloaded = new File("data/client/" + fileToGetName);
				FileOutputStream fos = null;
				
				fos = new FileOutputStream(fileDownloaded);
				byte[] buffer = new byte[1024];
				int n = 0;
				int totalBytes = 0;
				
				while(totalBytes != fileSize) {
					n = dis.read(buffer, 0, buffer.length);
					fos.write(buffer, 0, n);
					fos.flush();
					totalBytes+=n;
				}
				System.out.println("File saved on client side.");
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		});
		
		btnUpload.setOnAction((e)->{
			FileChooser filechooser = new FileChooser();
			File selectedFile = filechooser.showOpenDialog(stage);
			String fileName = selectedFile.getName();
			int fileID = listData.length +1;
			pw.println("UPLOAD " + fileID + " " + fileName + " " + selectedFile.length());
			pw.flush();
			System.out.println("Upload command sent from client");
			
			FileInputStream fis;
			
			try {
				fis = new FileInputStream(selectedFile);
				byte[] buffer = new byte[1024];
				int n = 0;
				
				while((n = fis.read(buffer))> 0) {
					dos.write(buffer, 0, n);
					dos.flush();
				}
				fis.close();
				System.out.println("File sent for upload to server");
				String response = br.readLine();
				responseArea.appendText("Status of uploaded file: " + response);
			}
			catch(FileNotFoundException fe) {
				fe.printStackTrace();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		});
		
		btnDisplay.setOnAction((e)->{
			Image image = new Image("file:data/client/" + fileToGetName);
			ImageView imgView = new ImageView();
			imgView.setImage(image);
			add(imgView, 0, 7, 4, 1);
		});
		
	}
	
	private void setupGUI() {
		
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		btnConnect = new Button("Connect");
		btnPULL = new Button("PULL");
		txtIDToRetrieve = new TextField();
		lblID = new Label("File ID To retrieve:");
		btnDownload = new Button("DOWNLOAD");
		btnUpload = new Button("UPLOAD");
		listArea = new TextArea();
		listArea.setPrefHeight(50);
		responseArea = new TextArea();
		responseArea.setPrefHeight(50);
		lblList = new Label("List: ");
		lblResponse = new Label("Server response: ");
		btnDisplay = new Button("Display Downloaded image");
		
		add(btnConnect, 0, 0);
		add(btnPULL, 1, 0);
		add(lblID, 0, 1);
		add(txtIDToRetrieve, 1, 1);
		add(btnDownload, 2, 1);
		add(btnUpload, 3, 1);
		add(lblList, 0, 2);
		add(listArea, 0, 3, 4, 1);
		add(lblResponse, 0, 4);
		add(responseArea, 0, 5, 4, 1);
		add(btnDisplay, 0, 6, 4, 1);
	}
	
	private String readResponse(BufferedReader br) {
		String response = "";
		
		try {
			response = br.readLine();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	private void sendCommand(PrintWriter pw, String msg) {
		pw.println(msg);
		pw.flush();
	}

}
