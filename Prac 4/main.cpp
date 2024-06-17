#include <iostream>
#include <cstdlib> //Library is used because the system function is part of cstdlib

using namespace std;

void OptionA();
void OptionB();
void OptionC();
void Pause();
int Sequence(int intNum);


int main()
{
    bool blnLoop = true; //Variable used to manage the main loop of the solution
    char chInput = '\0'; //Stores the menu option

 //Do-While loop is used to handle the main loop
    do
    {
        system("cls");

    cout << "Option A: Display A046901 sequence." << endl
         << "Option B: Calculate the sum of n-number of terms in the A046901 series." << endl
         << "Option C: Read in a sentence and reverse the characters." << endl
         << "Option X: Exit the program" << endl;
         cin >> chInput;

         //Switch case for user choice
         switch(chInput)
         {
         case 'a':
         case 'A':
         {
         OptionA(); //Calling Option A function
         break;
         }

         case 'b':
         case 'B':
         {
         OptionB(); //Calling Option B function
         break;
         }

         case 'c':
         case 'C':
         {
         OptionC(); // Calling Option C function
         break;
         }

         case 'x':
         case 'X':
         {
            blnLoop = false; //Change the loo to false to exit loop

         break;
         }


         default:
         //An incorrect option was selected
            cerr << "Incorrect option please select only A,B,C or X" << endl;

         }
         Pause();

    }while(blnLoop);

    return 0;

}


//Functions

void OptionA()
{
             int intNum = 0;
             cout << "Number of terms for A046901 to be displayed (>1):";
             cin >> intNum;

             //Confirm no conversion error occured
             //Repeat until a proper number has been entered.
             while(cin.fail())
             {
                 cin.clear(); //Clear the fail flag
                 string strJunk;
                 cin >> strJunk; //Get rid of any characters still on the input stream
                 cerr << "Please type a valied number greater than 1" << endl;
                 cout << "Number of terms for A046901 to be displayed (>1):";
                 cin >> intNum;
             }

             //Confirm the number enterd is greater than 1, if not break out and present
            //main menu again
             if(intNum <=1)
             {
                cerr << "The number of terms should be greater than 1." << endl;

             }

             //Output the first term
             int intPrev = 1; //Stores the value of the previous term
            //a(n-1)
             cout << "1 ";
             //Calculate the rest of the terms. Start with the second term in the series.
             for(int n=2;n<=intNum;n++)
             {
                int intNew = 0;


     if(intPrev > n)
        intNew = intPrev - n;
     else
        intNew = intPrev + n;

     intPrev = intNew;
     cout << intNew << " ";


            }
            cout << endl;
}

void OptionB()
{
         int intNum = 0;
         cout << "Number of terms for A046901 to be added (>1):";
         cin >> intNum;

         while(cin.fail()) //For errors
         {
             cin.clear();
             string strJunk;
             cin >> strJunk;
             cerr << "Please type a valied number greater than 1" << endl;
             cout << "Number of terms for A046901 to be added (>1):";
             cin >> intNum;
         }

             if(intNum <=1)
             {
                 cerr << "The number of terms should be greater than 1." << endl;

             }

         int intPrev = 1;
         int intSum = intPrev; //Used to store the sum of the series.
        //Store the first term into the sum
         for(int n=2;n<=intNum;n++)
         {
             int intNew = 0;

     if(intPrev > n)
        intNew = intPrev - n;
     else
        intNew = intPrev + n;

     intPrev = intNew;
     intSum = intSum + intPrev;

         }

         cout << "The sum of terms are: " << intSum;
         cout << endl;
}

void OptionC()
{

     string strSentence;
     cout << "Please type in a sentence that will be reversed:" << endl;
     cin.ignore(100,'\n');

     getline(cin, strSentence);

     string strNew;
     for(char c: strSentence)

     {
        strNew = c + strNew;

     }

     //Output the answer
     cout << "The reverse sentence is:" << endl;
     cout << strNew << endl;

}

void Pause() //Pause function
{
     cout << "Press Enter key to continue";
     cin.ignore(100,'\n');
     cin.get();
}

int Sequence(int intNum) //Sequence function
{
    int intPrev = 1;
    int n = 1;
    int intNew = 0;
    int intSum = intPrev;

     if(intPrev > n)
        intNew = intPrev - n;
     else
        intNew = intPrev + n;

     intPrev = intNew;
     cout << intNew << " ";
     intSum = intSum + intPrev;

    return intPrev;
}

