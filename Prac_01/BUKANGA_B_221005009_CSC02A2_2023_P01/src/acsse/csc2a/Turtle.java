package acsse.csc2a;

import acsse.csc2a.Grid;

public class Turtle {
    private int row, col;
    private int orientation;
    private Grid grid;

    final int ORIENT_UP = 0;
    final int ORIENT_DOWN = 1;
    final int ORIENT_LEFT = 2;
    final int ORIENT_RIGHT = 3;

    final int ERROR_INVALID_ORIENTATION = -1;
    final int ERROR_INVALID_COORDS = -2;
    final int ERROR_INVALID_MOVE = -2;

    private void init(int rows, int cols) {
        System.out.println("Init Turtle");
        row = rows - 1;
        col = cols - 1;
        orientation = ORIENT_RIGHT;
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

    public final void print() {
        grid.print();
    }

    public void turnLeft() {
        switch (orientation) {
            case ORIENT_UP:
                orientation = ORIENT_LEFT;
                break;
            case ORIENT_DOWN:
                orientation = ORIENT_RIGHT;
                break;
            case ORIENT_LEFT:
                orientation = ORIENT_DOWN;
                break;
            case ORIENT_RIGHT:
                orientation = ORIENT_UP;
                break;
            default:
                System.err.println("Invalid orientation");
                System.exit(ERROR_INVALID_ORIENTATION);
        }
    }

    public void turnRight() {
        switch (orientation) {
            case ORIENT_UP:
                orientation = ORIENT_RIGHT;
                break;
            case ORIENT_DOWN:
                orientation = ORIENT_LEFT;
                break;
            case ORIENT_LEFT:
                orientation = ORIENT_UP;
                break;
            case ORIENT_RIGHT:
                orientation = ORIENT_DOWN;
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
