#include <iostream>
#include <cstdlib>

using namespace std;

int main()
{
    bool blnContinue = true;//continue entering as long as one of the correct options are selected
    char chOption = '\0';

    do{
            system ("cls");

            cout<< "Option A: Number sequence: " << endl
                << "Option B: Sum of number sequence: " << endl
                << "Option C: Conversion string into reverse: " << endl
                << "Option X: Exit program: " << endl;

            cin >> chOption;
            switch(chOption)
            {
                //Option A
                case 'A':
                case 'a':
                {
                    int intTerms = 1;
                    int int1 = 0;
                    int int2 = 1;

                    cout << "Enter number of terms: ";
                    cin >> intTerms;

                    while (cin.fail())
                    {
                        cin.clear();
                        string strRubbish;
                        cin >> strRubbish;

                        cerr << "Enter an integer value";
                        cout << "Enter a value for n: ";
                        cin >> intTerms;
                    }

                    if (intTerms <= 0)
                    {
                        cerr << "Please enter a value above 0: ";
                            break;
                    }

                    for(int n = 1; n <= intTerms ;n ++)
                    {
                        if(int1 > n)
                        {
                            int2 = int1 - n;
                            int1 = int2;
                            cout << int2 << " ";
                        }
                        else
                        {
                            int2 = int1 + n;
                            int1 = int2;
                            cout << int2 << " ";
                        }

                    }
                    cout << endl;
                    break;
                }




                //Option B
                case 'B':
                case 'b':
                {
                    int intTerms = 1;
                    int int1 = 0;
                    int int2 = 1;
                    int intSum = 0;
                    int arr[intTerms] = {};

                    cout << "Enter number of terms: ";
                    cin >> intTerms;

                    while (cin.fail())
                    {
                        cin.clear();
                        string strRubbish;
                        cin >> strRubbish;

                        cerr << "Enter an integer value";
                        cout << "Enter a value for n: ";
                        cin >> intTerms;
                    }

                    if (intTerms <= 0)
                    {
                        cerr << "Please enter a value above 0: ";
                        break;
                    }



                    for(int n = 1; n <= intTerms ;n ++)
                    {
                        if(int1 > n)
                        {
                            int2 = int1 - n;
                            int1 = int2;
                            cout << int2 << " ";
                        }
                        else
                        {
                            int2 = int1 + n;
                            int1 = int2;
                            cout << int2 << " ";
                        }

                        intSum += int2;
                    }

                    cout << endl << "The value of the sum of the sequence is :" << intSum << endl;

                   break;
                }





                //Option C
                case 'C':
                case 'c':
                {
                    string strWord = "";
                    string strNewWord = "";
                    cout << "Enter a word: ";
                    cin >> strWord;


                    while(cin.fail())
                    {
                        cin.clear();
                        string strRubbish;
                        cin >> strRubbish;

                        cerr << "Invalid characters, Enter letters. " << endl;
                        cout << "Enter a word: ";
                        cin >> strWord;
                    }

                    for (int i = strWord.size() - 1; i >= 0; --i)
                    {
                        strNewWord += strWord[i];
                    }
                    cout << strNewWord << endl;

                    break;
                }




                //Option X
                case 'X':
                case 'x':
                {
                    blnContinue = false;
                    break;
                }
                default:
                {
                    cerr << "Please select a valid Option from the list. ";
                }
            }


            cout << "Press enter to continue." ;
            cin.ignore(100, '\n');
            cin.get();

    }while (blnContinue == true);

    return 0;
}
