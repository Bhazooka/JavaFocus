#include <iostream>
#include <cstdlib>

#include "turtle.h"

using namespace std;

Turtle::Turtle()
{
	grid = new Grid();
	init(DEFAULT_ROWS, DEFAULT_COLS);
}

Turtle::Turtle(int rows, int cols)
{
	grid = new Grid(rows, cols);
	init(rows, cols);
}

Turtle::Turtle(int rows, int cols, char chOutput)
{
	grid = new Grid(rows, cols, chOutput);
	init(rows, cols);
}

void Turtle::init(int rows, int cols)
{
	cout << "Init Turtle" << endl;
	row = rows - 1;
	col = 0;
	orientation = ORIENT_RIGHT;
	grid->set(row, col);
}

Turtle::~Turtle()
{
	cout << "Destroy Turtle" << endl;
	delete grid;
}

void Turtle::turnLeft()
{
	switch(orientation)
	{
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
			cerr << "Invalid orientation " << orientation << endl;
			exit(ERROR_INVALID_ORIENTATION);
	}
}

void Turtle::turnRight()
{
	switch(orientation)
	{
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
			cerr << "Invalid orientation " << orientation << endl;
			exit(ERROR_INVALID_ORIENTATION);
	}
}

void Turtle::moveForward()
{
	int newRow(row), newCol(col);
	switch(orientation)
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
			cerr << "Invalid orientation " << orientation << endl;
			exit(ERROR_INVALID_ORIENTATION);
	}

	if(grid->isValid(newRow, newCol))
	{
		row = newRow;
		col = newCol;
		grid->set(row, col);
	} else
	{
		cerr << "Invalid coordinates ROW:" << newRow << " COL: " << newCol << endl;
		exit(ERROR_INVALID_COORDS);
	}
}

int Turtle::getRow() const
{
	return row;
}

int Turtle::getCol() const
{
	return col;
}

char Turtle::getOrientation() const
{
	switch(orientation)
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

void Turtle::print() const
{
	grid->print();
}

Grid::Grid(int rows, int cols, char chrOutput)
{
	init(rows, cols);
	this->chrOutput = chrOutput;
}

Grid::Grid(int rows, int cols)
{
	init(rows, cols);
}

Grid::Grid()
{
	init(DEFAULT_ROWS, DEFAULT_COLS);
}

void Grid::init(int rows, int cols)
{
	cout << "Init Grid" << endl;
	this->rows = rows;
	this->cols = cols;

	this->grid = new bool*[this->rows];
	for(int r = 0; r < this->rows; r++)
	{
		this->grid[r] = new bool[this->cols];
		for(int c = 0; c < this->cols; c++)
		{
			this->grid[r][c] = false;
		}
	}
}

bool Grid::isValid(int row, int col) const
{
	bool blnValid = true;

	if(row >= (rows)) blnValid = false;

	if(col >= (cols)) blnValid = false;

	if(row < 0) blnValid = false;

	if(col < 0) blnValid = false;

	return blnValid;
}

void Grid::set(int row, int col)
{
	grid[row][col] = true;
}

void Grid::print() const
{
	for(int r = 0; r < rows; r++)
	{
		for(int c = 0; c < cols; c++)
		{
			if(grid[r][c])
				cout << chrOutput << " ";
			else
				cout << "- ";
		}
		cout << endl;
	}
}


void Grid::setOutChar(char chrOutput)
{
	this->chrOutput = chrOutput;
}

Grid::~Grid()
{
	cout << "Destroy Grid" << endl;
	delete []  grid;
}
