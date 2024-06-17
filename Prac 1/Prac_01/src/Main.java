import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @author barbu
 *	Main Function
 */
public class Main extends Application {

    /**
     * @param args
     * 	Launch function
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start Function
     */
    @Override
    public void start(Stage primaryStage) {
        // Create and show the GUI
        GUI gui = new GUI();
        try {
			gui.show(primaryStage);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}