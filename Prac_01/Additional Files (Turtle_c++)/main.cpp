#include <iostream>
#include <string>
#include "turtle.h"

using namespace std;

bool isWhiteSpace(string str)
{
	bool blnAllBlanks = true, blnContinue = true;
	unsigned int k = 0;
	while((k < str.length()) && blnContinue)
	{
		if (!isspace(str[k]))
			blnContinue = blnAllBlanks = false;
		k++;
	}
	return blnAllBlanks;
}


int main()
{
	string strLine;
	getline(cin, strLine); //Read from standard input stream

	if(isWhiteSpace(strLine) || strLine.length() == 0) //Test for empty input
	{
		cout << "Move set empty" << endl;
		return 0; //This is not an error
	}

	Turtle turtle(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_OUT_CHAR); //Instantiate turtle

	for(unsigned int k = 0; k < strLine.length(); k++) //Loop over move instructions
	{
		cout << "Turtle @" << turtle.getRow() << ":"<< turtle.getCol()
		     << " Facing " << turtle.getOrientation() << endl;	//Debugging
		switch(strLine[k]) //Test move execute appropriate turtle member function
		{
			case 'F':
				cout << "Moving forward" << endl;
				turtle.moveForward();
				break;
			case '+':
				cout << "Turning left" << endl;
				turtle.turnLeft();
				break;
			case '-':
				cout << "Turning right" << endl;
				turtle.turnRight();
				break;
			default: //Not a legal move instruction
				cerr << endl << "Invalid move instruction " << strLine[k] << endl;
				return ERROR_INVALID_MOVE;
		}
	}

	turtle.print(); //Display final grid state via delegation

	return 0;//Program executed successfully
}

