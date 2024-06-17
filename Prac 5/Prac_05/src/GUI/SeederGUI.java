package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

import UDP.server.Server;

/**
 * @author B, Bukanga 221005009
 *	Seeder GUI Set UP class
 *	Contains all the file handling and button OnCLick events for the seeder side
 */
public class SeederGUI extends Application {
    private Server udpServer; //You should define the Server class as per your application's requirements
    private int fileID;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Seeder Mode");

    	// Elements
    	Label seederLabel = new Label("Seeder Mode");
    	Label portLabel = new Label("Port:");
    	TextField portField = new TextField("135");
    	Button bindButton = new Button("Bind");
    	Button addFilesButton = new Button("Add Files");
    	Button quitButton = new Button("Quit");
    	TextArea listArea = new TextArea("List:\n");
    	TextArea statusArea = new TextArea("Status Area:\n");

    	//Layout
    	HBox firstHBox = new HBox(10, portLabel, portField, bindButton);
    	VBox vBox = new VBox(10, seederLabel, addFilesButton, firstHBox, listArea, statusArea, quitButton
    	);
    	vBox.setPadding(new Insets(10));
    	vBox.setAlignment(Pos.CENTER);

    	//Create a scene and set it on the stage
    	Scene scene = new Scene(vBox, 500, 400);
    	primaryStage.setScene(scene);

    	primaryStage.show();

    	//Set up action listeners for buttons here
    	setUpActionListeners(portField, bindButton, addFilesButton, listArea, statusArea, quitButton);

    }

    //Define your action listeners here
    private void setUpActionListeners(TextField portField, Button bindButton, Button addFilesButton, TextArea listArea, TextArea statusArea, Button quitButton) {
		bindButton.setOnAction((event) ->
		{
			if(!portField.getText().isBlank())
			{
			/**The trim function takes the text that I put in the textField and removes all white spaces*/				
				final int PORT = Integer.parseInt(portField.getText().trim());
				
				//create upd server and starts the server to the specified port
				udpServer = new Server(PORT); 
				udpServer.setStatusArea(statusArea); 
				Thread t = new Thread(udpServer); 
				t.start();
				
				//I learnt something new here. When the UI and the Connection are run in the same thread
				//the ui will be not responsive. 
			}else {
				alert(AlertType.ERROR, "Port is Empty.\nPlease Input the port value to bind to!"); 
			} 
			
		});

		addFilesButton.setOnAction((event) ->
		{
			//read the list file for the last file id wrote
			final FileChooser fChooser = new FileChooser(); 
			/*This is a dialog that allows the user to 
			 select files from their system.*/
			fChooser.setTitle("Files To Add"); 
			
			//File fileSelected = fChooser.showOpenDialog(null);: This line displays the file chooser dialog and waits for the user to select a file. 
			//The selected file is stored in the fileSelected variable.
			File fileSelected = fChooser.showOpenDialog(null);
			
			
			if(fileSelected != null)			//if fileSelected has a value (is not null)
			{
				try {
					/*Files.copy is a method in Java that copies all the bytes from one file to another. It takes two arguments separated by a comma, 
					 where the first one specifies the source file and the second one specifies the destination file.*/
					Files.copy(fileSelected.toPath(), new FileOutputStream(new File("data/seederFiles/" + fileSelected.getName())));
				} catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
					System.err.println("UserInterfacePane, seederUI, addFilesButton, copy selected file to project directory");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.err.println("UserInterfacePane, seederUI, addFilesButton, copy selected file to project directory");
				} 
			}
			
			//read the last value of the file id
			try(Scanner fScanner = new Scanner(new File("data/seederFiles/FileID.txt")))
			{
				if(fScanner.hasNext())
				{
					/**
					The code then reads the last value of the file ID from a file named "FileID.txt" in the "data/seederFiles/" directory using a Scanner. 
					It parses the value as an integer and stores it in the fileID variable.
					
					The trim removes white spaces from strings
					IDK WHAT THIS TRIM DOES FIND OUT LATER
					
					Explaining the trim
					fScanner.next(): The next() method of the Scanner reads the next token (word) from the input file. In this context, it's reading the next word or string token from the "FileID.txt" file.
					trim(): The trim() method is called on the string read by fScanner. This method removes any leading whitespace (spaces, tabs, etc.) and trailing whitespace from the string.
					The purpose of using trim() in this context is to ensure that the string obtained from the file, which might have leading or trailing spaces due to formatting or unintentional input, does not interfere with the conversion to an integer.
					For example, if the content of "FileID.txt" was " 123 " (with spaces), using trim() would result in strFID having the value "123", which can be easily converted to an integer using Integer.valueOf(). Without trim(), the presence of spaces could cause conversion issues.
					*/
					String strFID = fScanner.next().trim(); 
					fileID = Integer.valueOf(strFID); 
				}
				
			} catch (FileNotFoundException e) 
			{
				System.err.println(e.getMessage());
			}
			
			//add the file to the list file, and the file ID
			/**
			The code then adds information about the selected file to a list file named "List.txt" in the "data/seederFiles/" directory.
			It creates a File object named listFile representing the list file.
			Inside a try block, it creates a FileWriter (fWriter) to write to the list file in append mode
			It writes a line to the list file containing the fileID, a space, and the name of the selected file. 
			It then flushes the writer and increments the fileID.
			After updating the list file, it writes the new fileID back to the "FileID.txt" file.
			*/
			File listFile = new File("data/seederFiles/List.txt");
			/** the file writer takes 2 paremeters, the first is the place where it will write to, 
			Append Mode Parameter: The second parameter is a boolean value (true in this case) 
			that determines whether the FileWriter should append to an existing file (true) or overwrite the file if it already exists (false). 
			When set to true, if the file already exists, new content will be appended to the end of the file, in addition to current content.*/
			try(FileWriter fWriter = new FileWriter(new File("data/seederFiles/List.txt"), true))
			{
				fWriter.write(fileID +" " + fileSelected.getName() + "\n");
				fWriter.flush(); 
				fileID++; 
				//write the last value of the file id
				PrintWriter fIdWriter = new PrintWriter(new File("data/seederFiles/FileID.txt")); 
				fIdWriter.print(fileID); 
				fIdWriter.flush(); 
				fIdWriter.close(); 
			} 
			catch (IOException e) 
			{
				System.err.println(e.getMessage());
			}
			
			//display the list to the listArea
			try(Scanner fScanner = new Scanner(new File("data/seederFiles/List.txt"))) 
			{
				listArea.clear();
				listArea.appendText("List:\n"); 
				while(fScanner.hasNextLine())
				{
					listArea.appendText(fScanner.nextLine() +"\n"); 
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.err.println("Error occured while scanning file list");
			}
			
		});

		quitButton.setOnAction((event) ->
		{
			if(this.udpServer != null)
			{
				udpServer.stopServer(false); 
			}
		});
    }
    
    private void alert(AlertType aT, String message)
    {
    	Alert alert = new Alert(aT); 
    	alert.setContentText(message);
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.showAndWait();
    }
}
