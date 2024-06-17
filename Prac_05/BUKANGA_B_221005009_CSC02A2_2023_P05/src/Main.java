import acsse.csc2a.model.Ship;
import csc2a.acsse.gui.ShipPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		ShipPane pane = null;
		
		
		try {
			pane = new ShipPane(primaryStage);
			
			
			StackPane root  = new StackPane();
			
			Scene scene = new Scene(pane, 600, 400);
			
			primaryStage.setTitle("Ships Stuff");
			primaryStage.setWidth(400);
			primaryStage.setHeight(600);
			primaryStage.setScene(scene);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
