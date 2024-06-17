package acsse.csc2a;

/**
 * Class for handling a 2D array of output represented as booleans
 * 
 * @author Mr. A Maganlal
 * @version P01
 */
public class Grid
{
	private boolean[][]	grid;
	private int					rows, cols;
	private char				chrOutput;

	/**
	 * Default constructor which initialises the grid to {@link Turtle#DEFAULT_ROWS} rows
	 * and {@link Turtle#DEFAULT_COLS} columns.
	 */
	public Grid()
	{
		init(Turtle.DEFAULT_ROWS, Turtle.DEFAULT_COLS);
	}

	/**
	 * Create a Grid with the number of rows and columns provided
	 * 
	 * @param rows
	 *          - The number of rows
	 * @param cols
	 *          - The number of columns
	 */
	public Grid(int rows, int cols)
	{
		init(rows, cols);
	}

	/**
	 * Create a Grid with the number of rows, columns and output character provided
	 * 
	 * @param rows
	 *          - The number of rows
	 * @param cols
	 *          - The number of columns
	 * @param chrOutput
	 *          - The character to output as a true value
	 */
	public Grid(int rows, int cols, char chrOutput)
	{

		init(rows, cols);
		this.chrOutput = chrOutput;
	}

	/**
	 * Check if a row and column is valid for this Grid instance
	 * 
	 * @param row
	 *          - The row to validate
	 * @param col
	 *          - The column to validate
	 * @return true if the row, column are in a valid range, false otherwise
	 */
	protected boolean isValid(int row, int col)
	{
		if (row >= (rows)) return false;
		if (col >= (cols)) return false;
		if (row < 0) return false;
		if (col < 0) return false;
		// passed all checks, return true
		return true;
	}

	/**
	 * Change the output character
	 * 
	 * @param chrOutput
	 *          - The new output character to use
	 */
	public void setOutChar(char chrOutput)
	{
		this.chrOutput = chrOutput;
	}

	/**
	 * Modify a specific row and column entry to be true Should this be only setting true?
	 * 
	 * @param row
	 *          - The row index to set
	 * @param col
	 *          - The column index to set
	 */
	public void set(int row, int col)
	{
		grid[row][col] = true;
	}

	/**
	 * Output the Grid instance,showing the output character when the underlying 2D array is true
	 */
	public void print()
	{
		System.out.println("Char " + chrOutput);
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				if (grid[r][c])
					System.out.print(chrOutput + " ");
				else System.out.print("- ");
			}
			System.out.println();
		}
	}

	/**
	 * Initialise the 2D array to the specified number of rows and columns.
	 * All values are false by default
	 * @param rows - The number of rows to use
	 * @param cols - The number of columns to use
	 */
	private void init(int rows, int cols)
	{
		System.out.println("Init Grid");
		this.rows = rows;
		this.cols = cols;
		this.grid = new boolean[this.rows][];
		for (int r = 0; r < this.rows; r++)
		{
			this.grid[r] = new boolean[this.cols];
			for (int c = 0; c < this.cols; c++)
			{
				this.grid[r][c] = false;
			}
		}
	}
}
