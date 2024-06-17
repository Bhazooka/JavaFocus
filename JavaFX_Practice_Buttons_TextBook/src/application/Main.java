package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		//Create a scene and place the button in the scene
		Button btnOK = new Button("OK");
		Scene scene = new Scene(btnOK, 200, 250);
		
		primaryStage.setTitle("My JavaFx"); //set the stage title
		primaryStage.setScene(scene); //Place the scene in the stage
		primaryStage.show(); // Display the stage
		
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
