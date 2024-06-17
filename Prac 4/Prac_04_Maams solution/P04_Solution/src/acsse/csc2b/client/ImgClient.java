package acsse.csc2b.client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import acsse.csc2b.client.ImgClientPane;

public class ImgClient extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ImgClientPane root = new ImgClientPane(primaryStage);
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Image Client");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
