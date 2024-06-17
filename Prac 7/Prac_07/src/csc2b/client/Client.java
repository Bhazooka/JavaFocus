package csc2b.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author 221005009 BUKANGA, B
 *	Main client
 */
public class Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//create the client pane
		ZEDEMClientPane client = new ZEDEMClientPane();
		
		Scene scene = new Scene(client, 800, 600);
		primaryStage.setTitle("ZEDEM");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
