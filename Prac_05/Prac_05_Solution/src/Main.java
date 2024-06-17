import acsse.csc2a.gui.ShipPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Mr D Ogwok
 * @version P05
 */
public class Main extends Application{	
	
	// ShipPane Scene to be placed on Stage
	private ShipPane pane = null;
	
	/**
	 * Main method
	 * @param args
	 * 		Command Line arguments
	 */
	public static void main(String[] args) {
		// launch the JavaFX Application
		launch(args);
	}

	/* TODO: JavaDoc */
	@Override
	public void start(Stage mainStage) throws Exception {
		mainStage.setTitle("Ship Transportation Information");
		
		// Create the ShipPane
		pane = new ShipPane();
		
		//Set the Scene
		Scene scene = new Scene(pane);
		
		mainStage.setWidth(500);
		mainStage.setHeight(500);
		mainStage.setScene(scene);
		
		// Open the curtains
		mainStage.show();
	}

}
