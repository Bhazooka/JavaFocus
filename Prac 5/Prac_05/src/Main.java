import GUI.GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author B, Bukanga 221005009
 *	Main class to launch the application
 *	Main class calls the main GUI method to set up the main GUI
 *	The leecher and seeder classes are called by the GUI class
 *	based on user selection
 */
public class Main extends Application
{
	
	public static void main(String [] args)
	{
		Application.launch(args);
	}
	
	
	/**
	 * Start function to set up the UserInterface
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			GUI build = new GUI(primaryStage);
			Scene scene = new Scene(build); 
			
			primaryStage.setTitle("UDP P2P");
			primaryStage.setHeight(500);
			primaryStage.setWidth(600);
			primaryStage.setScene(scene); 
			
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
