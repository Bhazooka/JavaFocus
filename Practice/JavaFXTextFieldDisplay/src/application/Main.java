package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTextFieldDisplay extends Application {

    private TextField textField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX TextField Display");

        // Create a TextField to display the output
        textField = new TextField();
        textField.setPrefWidth(200);

        // Create a button to trigger the action
        Button displayButton = new Button("Display Output");
        displayButton.setOnAction(e -> displayOutput());

        // Create a VBox to hold the TextField and Button
        VBox root = new VBox(10);
        root.getChildren().addAll(textField, displayButton);
        root.setMinSize(300, 150);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayOutput() {
        // This is where you generate the output
        String output = "Hello, JavaFX!"; // Replace this with your desired output

        // Update the TextField with the output
        textField.setText(output);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
