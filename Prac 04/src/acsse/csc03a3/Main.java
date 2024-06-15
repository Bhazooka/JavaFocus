package acsse.csc03a3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;  // Note: JavaFX doesn't have a built-in UndoManager. You might need a custom one or use another approach.

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize the UndoManager
        UndoManager undoManager = new UndoManager();

        // Create the TextArea and set its initial text
        TextArea textArea = new TextArea();
        textArea.setPrefSize(200, 100);
        textArea.setText("Initial text");  // Set initial text
        undoManager.addEdit(new AbstractUndoableEdit() { // Record initial state
            public String getPresentationName() { return textArea.getText(); }
        });

        // Layout
        StackPane root = new StackPane();
        root.getChildren().add(textArea);

        // Creating Scene
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("JavaFX TextArea Example");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event to handle text changes and record them
        textArea.textProperty().addListener((observable, oldText, newText) -> {
            undoManager.addEdit(new AbstractUndoableEdit() {
                public String getPresentationName() { return newText; }
            });
        });

        // Undo operation
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                    textArea.setText(undoManager.getUndoPresentationName()); 
                }
                event.consume();
            }
        });

        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                    textArea.setText(undoManager.getRedoPresentationName()); 
                }
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
