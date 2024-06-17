package csc2b.client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application
{
    public static void main(String[] args)
    {
    	Application.launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the ClientPane, set up the Scene and Stage
		BUKAClientPane bukaClient = new BUKAClientPane();
		
		Scene scene = new Scene(bukaClient,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
