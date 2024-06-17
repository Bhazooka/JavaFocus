package csc2b.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application
{
    public static void main(String[] args)
    {
    	launch(args);

    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the ClientPane, set up the Scene and Stage
		primaryStage.setTitle("BUKA Client");
		BUKAClientPane buka = new BUKAClientPane(); //Buka client instance
		Scene scene = new Scene(buka,900,500); //scene takes in a gridpane buka
		primaryStage.setScene(scene); //stage sets scene
		primaryStage.show(); //stage shows scene
		
	}
}
