import java.util.Scanner;

import acsse.csc2a.Turtle;

/**
 * Entry point for P01
 * @author Mr. A Maganlal
 * @version P01
 */
public class Main
{
	/**
	 * main entry point 
	 * @param args - unused
	 * Test with input from user
	 * +FFF-FFF-FFF-FFF
	 * FFFFF+FFFFF+FFFFF+FFFF
	 * F+F-F+F 
	 */
	public static void main(String[] args)
	{
		// Get input from the user, not very user friendly
		Scanner sysin = new Scanner(System.in);
		String line = sysin.nextLine().trim(); // Remove whitespace from input String
		if (line.length() == 0) // If length is empty then we do not have useful input from user
		{
			System.out.println("Move set empty");
			System.exit(0); // Explicit exit with no error
		}
		// Create turtle
		Turtle turtle = new Turtle(Turtle.DEFAULT_ROWS, Turtle.DEFAULT_COLS, Turtle.DEFAULT_OUT_CHAR);
		// Process movement from user
		for (char movement : line.toCharArray())
		{
			String message = String.format("Turtle @ %d:%d Facing %s", turtle.getRow(), turtle.getCol(),
					turtle.getOrientation());
			System.out.println(message);
			switch (movement)
			{
				case 'F':
					System.out.println("Moving forward");
					turtle.moveForward();
					break;
				case '+':
					System.out.println("Turning left");
					turtle.turnLeft();
					break;
				case '-':
					System.out.println("Turning right");
					turtle.turnRight();
					break;
				default:
					System.err.println("Invalid move instruction " + movement);
					System.exit(Turtle.Error.ERROR_INVALID_MOVE.getCode());
			}
		}
		// Print final output
		turtle.print();
		// Implicit exit with no error
	}
}
