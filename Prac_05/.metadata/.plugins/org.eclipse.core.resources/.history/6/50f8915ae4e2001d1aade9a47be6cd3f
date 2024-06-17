package  csc2a.acsse.gui;

import java.io.File;

import javafx.scene.control.MenuBar;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import acsse.csc2a.model.*;
import acsse.csc2a.file.*;


public class ShipPane extends StackPane{

	MenuBar menuOpen = new MenuBar();
    Menu File = new Menu("File");
	FileChooser choose = new FileChooser();
	
	MenuItem menuitems = new MenuItem("Open");
	Ship ship = null;
	VBox vBox = new VBox();
	

	public ShipPane(Stage primaryStage) {
		super();
		menuOpen.getMenus().add(File);
		File.getItems().add(menuitems);
		this.getChildren().add(0,menuOpen);
		StackPane.setAlignment(menuOpen,Pos.TOP_LEFT);
		
		menuitems.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event)
			{
				choose.setTitle("Select File");
				choose.setInitialFileName("data");
				File file = choose.showOpenDialog(primaryStage);
				
				
				
				
				
				if(file !=null)
				{
					ship = DataReader.readShipFile(file);
					CreateShipPane(ship);				
				}
			}
		});
	}


	
	private VBox ListView(Ship ship)
	{
		//adding the vertical boxes to enclose the type of messages onto our plane
		VBox box = new VBox();
		VBox vb1 = new VBox();
		VBox vb2 = new VBox();
		VBox vb3 = new VBox();
		
		TitledPane heading1 = new TitledPane();
		heading1.setText("SOS Message");
		
		TitledPane heading2 = new TitledPane();
		heading2.setText("Encrypted Message");
		
		TitledPane heading3 = new TitledPane();
		heading3.setText("Normal Message");
		
		
		ListView<TitledPane> list = new ListView<>();
		int increment = 0;
		
		for(Message msg : ship.getMessages())
		{
			
			String string = "";
			GridPane grid = new GridPane();
			
			if(msg instanceof SOSMessage)
			{
				SOSMessage message = (SOSMessage) msg;
				grid.add(new Label("ID:"), 0, increment);
				string = message.getID();
				
				grid.add(new TextField(string), 1, increment);
				grid.add(new Label("Source:"), 2, increment);
				string = message.getPlanet_source().toString();
				grid.add(new TextField(string), 3, increment);
				
				grid.add(new Label("Type:"), 0, increment + 1);
				grid.add(new TextField("SOSMessage"), 1, increment + 1);
				grid.add(new Label("Destination:"), 2, increment + 1);
				string = message.getPlanet_destination().toString();
				
				grid.add(new TextField(string), 3, increment + 1);
				grid.add(new Label("Contents:"), 0, increment + 2);
				string = message.getContents();
				
				TextField t = new TextField(string);
				t.setPrefWidth(300);
				grid.add(t, 1, increment + 2);
				vb1.getChildren().add(grid);
				
			}
			
			else if(msg instanceof EncryptedMessage)
			{
				EncryptedMessage message = (EncryptedMessage) msg;
				grid.add(new Label("ID:"), 0, increment);
				string = message.getID();
				
				grid.add(new TextField(string), 1, increment);
				grid.add(new Label("Source:"), 2, increment);
				string = message.getPlanet_source().toString();
				
				grid.add(new TextField(string), 3, increment);
				
				grid.add(new Label("Type:"), 0, increment + 1);
				grid.add(new TextField("EncryptedMessage"), 1, increment + 1);
				grid.add(new Label("Destination:"), 2, increment + 1);
				string = message.getPlanet_destination().toString();
				
				grid.add(new TextField(string), 3, increment + 1);
				
				grid.add(new Label("Contents:"), 0, increment + 2);
				string = message.getContents();
				TextField t = new TextField(string);
				t.setPrefWidth(300);
				grid.add(t, 1, increment + 2);
				vb2.getChildren().add(grid);
			}
			
			else if(msg instanceof NormalMessage)
			{
				NormalMessage message = (NormalMessage) msg;
				grid.add(new Label("ID:"), 0, increment);
				string = message.getID();
				grid.add(new TextField(string), 1, increment);
				grid.add(new Label("Source:"), 2, increment);
				string = message.getPlanet_source().toString();
				grid.add(new TextField(string), 3, increment);
				


				grid.add(new Label("Type:"), 0, increment + 1);
				grid.add(new TextField("NormalMessage"), 1, increment + 1);
				grid.add(new Label("Destination:"), 2, increment + 1);
				string = message.getPlanet_destination().toString();
				grid.add(new TextField(string), 3, increment + 1);
				
				grid.add(new Label("Contents:"), 0, increment + 2);
				string = message.getContents();
				TextField t = new TextField(string);
				t.setPrefWidth(300);
				grid.add(t, 1, increment + 2);
				vb3.getChildren().add(grid);
			}
			

			increment++;
		}
		
		
		
		heading1.setContent(vb1);
		heading2.setContent(vb2);
		heading3.setContent(vb3);
		list.getItems().addAll(heading1,heading2,heading3);
		box.getChildren().add(list);
		return box;
		
	}

		//Method to create the ship pane
		private void CreateShipPane(Ship ship)
		{
			TitledPane headingPane = new TitledPane();
			headingPane.setText("Ship Information");
			GridPane grid = new GridPane();
			
			grid.add(new Label("Ship ID:"), 0, 0);
			String s= ship.getID();
			TextField txt = new TextField();
			txt.setText(s);
			grid.add(txt, 0, 1);
			
			grid.add(new Label("Ship Name:"), 1, 0);
			s = ship.getName();
			TextField txt1 = new TextField();
			txt1.setText(s);
			grid.add(txt1, 1, 1);
			
			headingPane.setContent(grid);
			vBox.getChildren().add(headingPane);
			
			//Adding message
			vBox.getChildren().add(ListView(ship));
			
			this.getChildren().add(vBox);
			StackPane.setAlignment(vBox,Pos.TOP_LEFT);
		}

/* 
	public Ship getShip() {
		return ship;
	}
	*/
}