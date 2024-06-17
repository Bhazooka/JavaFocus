#ifndef TURTLE_H
#define TURTLE_H

typedef bool** GridArray; //Type alias for 2D bool array

const int DEFAULT_ROWS = 15; //Default number of rows in grid
const int DEFAULT_COLS = 10; //Default number of columns in grid
const char DEFAULT_OUT_CHAR = '*'; //Default output character grid

/*
The following constants represent the possible orientations of the turtle.
On your own: Investigate the enum keyword in C++
*/
const int ORIENT_UP = 0;
const int ORIENT_DOWN = 1;
const int ORIENT_LEFT = 2;
const int ORIENT_RIGHT = 3;

/*
The following constants represent the primary unusual termination codes
related to moving the turtle
*/
const int ERROR_INVALID_ORIENTATION = -1;
const int ERROR_INVALID_COORDS = -2;
const int ERROR_INVALID_MOVE = -2;

class Grid //Grid class
{
public:
	Grid();//no-args constructor
	Grid(int rows, int cols);//constructor
	Grid(int rows, int cols, char chOutput); //constructor

	bool isValid(int row, int col) const;//Accessed by Turtle

	void setOutChar(char chrOutput); //mutator

	void set(int row, int col);//Toggle a specific cell (operation)
	void print() const; //Display grid to standard output (operation)

	~Grid(); //destructor
private:
	void init(int rows, int cols);
	GridArray grid; //Array (attributes / state)
	int rows, cols; //Dimensions of array (attributes / state)
	char chrOutput; //For visualizing the 2D boolean array (attributes / state)
};

class Turtle //Turtle class
{
public:
	Turtle();//no-args constructor
	Turtle(int rows, int cols); //constructor
	Turtle(int rows, int cols, char chOutput); //constructor

	int getRow() const; //accessor
	int getCol() const; //accessor
	char getOrientation() const; //accessor

	void print() const; //display function

	void turnLeft(); // -
	void turnRight(); // +
	void moveForward();  // F

	~Turtle(); //destructor
private:
	void init(int rows, int cols);
	int row, col; //Location of turtle (attributes / state)
	int orientation; //Direction the turtle is facing in (attributes / state)
	Grid* grid; //Containment
};
#endif
