#include <iostream>

using namespace std;

int main()
{
    int const ERR_RANGE = -1;
    int const ERR_CONV = -2;


    int intTemp = 0;
    int intDistance = 0;
    int intTime = 0;
    double dblTime_inHours = 0.0;

    char chOption = '\0';
    cout << "ENTER A OR B: ";
    cin >> chOption;


    switch(chOption)
    {
        case 'A': //OPTION A
        {

            cout << "--OPTION A--" << '\n';


            cout << "Enter temperature from -273 and above: ";
            cin >> intTemp;

            if (cin.fail())
            {
                cerr << "Invalid conversion";
                exit (ERR_CONV);
            }

            if (intTemp < -273)
            {
                cerr << "Invalid Temperature value. Please enter a value from -273 and above!" ;
                exit (ERR_RANGE);
                //Error
            }

            else if (intTemp >= -273 && intTemp < 0)
            {
                cout << "Stay indoors! You may freeze." << endl;
            }

            else if(intTemp >= 0 && intTemp < 12)
            {
                cout << "Nice and cold. Wear a jacket." << endl;
            }

            else if(intTemp >= 12 && intTemp < 20 )
            {
                cout << "Cool and Comfortable." << endl;
            }

            else
            {
                cout << "Getting warmer. Wear sunscreen." << endl;
            }
            break;
        }



        case 'B': //OPTION B
        {
            cout << "--OPTION B--" << '\n';


            cout << "Enter the distance(km) travelled: ";
            cin >> intDistance;
            if (intDistance < 0)
            {
                cout << "INVALID Distance. Distance cannot be negative" << endl;
                exit (ERR_RANGE);
            }

            cout << "Enter the Time(mins) it took to travel that distance: ";
            cin >> intTime;
            cout << '\n';
            if (intTime <= 0)
            {
                cout << "INVALID time. Enter time value above 0" << endl;
                exit (ERR_RANGE);
            }


            dblTime_inHours = intTime / 60; //conversion from minutes to hours
            double dblSpeed = 0.0;
            int intSpeedometer = 0;


            dblSpeed = intDistance/ dblTime_inHours;
            cout << dblSpeed << "km/h" << endl;

            if (dblSpeed <= 60 )
            {
                intSpeedometer = 1;
            }
            else if (dblSpeed > 60)
            {
                intSpeedometer = 2;
            }


            //switch case
            switch(intSpeedometer)
            {
            case 1:
                {
                    cout << "The speed is valid for normal roads." << endl;
                    break;
                }

            case 2:
                {
                    cout << "Too fast." << endl;
                    break;
                }

            }
            break;

        }
    }

    return 0;
}
