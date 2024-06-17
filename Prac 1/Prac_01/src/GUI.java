import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author barbu
 *	GUI Class
 */
public class GUI {
	
    private BufferedReader bufferedReader = null;
    private Socket socket = null;
    private PrintWriter printWriter = null;

    /**
     * @param primaryStage
     * 	Main function
     */
    public void show(Stage primaryStage) throws UnknownHostException {
    	
        primaryStage.setTitle("Test Email");

        //Create grid pane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));	//padding between cells
        grid.setHgap(8);	//gap between cells
        grid.setVgap(8);

        //Create label
        Label hostLabel = new Label("Host:");
        Label portLabel = new Label("Port:");
        Label fromLabel = new Label("From:");
        Label recipientLabel= new Label("Send To:");
        Label messageLabel = new Label("Message:");
        Label satusLabel = new Label("Status");
        
        //Create text fields
        TextField hostTextField = new TextField();
        TextField portTextField = new TextField();
        TextField recipientTextField = new TextField();
        TextField fromTextField = new TextField();
        TextArea messageTextArea = new TextArea();
        TextField statusText = new TextField();
        statusText.setEditable(false);


        //Create buttons and set their sizes
        Button connectButton = new Button("Connect");
        connectButton.setPrefSize(100,50);
        Button sendButton = new Button("Send");
        sendButton.setPrefSize(100,50);

        //Getting text from textFields and storing in variables
    	String host = hostTextField.getText();
       
        //Function to handle send button
        sendButton.setOnAction( e -> {
        	try {
				printWriter = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
            
        	String message = messageTextArea.getText();
        	String from = fromTextField.getText();
        	String to = recipientTextField.getText();

            read(bufferedReader);
			Reply(printWriter, "HELO Papercut");
			
			read(bufferedReader);
			Reply(printWriter, "MAIL FROM: <" + from + ">");
			
			read(bufferedReader);
			Reply(printWriter, "RCPT TO: <" + to + ">");
			
			read(bufferedReader);
			Reply(printWriter, "DATA");
			
			read(bufferedReader);
			Reply(printWriter," Subject: CSC2B");
			
			Reply(printWriter,"");
			Reply(printWriter, message);
			
			Reply(printWriter,".");
			read(bufferedReader);
			
			Reply(printWriter, "QUIT");
			read(bufferedReader);
        });
        
        connectButton.setOnAction(e -> {
        	int port = Integer.parseInt(portTextField.getText()); //TextFields dont take integers so we parse the textValue into an integer
        	
        	try {
				socket = new Socket(host, port);
				statusText.setText("Connected to " + socket.getInetAddress() + port);	
			} 
        	catch (UnknownHostException e1) {
				System.err.println("Failed to connect");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        
        //adding components to the grid pane
        grid.add(hostLabel, 0, 0);
        grid.add(hostTextField, 1, 0);
        
        grid.add(portLabel, 3, 0);		//The numbers are the coordinates (child Node, colIndex, rowIndex)
        grid.add(portTextField, 4, 0);
        
        grid.add(satusLabel, 7, 0);
        grid.add(statusText, 8, 0);
        
        grid.add(connectButton, 1, 2, 5, 1);//(Node child, columnIndex, rowIndex, colspan(spans 2 rows), rowspan)
        
        grid.add(recipientLabel, 0, 6);
        grid.add(recipientTextField, 1, 6);
        
        grid.add(fromLabel, 0, 7);
        grid.add(fromTextField, 1, 7);
        
        grid.add(messageLabel, 0, 8);
        grid.add(messageTextArea, 1, 8, 20, 6);//(Node child, columnIndex, rowIndex, colspan(spans 2 rows), rowspan)
        
        grid.add(sendButton, 1, 15, 5, 1);

        //create a Scene with the grid pane
        Scene scene = new Scene(grid, 300, 150);

        //set scene and show stage
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param printWriter
     * @param message
     * 	Analysis and replys
     */
    private static void Reply(PrintWriter printWriter, String message) {
    	printWriter.println(message);
    	printWriter.flush();
    }
    
    /**
     * @param bufferedReader
     * @return
     * 	Function to read
     */
    private static String read(BufferedReader bufferedReader) {
        String read = null;
		try {
			read = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(read);
        return read;
    }

}
