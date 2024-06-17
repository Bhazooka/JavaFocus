package csc2b.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
*@author M Bowditch 2023
*/
public class BUKAClient extends Application
{
    public static void main(String[] args)
    {
    	//launch the JavaFX Application
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the ClientPane, set up the Scene and Stage
			BUKAClientPane root  = new BUKAClientPane();
			Scene scene = new Scene(root,800,600);
			primaryStage.setTitle("BUKA client");
			primaryStage.setScene(scene);
			primaryStage.show();
	}
}
