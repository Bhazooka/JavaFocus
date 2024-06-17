package csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BUKAClientPane extends GridPane //You may change the JavaFX pane layout
{			
	
	Socket clientSocket;
	//IO Streams
	private PrintWriter pw = null;
	private StringBuffer sb = null ;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private InputStream is = null;
	private OutputStream os = null;
	private BufferedReader br = null;
	private boolean userLoggedIn = false;
	
	
	//GUI Nodes For Login Screen
	Label userNamelbl = new Label("Username: ") ;
	TextField userNametxt = new TextField("Riri");
	
	Label passwordlbl = new Label("Password: ");
	TextField passwordtxt = new TextField("123");
	
	Button loginbtn = new Button("Login: ");
	
	
	//GUI Nodes For User LoggedIn
	Label idlbl = new Label("ID: ");
	TextField idtxt= new TextField();
	
	Button logoutbtn = new Button("Logout");
	Button downloadbtn = new Button("Download");
	Button listbtn = new Button("Show List");
	
	TextArea listArea = new TextArea();
	
	

	
	
	//Create client connection
	//Create buttons for each command
	//Use buttons to send commands
    public BUKAClientPane()
    {
    	try {
    		clientSocket = new Socket("localhost", 2018);
    		
    		pw = new PrintWriter(clientSocket.getOutputStream());
    		dis = new DataInputStream(clientSocket.getInputStream());
    		dos = new DataOutputStream(clientSocket.getOutputStream());
    		br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		    		
    	}
    	catch(IOException ex) {
    		ex.getMessage();
    	}
    	//Call the function to create the GUI
    	createLoginGUI();
    	
    	
    	//Handle the buttons
    	loginbtn.setOnAction((e)-> {
    		//You can only use this button if the user has logged in
    		if(userLoggedIn == false) {
    			loginbtn.setOnAction(null);
    			
    			if(login(userNametxt.getText(), passwordtxt.getText()) ==true )
    			{
    				pw.println("AUTH " + userNametxt.getText() + " " + passwordtxt.getText());
    				pw.flush();
    				userLoggedIn = true;
    				String response = readFromBuffR();
    				sb.append("--" + response + "\n");
    			}
    			else if(userNametxt.getText().isBlank() && passwordtxt.getText().isBlank())
    			{
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setContentText("Username and(or) Password was left blank.");
    				alert.show();
    			}
    			else
    			{
					Alert alert = new Alert(AlertType.ERROR); 
					alert.setContentText("UserName or Password is incorrect."); 
					alert.show(); 
					
					String response = readFromBuffR(); 
					sb.append("--" + response + "\n"); 
    				
    			}
    			
    			//i the user is logged in, create the loggedinGUI
    			if(userLoggedIn == true)
    			{
    				createUserGUI();
    			}
    	
    		}
    	});
    	
		//List button
		listbtn.setOnAction((event)-> {
			pw.println("LIST");	//sending the list command to the server
			String response = readFromBuffR();
			sb.append("--" + response + "\n");
		});
		
		//Logout button
		logoutbtn.setOnAction((event)->{
			pw.println("LOGOUT");	//sending the LOGOUT command to the server
		});
		
		//download button
		downloadbtn.setOnAction((event)-> {
			if(idtxt.getText().isBlank())
			{
				Alert a = new Alert(AlertType.WARNING);
				a.setContentText("No selected ID");
			}
			else
			{
				pw.println("PDFRET"+"#" + idtxt.getText());
				String response = readFromBuffR();
				
				sb.append("--" + response + "\n");
			}
		});
    	
    }
	
    
    public void createLoginGUI()
    {	
    	HBox userNameHBox = new HBox(10, userNamelbl, userNametxt);
    	HBox PasswordHBox = new HBox(10, passwordlbl, passwordtxt); 
    	VBox vLoginBox = new VBox(5, userNameHBox, PasswordHBox, loginbtn);
    	
    	add(vLoginBox, 0,0);
    }
    
    public void createUserGUI()
    {
    	HBox idBox = new HBox(10, idlbl, idtxt, downloadbtn);
    	VBox vBoxRight = new VBox(10, idBox, listArea, listbtn,logoutbtn);
    	add(vBoxRight, 0, 0);
    }
    
    
   
    //login Function
    private boolean login(String userName, String password) {
    	boolean loggedIn = false;
    	
    	pw.println(userName + "#" + password);	//sends the username and password to the server to validate
    	String[] response = readFromBuffR().split("#"); /*the server reply, we read server reply using readFrom buffer, 
    													the response is split into 2 strings, username and password*/
    	
    	if(response[0].equals("200"))	//if the response is 200, its OK
    	{
    		loggedIn = true;
    	}
    	else
    	{
    		//sb.append("--" + response[0] + " " + response[1]);
    		loggedIn = false;
    		
    	}
    	return loggedIn;
    }
    
    /*
    private boolean login(String userName, String Password)
    {
    	String User;
    	if(userNametxt.getText() == " Drizzy" && passwordtxt.getText() == "p455w0rd")
    	{
    		return false;
    	}
    	else
    	{
    		return false;
    	}

    }
    */
    
    
    /**
     * Function to read response from a server. 
     * 
     * used after sending a Command to the Server */
	private String readFromBuffR(){
		String line = ""; 
		
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.getMessage(); 
		}
		return line; 
	}

}











//need socket to establish connection



/*
GridPane grid = new GridPane();

Label userNamelbl = new Label("Username: ") ;
TextField userNametxt = new TextField();

Label passwordlbl = new Label("Password: ");
TextField passwordtxt = new TextField();

Button loginbtn = new Button("Login");



public void createGUI() {
	grid.add(userNamelbl, 0, 0);
	add(userNametxt, 0, 0);
}
*/