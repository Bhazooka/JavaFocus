package acsse.csc2a.gui;

import java.io.File;
import java.util.ArrayList;

import acsse.csc2a.file.DataReader;
import acsse.csc2a.model.EncryptedMessage;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.NormalMessage;
import acsse.csc2a.model.SOSMessage;
import acsse.csc2a.model.Ship;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * @author Mr D Ogwok
 * @version P05
 */

public class ShipPane extends StackPane{
	
	/* TODO: JavaDoc */
	private VBox layout = null;
	private Ship ship = null;
	
	/* TODO: JavaDoc */
	public ShipPane() {
		// Add Menu Bar
		MenuBar menuBar = new MenuBar();
		Menu menuTitle = new Menu("File");
		menuBar.getMenus().add(menuTitle);
		MenuItem item = new MenuItem("Open");
		menuTitle.getItems().add(item);
		// Action Listener for click
		item.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// Open File Choose
				final FileChooser fc = new FileChooser();
				fc.setTitle("Choose File");
				fc.setInitialDirectory(new File("./data"));
				File file = fc.showOpenDialog(null);
				if(file != null) {
					//Set canvas data after reading from file
					setShip(DataReader.readShipFile(file));		
				}
			}
			
		});
		
		// VBox layout for Menu Bar
		layout = new VBox();
		layout.getChildren().addAll(menuBar);
		getChildren().add(layout);
	}
	
	/* TODO: JavaDoc */
	public void setShip(Ship ship) {
		this.ship = ship;
		// Create Children, component nodes of the Pane
		createChildren();
	}
	
	/* TODO: JavaDoc */
	public void createChildren() {
		
		if(ship != null) {
			
			// Root node of the Scene	
			VBox vbox = new VBox();
			
			// Ship TitledPane
			TitledPane tpShip = createShip();			
			
			ArrayList<SOSMessage> SOSmessages = new ArrayList<SOSMessage>();
			ArrayList<EncryptedMessage> Encryptedmessages = new ArrayList<EncryptedMessage>();
			ArrayList<NormalMessage> Normalmessages = new ArrayList<NormalMessage>();
			for(Message message: ship.getMessages()) {
				if(message instanceof SOSMessage) {
					SOSmessages.add((SOSMessage) message);
				}else if(message instanceof EncryptedMessage) {
					Encryptedmessages.add((EncryptedMessage) message);
				}else if(message instanceof NormalMessage) {
					Normalmessages.add((NormalMessage) message);
				}
			}
			
			// Layout in VBox
			VBox vbox1 = new VBox();
			VBox vbox2 = new VBox();
			VBox vbox3 = new VBox();
						
			// TitledPane - SOS Messages
			TitledPane messagepane = new TitledPane();
			messagepane.setText("SOS Messages");
			GridPane gp = null;
			for(Message message: ship.getMessages()) {
				if(message instanceof SOSMessage) {
					gp = createMessages(message);
					vbox1.getChildren().add(gp);
				}
			}
			// Add to current titledPane
			messagepane.setContent(vbox1);
			
			// TitledPane - Encrypted Messages
			TitledPane messagepane1 = new TitledPane();
			messagepane1.setText("Encrypted Messages");
			for(Message message: ship.getMessages()) {
				if(message instanceof EncryptedMessage) {
					gp = createMessages(message);
					vbox2.getChildren().add(gp);
				}
			}
			// Add to current titledPane
			messagepane1.setContent(vbox2);
			
			// TitledPane - Normal Messages
			TitledPane messagepane2 = new TitledPane();
			messagepane2.setText("Normal Messages");
			for(Message message: ship.getMessages()) {
				if(message instanceof NormalMessage) {
					gp = createMessages(message);
					vbox3.getChildren().add(gp);
				}
			}
			// Add to current titledPane
			messagepane2.setContent(vbox3);
			
			// Creating a ListView for Displaying the TitledPanes as a List.
			ListView<TitledPane> listView = new ListView<>();
			listView.getItems().add(messagepane); // Adding the TitledPane for the SOSMessages to the ListView.
			listView.getItems().add(messagepane1); // Adding the TitledPane for the EncryptedMessages to the ListView.
			listView.getItems().add(messagepane2); // Adding the TitledPane for the NormalMessages to the ListView.
			
			vbox.getChildren().addAll(tpShip, listView);
			
			//Set the root node of the Scene
			getChildren().clear();
			getChildren().addAll(layout, vbox);	
		}
	}
	
	/* TODO: JavaDoc */
	public TitledPane createShip() {
		TitledPane tpShip = new TitledPane();
		tpShip.setText("Ship Information");
		// Use Grid Pane to hold information
		GridPane grid = new GridPane();
		grid.setVgap(2);	// Spacing
		grid.setPadding(new Insets(5,5,5,5));	// Add padding
		
		// Add data to grid
		grid.add(new Label("Ship ID: "), 0, 0);
		grid.add(new TextField(this.ship.getID()), 0, 1);
		grid.add(new Label("Ship Name: "), 1, 0);
		grid.add(new TextField(this.ship.getName()), 1, 1);
		
		// Add child node
		tpShip.setContent(grid);
		
		return tpShip;
	}
	
	/* TODO: JavaDoc */
	public GridPane createMessages(Message message) {
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5,5,5,5));

		// Add Message
		grid.add(new Label("ID: "), 0, 0);
		grid.add(new TextField(message.getID()), 1, 0);
		grid.add(new Label("Type: "), 0, 1);
		grid.add(new TextField(message.getMessage_type().toString()), 1, 1);
		grid.add(new Label("Source: "), 2, 0);
		grid.add(new TextField(message.getPlanet_source().toString()), 3, 0);
		grid.add(new Label("Destination: "), 2, 1);
		grid.add(new TextField(message.getPlanet_destination().toString()), 3, 1);
		grid.add(new Label("Contents: "), 0, 4);
		grid.add(new TextField(message.getContents()), 1, 4, 3, 1);
		
		return grid;
	}
	
}
