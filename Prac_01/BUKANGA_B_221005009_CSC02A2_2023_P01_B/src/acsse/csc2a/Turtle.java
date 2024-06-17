package acsse.csc2a;

import acsse.csc2a.Grid;

public class Turtle {
    private int row, col;
    private Orientation orientation;
    private Grid grid;

    private enum Orientation {
        UP, DOWN, LEFT, RIGHT
    }

    final int ERROR_INVALID_ORIENTATION = -1;
    final int ERROR_INVALID_COORDS = -2;
    final int ERROR_INVALID_MOVE = -2;

    private void init(int rows, int cols) {
        System.out.println("Init Turtle");
        row = rows - 1;
        col = cols - 1;
        orientation = Orientation.RIGHT;
        this.grid = new Grid(rows, cols);
    }

    public Turtle() {
        init(Grid.DEFAULT_ROWS, Grid.DEFAULT_COLS);
    }

    public Turtle(int rows, int cols) {
        this.grid = new Grid(rows, cols);
        init(rows, cols);
    }

    public Turtle(int rows, int cols, char chOutput) {
        this.grid = new Grid(rows, cols, chOutput);
        init(rows, cols);
    }

    public final int getRow() {
        return row;
    }

    public final int getCol() {
        return col;
    }

    public final char getOrientation() {
        switch (orientation) {
            case UP:
                return 'U';
            case DOWN:
                return 'D';
            case LEFT:
                return 'L';
            case RIGHT:
                return 'R';
            default:
                return 'X';
        }
    }

    public final void print() {
        grid.print();
    }

    public void turnLeft() {
        switch (orientation) {
            case UP:
                orientation = Orientation.LEFT;
                break;
            case DOWN:
                orientation = Orientation.RIGHT;
                break;
            case LEFT:
                orientation = Orientation.DOWN;
                break;
            case RIGHT:
                orientation = Orientation.UP;
                break;
            default:
                System.err.println("Invalid orientation");
                System.exit(ERROR_INVALID_ORIENTATION);
        }
    }

    public void turnRight() {
        switch (orientation) {
            case UP:
                orientation = Orientation.RIGHT;
                break;
            case DOWN:
                orientation = Orientation.LEFT;
                break;
            case LEFT:
                orientation = Orientation.UP;
                break;
            case RIGHT:
                orientation = Orientation.DOWN;
                break;
            default:
                System.err.println("Invalid orientation ");
                System.exit(ERROR_INVALID_ORIENTATION);
        }
    }

    public void moveForward() {
        int newRow = row;
        int newCol = col;
        switch (orientation) {
            case UP:
                newRow--;
                break;
            case DOWN:
                newRow++;
                break;
            case LEFT:
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
            default:
                System.err.println("Invalid orientation " + orientation);
                System.exit(ERROR_INVALID_ORIENTATION);
        }

        if (grid.isValid(newRow, newCol)) {
            row = newRow;
            col = newCol;
            this.grid.set(row, col);
        } else {
            System.err.println("Invalid coordinates ROW:" + newRow + " COL: " + newCol);
            System.exit(ERROR_INVALID_COORDS);
        }
    }
}
