import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawVisitor implements  IDrawVisitor{

    int NUM_X = 20;
    int NUM_Y = 20;

    @Override
    //creating the planet and coloring it accordingly
    public void draw(Planet planet, GraphicsContext graphics) {
        graphics.setFill(planet.getColor());
        graphics.fillOval(planet.getPlanetPosition().getX(), planet.getPlanetPosition().getY(), planet.getRadius(), planet.getRadius());
    }

    @Override
    //creating the ship and coloring it accordingly
    public void draw(SpaceShip spaceship, GraphicsContext graphics) {
        graphics.setFill(Color.GREEN);
        graphics.fillRect(spaceship.getShipPosition().getX(), spaceship.getShipPosition().getY(), 100, 30);

        graphics.setFill(Color.WHITE);
        graphics.fillText(spaceship.getName(), spaceship.getShipPosition().getX() + NUM_X, spaceship.getShipPosition().getY() + NUM_Y);
    }
}
