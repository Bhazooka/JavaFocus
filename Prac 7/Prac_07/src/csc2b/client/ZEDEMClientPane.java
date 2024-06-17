package csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.module.ResolutionException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ZEDEMClientPane extends GridPane //You may change the JavaFX pane layout
{
	
	Label lblUserame = new Label("Username: ");
	Label lblPassword = new Label("Password: ");
	Label lblID = new Label("ID: ");
	Label lblConnectionSatut = new Label("Connection Status: ");
	Label lblResponse = new Label("Response: ");
	
	TextField txtUsername = new TextField("User");
	TextField txtPassword = new TextField("Pass235");
	TextField txtID = new TextField("");		//to enter the download ID
	TextField txtConnectionStatus = new TextField();
	TextField txtResponse = new TextField();
	
	//TextArea txtList = new TextArea();
	
	ListView<String> fileListView = new ListView<String>();
	
	private String[] dataList;
	
	Button btnLogin = new Button("Login");
	Button btnList = new Button("List");
	Button btnDownload = new Button("Download");
	Button btnLogout = new Button("Logout");
	Button btnUpload = new Button("Upload");
	
	//streams
	Socket clientSocket;
	PrintWriter pw;		
	BufferedReader br;
	DataInputStream dis;
	DataOutputStream dos;
	
	Stage stage;

	public ZEDEMClientPane() {
		
		setVgap(10);
		setHgap(10);
		
		add(lblUserame, 0, 0);
		add(txtUsername, 1, 0);
		add(lblPassword, 0, 1);
		add(txtPassword, 1, 1);
		add(btnLogin, 0, 4);
		add(lblConnectionSatut, 3, 0);
		add(txtConnectionStatus, 4, 0);
		
		add(lblResponse, 3, 1);
		add(txtResponse, 4, 1);

		//This part is for the connection
		try {
			clientSocket = new Socket("localhost", 2021);
			txtConnectionStatus.setText("online");
			pw = new PrintWriter(clientSocket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			dis = new DataInputStream(clientSocket.getInputStream());
			dos = new DataOutputStream(clientSocket.getOutputStream());
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//remember to do a connect button
		//btnConnect
		
		btnLogin.setOnAction(e-> {
			Login(txtUsername.getText(), txtPassword.getText());
		});
		
		btnList.setOnAction(e-> {
			PlayList();
		});
		
		btnDownload.setOnAction(e-> {
			Download();
		});
		
		//logout button, with its method inside it
		btnLogout.setOnAction(e-> {
			sendCommand("ZEDEMBYE");
			String response = readResponse();
			txtConnectionStatus.setText("Disconnected.");
			
			try {
				System.out.println("Closing Streams, and Logging off");
				clientSocket.close();
				pw.close();
				br.close();
				dis.close();
				dos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			System.out.println("Logged out");
		});
		
		btnUpload.setOnAction(e -> {
			Upload(stage);
		});
		
		
	}
	
	
	public void Login(String username, String password) {
		sendCommand("BONJOUR <"+username+"> <"+password+">");
		String response = readResponse();
		txtResponse.setText(response);
		
		if(response.contains("JA"))
		{	
			txtConnectionStatus.setText("Connected");
			//adding the rest of the pane
			add(btnList, 0, 7);
			add(fileListView, 0, 6, 5, 1);
			add(lblID, 2, 7);
			add(txtID, 3, 7);
			add(btnDownload, 4, 7);
			add(btnUpload, 4, 8);
			add(btnLogout, 5, 7);
			
		}
		else
		{
			txtResponse.setText("NEE");
		}
	}
	
	public void PlayList() {
		sendCommand("PLAYLIST");
		String response = readResponse();
		txtResponse.setText(response);
		
		if(response.contains("JA")) {
			try {
				dis = new DataInputStream(clientSocket.getInputStream());

				for(int i = 0; i < 3; i++)
				{
					String songOption = dis.readUTF();
					fileListView.getItems().add(songOption);
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	private void Download() {
		sendCommand("ZEDEMGET "+txtID.getText());
		String response = readResponse();
		txtResponse.setText(response);
		
		if(response.contains("JA"))
		{
			//DELIMITER !!!!!!!! #####
			StringTokenizer token = new StringTokenizer(response, "#");
			String fileID = token.nextToken();
			String fileName = token.nextToken();
			int fileSize = Integer.parseInt(token.nextToken());
			
			File file = new File("data/client/" + fileName);
			
			try {
				//to output to the file being created
				FileOutputStream fos = new FileOutputStream(file);
				//
				byte[] buffer = new byte[2048];
				int n = 0;
				int totalBytes = 0; 
				
				while(totalBytes != fileSize) {
					n = dis.read(buffer, 0, buffer.length); //get data from client socket, read bytes into buffer (the process of reading stores it in buffer)
					fos.write(buffer, 0, n); //use fileOutputstream to write to the file
					fos.flush();
					
					totalBytes += n;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	private void Upload(Stage stage) {
		sendCommand("UPLOAD");
		String response = readResponse();
		
		if (response.contains("JA"))
		{
			FileChooser fileChooser = new FileChooser();
	        File selectedFile = fileChooser.showOpenDialog(stage);
	        String fileName = selectedFile.getName();
	        int fileID = dataList.length + 1;
	        pw.println("UPLOAD " + fileID + " " + fileName + " " + selectedFile.length());
	        pw.flush();
	        System.out.println("Upload command sent from the client");

	        FileInputStream fileInputStream;
	        try {
	            fileInputStream = new FileInputStream(selectedFile);
	            byte[] buffer = new byte[1024];
	            int bytesRead = 0;
	            while ((bytesRead = fileInputStream.read(buffer)) > 0) {
	                dos.write(buffer, 0, bytesRead);
	                dos.flush();
	            }
	            //fileInputStream.close();
	            System.out.println("File sent for Upload to server");
	            //String response = br.readLine();
	            txtResponse.setText("Status of uploaded file: " + response);
	        } catch (FileNotFoundException fnfe2) {
	            fnfe2.printStackTrace();
	        } catch (IOException e2) {
	            e2.printStackTrace();
	        }
			
		}

	}
	
	
	/**
	 * Method to send commands to the Server
	 * 	Commands are carried through the printWriter object
	 * @param command 
	 */
	private void sendCommand(String command){
		try {
			pw = new PrintWriter(clientSocket.getOutputStream());
			pw.println(command);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to read response
	 * The bufferedReader stores the response from server 
	 * 	The client fetches that response using the bufferedreader.readline() method
	 * @return
	 */
	private String readResponse() {
		String serverResponse = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			serverResponse = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverResponse;
	}

}
