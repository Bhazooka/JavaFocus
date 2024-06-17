import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author Awe
 * @version P04
 *
 */
public class GUI extends Application{	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Client client = new Client();
		Scene scene = new Scene(client,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
