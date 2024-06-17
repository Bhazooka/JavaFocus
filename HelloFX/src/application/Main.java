package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;




public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Adding images, texts, shapes
		 
		Group root = new Group(); //create a root node to arrange all components we have
		Scene scene = new Scene(root,600,600,Color.LIGHTBLUE);	//add the root node to the scene
		Stage stage = new Stage();
		
		
		Text text = new Text();
		text.setText("DOPE");
		text.setY(50);
		text.setX(50);
		text.setFont(Font.font("Verdena", 50));
		text.setFill(Color.BLACK);
		
		Line line = new Line();
		line.setStartX(200);
		line.setStartY(200);
		line.setEndX(500);
		line.setEndY(500);
		line.setStrokeWidth(5);
		line.setStroke(Color.RED);
		line.setOpacity(0.5);
		line.setRotate(45);
		
		Rectangle rectangle = new Rectangle();
		rectangle.setWidth(100);
		rectangle.setHeight(100);
		rectangle.setX(100);
		rectangle.setY(100);
		rectangle.setFill(Color.BLACK);
		rectangle.setStrokeWidth(5);
		rectangle.setStroke(Color.BLACK);
		
		//triagnle is a type of Polygon. Its made from the polygon. Its an insytance of polygon
		Polygon triangle = new Polygon();
		triangle.setFill(Color.YELLOW);
		triangle.getPoints().setAll(	//takes in double values, its set on an x and y plane, where everypoint needs an x and y
				200.0, 200.0,
				300.0, 300.0,
				200.0, 300.0);
		triangle.setFill(Color.YELLOW);
		
		
		Circle circle = new Circle();
		circle.setCenterX(350);
		circle.setCenterY(350);
		circle.setRadius(50);
		circle.setFill(Color.ORANGE);
		
		//adding an image to the stage
		Image image = new Image("flamelogo.png");
		ImageView imageView = new ImageView(image);
		imageView.setX(100);
		imageView.setY(100);
		imageView.setScaleX(0.15);
		imageView.setScaleY(0.15);
		
		
		//add what you want to root node
		root.getChildren().add(text);
		root.getChildren().add(line);
		root.getChildren().add(rectangle);
		root.getChildren().add(triangle);
		root.getChildren().add(circle);
		root.getChildren().add(imageView);
		
		
		
		/*
		 * Setting up the stage/window
		 * 
		Image icon = new Image("flamelogo.png"); //this is a place holder to hold the image icon that will be used in your window top left corner
		stage.getIcons().add(icon);	//setting the icon
		stage.setTitle("Stage Demo Program Baraka");
		
		stage.setWidth(500);
		stage.setHeight(500);
		stage.setResizable(false);
		
		//set the position in which the window/stage will appear on the screen
		//by default the window will appear in the middle
		//stage.setX(50);
		//stage.setY(50);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("USE Q TO ESCAPE");
		stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
		*/
		
		//adding buttons
		
		
		stage.setScene(scene);	//add the scene to the stage
		stage.show(); //Shows the stage/ window
		
	}
}
