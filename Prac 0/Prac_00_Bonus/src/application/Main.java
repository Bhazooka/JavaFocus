package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

/**
 * @author B,Bukanga 221005009
 * Main Method
 *	
 */
public class Main extends Application {

    private TextArea textArea;
    private Text text = new Text();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Port Loop");	//this sounds like pencil in afrika

        //TextArea to display output, doesnt work with text fields, textfields on display 1 line
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefSize(200, 200);

        //Button execute the Scan function
        Button exeButton = new Button("Scan Ports");
        exeButton.setPrefSize(150, 100);
        exeButton.setLayoutX(600);
        exeButton.setLayoutY(0);
        
        //Lambda function, takes the value e and inputs it into the
        exeButton.setOnAction(e -> ScanPort());
        
        //Create VBox to hold the TextArea and Button
        VBox root = new VBox(30);
        root.getChildren().addAll(textArea, exeButton);
        root.setMinSize(350, 450);
        
        //Create scene and set on stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @method ScanPort
     * 	Used to loop through the ports and display to the textField
     */
    private void ScanPort() {
        StringBuilder output = new StringBuilder();	//were using stringbuilder to create an object string with no characters in it, where we can append onto the empty value

        for (int port = 1; port <= 65535; port+=3) {
            try (Socket s = new Socket("localhost", port)) {
                output.append("Program connected to localhost: ").append(s.getPort()).append("\n");
                output.append("Local Port of the connection: ").append(s.getLocalPort()).append("\n");
                output.append("Remote Host of the connection: ").append(s.getPort()).append("\n");
            } catch (IOException e) {
                output.append("Couldn't connect to port ").append(port).append("\n");
            }
        }

        try {
            InetAddress IP = InetAddress.getLocalHost();
            output.append("The computer IP Address: ").append(IP.getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        //Update the TextArea with the output
        textArea.setText(output.toString());
    }

    /**
     * @param main 
     * 	Launches the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
