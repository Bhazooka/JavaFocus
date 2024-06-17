package csc2b.client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Client extends Application
{
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args)
    {
      launch(args);
    }

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene scne = new Scene(new BUKAClientPane(), 800, 800);
		primaryStage.setScene(scne);
		primaryStage.show();
				
	}
}
