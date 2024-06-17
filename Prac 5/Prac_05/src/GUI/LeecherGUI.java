package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import UDP.client.Client;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author B, Bukanga 221005009
 *	Leecher GUI Set UP class
 *	Contains all the file handling and button OnCLick events for the leecher side
 */
public class LeecherGUI extends Application {
    private Client client; // You should define the Client class as per your application's requirements
    private boolean isConnected = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Leecher Mode");

    	Label leecherLabel = new Label("Leecher Mode");
    	Label conToServerLabel = new Label("Connect To Seeder");
    	Label addressLabel = new Label("Address:");
    	TextField addressField = new TextField("localhost");
    	Label portLabel = new Label("Port:");
    	TextField portField = new TextField("135");
    	Button connectButton = new Button("Connect");
    	Label requestLabel = new Label("Request");
    	TextField getFileField = new TextField();
    	getFileField.setPromptText("Enter File Name");
    	Button getListButton = new Button("Get List");
    	Button getFileButton = new Button("Get File");
    	TextArea listArea = new TextArea("List:\n");
    	TextArea statusArea = new TextArea("Status Area:\n");

    	//vertical layout
    	VBox vBox = new VBox(20,
    	    leecherLabel,
    	    conToServerLabel,
    	    new HBox(10, addressLabel, addressField, portLabel, portField, connectButton),
    	    requestLabel,
    	    new HBox(10, getListButton, getFileField, getFileButton),
    	    listArea,
    	    statusArea
    	);

    	listArea.setEditable(false);
    	listArea.setPrefHeight(150);
    	statusArea.setEditable(false);
    	statusArea.setPrefHeight(100);

    	vBox.setAlignment(Pos.CENTER);
    	vBox.setPadding(new Insets(20));

    	// Create a scene and set it on the stage
    	Scene scene = new Scene(vBox, 500, 400);
    	primaryStage.setScene(scene);

    	primaryStage.show();

    	//Set up action listeners for buttons here
    	setUpActionListeners(addressField, portField, connectButton, getListButton, getFileField, getFileButton, listArea, statusArea);
    }



    /**
     * 	The function gets the following, which is information for
     * 	Datagram packet to reach its destination
     * 
     * @param addressField
     * @param portField
     * @param connectButton	Connection used
     * @param getListButton
     * @param getFileField
     * @param getFileButton
     * @param listArea
     * @param statusArea
     */
    private void setUpActionListeners(TextField addressField, TextField portField, Button connectButton,
                                      Button getListButton, TextField getFileField, Button getFileButton,
                                      TextArea listArea, TextArea statusArea) {
    	connectButton.setOnAction((event) -> 
		{
			if(!addressField.getText().isBlank() || !portField.getText().isBlank())
			{
				//The trim function takes the text that I put in the textField and removes all white spaces
				client = new Client(addressField.getText().trim(), Integer.parseInt(portField.getText().trim()));
				
				//send the connect command
				client.sendCommands("Helo"); 
				//get the confirmation command
				String response = client.getResponse(); 
				statusArea.appendText("--" + response + "\n"); 
				if(response.equals("Helo"))
				{
					isConnected  = true; 
				}else {
					isConnected = false; 
				}
			}
			else
			{
				alert(AlertType.ERROR, "Address or Port is empty or contain white spaces");
			}
			
		});

    	
		getListButton.setOnAction((event) -> 
		{
			if(isConnected == true)
			{
				//send the command
				client.sendCommands("List"); 
				//get the list and append it to the list area
				String response [] = client.getResponse().split("#");
				
				statusArea.appendText("--List\n"); 
				for(String l : response)
				{
					listArea.appendText(l + "\n");
				}
			}
			else {
				System.err.println("Not connected");
			}
		});

		getFileButton.setOnAction((event) -> 
		{
			if (isConnected == true) 
			{
				//send the File command
				client.sendCommands("File#" + getFileField.getText().trim()); 
				
				//get the file name
				String response[] = client.getResponse().split("#");
				String fileName = response[0]; 
				String fileSize = response[1]; 
				String strBytes = response[2]; 
				
				statusArea.appendText("--File Name:" +fileName+"\tFileSize: "+ fileSize +"\n");
				byte [] data = strBytes.getBytes(); 
				
				try 
				{
					FileOutputStream fos = new FileOutputStream(new File("data/leecherFiles/"+fileName));
					fos.write(data);
					fos.flush(); 
					fos.close(); 
				} catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				} 
			}
			else {
				 statusArea.appendText("Not connected.\n");
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


