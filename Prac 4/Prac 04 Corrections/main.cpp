#include <iostream>
#include <cstdlib>

using namespace std;

void Pause();
int GetNumTerms();
int NextA046901(int intPrev, int intTerm);
void ReverseString(string& strSentence);

int main()
{
    bool blnLoop = true;
    char chInput = '\0';

    do
    {
      system ("cls"); //clears the screen on windows.
      cout << "Option A: Display the A046901 series." << endl
           << "Option B: Calculate the sum of n-number of terms in the sequence." << endl
           << "Option C: Read in a sentence and reverse the characters." << endl
           << "Option X: Exit the program." << endl;
      cin >> chInput;

      switch(chInput)
      {
      case 'A':
      case 'a':
            {
                int intNum = 0;
                cin >> intNum;
                while (intNum <= 1)
                {
                    cerr << "Please enter a number greater than 1" << endl;
                    cin.ignore(100,'\n');
                    intNum = GetNumTerms();
                }
                int intPrev = 1;
                cout << "1 ";
                for (int n = 2; n <= intNum; n++)
                {
                    int intNew = NextA046901(intPrev, n);
                    cout << intNew << " ";
                    intPrev = intNew;
                }
                cout << endl;
                break;
            }

      case 'B':
      case 'b':
            {
                int intNum = 0;
                intNum = GetNumTerms();
                while (intNum <= 1)
                {
                    cerr << "Please enter a number that's greater than 1. " << endl;
                    cin.ignore(100, '\n');
                    intNum = GetNumTerms();
                }
                int intPrev = 1;
                int intSum = intSum + intPrev;
                for(int n = 2; n <= intNum; n++)
                {
                    int intNew = NextA046901(intPrev, n);
                    intSum = intSum + intNew;
                    intPrev = intNew;
                }
                cout << "The sum of " << intNum << "terms are: " << intSum;
                cout << endl;
                break;

            }

      case 'C':
      case 'c':
            {
                string strSentence;
                cout << "Please type in a sentence that will be reversed: " << endl;
                cin.ignore(100,'\n');
                getline(cin, strSentence);
                ReverseString(strSentence);

                cout << "The reverse sentence is: " << endl;
                cout << strSentence << endl;
                break;
            }

      case 'X':
      case 'x':
            {
                blnLoop = false;
                break;
            }

      default:
        {
            cerr << "Please select a valid option" << endl;
        }
      }

    Pause();

    }while(blnLoop);


    return 0;
}


/*
This function waits for the Enter button to be pressed, simulating a pause
Parameters: None
Return: None
*/
void Pause()
{
    cout << "Press enter to continue." << endl;
    cin.ignore(100, '\n');
    cin.get();
}


/*
The function provides a prompt and ensures a numeric value is returned that represents the
number of terms for the numeric sequence
Parameters: None
Return: The number of terms required for the series.
*/
int GetNumTerms()
{
    int intNum = 0;
    cout << "Enter number of terms for Sequence A046901 to be displayed (>1)";
    cin >> intNum;

    while (cin.fail())
    {
        cin.clear();
        string strJunk;
        cin >> strJunk;
        cerr << "Enter a value greater than 1: ";
        cout << "Enter number of terms for Sequence A046901 to be displayed (>1)";
        cin >> intNum;
    }

    return intNum;

}

int NextA046901(int intPrev, int intTerm)
{
    int intNewValue = 0;

    if (intPrev > intTerm)
        intNewValue = intPrev - intTerm;
    else
        intNewValue = intPrev + intTerm;


    return intNewValue;
}

void ReverseString(string& strSentence)
{
    string strNew;
    for (char c: strSentence)
    {
        strNew = c + strNew;
    }
    strSentence = strNew;
}
