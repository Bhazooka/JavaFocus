package csc2b.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
/**
*@author M Bowditch 2023
*/
public class BUKAClientPane extends GridPane
{
	//Connection and streams:
	private Socket clientSocket = null;
	
	//Text streams
	private BufferedReader txtin=null;
	private PrintWriter txtout=null;
	
	//Binary streams
	private DataInputStream dis=null;
	private DataOutputStream dos=null;
	
	//Byte streams
	private OutputStream os= null;
	private InputStream is=null;
	
	//GUI elements:
	ObservableList<String> fileList; 
	private ListView<String> listView; //One approach to getting the file ID, could use a textfield instead
	private TextArea textArea;
	private Label lblUser;
	private Label lblPass;
	private TextField txtUserName;
	private TextField txtPassWord;
	private Button btnConnect;
	private Button btnLogin;
	private Button btnLIST;
	private Button btnPDFRET;
	private Button btnLOGOUT;
	
    public BUKAClientPane()
    {  	
	   	//Create buttons for each command
    	setupGUI();
    	
    	//Use buttons to send commands
    	btnConnect.setOnAction((event)->
    	{	//Create client connection
    		connect("localhost", 2018);
    	});
    	
    	btnLogin.setOnAction((event) ->
    	{
    		sendCommand("AUTH "+ txtUserName.getText() +" "+txtPassWord.getText());
    		textArea.appendText(readResponse()+"\r\n");
    	});  	
    	
    	btnLIST.setOnAction((event)->{
    		sendCommand("LIST");
    		String responseStr = readResponse();
    		String[] responseArr = responseStr.split("#");
    		for(int i = 0;i<responseArr.length;i++)
    		{
    			fileList.add(responseArr[i]);
    		}
    		listView.refresh();
    	});
    	
    	btnPDFRET.setOnAction((event)->
    	{
    		//Get the file from the list: Using listview
    		String fullFileName = listView.getSelectionModel().getSelectedItem();
    		System.out.println("Full selected FileName: "+fullFileName);
    		
    		
    		int selectedID = listView.getSelectionModel().getSelectedIndex() +1;//index starts at 0
    		sendCommand("PDFRET "+selectedID);
			
    		//Server respond with a 200 code and file size
    		String resp = readResponse();
    		String[] respArr = resp.split("\\s");
    		int size = Integer.parseInt(respArr[1]);
    		textArea.appendText(resp+"\r\n");
    		
    		//****NB: when receiving a file:
    		File fileToReceive = new File("data/client/"+fullFileName);
    		FileOutputStream fos = null;
    		try {
				fos = new FileOutputStream(fileToReceive);
				byte[] buffer = new byte[1024];
				int n = 0;
				int totalBytes = 0;
				while(totalBytes !=size)
				{
					n = dis.read(buffer, 0, buffer.length);
					fos.write(buffer,0,n);//write from the buffer to the file
					fos.flush();
					totalBytes += n;
				}
				System.out.println("File saved on client side");
				
				//textArea.appendText("File successfully Downloaded to Client");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    		finally
    		{
    			if(fos !=null)
    			{
    				try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	});
    	
    	btnLOGOUT.setOnAction((event)->
    	{
    		sendCommand("LOGOUT");
    		String response = readResponse();
    		textArea.appendText(response);
    		//try to close streams
    		try {
				dos.close();
				dis.close();
				txtout.close();
				txtin.close();
				clientSocket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	});
    	
    }
    
    private void connect(String host, int port)
    {
    	try {
			clientSocket = new Socket(host, port);
			//Set up the streams
			os = clientSocket.getOutputStream();
			is = clientSocket.getInputStream();
			txtin = new BufferedReader(new InputStreamReader(is));
			txtout = new PrintWriter(os);
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
					
			System.out.println("Client connected to server and streams created");
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
    }   
    private void setupGUI()
    {
    	setHgap(10);
    	setVgap(10);
    	setAlignment(Pos.CENTER);
    	
    	lblUser = new Label("Username:");
    	lblPass = new Label("Password");
    	txtUserName = new TextField();
    	txtPassWord = new TextField();
    	btnConnect = new Button("Connect");
    	btnLogin = new Button("Login");
    	btnLIST = new Button ("LIST");
    	btnPDFRET = new Button("PDFRET");
    	btnLOGOUT = new Button("LOGOUT");
    	
    	fileList = FXCollections.observableArrayList();
    	
    	listView = new ListView<String>(fileList);
    	listView.setPrefHeight(250);
    	textArea = new TextArea("Server Responses:\r\n");
    	textArea.setPrefHeight(200);
    	
    	add(btnConnect,0,0);
    	add(lblUser, 1, 0);
    	add(txtUserName, 2, 0);
    	add(lblPass, 3, 0);
    	add(txtPassWord, 4, 0);
    	add(btnLogin, 5, 0);
    	add(btnLIST,0,1);
    	add(btnPDFRET, 1, 1);
    	add(btnLOGOUT, 2, 1);
    	add(listView, 0, 2,6,1);
    	add(textArea, 0, 3,6,1);	
    }
    
    private void sendCommand(String command)
    {
    	txtout.println(command);
    	txtout.flush();
    }
    private String readResponse()
    {
    	String response = "";
    	try {
			response = txtin.readLine();
			System.out.println("Response from server: " +response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return response;
    }
}
