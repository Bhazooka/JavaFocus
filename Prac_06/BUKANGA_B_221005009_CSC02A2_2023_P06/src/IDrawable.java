import javafx.scene.canvas.GraphicsContext;

public interface IDrawable {
	
	//Accept method to be implemented and overridden
	public void accept(IDrawVisitor visitor, GraphicsContext graphics);

}
