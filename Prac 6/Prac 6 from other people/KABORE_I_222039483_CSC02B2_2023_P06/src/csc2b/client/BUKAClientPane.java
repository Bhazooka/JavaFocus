package csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BUKAClientPane extends GridPane //You may change the JavaFX pane layout
{
	//labels
	Label lblUsername = new Label("Enter Username");
	Label lblPassword = new Label("Enter Password");
	Label lblLogin = new Label("Login status");
	Label lblConnection = new Label("Connection status");
	Label lblResponse = new Label("BUKA Response");
	
	//textFields
	TextField txtUsername = new TextField();
	TextField txtPassword = new TextField();
	TextField txtLogin = new TextField("No login");
	TextField txtConnection = new TextField("Offline");
	TextField txtResponse = new TextField();
	TextField txtID = new TextField("Enter ID Here");
	
	//List for files
    ListView<String> fileListView = new ListView<>();
	
	//Create buttons for each command
	Button btnLogin = new Button("Login");
	Button btnList = new Button("List");
	Button btnPDF = new Button("PDFRET");
	Button btnLogoff = new Button("Log off");
	
	//Connection streams
	Socket socket; //connects to the server
	PrintWriter pw; //writes to the server
	BufferedReader br; //reads from the server
	DataInputStream dis; //recieves data from the server
	
    public BUKAClientPane()
    {
    	//Create client connection
    	try {
			socket = new Socket("localhost",2018);
			txtConnection.setText("Online");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	

    	setHgap(10); //set horizontal gap
    	setVgap(10); //set vertical gap
    	add(lblConnection,0,0);
    	add(txtConnection,1,0);
    	add(lblUsername,0,1);
    	add(txtUsername,0,2);
    	add(lblPassword,1,1);
    	add(txtPassword,1,2);
    	add(btnLogin,2,2);
    	add(lblLogin,3,1);
    	add(txtLogin,3,2);
    	add(lblResponse,0,4);
    	add(txtResponse,0,5,2,1);
    	add(btnLogoff,5,5);
    	
	//Use buttons to send commands
    	btnLogin.setOnAction(e->{
    		Login(txtUsername.getText(), txtPassword.getText());
    	});
    	
    	btnList.setOnAction(e->{
    		List();
    	});
    	
    	btnPDF.setOnAction(e->{
    		PDFRET();
    	});
    	
    	btnLogoff.setOnAction(e->{
    		txtLogin.setText("logged off");
    		
    		try {
				socket.close();
				pw.close();
	    		br.close();
	    		dis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
    		
    	});	
    }
    

	public void Login(String username, String password) {
		sendCommand("AUTH <"+username+"> <"+password+">"); 
		String response = readResponse();
		txtResponse.setText(response); 
		
		if(response.endsWith(" Successful>")) {
			txtLogin.setText("Logged in");
			//add other elements
			add(btnList,0,7);
			add(btnPDF,2,7);
			add(txtID,1,7);
			add(fileListView,0,8);
		}	
	}
	
	public void List() {
		sendCommand("LIST");
		String response = readResponse();
		txtResponse.setText(response);
		
		if(response.endsWith(" Successful>")) {
    		try {
    			//create data input stream through socket
				dis = new DataInputStream(socket.getInputStream());
				for(int i=0;i<3;i++) {
					//for each file read the string 
	        		String List = dis.readUTF();
	        		//get item into list view and add it 
	        		fileListView.getItems().add(List+"\n"); 
	    		}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void PDFRET() {
		sendCommand("PDFRET "+txtID.getText());
		String response = readResponse();
		txtResponse.setText(response);
		
		if(response.contains(" Successful")) {
			
			StringTokenizer token = new StringTokenizer(response,"#");
			String string = token.nextToken();
			String fileName = token.nextToken();
			int fileSize = Integer.parseInt(token.nextToken());
			File file = new File("data/client/"+fileName); //create file 
			
			try {
				//fos will write to the file being created
				FileOutputStream fos = new FileOutputStream(file); 
				
				byte[] buffer = new byte[1024]; //buffer to carry data
				int n = 0; //counter
				int totalBytes = 0; //bytes being read
				
				while(totalBytes!=fileSize) { //as long as bytes being read is less than total size of file
					n = dis.read(buffer, 0, buffer.length); //read bytes into the buffer until the end of the buffer
					fos.write(buffer, 0, n); //write data from the buffer to the file until n
					fos.flush(); 
					totalBytes += n; //increment total bytes by bytes read
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
	
	private void sendCommand(String command) {
		try {
			pw= new PrintWriter(socket.getOutputStream()); //create printwriter
			pw.println(command); //write command
			pw.flush(); //flush out 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readResponse() {
		String response = ""; 
		try {
			//read input from socket to input stream reader into the buffered reader 
			br = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			response = br.readLine(); //read the response from buffered reader
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response; //return the response as a string 
	}
}