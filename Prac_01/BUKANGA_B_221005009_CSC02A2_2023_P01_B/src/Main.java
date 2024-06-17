import acsse.csc2a.Turtle; 
import acsse.csc2a.Grid;
import java.util.Scanner;

public class Main{
    public static boolean isWhiteSpace(String str) 
    {
        boolean blnAllBlanks = true;
        boolean blnContinue = true;
        int k = 0; 
        while((k < str.length()) && blnContinue)
        {
            if (!Character.isWhitespace(str.charAt(k))) //Character.isWhitespace to check for whitespace
                blnContinue = blnAllBlanks = false;
            k++;
        }
        return blnAllBlanks;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); //scanner object to read input

        String strLine = sc.nextLine(); //sc.nextLine() to read input, and store inside strLine

        if(isWhiteSpace(strLine) || strLine.length() == 0)
        {
            System.out.println("Move set empty");
            return;
        }

        Turtle turtle = new Turtle(Grid.DEFAULT_ROWS, Grid.DEFAULT_COLS, Grid.DEFAULT_OUT_CHAR); //creating an instance of turtle, and initialising it

        for(int k = 0; k < strLine.length(); k++)
        {
            System.out.println("Turtle @" + turtle.getRow() + ":" + turtle.getCol() 
                                + " Facing " + turtle.getOrientation());
            switch(strLine.charAt(k)) 	//charAt to access the character at the position k
            {
                case 'F':
                    System.out.println("Moving forward");
                    turtle.moveForward();
                    break;
                case '+':
                    System.out.println("Turning Left");
                    turtle.turnLeft();
                    break;
                case '-': 
                    System.out.println("Turning Right");
                    turtle.turnRight();
                    break;
                default:
                    System.err.println("invalid move instruction" + strLine.charAt(k)); //print error messages
                    return;
            }
        }

        turtle.print();
    }
}