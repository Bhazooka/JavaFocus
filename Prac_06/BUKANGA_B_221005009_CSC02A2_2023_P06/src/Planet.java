
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Planet implements IDrawable{
	
	public final String name;
	public final Point2D planetPosition;
	public final Color color;
	public final int radius;
	
	//Planet Constructor
	public Planet(String name, Color color, int radius, double x, double y) {
		super();
		this.name = name;
		this.color = color;
		this.radius = radius;
		//The position of the object in space (2D plane)
		this.planetPosition = new Point2D(x,y);
	}


	//Getters
	public String getName() {
		return name;
	}

	public Point2D getPlanetPosition() {
		return planetPosition;
	}

	public Color getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}
	
	//accept function to override implemented from IDrawable
    @Override
    public void accept(IDrawVisitor visitor, GraphicsContext graphics) {
        visitor.draw(this, graphics);
    }
}
