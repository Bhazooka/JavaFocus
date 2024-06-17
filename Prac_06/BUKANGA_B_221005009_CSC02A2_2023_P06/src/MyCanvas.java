import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;

public class MyCanvas extends Canvas{

	
	//creating the canvas
	private GraphicsContext graphics;
	DrawVisitor drawObject = new DrawVisitor();

	//setting the canvas dimensions
	public MyCanvas() {
		this.setHeight(800);
        this.setWidth(1500);
        this.graphics = this.getGraphicsContext2D();
        //ScrollBar scrollwheel = new ScrollBar();

        //setting the colours
        graphics.setFill(Color.LIGHTGRAY);
        graphics.fillRect(0,0, this.getWidth(), this.getHeight());
	}
	
	//repaint canvas method that handles cases for both the ship objects and planet objects
	public void repaintCanvas(ArrayList<SpaceShip> shipList, ArrayList<Planet> planetList)
	{
		for(Planet planet: planetList)
		{
			planet.accept(drawObject, graphics);
		}
		
		for(SpaceShip ship: shipList)
		{
			ship.accept(drawObject, graphics);
		}
		
	}
	
}
