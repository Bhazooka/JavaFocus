import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI2 extends Application{

	
	public static void main(String args[]) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//1.create Grid
		GridPane grid = new GridPane();
		//2.Create Children 		
		Label label = new Label("Whats your name");
		TextField textbox = new TextField("Enter Name");
		Button button = new Button("Submit Name");
		//3.Add children to grid
//		grid.add(label, 0, 0);
//		grid.add(textbox, 0, 1);
//		grid.add(button, 0, 2);
		
		grid.getChildren().addAll(label, textbox,button);
		
		//4.Create scene		4.2 and add grid to scene parameteres and set the size in the parameters
		Scene scene = new Scene(grid, 600, 500);
		
		//5.Add scene to stage, by .setScene function
		primaryStage.setScene(scene);
		//6.Show the stage
		primaryStage.show();
		
		
	}


}
