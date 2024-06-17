
import acsse.csc2a.model.Ship;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class SpaceShip extends Ship implements IDrawable{

	public Point2D shipPosition;
	
	//Ship constructor
	public SpaceShip(String iD, String name, double x, double y) {
		super(iD, name);
		this.shipPosition = new Point2D(x,y);
	}

	public Point2D getShipPosition() {
		return this.shipPosition;
	}


	//accept function to override implemented from IDrawable
	@Override
    public void accept(IDrawVisitor visitor, GraphicsContext graphics) {
        visitor.draw(this, graphics);
    }
	
}
