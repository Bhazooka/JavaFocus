package acsse.csc2a;

/**
 * Class for managing a simple drawing Turtle which moves along a {@link Grid}
 * 
 * @author Mr. A Maganlal
 * @version P01
 * @see Grid
 */
public class Turtle
{
	/**
	 * An enumeration representing the possible orientations of the Turtle
	 * 
	 * @author Mr. A Maganlal
	 * @version P01
	 */
	public enum Orientation
	{
		/**
		 * Facing UP, towards the top of the screen
		 */
		ORIENT_UP,
		/**
		 * Facing DOWN, towards the bottom of the screen
		 */
		ORIENT_DOWN,
		/**
		 * Facing LEFT, towards the left of the screen
		 */
		ORIENT_LEFT,
		/**
		 * Facing RIGHT, towards the left of the screen
		 */
		ORIENT_RIGHT;
	}

	/**
	 * Error enumeration for specific errors that can occur when using the Turtle
	 * 
	 * @author Mr. A Maganlal
	 * @version P01
	 */
	public enum Error
	{
		/**
		 * The orientation does not match any enumeration value
		 */
		ERROR_INVALID_ORIENTATION(-1),
		/**
		 * The coordinates of the Turtle are not valid
		 */
		ERROR_INVALID_COORDS(-2),
		/**
		 * The movement of the Turtle is invalid Should this have a different code?
		 */
		ERROR_INVALID_MOVE(-2);

		private final int code;

		private Error(int code)
		{
			this.code = code;
		}

		public int getCode()
		{
			return code;
		}
	}

	// Constant for default number of rows
	public static final int		DEFAULT_ROWS			= 15;
	// Constant for default number of columns
	public static final int		DEFAULT_COLS			= 10;
	// Constant for default output character
	public static final char	DEFAULT_OUT_CHAR	= '*';
	private int								row, col;
	private Orientation				orientation;
	private Grid							grid;

	/**
	 * No-args constructor, default number of rows and columns used. Creates an instance of Grid
	 * internally to delegate.
	 */
	public Turtle()
	{
		grid = new Grid();
		init(DEFAULT_ROWS, DEFAULT_COLS);
	}

	/**
	 * Create an instance of a Turtle with the specified internal number of rows and columns
	 * 
	 * @param rows
	 *          - The number of rows to use
	 * @param cols
	 *          - The number of columns to use
	 */
	public Turtle(int rows, int cols)
	{
		grid = new Grid(rows, cols);
		init(rows, cols);
	}

	/**
	 * Create an instance of a Turtle with the specified internal number of rows, columns and output
	 * character
	 * 
	 * @param rows
	 *          - The number of rows to use
	 * @param cols
	 *          - The number of columns to use
	 * @param chOutput
	 *          - The output character to use
	 */
	public Turtle(int rows, int cols, char chOutput)
	{
		grid = new Grid(rows, cols, chOutput);
		init(rows, cols);
	}

	/**
	 * Get the current row of the Turtle
	 * 
	 * @return The current row of the Turtle
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * Get the current column of the Turtle
	 * 
	 * @return The current column of the Turtle
	 */
	public int getCol()
	{
		return col;
	}

	/**
	 * Get the current orientation of the Turtle as a character. Could this be integrated in the
	 * enumeration?
	 * 
	 * @return The character representation of the orientation of the turtle
	 */
	public char getOrientation()
	{
		switch (orientation)
		{
			case ORIENT_UP:
				return 'U';
			case ORIENT_DOWN:
				return 'D';
			case ORIENT_LEFT:
				return 'L';
			case ORIENT_RIGHT:
				return 'R';
			default:
				return 'X';
		}
	}

	/**
	 * Delegate printing to the underlying Grid instance
	 */
	public void print()
	{
		grid.print();
	}

	/**
	 * Turn the Turtle left by looking at the current orientation
	 */
	public void turnLeft()
	{
		switch (orientation)
		{
			case ORIENT_UP:
				orientation = Orientation.ORIENT_LEFT;
				break;
			case ORIENT_DOWN:
				orientation = Orientation.ORIENT_RIGHT;
				break;
			case ORIENT_LEFT:
				orientation = Orientation.ORIENT_DOWN;
				break;
			case ORIENT_RIGHT:
				orientation = Orientation.ORIENT_UP;
				break;
			default:
				// With an enumeration, this should only happen if the orientation is null
				// Exit on null value with error
				System.err.println("Invalid orientation.");
				System.exit(Error.ERROR_INVALID_ORIENTATION.getCode());
		}
	}

	/**
	 * Turn the Turtle right by looking at the current orientation
	 */
	public void turnRight()
	{
		switch (orientation)
		{
			case ORIENT_UP:
				orientation = Orientation.ORIENT_RIGHT;
				break;
			case ORIENT_DOWN:
				orientation = Orientation.ORIENT_LEFT;
				break;
			case ORIENT_LEFT:
				orientation = Orientation.ORIENT_UP;
				break;
			case ORIENT_RIGHT:
				orientation = Orientation.ORIENT_DOWN;
				break;
			default:
				// With an enumeration, this should only happen if the orientation is null
				// Exit on null value with error
				System.err.println("Invalid orientation.");
				System.exit(Error.ERROR_INVALID_ORIENTATION.getCode());
		}
	}

	/**
	 * Move forward based on the current orientation. Row changes if we care facing
	 * {@link Orientation#ORIENT_UP} or {@link Orientation#ORIENT_DOWN} Column changes if we care
	 * facing {@link Orientation#ORIENT_LEFT} or {@link Orientation#ORIENT_RIGHT}
	 */
	public void moveForward()
	{
		int newRow = row;
		int newCol = col;
		switch (orientation)
		{
			case ORIENT_UP:
				newRow--;
				break;
			case ORIENT_DOWN:
				newRow++;
				break;
			case ORIENT_LEFT:
				newCol--;
				break;
			case ORIENT_RIGHT:
				newCol++;
				break;
			default:
				// With an enumeration, this should only happen if the orientation is null
				// Exit on null value with error
				System.err.println("Invalid orientation.");
				System.exit(Error.ERROR_INVALID_ORIENTATION.getCode());
		}
		// Check if Grid instance has a valid row and column
		if (grid.isValid(newRow, newCol))
		{
			row = newRow;
			col = newCol;
			grid.set(row, col);
		}
		else // If coordinates are not valid then exit with error
		{
			System.err.println(String.format("Invalid coordinates ROW: %d  COL: %d", newRow, newCol));
			System.exit(Error.ERROR_INVALID_COORDS.getCode());
		}
	}

	/**
	 * Initialise the internal Grid as well the Turtle's starting coordinates.
	 * By default the Turtle will be in the bottom left of the grid, facing RIGHT.
	 * @param rows - The number of rows to use 
	 * @param cols - The number of columns to use
	 */
	private void init(int rows, int cols)
	{
		System.out.println("Init Turtle");
		row = rows - 1;
		col = 0;
		orientation = Orientation.ORIENT_RIGHT;
		grid.set(row, col);
	}
}
