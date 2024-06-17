//B, Bukanga 
//221005009 PRAC 02

import acsse.csc2a.*; //import all the files in acsse.csc2a
import acsse.csc2a.Message.PLANETS;

/**
 * A class containing the main method.
 * 
 * @author Baraka Bukanga
 * @version P02
 */
public class Main{

	/**
	 * Main method to test the functionality of our classes.
	 * @param args Not used.
	 */

    public static void main(String[] args)
    {

        String S_ID_Gen[] = new String[3];  //Array for 3 different ID numbers 
        String S_MSG_Gen[] = new String[3]; //Array to store 3 Different messages


    /**
	 * Generates a random number for ID and MSG
	 */
        //generate 3 random ID and Message for each ship.
        for (int x = 0; x <= 2; x++)
        {
            char[] rand_Num = Message.generateRandomNumbers();
            String S_ID = "SH" + new String(rand_Num);

            char[] rand_Msg = Message.generateRandomNumbers();
            String S_MSG = "MSG" + new String(rand_Msg);

            S_ID_Gen[x] = S_ID; //Store random ID in array 
            S_MSG_Gen[x] = S_MSG;   //Sotre random MSG in array
        }
        
        
        //Create 3 instances of class Ships (ship object)
        Ships[] ship = new Ships[3];

        
        //Print Format
        System.out.println("*********************************************************");

        for(int i = 0; i <= 2; i++){
            System.out.println("*********************************************************");
            
            System.out.println(" ______________________________________________________");
            System.out.println("|________________________SHIP " + (i+1) + "________________________|\n");
            ship[i] = new Ships("ID","Voyager", S_MSG_Gen[i], S_MSG_Gen[i],S_MSG_Gen[i]); 
            ship[i].printEverything("\t"+ S_ID_Gen[i],
                            "\tSwahili ",
                            "\t"+S_MSG_Gen[i], 
                            "It's insane how mathematics allows humans to bend and manipulate the world", 
                            (""+PLANETS.values()[i+1]).toUpperCase(),   //Call Values from the PLANET ENUM || index i + 1 to increase number to access other planets || Make it upper case
                            (""+PLANETS.values()[i+3]).toUpperCase()    
                            );
            ship[i].printMessages(S_MSG_Gen); //Print the 3 random message
            System.out.println("*********************************************************");
        }
        System.out.println("*********************************************************\n");

    

    } 
}