import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author barbu
 *
 */
public class Main extends Application{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			MyPane root = new MyPane();
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("The title");
			primaryStage.show();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
