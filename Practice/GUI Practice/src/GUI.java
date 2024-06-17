import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class GUI extends Application{	//Always remember to extend the application class
	
	Button button;
	
	/**
	 * How this works:
	 * 1. When we click run, the program will first call the main method, go into application and set everything up
	 * 2. Then call start method and do whats in the start. The code in the start will be our main javaFX code
	 * 
	 */

	//THe main method is the first method that gets called anytime the program is run, so we want to have the launch here
	public static void main(String[] args) {
		//1. 
		launch(args);
	}

	/**
	 * The start method holds all the program for the javaFX
	 * 1. Stage -- is the window (includes the close, minimize, resize...)
	 * 
	 * Before setting the scene, you need to dictate the layout
	 * 2. StackPane or GridPane -- How items are placed on you scene
	 * 		-Here you get children to add to your layout. 	
	 * 				children are stuff like buttons, labels, dropdown menues etc
	 * 		-GridPane is in grid fashion, meaning rows and cols		//I prefer using Grid
	 *		-Stackpane is from back to front		
	 * 
	 * 3. Scene -- The content inside the stage is. 
	 * 	The scene holds the layout, and the size, 
	 * 		-Note that the size of the window is dictated by the size of the scene
	 * 
	 * 
	 * 4.	Now all you do is Set the scene to the Stage(Link the scene to stage) and show the stage
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("New 2");
		
		button = new Button();
		button.setText("Click");
		
		//2.
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		/*If theres more than one child, then you can use the addAll function
		 * grid.getChildren().addAll(label, textbox,button);
		 */
		
		//you can also add children like this
/*		grid.add(label, 0, 0);
		grid.add(textbox, 0, 1);
		grid.add(button, 0, 2);
		*/
		
		//3.
		Scene scene = new Scene(layout,300,250);	// Scene(layout, width of window, height of window)
		
		//4.
		primaryStage.setScene(scene);	//This line says, use the scene created for the program. It is essentially linking the scene to the stage we made
		primaryStage.show();	//Then all we do is show the stage, with the scene inside
	}

}


























