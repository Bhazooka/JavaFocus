package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.file.Files;
import java.util.Scanner;
import UDP.client.Client;
import UDP.server.Server;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author B,Bukanga 221005009
 *	Main User interface to select leecher or seeder
 */
public class GUI extends GridPane
{
	private Stage primaryStage = null;
	private String mode = null;
	private boolean seederMode = false; 
	private boolean leecherMode = false;

	

	/** Main GUI Function
	 * Calls the modeSelector
	 * @param primaryStage
	 */
	public GUI(Stage primaryStage)
	{
		this.primaryStage = primaryStage; 
		modeSelector();
	}
	

	private void modeSelector()
	{
		GridPane selectorPane = new GridPane(); 
		Scene scene = new Scene(selectorPane, 300, 300); 
		Stage stage = new Stage(); 
		stage.setTitle("Mode Selection");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		
		//The mode list 
		ComboBox<String> cBox = new ComboBox<String>();
		ObservableList<String> options = FXCollections.observableArrayList("Seeder", "Leecher"); 
		cBox.setItems(options);
		
		cBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals("Seeder")){
					mode = new String(newValue);
					seederMode = true; 
					leecherMode = false; 
				}
				else if(newValue.equals("Leecher")) {
					mode = new String(newValue);
					leecherMode = true; 
					seederMode = false; 
				}
				else {
					leecherMode = false; 
					seederMode = false; 
					
				}
			}
		});
		
		
		Text text1 = new Text("Select Leecher or Seeder");
		Label modeLabel = new Label("Mode:"); 
		
		Button nextButton = new Button("Next"); 
		
		selectorPane.add(text1, 0, 0); 
		selectorPane.add(new HBox(12, modeLabel, cBox) ,0, 2); 
		selectorPane.add(nextButton, 0, 3); 
		selectorPane.setPadding(new Insets(10));
		selectorPane.setVgap(10);
		selectorPane.setAlignment(Pos.CENTER);
		
		nextButton.setOnAction((event) -> {
			
			if( ((leecherMode == false) && (seederMode==true)) || ((leecherMode == true) && (seederMode ==false)) )
			{	
				primaryStage.show(); 
				stage.close(); 
				guiSetup();
			}
		});
	}
	
	/**
	 * Method calls the GUI class based on the selected class
	 * The GUI Classes contains the setup for the respective mode
	 */
	private void guiSetup()
	{
		if(seederMode == true){
			SeederGUI seederGUI = new SeederGUI();
			seederGUI.start(primaryStage);
			
		}
		else if(leecherMode == true) {
			LeecherGUI leecherGUI = new LeecherGUI();
			leecherGUI.start(primaryStage);
		}
	
	}

	
}
