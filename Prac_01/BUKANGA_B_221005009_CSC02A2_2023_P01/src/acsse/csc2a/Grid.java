package acsse.csc2a;

public class Grid {

    private int rows, cols;
    private char chrOutput;
    private boolean[][] grid; // 2d Gridarray

    private void init(int rows, int cols) {
        System.out.println("Init Grid");
        this.rows = rows;
        this.cols = cols;

        this.grid = new boolean[this.rows][];
        for (int r = 0; r < this.rows; r++) {
            this.grid[r] = new boolean[this.cols];
            for (int c = 0; c < this.cols; c++) {
                this.grid[r][c] = false;
            }
        }
    }

    public static final int DEFAULT_ROWS = 15;
    public static final int DEFAULT_COLS = 10;
    public static final char DEFAULT_OUT_CHAR = '*';

    public Grid() {
        init(DEFAULT_ROWS, DEFAULT_COLS);
    }

    public Grid(int rows, int cols) {
        init(rows, cols);
    }

    public Grid(int rows, int cols, char chrOutput) {
        init(rows, cols);
        this.chrOutput = chrOutput;
    }

    public final boolean isValid(int row, int col) {
        boolean blnValid = true;

        if (row >= rows)
            blnValid = false;
        if (col >= cols)
            blnValid = false;
        if (row < 0)
            blnValid = false;
        if (col < 0)
            blnValid = false;

        return blnValid;
    }

    public void setOutChar(char chrOutput) {
        this.chrOutput = chrOutput;
    }

    public void set(int row, int col) {
        grid[row][col] = true;
    }

    public final void print() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c])
                    System.out.print(chrOutput + " ");
				else if (r == 14 && c == 0)
					System.out.print("* ");
                else
                    System.out.print("- ");
            }
            System.out.print("\n");
        }
		
    }

}
