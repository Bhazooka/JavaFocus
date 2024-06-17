import javafx.scene.canvas.GraphicsContext;

public interface IDrawVisitor {

	//draw functions to be implemented to different objects
	public void draw(Planet planet, GraphicsContext graphics);
	public void draw(SpaceShip ship, GraphicsContext graphics);
	
	
	

}
