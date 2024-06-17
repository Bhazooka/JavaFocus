import java.io.File;
import java.util.ArrayList;

import acsse.csc2a.model.Ship;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import acsse.csc2a.model.*;

public class MyPane extends StackPane{
	
	private ArrayList<SpaceShip> shipList = new ArrayList<>();
	private ArrayList<Planet> planetList =  new ArrayList<>();
	
	private MenuBar menuBar = null; 
	private BorderPane borderPane = null;
	private MyCanvas myCanvas; 
	
	

	//Constructor
    public MyPane(){
    	
        final Menu openMenu = new Menu("File");
        final MenuItem PlanetMenuItem = new MenuItem("Open Planets");
        final MenuItem SpaceShipsMenuItem = new MenuItem("Open Space Ships");
        

        //Creating Menu bar and arranging its positioning in the window
        menuBar = new MenuBar(openMenu);
        setAlignment(menuBar, Pos.TOP_CENTER);

        this.myCanvas = new MyCanvas();

        openMenu.getItems().add(PlanetMenuItem);
        openMenu.getItems().add(SpaceShipsMenuItem);
		//menuBar.getMenus().add(fileMenu);
		//menuBar.getMenus().add(fileMenu2);
		//fileMenu.getItems().add(selectPlanetMenu);
		//fileMenu.getItems().add(selectSpaceShipMenu);


        //Select ship option
		SpaceShipsMenuItem.setOnAction(e-> {
            this.shipList = FileIO.readSpaceShip(new File("data/spaceships.txt"));
            this.myCanvas.repaintCanvas(this.shipList, this.planetList);
        });

		//Select ship Option
        PlanetMenuItem.setOnAction(e-> {
            this.planetList = FileIO.readPlanet(new File("data/planets.txt"));
            this.myCanvas.repaintCanvas(this.shipList, this.planetList);
        });

        //Get the children nodes from the
        this.getChildren().addAll(this.myCanvas, menuBar);
        this.setWidth(this.myCanvas.getWidth());
        this.setHeight(menuBar.getHeight() + 50);
    }
	
}

